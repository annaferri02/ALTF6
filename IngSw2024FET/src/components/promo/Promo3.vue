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
      <button class="center-button">Menù</button>
    </div>
  </header>
  <main>
    <article>
      <h2>Vuoi dare al tuo evento un tocco di pepe e far parlare di sé?</h2>
      <p>Con la promozione "Pungente", il tuo evento verrà etichettato come un must-have per chi cerca esperienze uniche e indimenticabili. Come funziona:</p>
      <ul>
        <li><strong>Etichetta dedicata</strong>: Il tuo evento verrà contrassegnato con un'etichetta "Pungente" ben visibile, attirando l'attenzione degli utenti più avventurosi e alla ricerca di novità.</li>
        <li><strong>Promozione sui social</strong>: Il tuo evento verrà promosso sui nostri canali social con contenuti accattivanti e hashtag dedicati, raggiungendo un pubblico più ampio.</li>
        <li><strong>Visibilità prioritaria</strong>: Il tuo evento sarà posizionato in una sezione dedicata agli eventi "Pungenti", aumentando la sua visibilità per gli utenti interessati a questo tipo di esperienze.</li>
        <li><strong>Risultati misurabili</strong>: Monitora da vicino l'impatto della promozione grazie ai nostri strumenti di analisi e scopri quante persone sono state attratte dall'etichetta "Pungente".</li>
      </ul>
    </article>
    <aside>
      <h3>Dettagli Promozione</h3>
      <p>Costo giornaliero: 0.65€</p>
      <p>Durata minima: 7 giorni</p>
      <p>Durata: fino all'inzio dell'evento</p>
      <p>Posizionamento: Sezione dedicata agli eventi "Pungenti" e promozione sui social</p>
      <button class="center-button" @click="Pagamento_promo.vue">Promuovi ora</button>
    </aside>
  </main>
  <footer>
    <p>ALT + F6 - altf6@events.com - +39 3840957702 - www.events.it</p>
  </footer>
  </body>
</template>

<style src = "@/assets/main.css"></style>