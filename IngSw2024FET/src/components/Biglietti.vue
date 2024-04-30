<script setup lang="ts">
import axios from 'axios';
import { ref } from 'vue';

/* Inseriamo in una variabile reattiva chiamata data */
const data = ref(0);

/* Inseriamo nella variabile data il risultato della chiamata al backend */
axios.get("/api/callREST").then(response => {
console.log(JSON.stringify(response.data))
data.value = response.data
})

class MyValues{
  "biglietto": string;
  "numero_biglietti": number;
  "prezzo": number;
  "totale": number;
}

</script>

<template>
  <div>
    <h1> ciao </h1>
    <table>
      <tr v-for="items in data" >
        <td>{{ items.biglietto }}</td>
        <td>{{ items.numero_biglietti }}</td>
        <td>{{ items.prezzo }}</td>
        <td>{{ items.totale }}</td>
      </tr>
    </table>

  </div>
</template>

<style scoped>
h1 {
  font-weight: 500;
  font-size: 2.6rem;
  position: relative;
  top: -10px;
}
</style>