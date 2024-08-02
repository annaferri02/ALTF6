// src/router/index.ts
import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/components/Biglietti.vue'; // Sostituisci con il componente della tua home page
import Pagamento from '@/components/Pagamento.vue'; // Sostituisci con il percorso corretto per Pagamento.vue
import PagamentoEffettuato from '@/components/PagamentoEffettuato.vue'; // Sostituisci con il percorso corretto per PagamentoEffettuato.vue

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
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
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
