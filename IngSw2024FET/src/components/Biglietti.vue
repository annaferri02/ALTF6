<script setup lang="ts">

/*
const biglietti = ref([]);
const evento = ref({
  id_Evento: '',
  nome: '',
  data: '',
  descrizione: '',
  id_Organizzatore: '',
  flag_ticket: false,
  idLuogo: ''
});
const luogo = ref({
  idLuogo: '',
  via: '',
  numCivico: '',
  cap: '',
  provincia: '',
  stato: '',
  citta: '',
  capienza: 0,
  tipologia: '',
  nome: ''
});

const infos = ref([]);

onMounted(() => {
  axios.get("/ticket/getInfoBiglietti")
      .then(response => {

        /*console.log("Dati ricevuti:", response.data); // Verifica i dati ricevuti
        const data = response.data;

        if (Array.isArray(data.biglietti) && data.evento && data.luogo) {
          biglietti.value = data.biglietti;
          evento.value = {
            id_Evento: data.evento.id_Evento,
            nome: data.evento.nome,
            data: data.evento.data,
            descrizione: data.evento.descrizione,
            id_Organizzatore: data.evento.id_Organizzatore,
            flag_ticket: data.evento.flag_ticket,
            idLuogo: data.evento.idLuogo
          };
          luogo.value = {
            idLuogo: data.luogo.idLuogo,
            via: data.luogo.via,
            numCivico: data.luogo.numCivico,
            cap: data.luogo.cap,
            provincia: data.luogo.provincia,
            stato: data.luogo.stato,
            citta: data.luogo.citta,
            capienza: data.luogo.capienza,
            tipologia: data.luogo.tipologia,
            nome: data.luogo.nome
          };
        } else {
          console.error("Errore: la struttura dei dati ricevuti è inaspettata.");
        }

        infos.value = response.data;
      })
      .catch(error => {
        console.error("Errore durante il recupero dei dati:", error);
      });
});


export default {
  data() {
    return {
      biglietti: [],
      evento: {},
      luogo: {}
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      // Supponiamo che l'endpoint API restituisca il JSON fornito nella domanda
      axios.get("/ticket/getInfoBiglietti")
          .then(response => {
            // Salva i dati nelle variabili del componente
            this.biglietti = response.data.biglietti;
            this.evento = response.data.evento;
            this.luogo = response.data.luogo;
          })
          .catch(error => {
            console.error("C'è stato un errore nel recuperare i dati:", error);
          });
    }
  }
};*/
import { ref, onMounted } from 'vue';
import axios from 'axios';
// Definisci le variabili reattive
const biglietti = ref([]);
const evento = ref({});
const luogo = ref({});

// Funzione per recuperare i dati
const fetchData = async () => {
  try {
    const response = await axios.get('http://localhost:8080/ticket/getInfoBiglietti');
    console.log('Response data:', response.data);
    biglietti.value = response.data.biglietti;
    evento.value = response.data.evento;
    luogo.value = response.data.luogo;
    console.log('Dati aggiornati:', { biglietti: biglietti.value, evento: evento.value, luogo: luogo.value });
  } catch (error) {
    console.error("C'è stato un errore nel recuperare i dati:", error);
  }
};

// Recupera i dati quando il componente viene montato
onMounted(fetchData);
</script>



