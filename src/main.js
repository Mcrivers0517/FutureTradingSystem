import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import * as echarts from "echarts";
import router from './router/index';
import Vuex from 'vuex';
import store from './store';

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.prototype.$echarts = echarts;
Vue.use(Vuex);

import axios from "axios";
new Vue({
  router,
  store,
  created()
  {
    // this.$store.state.chartData[this.$store.state.activeRowNumber].times = this.generateRandomHour();
    // this.$store.state.chartData[this.$store.state.activeRowNumber].price = this.generateStockData();
    // this.$store.state.chartData[this.$store.state.activeRowNumber].tradeVolume = this.generateTradeVolumeData();

    // this.startPriceDataFetch();
    this.fetchAndProcessData();
    // this.timerId = setInterval(() =>
    // {
    //   this.fetchAndProcessData();
    // }, 1000);
  },
  methods: {
    generateRandomHour()
    {
      const times = [];
      const currentDate = new Date(); // Get the current date and time

      for (let minute = 0; minute < 60; minute++)
      {
        // Subtract minutes from the current time
        const time = new Date(currentDate.getTime() - minute * 60000);
        const hour = time.getHours();
        const minuteStr = time.getMinutes();

        // Format the time strings
        const hourStr = hour < 10 ? `0${hour}` : `${hour}`;
        const formattedMinuteStr =
          minuteStr < 10 ? `0${minuteStr}` : `${minuteStr}`;

        // Push the formatted time string into the times array
        times.unshift(`${hourStr}:${formattedMinuteStr}`); // Use unshift to add to the beginning
      }

      return times;
    },
    generateStockData()
    {
      const data = [];
      if (this.$store.state.activeRowNumber == 0)
      {
        console.log("----------------------------------------------------------------");
        let price = 4970;
        for (let i = 0; i < 1 * 60; i++)
        {
          const change = Math.random() * 100 - 50;
          price += change;

          // 确保价格在指定范围内波动
          if (price < 4920)
          {
            price = 4920;
          } else if (price > 5020)
          {
            price = 5020;
          }

          data.push(Math.round(price * 100) / 100);
        }
      }
      else if (this.$store.state.activeRowNumber == 1)
      {
        let price = 4450; // 将初始价格设为 4450，在指定范围内
        for (let i = 0; i < 60; i++)
        { // 60个数据点
          const change = Math.random() * 600 - 300; // 改变值在 -300 到 300 之间
          price += change;

          // 确保价格在指定范围内波动
          if (price < 4300)
          {
            price = 4300;
          } else if (price > 4600)
          {
            price = 4600;
          }

          data.push(Math.round(price * 100) / 100);
        }
      }
      else if (this.$store.state.activeRowNumber == 2)
      {
        let price = 14500; // 将初始价格设为 14500，在指定范围内
        for (let i = 0; i < 60; i++)
        { // 60个数据点
          const change = Math.random() * 6000 - 3000; // 改变值在 -3000 到 3000 之间
          price += change;

          // 确保价格在指定范围内波动
          if (price < 13000)
          {
            price = 13000;
          } else if (price > 16000)
          {
            price = 16000;
          }

          data.push(Math.round(price * 100) / 100);
        }
      }
      return data;
    },
    generateTradeVolumeData()
    {
      const data = [];
      const targetVolume = 50000; // 目标交易量

      for (let i = 0; i < 60; i++)
      {
        // 生成随机波动在目标交易量上下的交易量
        const volume = targetVolume + (Math.random() - 0.5) * targetVolume;

        // 计算波动范围（允许波动的最大值和最小值）
        const minFluctuation = -50; // 最小波动范围
        const maxFluctuation = 50; // 最大波动范围

        // 随机生成十位、个位和小数位都是随机的数字
        const decimal = Math.random() * (maxFluctuation - minFluctuation) + minFluctuation;
        const roundedVolume = Math.round((volume + decimal) * 10) / 10;

        data.push(roundedVolume);
      }
      return data;
    },
    async fetchAndProcessData()
    {
      this.timerId = setInterval(async () =>
      {
        const timestamp = Date.now(); // 毫秒时间戳
        const date = new Date(timestamp);

        const offset = 8 * 60; // UTC+8
        const dateTimeString = new Date(date.getTime() + offset * 60000)
          .toISOString()
          .replace(
            "Z",
            `+${String(Math.abs(offset / 60)).padStart(2, "0")}:00`
          );

        this.dateTimeString = dateTimeString;

        const response = await axios.post(
          "http://localhost:5000/getFuturesData",
          {
            dateTimeString,
          }
        );
        for (let index = 0; index < response.data.length; index++)
        {
          this.$store.state.chartSecondData[index].times.push(dateTimeString);
          this.$store.state.chartSecondData[index].price.push(response.data[index].price);
          this.$store.state.chartSecondData[index].tradeVolume.push(response.data[index].volume);
          if (this.$store.state.dailyOpenPrice.length < index + 1)
          {
            this.$store.state.dailyOpenPrice.push(response.data[index].dailyOpenPrice);
            this.$store.state.dailyHighestPrice.push(response.data[index].dailyHighestPrice);
            this.$store.state.dailyLowestPrice.push(response.data[index].dailyLowestPrice);
            this.$store.state.dailyChange.push(response.data[index].dailyChange);
            this.$store.state.dailyChangeRatio.push(response.data[index].dailyChangeRatio);
          }
          this.$store.state.dailyOpenPrice[index] = response.data[index].dailyOpenPrice;
          this.$store.state.dailyHighestPrice[index] = response.data[index].dailyHighestPrice;
          this.$store.state.dailyLowestPrice[index] = response.data[index].dailyLowestPrice;
          this.$store.state.dailyChange[index] = response.data[index].dailyChange;
          this.$store.state.dailyChangeRatio[index] = response.data[index].dailyChangeRatio;

          // this.$store.state.dailyOpenPrice[index].push(response.data[index].dailyOpenPrice);
          // this.$store.state.dailyHighestPrice[index].push(response.data[index].dailyHighestPrice);
          // this.$store.state.dailyLowestPrice[index].push(response.data[index].dailyLowestPrice);
          // this.$store.state.dailyChange[index].push(response.data[index].dailyChange);
          // this.$store.state.dailyChangeRatio[index].push(response.data[index].dailyChangeRatio);
        }
        // console.log(this.$store.state.dailyOpenPrice);
        // console.log(this.$store.state.dailyHighestPrice);
        // console.log(this.$store.state.dailyLowestPrice);
        // console.log(this.$store.state.dailyChange);
        // console.log(this.$store.state.dailyChangeRatio);
        // for (let index = 0; index < response.data.length; index++)
        // {
        //   this.$store.state.dailyHighestPrice[index].push(response.data[index].dailyHighestPrice);
        //   console.log(this.$store.state.dailyHighestPrice);
        // }
        // this.$store.state.dailyHighestPrice[1].push(response.data[1].dailyHighestPrice);
        // console.log("333", this.$store.state.dailyHighestPrice[1]);
        // console.log("222", this.$store.state.chartSecondData);
        // console.log("222", this.$store.state.chartSecondData.times);
        // for (const data of response.data)
        // {
        //   this.$store.state.dailyOpenPrice.push(data.dailyOpenPrice);
        //   this.$store.state.dailyHighestPrice.push(data.dailyHighestPrice);
        //   this.$store.state.dailyLowestPrice.push(data.dailyLowestPrice);
        //   this.$store.state.dailyChange.push(data.dailyChange);
        //   this.$store.state.dailyChangeRatio.push(data.dailyChangeRatio);
        // }
      }, 1000);
    },
  },
  render: h => h(App),
}).$mount('#app')
