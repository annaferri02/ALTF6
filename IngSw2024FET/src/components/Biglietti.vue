<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from 'axios';

// Definisci le variabili reattive
const count = ref(0);
const biglietti = ref([]);
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

    // Estrai le opzioni del menù
    if (luogo.value.tipologia === 'Indoor') {
      opzioniMenu.value = ['Parterre', 'Parterre VIP'];
      mostraPiantina.value = true;
      // Popola tribune
      tribune.value = ['tribuna', 'tribuna-dx', 'tribuna-sx'];
    } else if (luogo.value.tipologia === 'Outdoor') {
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
    const response = await axios.post('http://localhost:8080/ticket/gotoPagamento', {
      tribuna: selezioneTribuna.value,
      postiSelezionati: postiSelezionati.value,
      count: count.value
    });
    console.log(response.data);
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
      <div class="login">
        <a href="login.html">Login</a>
      </div>
      <div class="register">
        <a href="register.html">Registrazione</a>
      </div>
      <button class="center-button">Menù</button>
    </div>
  </header>
  <main>
    <h1 class="centrato">Seleziona il biglietto che preferisci!</h1>
    <div>
      <div v-if="mostraPiantina">
        <h3 class="centrato">Seleziona i posti numerati che desideri acquistare direttamente dalla piantina e i posti in parterre dal men&ugrave; sottostante</h3>
        <select class="tariffe">
          <option>Tariffe</option>
          <option>Parterre 50 &euro;</option>
          <option>Parterre VIP 100 &euro;</option>
          <option>Tribuna frontale 70 &euro;</option>
          <option>Tribuna laterale destra 90 &euro;</option>
          <option>Tribuna laterale sinistra 90 &euro;</option>
        </select>
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
        <select class="tariffe">
          <option>Tariffe</option>
          <option>Pit 50 &euro;</option>
          <option>Pit GOLD 100 &euro;</option>
        </select>
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
      <h3 style=" margin-left: 13%; margin-top: 40px">Seleziona la categoria di posti che desideri acquistare:</h3>
      <select v-model="selezioneTribuna">
        <option value="" disabled>Categoria</option>
        <option v-for="opzione in opzioniMenu" :key="opzione" :value="opzione">
          {{ opzione }}
        </option>
      </select>
      <br>
      <br>
      <div>
        <h3 style="margin-left: 12%">Seleziona il numero di posti che desideri acquistare:</h3>
        <label style="margin-left: 12%" for="numeroPosti">Numero di posti </label>
        <button class="button" @click="decrement">{{ "-" }}</button>
        <button class="numero">{{ count }}</button>
        <button class="button" @click="increment">{{ "+" }}</button>
        <br><br>
      </div>
      <button style="margin-left: 40%; margin-top: 20px" @click="vaiAlPagamento" class="center-button">Procedi con l'acquisto</button>
    </div>
  </main>

  <footer>
    <p>ALT + F6 - altf6@events.com - +39 3840957702 - www.events.it</p>
  </footer>
  </body>
</template>

<style src = "../assets/main.css"></style>
<style>

.button-margin {
  margin: 2px;
}
</style>