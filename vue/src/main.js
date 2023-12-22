import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import * as echarts from "echarts";

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.prototype.$echarts = echarts;

new Vue({
  render: h => h(App),
}).$mount('#app')
