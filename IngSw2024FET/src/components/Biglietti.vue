<script setup lang="ts">

import { ref, onMounted } from 'vue';
import axios from 'axios';
// Definisci le variabili reattive
const biglietti = ref([]);
const evento = ref({});
const luogo = ref({});

// Funzione per recuperare i dati
const fetchData = async () => {
  try {
    const response = await axios.get('http://localhost:8080/ticket/getInfoBiglietti');
    console.log('Response data:', response.data);
    biglietti.value = response.data.biglietti;
    evento.value = response.data.evento;
    luogo.value = response.data.luogo;
    console.log('Dati aggiornati:', { biglietti: biglietti.value, evento: evento.value, luogo: luogo.value });
  } catch (error) {
    console.error("C'è stato un errore nel recuperare i dati:", error);
  }
};

// Recupera i dati quando il componente viene montato
onMounted(fetchData);

//Metodo dinamico per il popolamento delle tribune usando come posti dei bottoni con un id univoco e il controllo della selezione e se il posto è già stato selezionato
function aggiornaNumeroPosti() {
  var categoria = document.getElementById("categoria").value;
  var numeroPosti = document.getElementById("numeroPosti").value;
  var allSelectedSeats = document.getElementById("allSelectedSeats");
  var selectedSeats = allSelectedSeats.value.split(",");
  var table = document.getElementById(categoria);
  var rows = table.getElementsByTagName("tr");
  var posti = [];
  for (var i = 0; i < rows.length; i++) {
    var cells = rows[i].getElementsByTagName("td");
    for (var j = 0; j < cells.length; j++) {
      var cell = cells[j];
      if (cell.style.backgroundColor === "red") {
        posti.push(cell.id);
      }
    }
  }
  allSelectedSeats.value = posti.join(",");
  document.getElementById("numeroPosti").value = posti.length;
}

</script>



<template>
  <head>
    <title>Pagina Biglietto</title>
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
      <button class="buttonM">Menù</button>
    </div>
  </header>
  <main>
    <h1 class="centrato">Seleziona il biglietto che preferisci!</h1>
    <br><br>
    <form class="content" name="gotoForm" method="post" action="Dispatcher">
      <template v-if="luogo.tipologia === 'Indoor'">
      <p class="centrato">Seleziona i posti numerati che desideri acquistare direttamente dalla piantina e i posti in parterre dal men&ugrave; sottostante</p>
      <section class="layout-esterno">
        <article class="tariffe">
          <h3>Tariffe:</h3>
          <h4>Parterre 50 &euro;</h4>
          <h4>Parterre VIP 100 &euro;</h4>
          <h4>Tribuna frontale 70 &euro;</h4>
          <h4>Tribuna laterale destra 90 &euro;</h4>
          <h4>Tribuna laterale sinistra 90 &euro;</h4>
        </article>
        <section class="container">
          <table id="palco">
            <tr>
              <td style="text-align: center; font-size: 40px">PALCO</td>
            </tr>
          </table>
          <table id="parterre" >
            <tr>
              <td style="text-align: center; font-size: 40px">PARTERRE</td>
            </tr>
          </table>
          <table id="parterreVIP" >
            <tr>
              <td style="text-align: center; font-size: 40px">PARTERRE VIP</td>
            </tr>
          </table>
          <table id="tribuna"></table>

          <table id="tribuna-sx"></table>

          <table id="tribuna-dx"></table>
        </section>

      </section>
      </template>
     <template v-if="luogo.tipologia==='Outdoor'">
      <p class="centrato">Seleziona i posti in Pit e Pit Gold mostrati nella piantina, dal men&ugrave; sottostante</p>
      <section class="layout-esterno1">
        <article class="tariffe">
          <h3>Tariffe:</h3>
          <h4>Pit 50 &euro;</h4>
          <h4>Pit Gold 100 &euro;</h4>
        </article>
        <section class="container1">
          <table id="palco1">
            <tr>
              <td style="text-align: center; font-size: 40px">PALCO</td>
            </tr>
          </table>
          <table id="pit" >
            <tr>
              <td style="text-align: center; font-size: 40px">PIT</td>
            </tr>
          </table>
          <table id="pitgold" >
            <tr>
              <td style="text-align: center; font-size: 40px">PIT GOLD</td>
            </tr>
          </table>
        </section>

      </section>
      <br>
      </template>

      <h3 style="margin-top: 10%; margin-left: 26%">Seleziona la categoria di posti che desideri acquistare:</h3>
      <select id="categoria" name="categoria" style="width: fit-content; margin-left: 26%" onchange="aggiornaNumeroPosti()">
      </select>
      <br>
      <h3 style="margin-left: 26%">Seleziona il numero di posti che desideri acquistare:</h3>
      <label style="margin-left: 26%" for="numeroPosti">Numero di posti </label>
      <button class="bottone-scelta" id="decrementa" style="border: none; font-size: 20px; font-weight: bolder">-</button>
      <input type="number" id="numeroPosti" name="numPosti" style="margin-bottom:30px; font-size: 20px; border: none" min="0" max="6" value="0" readonly>
      <button class="bottone-scelta" id="incrementa" style="border: none; font-size: 20px; font-weight: bolder">+</button>
      <input type="hidden" name="controllerAction" value="PagamentoManagement.gotoPagamentoBiglietto">
      <input type="hidden" id="allSelectedSeats" name="allSelectedSeats">
      <input type="hidden" name="idEvento" value="<%=evento.getIdEvento()%>">
      <br>
      <input type="submit" style="margin-top: 20px" class="center-button" value="Procedi con l'acquisto">
    </form>
  </main>

  <footer>
    <p>ALT + F6 - altf6@events.com - +39 3840957702 - www.events.it</p>
  </footer>

  </body>

</template>

<style src = "../assets/main.css"></style>