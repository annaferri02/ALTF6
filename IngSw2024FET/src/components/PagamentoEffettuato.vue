<template>
  <div class="page-container">
    <main class="mainPagamento">
      <h1>Pagamento effettuato, riceverai una notifica sullo stato della transazione</h1>
      <h3>Ora puoi tornare alla HomePage</h3>
      <form @submit.prevent="navigateToPromozioni">
        <input type="submit" class="center-button" value="Torna alla Home">
      </form>
    </main>
  </div>
</template>

<style>
.mainPagamento{
  text-align: center;
  width: fit-content;
  padding: 20px;
  border: lightgrey 1px solid;
  box-shadow: 10px 10px 5px rgba(0, 0, 0, 0.5);
  border-radius: 10px;
}
.page-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 90vh;
}
</style>

<script setup lang="ts">
import { useRouter } from 'vue-router';

const router = useRouter();

function navigateToPromozioni() {
  router.push({ name: 'Promozioni' });
}
</script>
