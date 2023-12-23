<template>
  <div class="common-layout">
    <el-container>
      <el-header class="header" height>
        <div class="logo">
          <img
            src="../assets/processed_logo.png"
            alt="Logo"
            class="logo-image"
          />
        </div>
        <el-menu
          class="el-menu-demo"
          mode="horizontal"
          background-color="#181a20"
          @select="handleMenu"
          text-color="#eaecef"
          active-text-color="#f0b90b"
          style="margin-left: 30px"
        >
          <el-menu-item index="1">主页</el-menu-item>
          <el-submenu index="2">
            <template slot="title">订单管理</template>
            <el-menu-item index="2-1">当前委托</el-menu-item>
            <el-menu-item index="2-2">历史委托</el-menu-item>
          </el-submenu>
          <el-menu-item index="3">行情</el-menu-item>
          <el-menu-item index="4">消息中心</el-menu-item>
        </el-menu>
        <div class="logout-button">
          <el-button
            type="primary"
            native-type="submit"
            @click="logout"
            id="logoutButton"
            >退出账号</el-button
          >
        </div>
      </el-header>
      <el-container class="content-container">
        <el-main class="main">
          <div class="chart-content">
            <div
              id="price-trend-chart"
              style="
                width: 100%;
                height: 100%;
                border-bottom: 2px solid #2b3139;
              "
            ></div>
          </div>
        </el-main>
        <el-footer class="footer" height="125vh">
          <el-container>
            <el-main class="deal">
              <div class="trend">
                <el-input
                  v-model="buyPrice"
                  placeholder="价格"
                  class="input"
                ></el-input>
                <el-input
                  v-model="buyQuantity"
                  placeholder="数量"
                  class="input"
                ></el-input>
                <div class="block">
                  <el-slider
                    v-model="buyQuantity"
                    :format-tooltip="formatTooltip"
                  ></el-slider>
                </div>
                <el-input
                  v-model="BuyAmount"
                  placeholder="成交额"
                  class="input"
                  style="margin-top: 10px"
                ></el-input>
                <div class="submit-button">
                  <el-button
                    type="primary"
                    native-type="submit"
                    @click="transaction"
                    id="buyButton"
                    >买入</el-button
                  >
                </div>
              </div>
              <div class="trend">
                <el-input
                  v-model="sellPrice"
                  placeholder="价格"
                  class="input"
                ></el-input>
                <el-input
                  v-model="sellQuantity"
                  placeholder="数量"
                  class="input"
                ></el-input>
                <div class="block">
                  <el-slider
                    v-model="sellQuantity"
                    :format-tooltip="formatTooltip"
                  ></el-slider>
                </div>
                <el-input
                  v-model="SellAmount"
                  placeholder="成交额"
                  class="input"
                  style="margin-top: 10px"
                ></el-input>
                <div class="submit-button">
                  <el-button
                    type="primary"
                    native-type="submit"
                    @click="transaction"
                    id="sellButton"
                    >卖出</el-button
                  >
                </div>
              </div>
            </el-main>
          </el-container>
          <el-container>
            <el-main
              ><el-container>
                <el-header
                  ><el-menu
                    :default-active="activeIndex"
                    class="order-type"
                    mode="horizontal"
                    @select="handleSelect"
                    background-color="#181a20"
                    text-color="#f0b90b"
                    active-text-color="#f0b90b"
                  >
                    <el-menu-item index="1">当前订单</el-menu-item>
                    <el-menu-item index="2">历史订单</el-menu-item>
                  </el-menu>
                </el-header>
                <el-main
                  ><el-table
                    :data="CurrentOrderData"
                    class="posi-table"
                    :cell-style="tableRowClassName"
                    height="430"
                    v-if="activeIndex === '1'"
                  >
                    <el-table-column prop="type" label="品种">
                    </el-table-column>
                    <el-table-column prop="Date" label="日期">
                    </el-table-column>
                    <el-table-column prop="Attribute" label="属性">
                    </el-table-column>
                    <el-table-column prop="Price" label="价格">
                    </el-table-column>
                    <el-table-column prop="Status" label="状态">
                    </el-table-column>
                    <el-table-column prop="Quantity" label="数量">
                    </el-table-column>
                    <el-table-column label="金额">
                      <template slot-scope="scope">
                        {{ scope.row.Price * scope.row.Quantity }}
                      </template>
                    </el-table-column>
                    <el-table-column label="撤单" :width="60">
                      <el-button
                        class="revoke"
                        @click="cancelOrder(row.id)"
                        icon="el-icon-delete-solid"
                      ></el-button>
                    </el-table-column>
                  </el-table>
                  <el-table
                    :data="HistoricalOrderData"
                    class="posi-table"
                    :cell-style="tableRowClassName"
                    height="430"
                    v-if="activeIndex === '2'"
                  >
                    <el-table-column prop="type" label="品种">
                    </el-table-column>
                    <el-table-column prop="Date" label="日期">
                    </el-table-column>
                    <el-table-column prop="Attribute" label="属性">
                    </el-table-column>
                    <el-table-column prop="Price" label="价格">
                    </el-table-column>
                    <el-table-column prop="Status" label="状态">
                    </el-table-column>
                    <el-table-column prop="Quantity" label="数量">
                    </el-table-column>
                    <el-table-column label="金额">
                      <template slot-scope="scope">
                        {{ scope.row.Price * scope.row.Quantity }}
                      </template>
                    </el-table-column>
                    <el-table-column label="删除" :width="60">
                      <el-button
                        class="delete"
                        @click="deleteOrder(row.id)"
                        icon="el-icon-delete-solid"
                      ></el-button>
                    </el-table-column>
                  </el-table>
                </el-main> </el-container
            ></el-main>
          </el-container>
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      CurrentOrderData: [
        // {
        //   type: "商品A",
        //   Date: "2023-01-05",
        //   Attribute: "买平",
        //   Status: "未成交",
        //   Price: 100.5,
        //   Quantity: 10,
        // },
        // {
        //   type: "商品B",
        //   Date: "2023-01-06",
        //   Attribute: "卖平",
        //   Status: "未成交",
        //   Price: 98.75,
        //   Quantity: 8,
        // },
        // {
        //   type: "商品C",
        //   Date: "2023-01-07",
        //   Attribute: "买开",
        //   Status: "未成交",
        //   Price: 55.25,
        //   Quantity: 15,
        // },
      ],
      HistoricalOrderData: [
        // {
        //   type: "商品D",
        //   Date: "2023-05-10",
        //   Attribute: "卖平",
        //   Status: "已成交",
        //   Price: 150.25,
        //   Quantity: 5,
        // },
        // {
        //   type: "商品E",
        //   Date: "2023-04-15",
        //   Attribute: "买开",
        //   Status: "已成交",
        //   Price: 120.75,
        //   Quantity: 12,
        // },
        // {
        //   type: "商品F",
        //   Date: "2023-03-20",
        //   Attribute: "卖开",
        //   Status: "已成交",
        //   Price: 200.0,
        //   Quantity: 7,
        // },
      ],
      myChart: null,
      buyPrice: null,
      buyQuantity: null,
      sellPrice: null,
      sellQuantity: null,
      buyAmount: null,
      sellAmount: null,
      activeIndex: "1",
      priceUpdateInterval: null,
      chartData: {
        times: [],
        price: [],
        tradeVolume: [],
      },
      chartSecondData: {
        times: [],
        price: [],
        tradeVolume: [],
      },
      timerId: null,
    };
  },
  mounted() {
    this.getCurrentPositions();
    this.getHistoricalPositions();
    this.startPriceDataFetch();
    document
      .getElementById("price-trend-chart")
      .addEventListener("wheel", this.handleMouseWheel);

    // 以下mounted()内容仅用作前端测试，项目运行时应注释

    // this.chartData.times = this.generateRandomHour();
    // this.chartData.price = this.generateStockData();
    // this.chartData.tradeVolume = this.generateTradeVolumeData();
    // this.drawChart();

    // let currentSecond = 0;
    // let accumulatedVolume = 0; // 累计交易量

    // this.timerId = setInterval(() => {
    //   // 生成新的价格
    //   let newPrice =
    //     Math.round(
    //       (this.chartData.price[this.chartData.price.length - 1] +
    //         Math.random() * 100 -
    //         50) *
    //         100
    //     ) / 100;
    //   if (newPrice < 10) {
    //     newPrice = 50;
    //   }

    //   // 生成新的交易量并累加
    //   let newVolume = Math.round(Math.random() * 20 * 100) / 100;
    //   accumulatedVolume += newVolume;

    //   // 每秒更新价格，每分钟更新时间和价格点
    //   if (currentSecond < 59) {
    //     // 替换当前分钟的最后一个价格
    //     this.chartData.price[this.chartData.price.length - 1] = newPrice;
    //     this.chartData.tradeVolume[this.chartData.tradeVolume.length - 1] =
    //       accumulatedVolume;
    //     currentSecond++;
    //   } else {
    //     // 一分钟结束，更新时间标签和价格点
    //     const newTime = this.getNextMinute(
    //       this.chartData.times[this.chartData.times.length - 1]
    //     );
    //     this.chartData.times.push(newTime);
    //     this.chartData.price.push(newPrice);
    //     this.chartData.tradeVolume.push(accumulatedVolume); // 添加累计交易量

    //     // 重置秒数和累计交易量
    //     currentSecond = 0;
    //     accumulatedVolume = 0;
    //   }

    //   // 更新图表
    //   this.updateChart();
    // }, 1000);
  },
  methods: {
    getNextMinute(currentTime) {
      const [hour, minute, second] = currentTime.split(":").map(Number);
      let nextMinute = minute + 1;
      let nextHour = hour;
      if (nextMinute === 60) {
        nextMinute = 0;
        nextHour = nextHour === 23 ? 0 : nextHour + 1;
      }
      return `${nextHour.toString().padStart(2, "0")}:${nextMinute
        .toString()
        .padStart(2, "0")}`;
    },
    logout() {
      this.$router.push("/Login");
    },
    handleMenu(index) {
      // 处理菜单项点击事件，你可以在这里进行路由跳转
      if (index === "1") {
        this.$router.push("/"); // 跳转到主页
      } else if (index === "2-1") {
        this.$router.push("/StockTrendChart"); // 跳转到当前委托页
      } else if (index === "2-2") {
        this.$router.push("/StockTrendChart"); // 跳转到历史委托页
      } else if (index === "3") {
        this.$router.push("/MarketQuotes"); // 跳转到行情页
      } else if (index === "4") {
        this.$router.push("/message-center"); // 跳转到消息中心页
      }
    },
    // 撤销订单接口
    async cancelOrder(orderId) {
      try {
        const response = await axios.post("http://localhost:5000/cancelOrder", {
          orderId,
        });
        if (response.data.success) {
          // 从 CurrentOrderData 中移除对应的订单
          this.CurrentOrderData = this.CurrentOrderData.filter(
            (order) => order.id !== orderId
          );
        } else {
          // 处理失败情况
          console.error("Cancel order failed:", response.data.message);
        }
      } catch (error) {
        console.error("Error during cancel operation:", error);
      }
    },
    // 删除订单接口
    async deleteOrder(orderId) {
      try {
        const response = await axios.post("http://localhost:5000/deleteOrder", {
          orderId,
        });
        if (response.data.success) {
          // 从 HistoricalOrderData 中移除对应的订单
          this.HistoricalOrderData = this.HistoricalOrderData.filter(
            (order) => order.id !== orderId
          );
        } else {
          // 处理失败情况
          console.error("Delete order failed:", response.data.message);
        }
      } catch (error) {
        console.error("Error during delete operation:", error);
      }
    },
    // 交易接口
    async transaction(event) {
      const elementId = event.target.id;
      if (elementId == "buyButton") {
        try {
          const payload = {
            price: this.buyPrice,
            quantity: this.buyQuantity,
            amount: this.BuyAmount,
          };
          const response = await axios.post(
            "http://localhost:5000/buy",
            payload
          );
          console.log("Buy response:", response.data);
        } catch (error) {
          console.error("Error during buy operation:", error);
        }
      } else {
        try {
          const payload = {
            price: this.sellPrice,
            quantity: this.sellQuantity,
            amount: this.SellAmount,
          };
          const response = await axios.post(
            "http://localhost:5000/sell",
            payload
          );
          console.log("Sell response:", response.data);
        } catch (error) {
          console.error("Error during sell operation:", error);
        }
      }
    },
    // 获取价格和交易量接口
    async fetchAndProcessData() {
      try {
        const response = await axios.get(
          "http://localhost:5000/getFuturesData"
        );
        const responseData = response.data;
        this.chartSecondData.times.push(responseData.time);
        this.chartSecondData.price.push(responseData.price);
        this.chartSecondData.tradeVolume.push(responseData.volume);
        this.updateChart();
      } catch (error) {
        console.error("Error fetching and processing price data:", error);
      }
    },
    updateChart() {
      if (!this.myChart) {
        this.drawChart();
      }

      let option = {
        xAxis: {
          type: "category",
          data: this.chartData.times,
          splitLine: {
            show: true,
            lineStyle: {
              color: "#1e2227",
            },
          },
        },
        yAxis: [
          {
            type: "value",
            name: "价格",
            position: "right", // 将Y轴移动到右侧
            axisLine: {
              lineStyle: {
                color: "#b7bdc6", // 设置坐标轴线的颜色为蓝色
              },
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: "#1e2227",
              },
            },
          },
        ],
        tooltip: {
          trigger: "axis",
        },
        series: [
          {
            name: "价格",
            type: "line",
            data: this.chartData.price,
            lineStyle: {
              color: "#f0b90b",
            },
            symbol: "none",
            itemStyle: {
              color: "#f0b90b", // 设置数据点的颜色
            },
            areaStyle: {
              color: {
                type: "linear",
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [
                  { offset: 0.5, color: "rgba(131, 105, 20, 1)" }, // 起始颜色
                  { offset: 1, color: "rgba(0, 0, 0, 0)" }, // 结束颜色
                ],
              },
            },
          },
          {
            name: "交易量",
            type: "bar",
            data: this.chartData.tradeVolume,
            yAxisIndex: 1, // 使用右侧的Y轴
            itemStyle: {
              color: (params) => {
                const dataIndex = params.dataIndex;
                // 访问Vue组件上下文，通过箭头函数
                const priceData = this.chartData.price;
                if (
                  dataIndex > 0 &&
                  priceData[dataIndex] > priceData[dataIndex - 1]
                ) {
                  return "#ff707e"; // 价格上涨，柱子设置为红色
                } else {
                  return "#32d993"; // 价格下跌，柱子设置为绿色
                }
              }, // 设置数据点的颜色
            },
          },
        ],
      };

      this.myChart.setOption(option);
    },

    formatTime(timeString) {
      const time = new Date(timeString);
      return `${time.getHours()}:${time
        .getMinutes()
        .toString()
        .padStart(2, "0")}`;
    },
    startPriceDataFetch() {
      this.fetchAndProcessData(); // 初始获取一次数据
      // this.priceUpdateInterval = setInterval(this.fetchAndProcessData, 1000); // 每秒钟更新一次

      let currentSecond = 0;
      let accumulatedVolume = 0; // 累计交易量

      this.timerId = setInterval(() => {
        // 获取新的价格
        this.fetchAndProcessData();

        let newPrice =
          this.chartSecondData.price[this.chartSecondData.price.length - 1];

        // 获取新的交易量并累加
        let newVolume =
          this.chartSecondData.tradeVolume[
            this.chartSecondData.tradeVolume.length - 1
          ];
        accumulatedVolume += newVolume;

        // 每秒更新价格，每分钟更新时间和价格点
        if (currentSecond < 59) {
          // 替换当前分钟的最后一个价格
          this.chartData.price[this.chartData.price.length - 1] = newPrice;
          this.chartData.tradeVolume[this.chartData.tradeVolume.length - 1] =
            accumulatedVolume;
          currentSecond++;
        } else {
          // 一分钟结束，更新时间标签和价格点
          const newTime = this.getNextMinute(
            this.chartSecondData.times[this.chartSecondData.times.length - 1]
          );
          this.chartData.times.push(newTime);
          this.chartData.price.push(newPrice);
          this.chartData.tradeVolume.push(accumulatedVolume); // 添加累计交易量

          // 重置秒数和累计交易量
          currentSecond = 0;
          accumulatedVolume = 0;
          this.chartSecondData.times = [];
          this.chartSecondData.price = [];
          this.chartSecondData.tradeVolume = [];
        }

        // 更新图表
        this.updateChart();
      }, 1000);
    },
    async getCurrentPositions() {
      try {
        const response = await axios.get(
          "http://localhost:5000/getCurrentPositions"
        );
        const responseData = response.data;
        const CurrentPositionData = responseData.map((data) => ({
          type: data.type,
          Date: data.Date,
          Attribute: data.Attribute,
          Price: data.Price,
          Status: data.Status,
          Quantity: data.Quantity,
          // 其他需要的字段
        }));
        this.CurrentPositionData = CurrentPositionData;
      } catch (error) {
        console.error("Error getting current positions:", error);
      }
    },

    async getHistoricalPositions() {
      try {
        const response = await axios.get(
          "http://localhost:5000/getHistoricalPositions"
        );
        const responseData = response.data;
        const HistoricalPositionData = responseData.map((data) => ({
          type: data.type,
          Date: data.Date,
          Attribute: data.Attribute,
          Price: data.Price,
          Status: data.Status,
          Quantity: data.Quantity,
          // 其他需要的字段
        }));
        this.HistoricalPositionData = HistoricalPositionData;
      } catch (error) {
        console.error("Error getting historical positions:", error);
      }
    },

    handleSelect(key) {
      this.activeIndex = key;
    },
    tableRowClassName() {
      return "background: #181a20";
    },
    handleMouseWheel(event) {
      let myChart = this.myChart;
      let option = myChart.getOption();
      option.dataZoom = [
        {
          type: "slider",
          start: 0,
          end: 100,
        },
        {
          type: "inside",
          start: 0,
          end: 100,
        },
      ];

      // 计算横向范围的变化量
      const zoomDelta = event.deltaY > 0 ? 0.1 : -0.1; // 根据滚轮方向调整变化量

      // 更新横向范围
      option.dataZoom[0].start += zoomDelta;
      option.dataZoom[0].end -= zoomDelta;

      // 限制横向范围在合理范围内（0到100之间）
      option.dataZoom[0].start = Math.min(
        100,
        Math.max(0, option.dataZoom[0].start)
      );
      option.dataZoom[0].end = Math.min(
        100,
        Math.max(0, option.dataZoom[0].end)
      );

      this.myChart.setOption(option);
    },
    //随机产生的时间数据，仅供前端测试所用
    generateRandomHour() {
      const times = [];
      for (let hour = 0; hour < 24; hour++) {
        for (let minute = 0; minute < 60; minute++) {
          const hourStr = hour < 10 ? `0${hour}` : `${hour}`;
          const minuteStr = minute < 10 ? `0${minute}` : `${minute}`;
          times.push(`${hourStr}:${minuteStr}`);
        }
      }
      return times;
    },
    //随机产生的期货价格数据，仅供前端测试所用
    generateStockData() {
      const data = [];
      let price = 800;
      for (let i = 0; i < 24 * 60; i++) {
        const change = Math.random() * 100 - 50;
        price += change;

        // 确保价格不低于零
        if (price < 10) {
          price = 50;
        }

        data.push(Math.round(price * 100) / 100);
      }
      return data;
    },
    //随机产生的交易量数据，仅供前端测试所用
    generateTradeVolumeData() {
      const data = [];
      for (let i = 0; i < 24 * 60; i++) {
        const volume = Math.random() * 1000; // 随机生成交易量，你可以根据需要调整范围
        data.push(Math.round(volume * 100) / 100);
      }
      return data;
    },
    drawChart() {
      let myChart = this.$echarts.init(
        document.getElementById("price-trend-chart") //
      );
      this.myChart = myChart;

      // let price = this.chartData.price;

      // console.log(this.times);

      let option = {
        title: {},
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["价格", "交易量"],
          show: true,
          textStyle: {
            color: "#b7bdc6", // 设置图例项文本的颜色
          },
        },
        xAxis: {
          type: "category",
          data: this.chartData.times,
          axisLine: {
            lineStyle: {
              color: "#b7bdc6", // 设置坐标轴线的颜色
            },
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#1e2227",
            },
          },
        },
        yAxis: [
          {
            type: "value",
            name: "价格",
            position: "right", // 将Y轴移动到右侧
            axisLine: {
              lineStyle: { color: "#b7bdc6" },
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: "#1e2227",
              },
            },
          },
          {
            type: "value",
            name: "交易量",
            position: "left", // 将交易量Y轴显示在左侧
            axisLine: {
              lineStyle: { color: "#b7bdc6" }, // 设置交易量Y轴的颜色
            },
            splitLine: {
              show: false, // 可根据需要决定是否显示网格线
            },
            max: 5000,
          },
        ],
        series: [
          {
            name: "价格",
            type: "line",
            data: this.chartData.price,
            lineStyle: {
              color: "#f0b90b",
            },
            symbol: "none",
            itemStyle: {
              color: "#f0b90b", // 设置数据点的颜色
            },
            areaStyle: {
              color: {
                type: "linear",
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [
                  { offset: 0.5, color: "rgba(131, 105, 20, 1)" }, // 起始颜色
                  { offset: 1, color: "rgba(0, 0, 0, 0)" }, // 结束颜色
                ],
              },
            },
          },
          {
            name: "交易量",
            type: "bar",
            data: this.chartData.tradeVolume, // 新编写的交易量数据生成函数
            symbol: "none",
            itemStyle: {
              color: (params) => {
                const dataIndex = params.dataIndex;
                // 访问Vue组件上下文，通过箭头函数
                const priceData = this.chartData.price;
                if (
                  dataIndex > 0 &&
                  priceData[dataIndex] > priceData[dataIndex - 1]
                ) {
                  return "#ff707e"; // 价格上涨，柱子设置为红色
                } else {
                  return "#32d993"; // 价格下跌，柱子设置为绿色
                }
              }, // 设置数据点的颜色
            },
            yAxisIndex: 1, // 使用右侧的Y轴
          },
        ],
        dataZoom: [
          {
            type: "slider",
            show: true,
            start: 80, // 设置默认显示范围的起始位置，0 表示从第一个数据开始
            end: 100, // 设置默认显示范围的结束位置，50 表示显示前50%的数据
            backgroundColor: "#181a20", // 修改背景颜色
            // dataBackgroundColor: '#fff', // 修改数据背景颜色
            handleColor: "#eaecef", // 修改手柄颜色
            handleSize: 50, // 滑动条控制块的大小
            dataBackground: {
              //数据阴影的样式。
              lineStyle: { color: "#f0b90b" }, //阴影的线条样式
              areaStyle: { color: "rgba(131, 105, 20, 1)" }, //阴影的填充样式
            },
            selectedDataBackground: {
              //选中部分数据阴影的样式
              lineStyle: { color: "#f0b90b" }, //选中部分阴影的线条样式
              areaStyle: { color: "rgba(131, 105, 20, 1)" }, //选中部分阴影的填充样式
            },
            fillerColor: "rgba(131, 105, 20, 0.3)",
            moveHandleStyle: {
              color: "#eaecef",
            }, //移动手柄的样式配置
          },
        ],
      };
      console.log(option);
      myChart.setOption(option);
    },
    formatTooltip(val) {
      return val + "%";
    },
  },
  beforeDestroy() {
    if (this.priceUpdateInterval) {
      clearInterval(this.priceUpdateInterval); // 清除定时器
    }
  },
  computed: {
    BuyAmount() {
      return this.buyPrice * this.buyQuantity;
    },
    SellAmount() {
      return this.sellPrice * this.sellQuantity;
    },
  },
};
</script>

