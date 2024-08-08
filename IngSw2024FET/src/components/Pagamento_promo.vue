<script setup lang="ts">
import { ref, onMounted } from 'vue'; // Importa funzioni Vue per la reattività e il montaggio del componente
import { useRouter } from 'vue-router'; // Importa useRouter per gestire la navigazione
import axios from "axios"; // Importa axios per le richieste HTTP

const router = useRouter(); // Ottiene l'istanza del router per la navigazione
const scadenza = ref(''); // Variabile reattiva per memorizzare la data di scadenza della carta di credito

// Variabile reattiva per memorizzare i dettagli dell'evento promozionale
const parsedData = ref({
  Nome: '',
  Prezzo: '',
  Data: '',
  Giorni: ''
});

const promo = ref(''); // Variabile reattiva per memorizzare il tipo di promozione

// Funzione eseguita quando il componente viene montato
onMounted(() => {
  // Recupera il tipo di promozione dal localStorage
  const tipoPromozione = localStorage.getItem('promo');
  // Recupera i dati dell'evento promozionale dal localStorage
  const storedData = localStorage.getItem('infoPromo');
  console.log('Raw stored data:', storedData); // Mostra i dati grezzi nel console log

  if (storedData) {
    try {
      // Parsea i dati JSON salvati nel localStorage
      const data = JSON.parse(storedData);
      parsedData.value = data; // Assegna i dati parsati alla variabile reattiva
    } catch (error) {
      // Gestisce eventuali errori di parsing
      console.error('Error parsing stored data:', error);
    }
  }

  // Imposta il valore di tipoPromozione o 'Non disponibile' se non è stato trovato
  promo.value = tipoPromozione || 'Non disponibile';
});

// Funzione per gestire il processo di pagamento e navigare alla pagina di conferma
const goToPromotion = async () => {
  // Controlla se la data di scadenza è passata
  const today = new Date();
  const scadenzaData = new Date(scadenza.value + '-01'); // Crea una data a partire dal valore di scadenza
  if (scadenzaData < today) {
    alert('La carta di credito risulta scaduta.'); // Mostra un messaggio di errore
    return; // Interrompe l'esecuzione se la data è scaduta
  }

  try {
    // Mostra i dati inviati nel console log
    console.log('Sending data:', {
      NomeEvento: parsedData.value.Nome,
      Prezzo: parsedData.value.Prezzo,
      TipologiaPromozione: promo.value
    });

    // Invia una richiesta POST al server con i dettagli dell'ordine
    const response = await axios.post('http://localhost:8080/api/processPromoOrder', {
      NomeEvento: parsedData.value.Nome,
      Prezzo: parsedData.value.Prezzo,
      TipologiaPromozione: promo.value
    });

    // Mostra la risposta del server nel console log
    console.log('Response:', response.data);
    // Naviga alla pagina di conferma del pagamento
    router.push({ name: 'PagamentoEffettuato' });
  } catch (error) {
    // Gestisce eventuali errori nella richiesta
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
  <form class="main-pagamento" @submit.prevent="goToPromotion">
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
      <input type="month" id="scadenza" name="scadenza" v-model="scadenza"  required>
      <label for="cvv">CVV</label>
      <input type="text" id="cvv" name="cvv" required maxlength="3" pattern="\d{3}" >
    </div>
    <div class="riepilogo-ordine">
      <h2>Riepilogo ordine</h2>
      <!--Inserire i dati del riepilogo ordine ricevuti dal backend-->
      <p>Nome evento: {{  parsedData.Nome }}</p>
      <p>Prezzo: {{  parsedData.Prezzo }} &euro;</p>
      <p>Data Inizio evento: {{  parsedData.Data }}</p>
      <p>Numero di giornate: {{  parsedData.Giorni }}</p>
      <p>Tipologia promozione: {{ promo }}</p>
      <button type="submit" style="margin-left: 40%; margin-top: 20px" class="center-button">PAGA ORA</button>

    </div>

  </form>

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

<style src = "../assets/main.css"></style>

