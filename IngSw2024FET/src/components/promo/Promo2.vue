<script setup lang="ts">

import Pagamento from "@/components/Pagamento.vue";
import Pagamento_promo from "@/components/Pagamento_promo.vue";
import { ref, onMounted, computed } from 'vue';
import { useRouter } from 'vue-router';
import axios from "axios";

const router = useRouter();
const data = ref<any[]>([]);
const selectedValue = ref<string>('');

onMounted(async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/getEventiOrganizzatore');
    data.value = response.data;
    // Inizializza selectedValues con lo stesso numero di elementi di data
    console.log('Data:', data.value);
  } catch (error) {
    console.error('Error fetching data:', error);
  }
});
const goToPromotion = async (promoPage: string) => {
  try {
    console.log('Selected value:', selectedValue.value);

    const response = await axios.post('http://localhost:8080/api/getInfoPromo', {
      nomeEvento: selectedValue.value, // Assicurati che sia una stringa o una lista di stringhe
      nomePromo: "2"
    });

    console.log(response.data);
    localStorage.setItem('infoPromo', JSON.stringify(response.data));
    localStorage.setItem('promo', "2");
    router.push({ path: promoPage });
  } catch (error) {
    console.error('Error sending data:', error);
  }
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

      <select v-model="selectedValue" class="eventi">
        <option value="">Seleziona Evento</option>
        <option v-for="item in data" :key="item.id" :value="item">
          {{ item }}
        </option>
      </select>

      <button class="center-button" @click="goToPromotion('Pagamento_promo')">AUMENTA LA VISIBILITÀ DEL TUO EVENTO</button>
    </aside>
  </main>
  <footer>
    <p>ALT + F6 - altf6@events.com - +39 3840957702 - www.events.it</p>
  </footer>
  </body>
</template>

<style src = "@/assets/main.css"></style>