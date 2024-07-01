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
    <div class="promotion-section">
      <h2>Vuoi aumentare la visibilità del tuo evento?</h2>
      <p>Dai un'occhiata ai pacchetti disponibili "Promuovi ora", grazie ai quali renderai il tuo evento più visibile aumentando il numero di partecipazioni!</p>
      <h2>Scegli la soluzione "Promuovi Ora" che preferisci</h2>
      <div id= "app" class="container">
        <PromotionCard
            title="Rimetti in rilievo"
            description="Riposiziona l'evento in prima pagina, come appena pubblicato."
          Videosrc= "/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/primo.mov"
            price="A PARTIRE DA 0,80€"
        />
        <PromotionCard
          title="Metti in esposizione"
          description="Etichetta l'evento come in 'Esposizione', facendolo apparire tra i primi risultati della ricerca."
            Videosrc= "/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/secondo.mov"
          price="A PARTIRE DA 0,55€"
        />
        <PromotionCard
          title="Pungente"
          description="Etichetta l'evento come 'Pungente' e attirerà più partecipazioni."
              Videosrc= "/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/terzo.mov"
          price="A PARTIRE DA 0,65€"
        />
        <PromotionCard
          title="Promo 2 x 1"
          description="Combina le opzioni in rilievo e in esposizione ad un prezzo vantaggioso."
            Videosrc= "/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/quarto.mov"
          price="A PARTIRE DA 4,50€"
          note="PROMOZIONE CONSIGLIATA Risparmi il 25%"
        />
      </div>
  <button>AUMENTA LA VISIBILITÀ DEL TUO EVENTO</button>
  <section class="benefits">
    <h3>Perché scegliere le soluzioni 'Promuovi ora' ?</h3>
    <ul>
      <li><strong>Arriva a più partecipanti possibili!</strong> Il tuo evento verrà messo in risalto e i contatti si moltiplicheranno.</li>
      <li><strong>Vendi al prezzo che scegli tu!</strong> Valorizza il tuo evento e vendi i biglietti al prezzo che ritieni più opportuno.</li>
      <li><strong>Raggiungi più persone nel minor tempo possibile!</strong> Più utenti vedranno il tuo evento e minor tempo ci impiegherà ad andare sold-out.</li>
    </ul>
  </section>
    <h4>Pagamenti sicuri assicurati!</h4>
    <p>Scegli il metodo di pagamento che preferisci e rendi il tuo evento imperdibile.</p>
    <div class="payment-methods">
      <img src="/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/visa.png" alt="Visa" width="100" height="100"/>
      <img src="/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/maestro.avif" alt="Maestro" width="100" height="100"/>
      <img src="/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/paypal.png" alt="PayPal" width="100" height="100"/>
      <img src="/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/mastercard.png" alt="MasterCard" width="100" height="100"/>
      <img src="/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/postepay.png" alt="PostePay" width="100" height="100"/>
    </div>
  <button class="center-button" @click="Pagamento.vue"> AUMENTA LA VISIBILITÀ DEL TUO EVENTO </button>
</div>
</main>
<footer>
<p>ALT + F6 - altf6@events.com - +39 3840957702 - www.events.it</p>
</footer>
</body>
</template>

<style src = "/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/base.css"></style>
<style src = "/Users/annaferri/Desktop/ALTF6/IngSw2024FET/src/assets/main.css"></style>