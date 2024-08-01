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

const riepilogoList = ref(0);

/* Inseriamo nella variabile data il risultato della chiamata al backend */
axios.get("/api/callREST2").then(response => {
  console.log(JSON.stringify(response.data));
  riepilogoList.value = response.data;
});

</script>

<template>
  <head>
    <title>Pagina Pagamento</title>
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

    <div class="indirizzo-fatturazione">
      <h2>Indirizzo di fatturazione</h2>
      <label for="nomeAzienda">Nome azienda</label>
      <input type="text" id="nomeAzienda" name="nomeAzienda" required>
      <label for="indirizzo">Indirizzo</label>
      <input type="text" id="indirizzo" name="indirizzo" required>
      <label for="citta">Citt&agrave;</label>
      <input type="text" id="citta" name="citta" required>
      <label for="provincia">Provincia</label>
      <input type="text" id="provincia" name="provincia" required>
      <label for="cap">CAP</label>
      <input type="text" id="cap" name="cap" required>
      <label for="stato">Stato</label>
      <input type="text" id="stato" name="stato" required>
      <label for="telefono">Numero di telefono</label>
      <input type="tel" id="telefono" name="telefono" required>
      <label for="email">E-mail</label>
      <input type="email" id="email" name="email" required>
    </div>
    <div class="dettagli-pagamento">
      <h2>Dettagli Pagamento</h2>
      <label for="nome">Nome</label>
      <input type="text" id="nome" name="nome" required>
      <label for="cognome">Cognome</label>
      <input type="text" id="cognome" name="cognome" required>
      <label for="numeroCarta">Numero carta</label>
      <input type="text" id="numeroCarta" name="numeroCarta" pattern="\d{16}" maxlength="16" required>
      <label for="scadenza">Data di scadenza</label>
      <input type="month" id="scadenza" name="scadenza" required>
      <label for="cvv">CVV</label>
      <input type="text" id="cvv" name="cvv" required maxlength="3" pattern="\d{3}" >
    </div>
    <form class="riepilogo-ordine">
      <h2>Riepilogo ordine</h2>
      <!--Inserire i dati del riepilogo ordine ricevuti dal backend-->
      <template v-for="item in riepilogoList">
        <h2>Nome evento: {{ item.nome_evento}}</h2>
        <p>Tipologia promozione: {{ item.tipologia_promozione}}</p>
        <h2>Totale: {{ item.totale }}</h2>
      </template>
      <input type="submit" value="PAGA ORA" class="centrale-button">
    </form>
  </main>

  <footer>
    <p>ALT + F6 - altf6@events.com - +39 3840957702 - www.events.it</p>
  </footer>

  </body>
</template>

<style src = "../assets/main.css"></style>

