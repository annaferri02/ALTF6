<script setup lang="ts">
import { useRouter } from 'vue-router';

const router = useRouter();

//metodo per navigare alla pagina delle promozioni
const goToPromotion = (promoPage: string) => {
  router.push({ path: promoPage });
};
</script>

<template>
  <head>
    <title>Promuovi il tuo evento</title>
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
  <main>
    <div class="promotion_section">
      <h1>Vuoi aumentare la visibilità del tuo evento?</h1>
      <h3 style="font-weight: normal">Dai un'occhiata ai pacchetti disponibili "Promuovi ora", grazie ai quali <br> renderai il tuo evento più visibile aumentando il numero di partecipazioni!</h3>
      <div class="freccia"></div>
      <h2>Scegli la soluzione "Promuovi Ora" che preferisci</h2>
      <h3 style="font-weight: normal"> Cliccando sulle promozioni potrai accedere ai dettagli e scegliere se applicare la promozione al tuo evento </h3>
      <div class="container">
        <div class="promo_banner" @click="goToPromotion('Promo1')">
          <h3>Rimetti in rilievo</h3>
          <img src="@/assets/1.gif">
          <p style="font-style: italic">Riposiziona l’evento in prima pagina, come appena pubblicato.</p>
          <p><strong>A PARTIRE DA 0,80€</strong></p>
        </div>
        <div class="promo_banner" @click="goToPromotion('Promo2')">
          <h3>Metti in esposizione</h3>
          <img src="@/assets/2.gif">
          <p style="font-style: italic">Etichetta l’evento come in “Esposizione”, facendolo apparire tra i primi risultati della ricerca.</p>
          <p><strong>A PARTIRE DA 0,50€</strong></p>
        </div>
        <div class="promo_banner" @click="goToPromotion('Promo3')">
          <h3>Pungente</h3>
          <img src="@/assets/3.gif">
          <p style="font-style: italic">Etichetta l’evento come “Pungente” e attira più partecipazioni.</p>
          <p><strong>A PARTIRE DA 0,65€</strong></p>
        </div>
        <div class="promo_banner" @click="goToPromotion('Promo4')">
          <h3>Promo 2 x 1</h3>
          <img src="@/assets/4.gif">
          <p style="font-style: italic">Combina le opzioni in rilievo e in esposizione ad un prezzo vantaggioso.</p>
          <p><strong>A PARTIRE DA 1,20€</strong></p>
        </div>
      </div>
      <br>
      <div class="motivazioni">
        <h2>Perché scegliere le soluzioni 'Promuovi ora'?</h2>

            <h3 style="font-style: italic" ><strong>Arriva a più partecipanti possibili!</strong> </h3>
            <h4 style="font-style: italic; font-weight: normal">Il tuo evento verrà messo in risalto e i contatti si moltiplicheranno.</h4>
            <h3 style="font-style: italic" ><strong>Vendi al prezzo che scegli tu!</strong></h3>
            <h4 style="font-style: italic; font-weight: normal">Valorizza il tuo evento e vendi i biglietti al prezzo che ritieni più opportuno. </h4>
            <h3 style="font-style: italic" ><strong>Raggiungi più persone nel minor tempo possibile!</strong></h3>
            <h4 style="font-style: italic; font-weight: normal">Più utenti vedranno il tuo evento e minor tempo ci impiegherà ad andare sold-out. </h4>
      </div>
      <br>
      <h2>Pagamenti sicuri assicurati!</h2>
      <h3 style="font-weight: normal">Scegli il metodo di pagamento che preferisci e rendi il tuo evento imperdibile.</h3>
      <div class="payment-methods">
        <img style="width: 100px; border-radius: 50%; height: 100px" src="@/assets/visa1.png" alt="Visa"/>
        <img style="width: 100px; border-radius: 50%; height: 100px" src="@/assets/maestro1.png" alt="Maestro"/>
        <img style="width: 100px; border-radius: 50%; height: 100px" src="@/assets/paypal1.png" alt="PayPal"/>
        <img style="width: 100px; border-radius: 50%; height: 100px" src="@/assets/mastercard1.jpg" alt="MasterCard"/>
        <img style="width: 100px; border-radius: 50%; height: 100px" src="@/assets/postepay.png" alt="PostePay"/>
      </div>
      <br>
    </div>
  </main>
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
