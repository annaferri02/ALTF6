<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from "axios";
import { useRouter } from 'vue-router';

// Ottieni il router per la navigazione programmatica
const router = useRouter();

// Definisci le variabili reattive per i dati
const storedDataList = ref([]);
const prezziDataList = ref([]);
const scadenza = ref('');

// Questa funzione viene eseguita quando il componente è montato
onMounted(() => {
  // Recupera i dati salvati nel localStorage
  const storedData = localStorage.getItem('paymentData');
  const prezzi = localStorage.getItem('prezzi');

  // Log dei dati recuperati per il debug
  console.log('Raw stored data:', storedData);
  console.log('Raw prezzi data:', prezzi);

  if (storedData) {
    try {
      // Parsing dei dati dal localStorage
      const parsedData = JSON.parse(storedData);
      const parsedPrezzi = JSON.parse(prezzi);

      // Assegna i dati alle variabili reattive
      storedDataList.value = parsedData.posti || []; // Accedi alla proprietà "posti"
      prezziDataList.value = parsedPrezzi || [];

      // Log dei dati parsati per il debug
      console.log('Parsed stored data:', storedDataList.value);
    } catch (error) {
      // Gestione degli errori nel parsing dei dati
      console.error('Error parsing stored data:', error);
    }
  }
  // Log dei dati ricevuti per il debug
  console.log('Dati ricevuti:', storedDataList.value);
});

// Funzione chiamata al momento del submit del form
const vaiAlPagamento = async (event: Event) => {
  // Previene il comportamento predefinito del pulsante submit
  event.preventDefault();

  // Controllo della data di scadenza
  const today = new Date();
  // Crea una data con il giorno impostato al primo del mese per il confronto
  const scadenzaData = new Date(scadenza.value + '-01'); // Formatta come YYYY-MM-01

  if (scadenzaData < today) {
    // Mostra un messaggio di errore se la carta è scaduta
    alert('La carta di credito risulta scaduta.');
    return; // Interrompe l'esecuzione se la data è scaduta
  }

  try {
    // Invia i dati al server
    const response = await axios.post('http://localhost:8080/ticket/Pagamento', {
      biglietti: storedDataList.value,
      utente: '1',
      evento: 'E0001'
    });

    // Log della risposta del server
    console.log(response.data);
    // Reindirizza l'utente alla pagina di pagamento effettuato
    router.push({ name: 'PagamentoEffettuato' });
  } catch (error) {
    // Gestione degli errori nella richiesta
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
  <form class="main-pagamento"  @submit.prevent="vaiAlPagamento">
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
        <label for="nome">Nome</label>
        <input type="text" id="nome" name="nome" required>
        <label for="cognome">Cognome</label>
        <input type="text" id="cognome" name="cognome" required>
        <label for="numeroCarta">Numero carta</label>
        <input type="text" id="numeroCarta" name="numeroCarta" pattern="\d{16}" maxlength="16" required>
        <label for="scadenza">Data di scadenza</label>
        <input type="month" id="scadenza" name="scadenza" v-model="scadenza" required>
        <label for="cvv">CVV</label>
        <input type="text" id="cvv" name="cvv" required maxlength="3" pattern="\d{3}">
      </div>
      <div class="riepilogo-ordine">
        <h2 style="margin-bottom: 0">Riepilogo ordine</h2>
        <template style="margin-left: 0" v-if="storedDataList && storedDataList.length">
          <div v-for="(item, index) in storedDataList" :key="index">
            <h4>Tipo biglietto: {{ item.Tipo }}</h4>
            <template v-if="item.Tipo === 'Tribuna Centrale'">
              <h4> Prezzo: {{prezziDataList[2]}} &euro;</h4>
            </template>
            <template v-else-if="item.Tipo === 'Tribuna Destra'">
              <h4> Prezzo: {{prezziDataList[3]}} &euro;</h4>
            </template>
            <template v-else-if="item.Tipo === 'Tribuna Sinistra'">
              <h4> Prezzo: {{prezziDataList[4]}} &euro;</h4>
            </template>
            <template v-else-if="item.Tipo === 'Parterre' || item.Tipo === 'Pit'">
              <h4> Prezzo: {{ prezziDataList[0] }} &euro;</h4>
            </template>
            <template v-else-if="item.Tipo === 'Parterre VIP' || item.Tipo === 'Pit GOLD'">
              <h4> Prezzo: {{prezziDataList[1]}} &euro;</h4>
            </template>
            <template v-if="item.Posto > 0">
              <h4>Posto: {{ item.Posto }}</h4>
            </template>
          </div>
        </template>
        <template v-else>
          <p>Nessun biglietto trovato.</p>
        </template>
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

<style src = "@/assets/main.css"></style>