<template>
  <head>
    <title>Pagina Biglietto</title>
  </head>
  <body>
  <header>
    <div class="logo">
      <!-- <img src="/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/Logo.svg" alt="Logo" width="200" height="100">-->
    </div>
    <div class="right-section">
      <div class="login">
        <a href="login.html">Login</a>
      </div>
      <div class="register">
        <a href="register.html">Registrazione</a>
      </div>
      <button class="buttonM">Menù</button>
    </div>
  </header>
  <main>

    <div>
      <template v-if="Object.keys(evento).length">
        <h1>{{ evento.nome }}</h1>
        <p>{{ evento.descrizione }}</p>
        <p>Data: {{ evento.data || 'Data non disponibile' }}</p>
        <p>Luogo: {{ luogo.nome }}, {{ luogo.via }} {{ luogo.numCivico }}, {{ luogo.citta }} ({{ luogo.provincia }})</p>
        <p>Tipologia: {{ luogo.tipologia }}</p>
        <p>Biglietti disponibili: {{ biglietti.join(', ') }}</p>
      </template>
      <template v-else>
        <p>Caricamento dati...</p>
      </template>
    </div>
    <!--<h1 class="centrato">Seleziona il biglietto che preferisci!</h1>
    <br><br>
    <p class="centrato" v-if="luogo.tipologia === 'Indoor'">Seleziona i posti numerati che desideri acquistare direttamente dalla piantina e i posti in parterre dal menù sottostante</p>
    <p class="centrato" v-if="luogo.tipologia === 'Outdoor'">Seleziona i posti in Pit e Pit Gold mostrati nella piantina, dal menù sottostante</p>
    <section v-if="luogo.tipologia === 'Indoor'" class="layout-esterno">
      <article class="tariffe">
        <h3>Tariffe:</h3>
        <h4>Parterre 50 &euro;</h4>
        <h4>Parterre VIP 100 &euro;</h4>
        <h4>Tribuna frontale 70 &euro;</h4>
        <h4>Tribuna laterale destra 90 &euro;</h4>
        <h4>Tribuna laterale sinistra 90 &euro;</h4>
      </article>
      <section class="container">

        <table id="palco">
          <tr>
            <td style="text-align: center; font-size: 40px">PALCO</td>
          </tr>
        </table>
        <table id="parterre">
          <tr>
            <td style="text-align: center; font-size: 40px">PARTERRE</td>
          </tr>
        </table>
        <table id="parterreVIP">
          <tr>
            <td style="text-align: center; font-size: 40px">PARTERRE VIP</td>
          </tr>
        </table>
        <table id="tribuna"></table>
        <table id="tribuna-sx"></table>
        <table id="tribuna-dx"></table>
      </section>
    </section>
    <section v-if="luogo.tipologia === 'Outdoor'" class="layout-esterno1">
      <article class="tariffe">
        <h3>Tariffe:</h3>
        <h4>Pit 50 &euro;</h4>
        <h4>Pit Gold 100 &euro;</h4>
      </article>
      <section class="container1">
        <table id="palco1">
          <tr>
            <td style="text-align: center; font-size: 40px">PALCO</td>
          </tr>
        </table>
        <table id="pit">
          <tr>
            <td style="text-align: center; font-size: 40px">PIT</td>
          </tr>
        </table>
        <table id="pitgold">
          <tr>
            <td style="text-align: center; font-size: 40px">PIT GOLD</td>
          </tr>
        </table>
      </section>
    </section>
    <br>-->
   <!-- <h3 style="margin-top: 10%; margin-left: 26%">Seleziona la categoria di posti che desideri acquistare:</h3>
    <select id="categoria" v-model="categoria" style="width: fit-content; margin-left: 26%">
      <option v-for="option in categoryOptions" :value="option.value">{{ option.text }}</option>
    </select>
    <br>
    <h3 style="margin-left: 26%">Seleziona il numero di posti che desideri acquistare:</h3>
    <label style="margin-left: 26%" for="numeroPosti">Numero di posti </label>
    <button class="bottone-scelta" @click="decrementPosti" style="border: none; font-size: 20px; font-weight: bolder">-</button>
    <input type="number" id="numeroPosti" v-model="numeroPosti" style="margin-bottom:30px; font-size: 20px; border: none" min="0" max="6" readonly>
    <button class="bottone-scelta" @click="incrementPosti" style="border: none; font-size: 20px; font-weight: bolder">+</button>
    <input type="hidden" name="controllerAction" value="PagamentoManagement.gotoPagamentoBiglietto">
    <input type="hidden" id="allSelectedSeats" :value="allSelectedSeats">
    <input type="hidden" name="idEvento" :value="evento.idEvento">
    <button @click="proceedToPurchase" style="margin-top: 20px">Procedi con l'acquisto</button>-->
  </main>

  <footer>
    <p>ALT + F6 - altf6@events.com - +39 3840957702 - www.events.it</p>
  </footer>
  </body>
</template>
