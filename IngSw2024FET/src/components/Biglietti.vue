<script setup lang="ts">
import { ref, onMounted } from 'vue'; // Importa funzioni Vue per reattività e montaggio del componente
import axios from 'axios'; // Importa axios per effettuare richieste HTTP
import { useRouter } from 'vue-router'; // Importa useRouter per gestire la navigazione

const router = useRouter(); // Ottiene l'istanza del router per la navigazione

// Definisci le variabili reattive per gestire lo stato del componente
const count = ref(0); // Numero di biglietti aggiuntivi da acquistare
const biglietti = ref([]); // Lista dei biglietti (posti occupati)
const prezzi = ref([]); // Lista dei prezzi per le diverse categorie di posti
const evento = ref({}); // Dettagli dell'evento
const luogo = ref({}); // Dettagli del luogo dell'evento
const opzioniMenu = ref([]); // Opzioni del menu per la selezione dei posti
const tribune = ref([]); // Tipi di tribune disponibili
const postiOccupati = ref([]); // Posti occupati (ID dei biglietti)
const selezioneTribuna = ref(''); // Categoria di posti selezionata
const mostraPiantina = ref(false); // Flag per mostrare o meno la piantina
const postiSelezionati = ref([]); // Lista dei posti selezionati dall'utente

// Funzione per recuperare i dati dal server
const fetchData = async () => {
  try {
    // Effettua una richiesta GET per ottenere le informazioni sui biglietti
    const response = await axios.get('http://localhost:8080/ticket/getInfoBiglietti');
    const data = response.data;

    // Aggiorna gli stati con i dati ricevuti
    biglietti.value = data.biglietti;  // Supponiamo che biglietti contenga gli ID dei posti occupati
    evento.value = data.evento;
    luogo.value = data.luogo;
    postiOccupati.value = data.biglietti;  // Usa biglietti come posti occupati
    prezzi.value = data.prezzi;

    console.log('Dati ricevuti:', data.luogo);

    // Estrai le opzioni del menù in base alla tipologia del luogo
    if (luogo.value.Tipologia === 'Indoor') {
      opzioniMenu.value = ['Parterre', 'Parterre VIP'];
      mostraPiantina.value = true;
      // Popola tribune
      tribune.value = ['tribuna', 'tribuna-dx', 'tribuna-sx'];
    } else if (luogo.value.Tipologia === 'Outdoor') {
      opzioniMenu.value = ['Pit', 'Pit GOLD'];
      mostraPiantina.value = false;
    }
  } catch (error) {
    // Gestisce eventuali errori durante il recupero dei dati
    console.error('Error fetching data:', error);
  }
};

// Esegui fetchData quando il componente viene montato
onMounted(fetchData);

// Configura il numero di righe e posti per fila per le tribune
const rows = 6;
const seatsPerRowStandard = 9; // Numero di posti per fila nelle tribune laterali
const seatsPerRowTribuna = Math.floor(seatsPerRowStandard * 2.5); // Numero di posti per fila nella tribuna centrale (1.5x)

// Funzione per creare la disposizione dei posti in base alla sezione
const createSeats = (sectionId) => {
  const seats = [];
  let startId;
  let seatsPerRow;
  const postiOccupatiNumerici = postiOccupati.value.map(id => Number(id));

  switch (sectionId) {
    case 'tribuna-sx':
      startId = 1;
      seatsPerRow = seatsPerRowStandard;
      break;
    case 'tribuna-dx':
      startId = 101;
      seatsPerRow = seatsPerRowStandard;
      break;
    case 'tribuna':
      startId = 201;
      seatsPerRow = seatsPerRowTribuna;
      break;
    default:
      startId = 1; // Caso predefinito
      seatsPerRow = seatsPerRowStandard; // Usa la configurazione predefinita
  }

  // Crea la disposizione dei posti
  for (let i = 0; i < rows; i++) {
    const row = [];
    for (let j = 0; j < seatsPerRow; j++) {
      const seatId = startId++;
      row.push({
        id: seatId,
        occupied: postiOccupatiNumerici.includes(seatId), // Verifica se il posto è occupato
        selected: postiSelezionati.value.includes(seatId) // Verifica se il posto è selezionato
      });
    }
    seats.push(row);
  }
  return seats;
}

