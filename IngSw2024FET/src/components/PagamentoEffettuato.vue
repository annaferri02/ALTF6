<template>
<main class="mainPagamento">
<h1>Pagamento effettuato, riceverai una notifica sullo stato della transazione</h1>
<h3>Ora puoi tornare alla HomePage</h3>
<form>
  <input type="hidden" name="controllerAction" value="HomeManagement.view">
  <input type="submit" class="center-button" value="Torna alla Home">
</form>

</main>
</template>

<style>
.mainPagamento{ text-align: center;
  padding: 20px;
  border: 1px solid #ddd;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
}
</style>
<script setup lang="ts">
</script>