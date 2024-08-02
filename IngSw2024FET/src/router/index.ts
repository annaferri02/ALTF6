// src/router/index.ts
import { createRouter, createWebHistory } from 'vue-router';
import Home from '@/components/Biglietti.vue'; // Sostituisci con il componente della tua home page
import Pagamento from '@/components/Pagamento.vue'; // Sostituisci con il percorso corretto per Pagamento.vue

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
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
