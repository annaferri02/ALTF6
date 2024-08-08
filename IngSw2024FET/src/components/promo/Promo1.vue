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
      nomePromo: "1"
    });

    console.log(response.data);
    localStorage.setItem('infoPromo', JSON.stringify(response.data));
    localStorage.setItem('promo', "1");
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
        <div class="homepage">
          <a class="hover-enlarge" href="homepage.html">Home</a>
        </div>
        <div class="areapersonale">
          <a class="hover-enlarge" href="areapersonale.html">Area Personale</a>
        </div>
        <div class="logout">
          <a class="hover-enlarge" href="logout.html">Logout</a>
        </div>
      </div>
    </header>
  <main>
    <article>
      <h1 class="centrato">Vuoi che il tuo evento non passi inosservato?</h1>
      <h3 class="centrato" style="font-weight: normal">Sfrutta la nostra promozione 'Rimetti in Rilievo' e assicurati che il tuo evento sia la prima cosa che gli utenti vedono quando consultano il feed degli eventi.</h3> <br>
      <h4 class="centrato">Come funziona:</h4>
        <h4 class="centrato" style="font-weight: inherit"> Il tuo evento verrà posizionato in cima al feed, garantendo la massima visibilità.</h4>
        <h4 class="centrato" style="font-weight: inherit">Pagherai una piccola quota al giorno per godere di questo vantaggio.</h4>
        <h4 class="centrato" style="font-weight: inherit">Monitora da vicino l'impatto della promozione grazie ai nostri strumenti di analisi direttamente dalla tua area personale.</h4>
    </article>
    <aside class="centrato">
      <h4>Dettagli Promozione</h4>
      <p><strong>Costo giornaliero:</strong> 0.80€</p>
      <p><strong>Durata:</strong> fino all'inzio dell'evento</p>
      <p><strong>Posizionamento:</strong> Prime 3 posizioni</p>
      <select style="border: lightgrey 1px solid; background-color: #fffdf3; margin-right: 13%" v-model="selectedValue" class="eventi">
        <option value="">Seleziona Evento</option>
        <option v-for="item in data" :key="item.id" :value="item">
          {{ item }}
        </option>
      </select>
      <br> <br>
      <button style="margin-top: 20px;" class="center-button" @click="goToPromotion('Pagamento_promo')">AUMENTA LA VISIBILITÀ DEL TUO EVENTO</button>
    </aside>
  </main>
    <footer>
      <hr class="separator-line">
      <h5 class="nome"> &copy; EventiDivertenti</h5>
      <div class="center-section">
        <div class="termini">
          <a class="hover-enlarge" href="termini.html">Terms & Conditions</a>
        </div>
        <div class="help">
          <a class="hover-enlarge" href="help.html">HELP</a>
        </div>
        <div class="contattaci">
          <a class="hover-enlarge" href="contatti.html">Contattaci</a>
        </div>
        <div class="privacy">
          <a class="hover-enlarge" href="privacy.html">Privacy</a>
        </div>
      </div>
    </footer>
  </body>
</template>

<style src="@/assets/main.css"></style>