// Funzioni per gestire il numero di biglietti aggiuntivi
function increment() {
  if (count.value < 6) {
    count.value++;
  }
}

function decrement() {
  if (count.value > 0) {
    count.value--;
  }
}

// Funzione per gestire la selezione dei posti
function toggleSeatSelection(seat) {
  if (seat.occupied) {
    alert("Il posto è già occupato!"); // Mostra un messaggio se il posto è già occupato
    return;
  }
  seat.selected = !seat.selected; // Alterna la selezione del posto
  if (seat.selected) {
    postiSelezionati.value.push(seat.id); // Aggiunge il posto selezionato alla lista
  } else {
    const index = postiSelezionati.value.indexOf(seat.id);
    if (index > -1) {
      postiSelezionati.value.splice(index, 1); // Rimuove il posto dalla lista se deselezionato
    }
  }
  console.log('Posto selezionato:', seat.id, 'Selezionato:', seat.selected);
  console.log('Posti Selezionati:', postiSelezionati.value);
  console.log('Posti Occupati:', postiOccupati.value);
}

// Funzione per ottenere lo stile CSS della tribuna
const getTribunaStyle = (tribuna) => {
  switch (tribuna) {
    case 'tribuna-sx':
      return { 'grid-column': '1', 'grid-row': '2 / 4', transform: 'rotate(-90deg)'};
    case 'tribuna-dx':
      return { 'grid-column': '3', "grid-row": "2 / 4", transform: 'rotate(90deg)', 'padding':'10px'};
    case 'tribuna':
      return { 'grid-column': '1/4', 'grid-row': '4', 'margin': '10px'};
    default:
      return {};
  }
};

// Funzione per gestire la navigazione al pagamento
const vaiAlPagamento = async () => {
  try {
    const numSelezionati = postiSelezionati.value.length + count.value;

    if (numSelezionati === 0) {
      alert('Seleziona almeno un posto!'); // Mostra un messaggio se nessun posto è selezionato
      return;
    } else if (numSelezionati > 6) {
      alert('Seleziona al massimo 6 posti!'); // Mostra un messaggio se il numero di posti supera il massimo consentito
      return;
    } else if (count.value > 0 && selezioneTribuna.value === '') {
      alert('Seleziona una categoria di posti!'); // Mostra un messaggio se una categoria di posti non è selezionata
      return;
    }

    // Invia i dati al server per la procedura di pagamento
    const response = await axios.post('http://localhost:8080/ticket/gotoPagamento', {
      tribuna: selezioneTribuna.value,
      postiSelezionati: postiSelezionati.value,
      count: count.value,
    });
    console.log(response.data);
    localStorage.setItem('paymentData', JSON.stringify(response.data)); // Salva i dati di pagamento nel localStorage
    localStorage.setItem('prezzi', JSON.stringify(prezzi.value)); // Salva i prezzi nel localStorage
    router.push({ name: 'Pagamento' }); // Naviga alla pagina di pagamento

  } catch (error) {
    // Gestisce eventuali errori nella richiesta di pagamento
    console.error('Error sending data:', error);
  }
};

</script>

