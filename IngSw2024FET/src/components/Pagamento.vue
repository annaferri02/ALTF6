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

class MyPag{

}

</script>

<template>
  <div>
    <h1>Benvenuto</h1>
    <h3>Seleziona il metodo di pagamento</h3>
    <div>
      <input type="radio" id="paypal" name="payment" value="paypal">
      <label for="paypal">Paypal</label><br>
      <input type="radio" id="carta" name="payment" value="carta">
      <label for="carta">Carta di credito</label><br>
      <input type="radio" id="bonifico" name="payment" value="bonifico">
      <label for="bonifico">Bonifico bancario</label>
    </div>
  </div>
</template>

<style scoped>

</style>