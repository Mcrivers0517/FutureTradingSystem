import Vue from "vue";
import VueRouter from 'vue-router';
import UserProfiles from '../components/UserProfiles.vue';
import LoginFile from '../components/LoginFile.vue';
import MarketQuotes from '../components/MarketQuotes.vue';
import RegisterFile from '../components/RegisterFile.vue';
import StockTrendChart from '../components/StockTrendChart.vue';

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
