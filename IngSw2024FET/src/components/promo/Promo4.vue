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
  localStorage.setItem('promo', "4");
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
      <h2>Vuoi massimizzare la visibilità del tuo evento e attirare un pubblico ancora più ampio?</h2>
      <p>Con la nostra promozione "Promo 2 x 1", potrai combinare il potere della visibilità immediata e del posizionamento premium con la portata a lungo termine dell'ottimizzazione SEO. Come funziona:</p>
      <ul>
        <li><strong>Attivazione simultanea</strong>: Una volta attivata la promozione, il tuo evento verrà immediatamente posizionato in cima al feed degli eventi (Rimetti in Rilievo) e verrà avviato il processo di ottimizzazione SEO per migliorare il suo posizionamento nei risultati di ricerca (Metti in Esposizione).</li>
        <li><strong>Durata personalizzabile</strong>: Puoi scegliere la durata totale della promozione, che si applicherà sia alla visibilità in cima al feed che all'ottimizzazione SEO.</li>
        <li><strong>Risultati misurabili</strong>: Avrai accesso a un pannello di controllo per monitorare le performance della tua promozione, come il numero di visualizzazioni, i clic e il posizionamento nei risultati di ricerca.</li>
      </ul>
    </article>
    <aside>
      <h3>Dettagli Promozione</h3>
      <p>Costo giornaliero: 1.20€</p>
      <p>Durata minima: 7 giorni</p>
      <p>Durata: fino all'inzio dell'evento</p>
      <p>Posizionamento:</p>
      <ul>
          <li><strong>Rimetti in Rilievo</strong>: Prima posizione nel feed degli eventi</li>
          <li><strong>Metti in Esposizione</strong>: Primi risultati delle ricerche pertinenti</li>
      </ul>

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