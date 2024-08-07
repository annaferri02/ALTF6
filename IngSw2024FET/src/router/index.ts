// src/router/index.ts
import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/components/Promozioni.vue'; // Sostituisci con il componente della tua home page
import Pagamento from '@/components/Pagamento.vue'; // Sostituisci con il percorso corretto per Pagamento.vue
import PagamentoEffettuato from '@/components/PagamentoEffettuato.vue'; // Sostituisci con il percorso corretto per PagamentoEffettuato.vue
import Promo1 from '@/components/promo/Promo1.vue'; // Sostituisci con il percorso corretto per Promo1.vue
import Promo2 from '@/components/promo/Promo2.vue'; // Sostituisci con il percorso corretto per Promo2.vue
import Promo3 from '@/components/promo/Promo3.vue'; // Sostituisci con il percorso corretto per Promo3.vue
import Promo4 from '@/components/promo/Promo4.vue'; // Sostituisci con il percorso corretto per Promo4.vue
import Pagamento_promo from "@/components/Pagamento_promo.vue";
import Promozioni from "@/components/Promozioni.vue";
import HomeBiglietti from "@/components/Biglietti.vue";

const routes = [
    {
        path: '/',
        name: 'Promozioni',
        component: HomeBiglietti,
    },
    {
        path: '/pagamento',
        name: 'Pagamento',
        component: Pagamento,
    },
    {
        path: '/reindirizzamento',
        name: 'PagamentoEffettuato',
        component: PagamentoEffettuato,
    },
    {
        path: '/promo1',
        name: 'Promo1',
        component: Promo1,
    },
    {
        path: '/promo2',
        name: 'Promo2',
        component: Promo2,
    },
    {
        path: '/promo3',
        name: 'Promo3',
        component: Promo3,
    },
    {
        path: '/promo4',
        name: 'Promo4',
        component: Promo4,
    },
    {
        path: '/pagamento_promo',
        name: 'Pagamento_promo',
        component: Pagamento_promo,
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
