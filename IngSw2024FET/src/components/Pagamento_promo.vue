<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axios from "axios";

const router = useRouter();

const parsedData = ref({
  Nome: '',
  Prezzo: '',
  Data: '',
  Giorni: ''
});

const promo = ref('');

onMounted(() => {
  // Recupera i dati salvati nel localStorage
  const tipoPromozione = localStorage.getItem('promo');
  const storedData = localStorage.getItem('infoPromo');
  console.log('Raw stored data:', storedData);

  if (storedData) {
    try {
      const data = JSON.parse(storedData);
      parsedData.value = data; // Assicurati che data abbia la struttura prevista
    } catch (error) {
      console.error('Error parsing stored data:', error);
    }
  }

  promo.value = tipoPromozione || 'Non disponibile'; // Imposta un valore predefinito se nessun tipo di promozione è trovato
});

const goToPromotion = async () => {
  try {
    console.log('Sending data:', {
      NomeEvento: parsedData.value.Nome,
      Prezzo: parsedData.value.Prezzo,
      TipologiaPromozione: promo.value
    });

    const response = await axios.post('http://localhost:8080/api/processPromoOrder', {
      NomeEvento: parsedData.value.Nome,
      Prezzo: parsedData.value.Prezzo,
      TipologiaPromozione: promo.value
    });

    console.log('Response:', response.data);
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
  <main>

    <div class="indirizzo-fatturazione">
      <h2>Indirizzo di fatturazione</h2>
      <label for="nomeAzienda">Nome azienda</label>
      <input type="text" id="nomeAzienda" name="nomeAzienda" required>
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
      <label for="nome">Nome</label>
      <input type="text" id="nome" name="nome" required>
      <label for="cognome">Cognome</label>
      <input type="text" id="cognome" name="cognome" required>
      <label for="numeroCarta">Numero carta</label>
      <input type="text" id="numeroCarta" name="numeroCarta" pattern="\d{16}" maxlength="16" required>
      <label for="scadenza">Data di scadenza</label>
      <input type="month" id="scadenza" name="scadenza" required>
      <label for="cvv">CVV</label>
      <input type="text" id="cvv" name="cvv" required maxlength="3" pattern="\d{3}" >
    </div>
      <h2>Riepilogo ordine</h2>
      <!--Inserire i dati del riepilogo ordine ricevuti dal backend-->
      <p>Nome evento: {{  parsedData.Nome }}</p>
      <p>Prezzo: {{  parsedData.Prezzo }}</p>
      <p>Data Inizio evento: {{  parsedData.Data }}</p>
      <p>Numero di giornate: {{  parsedData.Giorni }}</p>
      <p>Tipologia promozione: {{ promo }}</p>

      <button class="center-button" @click="goToPromotion()">PAGA ORA </button>
  </main>

  <footer>
    <p>ALT + F6 - altf6@events.com - +39 3840957702 - www.events.it</p>
  </footer>

  </body>
</template>

<style src = "../assets/main.css"></style>

