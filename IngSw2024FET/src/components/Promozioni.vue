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
    <title>Promuovi il tuo evento</title>
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
        <button class="buttonM">Menù</button>
      </div>
    </header>
    <main>
      <div class="promotion_section">
        <h2>Vuoi aumentare la visibilità del tuo evento?</h2>
        <p>Dai un'occhiata ai pacchetti disponibili "Promuovi ora", grazie ai quali <br> renderai il tuo evento più visibile aumentando il numero di partecipazioni!</p>
        <h2>Scegli la soluzione "Promuovi Ora" che preferisci</h2>
        <div class="container">
          <a href="/promo/promo1.vue">
            <div class="promo_banner">
              <h3>Rimetti in rilievo</h3>
              <img src="@/assets/primo.gif">
              <p>Riposiziona l’evento in prima pagina, come appena pubblicato.</p>
              <p>A PARTIRE DA 0,80€</p>
            </div>
          </a>
          <a href="/promo/promo2.vue">
            <div class="promo_banner">
              <h3>Metti in esposizione</h3>
              <img src="@/assets/secondo.gif">
              <p>Etichetta l’evento come in “Esposizione”, facendolo apparire tra i primi risultati della ricerca.</p>
              <p>A PARTIRE DA 0,50€</p>
            </div>
          </a>
          <a href="/promo/promo3.vue">
            <div class="promo_banner">
              <h3>Pungente</h3>
              <img src="@/assets/terzo.gif">
              <p>Etichetta l’evento come “Pungente” e attira più partecipazioni.</p>
              <p>A PARTIRE DA 0,65€</p>
            </div>
          </a>
          <a href="/promo/promo4.vue">
            <div class="promo_banner">
              <h3>Promo 2 x 1</h3>
              <img src="@/assets/quarto.gif">
              <p>Combina le opzioni in rilievo e in esposizione ad un prezzo vantaggioso.</p>
              <p>A PARTIRE DA 1,20€</p>
            </div>
          </a>
        </div>
        <section class="benefits">
          <h3>Perché scegliere le soluzioni 'Promuovi ora'?</h3>
          <ul>
            <li><strong>Arriva a più partecipanti possibili!</strong> Il tuo evento verrà messo in risalto e i contatti si moltiplicheranno.</li>
            <li><strong>Vendi al prezzo che scegli tu!</strong> Valorizza il tuo evento e vendi i biglietti al prezzo che ritieni più opportuno.</li>
            <li><strong>Raggiungi più persone nel minor tempo possibile!</strong> Più utenti vedranno il tuo evento e minor tempo ci impiegherà ad andare sold-out.</li>
          </ul>
        </section>
        <button class="center-button" @click="Pagamento.vue">AUMENTA LA VISIBILITÀ DEL TUO EVENTO</button>
        <h4>Pagamenti sicuri assicurati!</h4>
        <p>Scegli il metodo di pagamento che preferisci e rendi il tuo evento imperdibile.</p>
        <div class="payment-methods">
          <img src="@/assets/visa.png" alt="Visa"/>
          <img src="@/assets/maestro.avif" alt="Maestro"/>
          <img src="@/assets/paypal.png" alt="PayPal"/>
          <img src="@/assets/mastercard.png" alt="MasterCard"/>
          <img src="@/assets/postepay.png" alt="PostePay"/>
        </div>
        <button class="center-button" @click="Pagamento.vue"> AUMENTA LA VISIBILITÀ DEL TUO EVENTO </button>
      </div>
    </main>
    <footer>
      <p>ALT + F6 - altf6@events.com - +39 3840957702 - www.events.it</p>
    </footer>
  </body>
</template>

<style src = "@/assets/main.css"></style>