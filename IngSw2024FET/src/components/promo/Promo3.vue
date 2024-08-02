<script setup lang="ts">

import Pagamento from "@/components/Pagamento.vue";
import Pagamento_promo from "@/components/Pagamento_promo.vue";
import { useRouter } from 'vue-router';
import {onMounted, ref} from "vue";
import axios from "axios";

const router = useRouter();
const data = ref([]);

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/getEventiOrganizzatore');
    data.value = response.data;
  } catch (error) {
    console.error('Error fetching data:', error);
  }

});
const goToPromotion = (promoPage: string) => {
  localStorage.setItem('promo', "3");
  router.push({ path: promoPage });
};
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

      <template v-for="(item, index) in data" :key="index">
        <select class="eventi">
          <option>Evento</option>
          <option>{{ item }} </option>
        </select>
      </template>
      <button class="center-button" @click="goToPromotion('Pagamento_promo')">AUMENTA LA VISIBILITÀ DEL TUO EVENTO</button>
    </aside>
  </main>
  <footer>
    <p>ALT + F6 - altf6@events.com - +39 3840957702 - www.events.it</p>
  </footer>
  </body>
</template>

<style src = "@/assets/main.css"></style>