import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import * as echarts from "echarts";
import router from './router/index';
import Vuex from 'vuex';

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.prototype.$echarts = echarts;
Vue.use(Vuex);

// const store =
//   new Vuex.Store({
//     state: {
//     },
//     mutations: {
//     },
//     actions: {
//     }
//   })
  
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