<template>
  <head>
    <title>Pagina Biglietto</title>
  </head>
  <body>
  <header>
    <div class="logo">
      <img src="@/assets/Logo.svg" alt="Logo" width="200" height="100">
    </div>
    <div class="right-section">
      <div class="homepage">
        <a class="hover-enlarge" href="homepage.html">Home</a>
      </div>
      <div class="areapersonale">
        <a class="hover-enlarge" href="areapersonale.html">Area Personale</a>
      </div>
      <div class="logout">
        <a class="hover-enlarge" href="logout.html">Logout</a>
      </div>
    </div>
  </header>
  <main>
    <h1 class="centrato">Seleziona il biglietto che preferisci!</h1>
    <div class="main-posti">
      <div v-if="mostraPiantina">
        <h3 class="centrato">Seleziona i posti numerati che desideri acquistare direttamente dalla piantina e i posti in parterre dal men&ugrave; sottostante</h3>

        <div class="tariffe">
          <option>Tariffe</option>
          <option>Parterre {{ prezzi[0] }} &euro;</option>
          <option>Parterre VIP {{ prezzi[1] }} &euro;</option>
          <option>Tribuna frontale {{ prezzi[2] }} &euro;</option>
          <option>Tribuna laterale destra {{ prezzi[3] }} &euro;</option>
          <option>Tribuna laterale sinistra {{ prezzi[4] }} &euro;</option>
        </div>

        <section class="piantina">
          <table id="palco">
            <tr>
              <td style="text-align: center; font-size: 40px">PALCO</td>
            </tr>
          </table>
          <table id="parterre" >
            <tr>
              <td style="text-align: center; font-size: 40px">PARTERRE</td>
            </tr>
          </table>
          <table id="parterreVIP" >
            <tr>
              <td style="text-align: center; font-size: 40px">PARTERRE VIP</td>
            </tr>
          </table>
          <table v-for="tribuna in tribune" :key="tribuna" class="tribuna" :style="getTribunaStyle(tribuna)">
            <tr>
              <td :colspan="seatsPerRowStandard" style="text-align: center; font-size: 40px"></td>
            </tr>
            <tr v-for="row in createSeats(tribuna)" :key="row[0].id">
              <td v-for="seat in row" :key="seat.id">
                <button
                    :id="seat.id"
                    :class="{
                            'posto-occupato': seat.occupied,
                            'posto-selezionato': seat.selected,
                            'posto-disponibile': !seat.occupied && !seat.selected,
                            'button-margin': true
                          }"

                    :disabled="seat.occupied"
                    @click="toggleSeatSelection(seat)"
                    :aria-label="`Posto ${seat.id} ${seat.occupied ? 'occupato' : seat.selected ? 'selezionato' : 'disponibile'}`"
                >
                  {{ seat.id }}
                </button>
              </td>
            </tr>
          </table>

        </section>
      </div>
      <div v-if="!mostraPiantina">
        <h3 class="centrato">Seleziona i posti in Pit e Pit Gold mostrati nella piantina, dal men&ugrave; sottostante</h3>
        <div class="tariffe">
          <option>Tariffe</option>
          <option>PIT {{ prezzi[0] }} &euro;</option>
          <option>PIT GOLD {{ prezzi[1] }} &euro;</option>
        </div>
        <section class="piantina1">
          <table id="palco1">
            <tr>
              <td style="text-align: center; font-size: 40px">PALCO</td>
            </tr>
          </table>
          <table id="pit" >
            <tr>
              <td style="text-align: center; font-size: 40px">PIT</td>
            </tr>
          </table>
          <table id="pitgold" >
            <tr>
              <td style="text-align: center; font-size: 40px">PIT GOLD</td>
            </tr>
          </table>
        </section>
      </div>
      <br><br>
      <div class="bottoni">
      <h3>Seleziona la categoria di posti che desideri acquistare:</h3>
      <select style=" margin-left : 0px; border: lightgrey 1px solid;" v-model="selezioneTribuna">
        <option value="" >Categoria</option>
        <option v-for="opzione in opzioniMenu" :key="opzione" :value="opzione">
          {{ opzione }}
        </option>
      </select>
      <div>
        <h3 >Seleziona il numero di posti che desideri acquistare:</h3>
        <label for="numeroPosti">Numero di posti </label>
        <button class="button" @click="decrement">{{ "-" }}</button>
        <button class="numero">{{ count }}</button>
        <button class="button" @click="increment">{{ "+" }}</button>
        <br><br>
      </div>
      </div>
      <button @click="vaiAlPagamento" class="center-button">Procedi con l'acquisto</button>
    </div>
  </main>

  <footer>
    <hr class="separator-line">
    <h5 class="nome"> &copy; EventiDivertenti</h5>
    <div class="center-section">
      <div class="termini">
        <a class="hover-enlarge" href="termini.html">Terms & Conditions</a>
      </div>
      <div class="help">
        <a class="hover-enlarge" href="help.html">HELP</a>
      </div>
      <div class="contattaci">
        <a class="hover-enlarge" href="contatti.html">Contattaci</a>
      </div>
      <div class="privacy">
        <a class="hover-enlarge" href="privacy.html">Privacy</a>
      </div>
    </div>
  </footer>
  </body>
</template>

<style src = "@/assets/main.css"></style>
<style>

.button-margin {
  margin: 2px;
}
</style>