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
  localStorage.setItem('promo', "2");
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
      <h2>Vuoi che il tuo evento sia facilmente rintracciabile dagli utenti che cercano attivamente eventi simili al tuo?</h2>
      <p>Con la promozione "Metti in Esposizione", il tuo evento apparirà tra i primi risultati delle ricerche pertinenti, aumentando significativamente la sua visibilità e attrattività. Come funziona:</p>
      <ul>
        <li><strong>Ottimizzazione SEO</strong>: Il tuo evento verrà ottimizzato con le parole chiave più rilevanti, migliorando il suo posizionamento nei risultati di ricerca interni alla piattaforma.</li>
        <li><strong>Visibilità prolungata</strong>: Garantisce una visibilità prolungata nel tempo, aumentando le possibilità di raggiungere un pubblico più ampio.</li>
        <li><strong>Targeting personalizzato</strong>: Potrai scegliere le parole chiave più adatte al tuo evento, assicurandoti di raggiungere un pubblico altamente interessato.</li>
        <li><strong>Risultati misurabili</strong>: Monitora il numero di clic e le visualizzazioni del tuo evento per valutare l'efficacia della promozione.</li>
      </ul>
    </article>
    <aside>
      <h3>Dettagli Promozione</h3>
      <p>Costo giornaliero: 0.50€</p>
      <p>Durata minima: 14 giorni</p>
      <p>Durata: fino all'inzio dell'evento</p>
      <p>Posizionamento: Risultati ricerche -> prime 3 posizioni</p>

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