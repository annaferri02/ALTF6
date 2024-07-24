<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue';
import Pagamento from "@/components/Pagamento.vue";

/* Inseriamo in una variabile reattiva chiamata data */
const informazioni_biglietti = ref(0);

/* Inseriamo nella variabile data il risultato della chiamata al backend */
axios.get("/api/callREST").then(response => {
  console.log(JSON.stringify(response.data));
  informazioni_biglietti.value = response.data;
})

    new Vue({
      el: '#app',
      data: {
        loggedUser: null,
        applicationMessage: null,
        menuActiveLink: 'Home',
        biglietti: [],
        evento: {},
        tipo: 'Indoor',
        numeroPosti: 0,
        selectedSeats: []
      },
      computed: {
        bigliettiString() {
          return this.biglietti.join(',');
        },
        categoryOptions() {
          if (this.tipo === 'Indoor') {
            return [
              { text: 'Parterre', value: 1 },
              { text: 'Parterre VIP', value: 2 }
            ];
          } else if (this.tipo === 'Outdoor') {
            return [
              { text: 'Pit', value: 1 },
              { text: 'Pit GOLD', value: 2 }
            ];
          }
          return [];
        },
        allSelectedSeats() {
          return this.selectedSeats.join(',');
        }
      },
      methods: {
        logout() {
          // Logica per il logout
        },
        incrementPosti() {
          if (this.numeroPosti < 6) {
            this.numeroPosti++;
          }
        },
        decrementPosti() {
          if (this.numeroPosti > 0) {
            this.numeroPosti--;
          }
        },
        updateSelectedSeats(seatId) {
          const index = this.selectedSeats.indexOf(seatId);
          if (index === -1) {
            this.selectedSeats.push(seatId);
          } else {
            this.selectedSeats.splice(index, 1);
          }
        },
        proceedToPurchase() {
          const totalSeats = this.numeroPosti + this.selectedSeats.length;
          if (totalSeats === 0) {
            alert('Devi selezionare almeno un posto o indicare il numero di posti desiderati.');
            return;
          }
          if (totalSeats > 6) {
            alert('Puoi selezionare al massimo 6 posti.');
            return;
          }
          // Logica per procedere all'acquisto
        }
      },
      mounted() {
        // Logica per popolare la mappa dei posti basata su tipo di luogo
      }
    });
</script>


<template>
  <head>
    <title>Pagina Biglietto</title>
  </head>
  <body>
  <header>
    <div class="logo">
      <img src="/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/Logo.svg" alt="Logo" width="200" height="100">
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
    <h1 class="centrato">Seleziona il biglietto che preferisci!</h1>
    <br><br>
    <p class="centrato" v-if="Luogo.tipoluogo === 'Indoor'">Seleziona i posti numerati che desideri acquistare direttamente dalla piantina e i posti in parterre dal menù sottostante</p>
    <p class="centrato" v-if="Luogo.tipoluogo === 'Outdoor'">Seleziona i posti in Pit e Pit Gold mostrati nella piantina, dal menù sottostante</p>
    <section v-if="Luogo.tipoluogo === 'Indoor'" class="layout-esterno">
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
    <section v-if="tipo === 'Outdoor'" class="layout-esterno1">
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
    <br>
    <h3 style="margin-top: 10%; margin-left: 26%">Seleziona la categoria di posti che desideri acquistare:</h3>
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
    <button @click="proceedToPurchase" style="margin-top: 20px">Procedi con l'acquisto</button>
  </main>

  <footer>
    <p>ALT + F6 - altf6@events.com - +39 3840957702 - www.events.it</p>
  </footer>
  </body>
</template>
