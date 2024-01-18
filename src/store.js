import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        // chartData: {
        //     times: [],
        //     price: [],
        //     tradeVolume: [],
        // },
        // chartSecondData: {
        //     times: [],
        //     price: [],
        //     tradeVolume: [],
        // },
        chartData: new Array(3).fill().map(() => ({
            times: [],
            price: [],
            tradeVolume: [],
        })),
        chartSecondData: new Array(3).fill().map(() => ({
            times: [1],
            price: [1],
            tradeVolume: [1],
        })),
        dailyOpenPrice: [],
        dailyHighestPrice: [],
        dailyLowestPrice: [],
        dailyChange: [],
        dailyChangeRatio: [],
        tempVolume: [],
        activeRowNumber: 0,
        activeUserId: -1,
        transacted: false,
        transactedDelegateId: 0,
    },
    mutations: {
        // 在这里定义用于修改状态的 mutation
        increment(state)
        {
            state.count++;
        },
        decrement(state)
        {
            state.count--;
        },
        login(state)
        {
            state.isLoggedIn = true;
        },
        logout(state)
        {
            state.isLoggedIn = false;
        },
        // 其他的 mutations...
    },
    actions: {
        // 在这里定义 actions，可以在这里进行异步操作并提交 mutation
        // 例如：处理异步登录逻辑
        asyncLogin(context)
        {
            setTimeout(() =>
            {
                context.commit('login');
            }, 1000); // 模拟异步操作
        },
        // 其他的 actions...
    },
    getters: {
        // 在这里定义 getters，用于从 state 中派生出一些状态
        // 例如：获取 count 的平方值
        squaredCount: state => state.count * state.count,
        // 其他的 getters...
    },
});