import Vue from "vue";
import VueRouter from 'vue-router';
import UserProfiles from '../pages/UserProfiles.vue';
import LoginFile from '../pages/LoginFile.vue';
import MarketQuotes from '../pages/MarketQuotes.vue';
import RegisterFile from '../pages/RegisterFile.vue';
import StockTrendChart from '../pages/StockTrendChart.vue';
import CurrentDelegate from '../pages/CurrentDelegate.vue';
import HistoricalDelegate from '../pages/HistoricalDelegate.vue';
import TestFunction from '../pages/TestFunction.vue';

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
        {
            path: '/TestFunction',
            name: 'TestFunction',
            component: TestFunction
        },
        {
            path: '/CurrentDelegate',
            name: 'CurrentDelegate',
            component: CurrentDelegate
        },
        {
            path: '/HistoricalDelegate',
            name: 'HistoricalDelegate',
            component: HistoricalDelegate
        }
    ]
})

export default router;
