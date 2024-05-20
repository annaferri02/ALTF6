<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue';

/* Inseriamo in una variabile reattiva chiamata data */
const informazioni_biglietti = ref(0);

/* Inseriamo nella variabile data il risultato della chiamata al backend */
axios.get("/api/callREST").then(response => {
  console.log(JSON.stringify(response.data));
  informazioni_biglietti.value = response.data;
})


class Biglietto {
  "dataa": string;
  "ora": string;
  "luogo": string;
  "quantita_1": number;
  "tipo_biglietto_1": string;
  "prezzo_tipo1": number;
  "descrizione_1": string;
  "quantita_2": number;
  "tipo_biglietto_2": string;
  "prezzo_tipo2": number;
  "descrizione_2": string;
  "totale": number;
}

</script>

<template>
  <head>
    <title>Pagina Biglietto</title>
    <link rel="stylesheet" href="/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/main.css">
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
    <div class="riepilogo">
      <h1>Nome concerto</h1>
      <h2>Biglietti disponibili</h2>
      <div class="selezioe_biglietti">
        <p>{{ informazioni_biglietti.tipo_biglietto_1 }} <span style="float: right; padding-right: 20px;"> {{ informazioni_biglietti.prezzo_tipo1}}</span></p>
        <p>{{ informazioni_biglietti.descrizione_1 }}</p>
        <br>
        <p>{{ informazioni_biglietti.tipo_biglietto_2 }} <span style="float: right; padding-right: 20px;"> {{ informazioni_biglietti.prezzo_tipo2}}</span></p>
        <p>{{ informazioni_biglietti.descrizione_2 }}</p>
      </div>
      <h2>Riepilogo</h2>
      <p>{{informazioni_biglietti.quantita_1}} {{informazioni_biglietti.tipo_biglietto_1}} <span style="float: right; padding-right: 20px;"> {{informazioni_biglietti.prezzo_tipo1}}</span></p>
      <p>{{informazioni_biglietti.quantita_2}} {{informazioni_biglietti.tipo_biglietto_2}} <span style="float: right; padding-right: 20px;"> {{informazioni_biglietti.prezzo_tipo2}}</span></p>
      <button action="">VAI AL PAGAMENTO</button>
    </div>
    <div class="immagine_concerto">
      <div class="dati_concerto">
        <h1>{{ informazioni_biglietti.dataa }} <br> {{ informazioni_biglietti.ora }} <br> {{ informazioni_biglietti.luogo }}</h1>
      </div>
    </div>
  </main>
  <footer>
    <p>ALT + F6 - altf6@events.com - +39 3840957702 - www.events.it</p>
  </footer>
  </body>
</template>

<style src = "/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/base.css"></style>
<style src = "/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/main.css"></style>