<style scoped>
.common-layout {
  background-color: #2767c6;
  border-radius: 12px 12px 12px 12px;
}

.header {
  background-color: #181a20;
  color: #eaecef;
  height: 8vh;
  text-align: center;
  border-radius: 12px 12px 0 0;
  display: flex;
}

.main {
  background-color: #181a20;
  padding: 10px;
  height: 60vh;
  display: flex;
  justify-content: center;
  flex-direction: column;
  position: relative;
}
.main-content {
  height: 100%;
  box-sizing: border-box; /* 让padding不会增加元素的总宽度 */
  background-color: #181a20;
  border-top: 2px solid #2b3139;
  border-bottom: 2px solid #2b3139;
}
.footer {
  background-color: #181a20;
  border-radius: 0 0 12px 12px;
}
.content-container {
  flex: 1;
}
.el-menu {
  border: 0 !important;
}
.logo-image {
  width: 45px;
  height: auto;
  margin-top: 8px;
  margin-left: 15px;
}
.posi {
  font-size: 20px;
  font-weight: 600;
  color: #eaecef;
}
.posi-table {
  width: 100%;
  background-color: transparent !important;
}
.posi-table::v-deep .el-table th.el-table__cell,
::v-deep .el-table th,
::v-deep .el-table tr {
  color: #efebea;
  background-color: transparent !important;
  border-bottom-color: #2b3139 !important;
}
::v-deep .posi-table td,
::v-deep .posi-table th.is-leaf {
  border-bottom: 1px solid #2b3139;
}
.el-table--border::after,
.el-table--group::after,
.el-table::before {
  background-color: #2b3139;
}
/* .el-table tbody tr:hover > td {
  background-color: red;
} */

