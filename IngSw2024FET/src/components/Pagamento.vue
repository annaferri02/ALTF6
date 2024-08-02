<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from "axios";
import { useRouter } from 'vue-router';

const router = useRouter();

const storedDataList = ref([]);

onMounted(() => {
  // Recupera i dati salvati nel localStorage
  const storedData = localStorage.getItem('paymentData');
  console.log('Raw stored data:', storedData);
  if (storedData) {
    try {
      const parsedData = JSON.parse(storedData);
      storedDataList.value = parsedData.posti || []; // Accedi alla proprietà "posti"
      console.log('Parsed stored data:', storedDataList.value);
    } catch (error) {
      console.error('Error parsing stored data:', error);
    }
  }
  console.log('Dati ricevuti:', storedDataList.value);
});


const vaiAlPagamento = async (event: Event) => {
  event.preventDefault(); // Previene il comportamento predefinito del pulsante
  try {
    const response = await axios.post('http://localhost:8080/ticket/Pagamento', {
      biglietti: storedDataList.value,
      utente: '1',
      evento: 'E0001'
    });
    console.log('Dati inviati:', {
      biglietti: storedDataList.value,
      utente: '1',
      evento: 'E0001'
    });
    console.log(response.data);
    router.push({ name: 'PagamentoEffettuato' });
  } catch (error) {
    console.error('Error sending data:', error);
  }
};
</script>

<template>
  <head>
    <title>Pagina Pagamento</title>
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
  <main class="main">
    <div class="indirizzo-fatturazione">
      <h2>Indirizzo di fatturazione</h2>
      <label for="nome2">Nome</label>
      <input type="text" id="nome2" name="nome2" required>
      <label for="cognome2">Cognome</label>
      <input type="text" id="cognome2" name="cognome2" required>
      <label for="indirizzo">Indirizzo</label>
      <input type="text" id="indirizzo" name="indirizzo" required>
      <label for="citta">Citt&agrave;</label>
      <input type="text" id="citta" name="citta" required>
      <label for="provincia">Provincia</label>
      <input type="text" id="provincia" name="provincia" required>
      <label for="cap">CAP</label>
      <input type="text" id="cap" name="cap" required>
      <label for="stato">Stato</label>
      <input type="text" id="stato" name="stato" required>
      <label for="telefono">Numero di telefono</label>
      <input type="tel" id="telefono" name="telefono" required>
      <label for="email">E-mail</label>
      <input type="email" id="email" name="email" required>
    </div>
    <div class="dettagli-pagamento">
      <h2>Dettagli Pagamento</h2>
      <h2>Dettagli Pagamento</h2>
      <label for="nome">Nome</label>
      <input type="text" id="nome" name="nome" required>
      <label for="cognome">Cognome</label>
      <input type="text" id="cognome" name="cognome" required>
      <label for="numeroCarta">Numero carta</label>
      <input type="text" id="numeroCarta" name="numeroCarta" pattern="\d{16}" maxlength="16" required>
      <label for="scadenza">Data di scadenza</label>
      <input type="month" id="scadenza" name="scadenza" required>
      <label for="cvv">CVV</label>
      <input type="text" id="cvv" name="cvv" required maxlength="3" pattern="\d{3}">
    </div>
    <form class="riepilogo-ordine" @submit.prevent="vaiAlPagamento">
      <h2>Riepilogo ordine</h2>
      <template v-if="storedDataList && storedDataList.length">
        <div v-for="(item, index) in storedDataList" :key="index">
          <p>Tipo biglietto: {{ item.Tipo }}</p>
          <template v-if="item.Tipo === 'Tribuna Centrale'">
            <p> Prezzo: 70 &euro;</p>
          </template>
          <template v-else-if="item.Tipo === 'Tribuna Destra'">
            <p> Prezzo: 90 &euro;</p>
          </template>
          <template v-else-if="item.Tipo === 'Tribuna Sinistra'">
            <p> Prezzo: 90 &euro;</p>
          </template>
          <template v-else-if="item.Tipo === 'Parterre' || item.Tipo === 'Pit'">
            <p> Prezzo: 50 &euro;</p>
          </template>
          <template v-else-if="item.Tipo === 'Parterre VIP' || item.Tipo === 'Pit GOLD'">
            <p> Prezzo: 100 &euro;</p>
          </template>
          <template v-if="item.Posto > 0">
            <p>Posto: {{ item.Posto }}</p>
          </template>
        </div>
      </template>
      <template v-else>
        <p>Nessun biglietto trovato.</p>
      </template>
      <button type="submit" style="margin-left: 40%; margin-top: 20px" class="center-button">PAGA ORA</button>
    </form>
  </main>
  <footer>
    <p>ALT + F6 - altf6@events.com - +39 3840957702 - www.events.it</p>
  </footer>
  </body>
</template>

<style src = "@/assets/main.css"></style>
