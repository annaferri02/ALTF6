<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue';

/* Inseriamo in una variabile reattiva per ogni valore inserito dall'utente */
const nome_cognome = ref('');
const email = ref('');
const residenza = ref('');
const numero_carta = ref('');
const scadenza = ref('');
const cvv = ref('');

//devo salvare i dati come stringa json nel backend:
// Modo per salvare i dati nel backend, in questo caso è un metodo POST.
//api/saveData è il percorso del backend, userData è l'oggetto che contiene i dati da salvare
const saveData = async () => {
  const userData = {
    nome_cognome: nome_cognome.value,
    email: email.value,
    residenza: residenza.value,
    numero_carta: numero_carta.value,
    scadenza: scadenza.value,
    cvv: cvv.value
  };
  try {
    const response = await axios.post('http://localhost:3000/api/saveData', userData);
    console.log(response.data);
  } catch (error) {
    console.error(error);
  }
};

class riepilogoOrdine{
  "nome_concerto": string;
  "tipo_biglietto_1": string;
  "tipo_biglietto_2": string;
  "quantita_1": string;
  "quantita_2": string;
  "totale": string;
}
//devo trasformare la classe riepilogoOrdine in un oggetto
let riepilogo = new riepilogoOrdine();
riepilogo.nome_concerto = "Nome del concerto";
riepilogo.tipo_biglietto_1 = "Tipo del biglietto 1";
riepilogo.tipo_biglietto_2 = "Tipo del biglietto 2";
riepilogo.quantita_1 = "Quantità 1";
riepilogo.quantita_2 = "Quantità 2";
riepilogo.totale = "Totale";

/* Inseriamo nella variabile data il risultato della chiamata al backend */
axios.get("http://localhost:3000/api/bello").then(response => {
  console.log(JSON.stringify(response.data))
  riepilogo.nome_concerto = response.data.nome_concerto
  riepilogo.quantita_1 = response.data.quantita_1
  riepilogo.quantita_2 = response.data.quantita_2
  riepilogo.tipo_biglietto_1 = response.data.tipo_biglietto_1
  riepilogo.tipo_biglietto_2 = response.data.tipo_biglietto_2
  riepilogo.totale = response.data.totale
})
    .catch(error => {
      console.error('Errore durante la richiesta:', error);
    });

</script>

<template>
  <head>
    <title>Pagina Pagamento</title>
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
        <h1>Riepilogo ordine</h1>
        <!--Inserire i dati del riepilogo ordine ricevuti dal backend-->
        <h2>Nome concerto: {{ riepilogo.nome_concerto }}</h2>
        <p>Tipo biglietto 1: {{ riepilogo.tipo_biglietto_1 }}, quantità: {{ riepilogo.quantita_1 }}</p>
        <p>Tipo biglietto 2: {{ riepilogo.tipo_biglietto_2 }}, quantità: {{ riepilogo.quantita_2 }}</p>
        <h2>Totale: {{ riepilogo.totale }}</h2>
        <a href="scelta_biglietti.html">Modifica ordine</a>
      </div>
      <div class="form_pagamento">
        <form action="/pagamento" method="post">
          <h1>Indirizzo di fatturazione</h1>
          <label for="nome_cognome">Nome e Cognome</label><br>
          <input :value="nome_cognome" ><br>
          <label for="email">Indirizzo email</label><br>
          <input :value="email" placeholder=" "><br>
          <label for="indirizzo">Indirizzo di residenza</label><br>
          <input :value="residenza" placeholder=" "><br>
          <h1>Dati di pagamento</h1>
          <label for="nome_cogmome_card">Nome e Cognome</label><br>
          <input :value="nome_cognome" placeholder=" "><br>
          <label for="numero_carta">Numero carta</label><br>
          <input :value="numero_carta" placeholder=" "><br>
          <label for="scadenza">Scadenza</label><br>
          <input :value="scadenza" placeholder=" "><br>
          <label for="cvv">CVV</label><br>
          <input :value="cvv" placeholder=" "><br><br>
          <input type="submit" value="Paga ora" class="button">
        </form>
      </div>
    </main>
    <footer>
      <p>ALT + F6 - altf6@events.com - +39 3840957702 - www.events.it</p>
    </footer>
</body>
</template>

<style src = "/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/base.css"></style>
<style src = "/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/main.css"></style>