.el-table--enable-row-hover .el-table__body tr:hover > td {
  background-color: red !important;
}
.assets-container {
  display: flex;
  flex-direction: column; /* 设置垂直排列 */
}
.assets {
  font-size: 20px;
  font-weight: 600;
}
.assets-value {
  font-size: 30px;
  font-weight: 600;
}
.assets-values {
  margin-top: 1.6%;
  display: flex;
}
.assets-currency {
  font-size: 13px;
  font-weight: 600;
  margin-top: 2.5%;
  margin-left: 1%;
}
.profit-and-loss {
  display: flex;
}

.profit-and-loss-title {
  margin-top: 1.6%;
  font-size: 16px;
  font-weight: 200;
}

.profit-and-loss-value {
  margin-top: 1.6%;
  font-size: 15px;
  font-weight: 600;
  margin-left: 1%;
  color: #f6465d;
}
.custom-avatar {
  border-radius: 10px;
}
.chart-content {
  width: 100%;
  height: 100%;
}
.deal {
  width: 100%;
  height: 400px;
  background-color: #181a20;
  border-bottom: 2px solid #2b3139;
  display: flex;
  justify-content: space-between;
}
.trend {
  width: 50%;
  height: 99%;
  background-color: #181a20;
  display: flex;
  justify-content: center;
  flex-direction: column;
  transform: translateY(-0%);
  align-items: center;
}
.input {
  width: 70%;
  margin-bottom: 25px;
}
/deep/.el-input__inner {
  height: 52px;
  background: #2a2d35;
  font-size: 14px;
  font-weight: 600;
  font-family: PingFangSC-Regular, PingFang SC;
  color: #eaecef;
  border: 1px solid #2b3139;
}
/deep/.el-input__inner:hover {
  border-color: #f0b90b;
}
.block {
  width: 70%;
}
/deep/.el-slider__bar {
  background-color: #b7bdc6;
}
/deep/.el-slider__runway {
  background-color: #474d57;
}
/deep/.el-slider__button {
  border-color: #b7bdc6;
}
.submit-button {
  height: 42px;
  display: flex;
  justify-content: center;
  width: 100%;
}
#buyButton.el-button--primary {
  background: #0ecb81;
  border-color: #0ecb81;
  color: #e8ffff;
  font-weight: 600;
  border-radius: 8px;
  width: 70%;
}
#buyButton.el-button--primary:hover {
  background: #32d993;
  border-color: #32d993;
  color: #e8ffff;
  border-radius: 8px;
}
#sellButton.el-button--primary {
  background: #f6465d;
  border-color: #f6465d;
  color: #e8ffff;
  font-weight: 600;
  border-radius: 8px;
  width: 70%;
}
#sellButton.el-button--primary:hover {
  background: #ff707e;
  border-color: #ff707e;
  color: #e8ffff;
  border-radius: 8px;
}
.order-type {
  height: 10px;
}
.el-button.delete {
  background-color: #181a20;
  color: #848e9c;
  border: none;
}
.el-button.revoke {
  background-color: #181a20;
  color: #848e9c;
  border: none;
}
.logout-button {
  margin-top: 15px;
  height: 42px;
  display: flex;
  justify-content: center;
  position: absolute;
  right: 0; /* 使用 right 属性将元素右对齐 */
  margin-right: 30px;
}
#logoutButton.el-button--primary {
  background: #fcd535;
  border-color: #fcd535;
  color: #181a20;
  border-radius: 4px;
}
#logoutButton.el-button--primary:hover {
  background: #e5c333;
  border-color: #e5c333;
  color: #181a20;
  border-radius: 4px;
}
</style>