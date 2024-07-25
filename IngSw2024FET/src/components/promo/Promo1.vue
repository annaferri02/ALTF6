<script setup lang="ts">

import Pagamento from "@/components/Pagamento.vue";
import Pagamento_promo from "@/components/Pagamento_promo.vue";
</script>

<template>
  <head>
    <title>Rimetti in rilievo</title>
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
    <article>
      <h2>Vuoi che il tuo evento non passi inosservato?</h2>
      <p>Sfrutta la nostra promozione 'Rimetti in Rilievo' e assicurati che il tuo evento sia la prima cosa che gli utenti vedono quando consultano il feed degli eventi.Come funziona:</p>
      <ul>
        <li><strong>Posizionamento premium</strong>: Il tuo evento verrà posizionato in cima al feed, garantendo la massima visibilità.</li>
        <li><strong>Tariffa giornaliera competitiva</strong>: Pagherai una piccola quota al giorno per godere di questo vantaggio.</li>
        <li><strong>Risultati misurabili</strong>: Monitora da vicino l'impatto della promozione grazie ai nostri strumenti di analisi.</li>
        <li><strong>Flessibilità</strong>: Scegli la durata della promozione in base alle tue esigenze.</li>
      </ul>
    </article>
    <aside>
      <h3>Dettagli Promozione</h3>
      <p>Costo giornaliero: 0.80€</p>
      <p>Durata minima: 7 giorni</p>
      <p>Durata: fino all'inzio dell'evento</p>
      <p>Posizionamento: Prima pagina -> prime 3 posizioni</p>
      <button @click="Pagamento_promo.vue">Promuovi ora</button>
    </aside>
  </main>
  <footer>
    <p>ALT + F6 - altf6@events.com - +39 3840957702 - www.events.it</p>
  </footer>
  </body>
</template>

<style src = "../assets/main.css"></style>