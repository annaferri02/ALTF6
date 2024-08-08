<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from 'axios';

import { useRouter } from 'vue-router';

const router = useRouter();

// Definisci le variabili reattive
const count = ref(0);
const biglietti = ref([]);
const prezzi = ref([]);
const evento = ref({});
const luogo = ref({});
const opzioniMenu = ref([]);
const tribune = ref([]);
const postiOccupati = ref([]);
const selezioneTribuna = ref('');
const mostraPiantina = ref(false);
const postiSelezionati = ref([]);

// Funzione per recuperare i dati
const fetchData = async () => {
  try {
    const response = await axios.get('http://localhost:8080/ticket/getInfoBiglietti');
    const data = response.data;

    // Aggiorna gli stati
    biglietti.value = data.biglietti;  // Supponiamo che biglietti contenga gli ID dei posti occupati
    evento.value = data.evento;
    luogo.value = data.luogo;
    postiOccupati.value = data.biglietti;  // Usa biglietti come posti occupati
    prezzi.value = data.prezzi;


    console.log('Dati ricevuti:', data.luogo);

    // Estrai le opzioni del menù
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
    console.error('Error fetching data:', error);
  }
};

onMounted(fetchData);

const rows = 6;
const seatsPerRowStandard = 9; // Numero di posti per fila nelle tribune laterali
const seatsPerRowTribuna = Math.floor(seatsPerRowStandard * 2.5); // Numero di posti per fila nella tribuna centrale (1.5x)

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

  for (let i = 0; i < rows; i++) {
    const row = [];
    for (let j = 0; j < seatsPerRow; j++) {
      const seatId = startId++;
      row.push({
        id: seatId,
        occupied: postiOccupatiNumerici.includes(seatId) , // Supponiamo che biglietti contenga gli ID dei posti occupati
        selected: postiSelezionati.value.includes(seatId)});
    }
    seats.push(row);
  }
  return seats;
}

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

// Funzione che seleziona i posti e se sono invece già occupati li segna in un altro colore
function toggleSeatSelection(seat) {
  if (seat.occupied) {
    alert("Il posto è già occupato!");
    return;
  }
  seat.selected = !seat.selected;
  if (seat.selected) {
    postiSelezionati.value.push(seat.id);
  } else {
    const index = postiSelezionati.value.indexOf(seat.id);
    if (index > -1) {
      postiSelezionati.value.splice(index, 1);
    }
  }
  console.log('Posto selezionato:', seat.id, 'Selezionato:', seat.selected);
  console.log('Posti Selezionati:', postiSelezionati.value);
  console.log('Posti Occupati:', postiOccupati.value);
}



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

const vaiAlPagamento = async () => {
  try {

    const numSelezionati = postiSelezionati.value.length + count.value;

    if (numSelezionati === 0) {
      alert('Seleziona almeno un posto!');
      return;
    } else if (numSelezionati > 6) {
      alert('Seleziona al massimo 6 posti!');
      return;
    } else if (count.value > 0 && selezioneTribuna.value === '') {
      alert('Seleziona una categoria di posti!');
      return;
    }

    const response = await axios.post('http://localhost:8080/ticket/gotoPagamento', {
      tribuna: selezioneTribuna.value,
      postiSelezionati: postiSelezionati.value,
      count: count.value,
    });
    console.log(response.data);
    localStorage.setItem('paymentData', JSON.stringify(response.data));
    localStorage.setItem('prezzi', JSON.stringify(prezzi.value));
    router.push({ name: 'Pagamento' });

  } catch (error) {
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
          <option>Pit 50 &euro;</option>
          <option>Pit GOLD 100 &euro;</option>
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