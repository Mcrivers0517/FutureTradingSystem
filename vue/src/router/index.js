import Vue from "vue";
import VueRouter from 'vue-router';
import UserProfiles from '../pages/UserProfiles.vue';
import LoginFile from '../pages/LoginFile.vue';
import MarketQuotes from '../pages/MarketQuotes.vue';
import RegisterFile from '../pages/RegisterFile.vue';
import StockTrendChart from '../pages/StockTrendChart.vue';

Vue.use(VueRouter);

const router = new VueRouter({
    routes: [
        {
            path: '/',
            name: 'UserProfiles',
            component: UserProfiles
        },
        {
            path: '/Login',
            name: 'LoginPage',
            component: LoginFile
        },
        {
            path: '/Register',
            name: 'RegisterPage',
            component: RegisterFile
        },
        {
            path: '/MarketQuotes',
            name: 'MarketQuotes',
            component: MarketQuotes
        },
        {
            path: '/StockTrendChart',
            name: 'StockTrendChart',
            component: StockTrendChart
        },
    ]
})

export default router;
