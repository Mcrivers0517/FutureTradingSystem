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
            <div class="data-display">
              <span
                style="
                  color: #f6455d;
                  font-size: 18px;
                  position: relative;
                  left: 30px;
                  top: -2px;
                "
                >{{
                  this.chartSecondData.price[
                    this.chartSecondData.price.length - 2
                  ]
                }}.00</span
              >
              <span
                style="
                  color: #848e9c;
                  position: relative;
                  left: 75px;
                  top: -14px;
                "
                >24h涨跌
              </span>
              <span
                :style="{
                  position: 'relative',
                  left: '30px',
                  top: '5px',
                  color:
                    this.dailyChange > 0
                      ? '#f6455d'
                      : this.dailyChange === 0
                      ? '#e8ffff'
                      : '#0ecb81',
                }"
              >
                {{ this.dailyChange.toFixed(2) }}
                {{ (this.dailyChangeRatio * 100).toFixed(2) }}%
              </span>
              <span
                style="
                  color: #848e9c;
                  position: relative;
                  left: 75px;
                  top: -14px;
                "
                >今日开盘价
              </span>
              <span
                style="color: #eaecef; position: relative; left: 18px; top: 5px"
                >{{ this.dailyOpenPrice.toFixed(2) }}
              </span>
              <span
                style="
                  color: #848e9c;
                  position: relative;
                  left: 75px;
                  top: -14px;
                "
                >24h最高价
              </span>
              <span
                style="color: #eaecef; position: relative; left: 18px; top: 5px"
                >{{ this.dailyHighestPrice.toFixed(2) }}
              </span>
              <span
                style="
                  color: #848e9c;
                  position: relative;
                  left: 75px;
                  top: -14px;
                "
                >24h最低价
              </span>
              <span
                style="color: #eaecef; position: relative; left: 18px; top: 5px"
                >{{ this.dailyLowestPrice.toFixed(2) }}
              </span>
            </div>
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
                    class="slider"
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
                    @click="buy"
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
                    class="slider"
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
                    @click="sell"
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
                    :data="CurrentDelegateData"
                    class="posi-table"
                    :cell-style="tableRowClassName"
                    height="430"
                    v-if="activeIndex === '1'"
                  >
                    <el-table-column prop="delegateId" label="流水号">
                    </el-table-column>
                    <el-table-column prop="delegateTime" label="日期">
                    </el-table-column>
                    <el-table-column prop="attribute" label="属性">
                    </el-table-column>
                    <el-table-column prop="delegatePrice" label="价格">
                    </el-table-column>
                    <el-table-column prop="status" label="状态">
                    </el-table-column>
                    <el-table-column prop="amount" label="数量">
                    </el-table-column>
                    <el-table-column label="金额">
                      <template slot-scope="scope">
                        {{ scope.row.delegatePrice * scope.row.amount }}
                      </template>
                    </el-table-column>
                    <el-table-column label="撤单" :width="60">
                      <template slot-scope="scope">
                        <el-button
                          class="revoke"
                          @click="cancelDelegate(scope.row)"
                          icon="el-icon-delete-solid"
                        ></el-button>
                      </template>
                    </el-table-column>
                  </el-table>
                  <el-table
                    :data="HistoricalDelegateData"
                    class="posi-table"
                    :cell-style="tableRowClassName"
                    height="430"
                    v-if="activeIndex === '2'"
                    ><el-table-column prop="delegateId" label="流水号">
                    </el-table-column>
                    <el-table-column prop="delegateTime" label="日期">
                    </el-table-column>
                    <el-table-column prop="attribute" label="属性">
                    </el-table-column>
                    <el-table-column prop="delegatePrice" label="价格">
                    </el-table-column>
                    <el-table-column prop="status" label="状态">
                    </el-table-column>
                    <el-table-column prop="amount" label="数量">
                    </el-table-column>
                    <el-table-column label="金额" :width="120">
                      <template slot-scope="scope">
                        {{ scope.row.delegatePrice * scope.row.amount }}
                      </template>
                    </el-table-column>
                  </el-table>
                </el-main>
              </el-container></el-main
            >
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
      CurrentDelegateData: [],
      HistoricalDelegateData: [],
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
      dailyOpenPrice: 0,
      dailyHighestPrice: 0,
      dailyLowestPrice: 0,
      dailyChange: 0,
      dailyChangeRatio: 0,
      dateTimeString: "",
      timerId: null,
      accumulatedVolume: 0,
    };
  },
  mounted() {
    // console.log(
    //   "times",
    //   this.$store.state.chartData[this.$store.state.activeRowNumber].times
    //     .length
    // );
    this.startPriceDataFetch();
    document
      .getElementById("price-trend-chart")
      .addEventListener("wheel", this.handleMouseWheel);
  },
  created() {
    if (
      this.$store.state.chartData[this.$store.state.activeRowNumber].times
        .length == 0
    ) {
      this.$store.state.chartData[this.$store.state.activeRowNumber].times =
        this.generateRandomHour();
    }
    if (
      this.$store.state.chartData[this.$store.state.activeRowNumber].price
        .length == 0
    ) {
      this.$store.state.chartData[this.$store.state.activeRowNumber].price =
        this.generateStockData();
    }
    if (
      this.$store.state.chartData[this.$store.state.activeRowNumber].tradeVolume
        .length == 0
    ) {
      this.$store.state.chartData[
        this.$store.state.activeRowNumber
      ].tradeVolume = this.generateTradeVolumeData();
    }
    if (
      this.$store.state.tempVolume[this.$store.state.activeRowNumber] == null
    ) {
      this.accumulatedVolume = 0;
    } else {
      this.accumulatedVolume =
        this.$store.state.tempVolume[this.$store.state.activeRowNumber];
    }
    this.getCurrentDelegate();
    this.getHistoricalDelegate();
    // console.log("accumulatedVolume", this.accumulatedVolume);
    // this.getCurrentOrders();
    // this.getHistoricalOrders();
    // this.chartData.price = this.generateStockData()
  },
  methods: {
    getNextMinute(currentTime) {
      const date = new Date(currentTime);
      // console.log(currentTime);
      const hour = date.getHours();
      const minute = date.getMinutes();

      let nextMinute = minute + 1;
      let nextHour = hour;

      if (nextMinute === 60) {
        nextMinute = 0;
        nextHour = nextHour === 23 ? 0 : nextHour + 1;
      }

      return `${String(nextHour).padStart(2, "0")}:${String(
        nextMinute
      ).padStart(2, "0")}`;
    },
    logout() {
      this.$store.state.tempVolume[this.$store.state.activeRowNumber] =
        this.accumulatedVolume;
      clearInterval(this.timerId);
      this.$router.push("/Login");
    },
    handleMenu(index) {
      // 处理菜单项点击事件，在这里进行路由跳转
      if (index === "1") {
        this.$store.state.tempVolume[this.$store.state.activeRowNumber] =
          this.accumulatedVolume;
        clearInterval(this.timerId);
        this.$router.push("/"); // 跳转到主页
      } else if (index === "2-1") {
        this.$store.state.tempVolume[this.$store.state.activeRowNumber] =
          this.accumulatedVolume;
        clearInterval(this.timerId);
        this.$router.push("/CurrentDelegate"); // 跳转到当前委托页
      } else if (index === "2-2") {
        this.$store.state.tempVolume[this.$store.state.activeRowNumber] =
          this.accumulatedVolume;
        clearInterval(this.timerId);
        this.$router.push("/HistoricalDelegate"); // 跳转到历史委托页
      } else if (index === "3") {
        this.$store.state.tempVolume[this.$store.state.activeRowNumber] =
          this.accumulatedVolume;
        clearInterval(this.timerId);
        this.$router.push("/MarketQuotes"); // 跳转到行情页
      } else if (index === "4") {
        this.$store.state.tempVolume[this.$store.state.activeRowNumber] =
          this.accumulatedVolume;
        clearInterval(this.timerId);
        this.$router.push("/message-center"); // 跳转到消息中心页
      }
    },
    // 撤销订单接口
    async cancelDelegate(row) {
      console.log(row);
      try {
        const response = await axios.post(
          "http://localhost:5000/cancelDelegate",
          {
            delegateId: row.delegateId,
          }
        );
        console.log(response);
        if (response.data.result == "撤销订单成功") {
          console.log(111111);
          alert(response.data.result);
          // 从 CurrentDelegateData 中移除对应的订单
          this.CurrentDelegateData = this.CurrentDelegateData.filter(
            (delegate) => delegate.delegateId !== row.delegateId
          );
        } else{
          alert(response.data.result);
        }
      } catch (error) {
        console.error("Error during cancel operation:", error);
      }
    },
    // 交易接口
    async buy() {
      try {
        const payload = {
          dateTimeString: this.dateTimeString,
          futureId: this.$store.state.activeRowNumber,
          userId: this.$store.state.activeUserId,
          amount: this.buyQuantity,
          delegatePrice: this.buyPrice,
          attribute: "buy",
        };

        console.log(this.dateTimeString);

        const response = await axios.post(
          "http://localhost:5000/createDelegate",
          payload
        );

        console.log(response.data);

        if (response.data.result == false) {
          alert("保证金不足订单金额的10%，委托订单创建失败");
        } else {
          alert("订单创建成功");
        }
      } catch (error) {
        console.error("Error during buy operation:", error);
      }
    },
    async sell() {
      try {
        const payload = {
          dateTimeString: this.dateTimeString,
          futureId: this.$store.state.activeRowNumber,
          userId: this.$store.state.activeUserId,
          amount: this.sellQuantity,
          delegatePrice: this.sellPrice,
          attribute: "sell",
        };

        console.log(this.dateTimeString);

        const response = await axios.post(
          "http://localhost:5000/createDelegate",
          payload
        );

        console.log(response.data);

        if (response.data.result == false) {
          alert("保证金不足订单金额的10%，委托订单创建失败");
        } else {
          alert("订单创建成功");
        }
      } catch (error) {
        console.error("Error during sell operation:", error);
      }
    },
    // 获取价格和交易量接口
    async fetchAndProcessData() {
      try {
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
        console.log("dateTimeString", this.dateTimeString);

        // const response = await axios.post(
        //   "http://localhost:5000/getFuturesData",
        //   {
        //     dateTimeString,
        //   }
        // );
        // const responseData = response.data;
        // this.$store.state.chartData[this.$store.state.activeRowNumber].times.push(dateTimeString);
        // this.$store.state.chartData[this.$store.state.activeRowNumber].price.push(responseData[0].price);
        // this.$store.state.chartData[this.$store.state.activeRowNumber].tradeVolume.push(responseData[0].volume);
        // this.dailyOpenPrice = responseData[0].dailyOpenPrice;
        // this.dailyHighestPrice = responseData[0].dailyHighestPrice;
        // this.dailyLowestPrice = responseData[0].dailyLowestPrice;
        // this.dailyChange = responseData[0].dailyChange;
        // this.dailyChangeRatio = responseData[0].dailyChangeRatio;
        // console.log("responseData:", responseData);
        // console.log("chartSecondData", this.chartSecondData);
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
          data: this.$store.state.chartData[this.$store.state.activeRowNumber]
            .times,
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
            min:
              Math.min(
                ...this.$store.state.chartData[
                  this.$store.state.activeRowNumber
                ].price
              ) - 100,
            // min: 4850,
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
            max:
              Math.max(
                ...this.$store.state.chartData[
                  this.$store.state.activeRowNumber
                ].tradeVolume
              ) * 5,
          },
        ],
        tooltip: {
          trigger: "axis",
        },
        series: [
          {
            name: "价格",
            type: "line",
            data: this.$store.state.chartData[this.$store.state.activeRowNumber]
              .price,
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
            data: this.$store.state.chartData[this.$store.state.activeRowNumber]
              .tradeVolume,
            yAxisIndex: 1, // 使用右侧的Y轴
            itemStyle: {
              color: (params) => {
                const dataIndex = params.dataIndex;
                // 访问Vue组件上下文，通过箭头函数
                const priceData =
                  this.$store.state.chartData[this.$store.state.activeRowNumber]
                    .price;
                if (
                  dataIndex > 0 &&
                  priceData[dataIndex] > priceData[dataIndex - 1]
                ) {
                  return "#f6465d"; // 价格上涨，柱子设置为红色
                } else {
                  return "#0ecb81"; // 价格下跌，柱子设置为绿色
                }
              }, // 设置数据点的颜色
            },
          },
        ],
      };

      // 以下是为了实现动态改变图表的纵坐标最大值，使得图像始终显示在合理的位置
      const old_option = this.myChart.getOption();
      const dataZoomInfo = old_option.dataZoom[0]; // 假设第一个 dataZoom 组件用于交易量数据

      // // 获取当前显示范围的起始位置和结束位置
      const start = dataZoomInfo.start;
      const end = dataZoomInfo.end;

      // 获取当前显示范围的数据
      const allTradeVolumeData =
        this.$store.state.chartData[this.$store.state.activeRowNumber]
          .tradeVolume;
      const startTradeVolumeIndex = Math.floor(
        (start / 100) * allTradeVolumeData.length
      );
      const endTradeVolumeIndex = Math.floor(
        (end / 100) * allTradeVolumeData.length
      );
      const visibleTradeVolumeData = allTradeVolumeData.slice(
        startTradeVolumeIndex,
        endTradeVolumeIndex + 1
      );

      // 使得TradeVolume纵轴的最大值=显示的最大值*5
      const maxTradeVolume = parseInt(Math.max(...visibleTradeVolumeData) * 5);

      // 更新 yAxis 中关于交易量的配置
      option.yAxis[1].max = maxTradeVolume;

      // 获取当前显示范围的价格数据
      const allPriceData =
        this.$store.state.chartData[this.$store.state.activeRowNumber].price;
      const startPriceIndex = Math.floor((start / 100) * allPriceData.length);
      const endPriceIndex = Math.floor((end / 100) * allPriceData.length);
      const visiblePriceData = allPriceData.slice(
        startPriceIndex,
        endPriceIndex + 1
      );

      // 使得Price纵轴的最大值=显示的最大值+(显示的最大值-显示的最小值)*0.2，Price纵轴的最小值=显示的最小值-(显示的最大值-显示的最小值)*0.2
      const maxPrice =
        Math.max(...visiblePriceData) +
        (Math.max(...visiblePriceData) - Math.min(...visiblePriceData)) * 0.2;
      const minPrice =
        Math.min(...visiblePriceData) -
        (Math.max(...visiblePriceData) - Math.min(...visiblePriceData)) * 0.2;

      option.yAxis[0].max = maxPrice;
      option.yAxis[0].min = minPrice;

      // 更新图表
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
      this.timerId = setInterval(() => {
        let currentDate = new Date();
        let currentSecond = currentDate.getSeconds();

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

        this.chartSecondData.price =
          this.$store.state.chartSecondData[
            this.$store.state.activeRowNumber
          ].price;
        this.dailyOpenPrice =
          this.$store.state.dailyOpenPrice[this.$store.state.activeRowNumber];
        this.dailyHighestPrice =
          this.$store.state.dailyHighestPrice[
            this.$store.state.activeRowNumber
          ];
        this.dailyLowestPrice =
          this.$store.state.dailyLowestPrice[this.$store.state.activeRowNumber];
        this.dailyChange =
          this.$store.state.dailyChange[this.$store.state.activeRowNumber];
        this.dailyChangeRatio =
          this.$store.state.dailyChangeRatio[this.$store.state.activeRowNumber];

        // console.log("222", this.$store.state.chartData[this.$store.state.activeRowNumber].price);

        let newPrice =
          this.$store.state.chartSecondData[this.$store.state.activeRowNumber]
            .price[
            this.$store.state.chartSecondData[this.$store.state.activeRowNumber]
              .price.length - 1
          ];

        // console.log(
        //   "111",
        //   this.$store.state.dailyHighestPrice[this.$store.state.activeRowNumber]
        // );

        // 获取新的交易量并累加
        let newVolume =
          this.$store.state.chartSecondData[this.$store.state.activeRowNumber]
            .tradeVolume[
            this.$store.state.chartSecondData[this.$store.state.activeRowNumber]
              .tradeVolume.length - 1
          ];
        // console.log(
        //   this.$store.state.chartData[this.$store.state.activeRowNumber]
        //     .tradeVolume[
        //     this.$store.state.chartData[this.$store.state.activeRowNumber]
        //       .tradeVolume.length - 1
        //   ]
        // );

        this.accumulatedVolume += newVolume;

        // 每秒更新价格，每分钟更新时间和价格点
        if (currentSecond < 59) {
          // 替换当前分钟的最后一个价格
          this.$store.state.chartData[this.$store.state.activeRowNumber].price[
            this.$store.state.chartData[this.$store.state.activeRowNumber].price
              .length - 1
          ] = newPrice;
          this.$store.state.chartData[
            this.$store.state.activeRowNumber
          ].tradeVolume[
            this.$store.state.chartData[this.$store.state.activeRowNumber]
              .tradeVolume.length - 1
          ] = this.accumulatedVolume;
          // console.log(currentSecond);
          currentSecond++;
        } else {
          // 一分钟结束，更新时间标签和价格点
          // console.log(
          //   this.$store.state.chartSecondData[this.$store.state.activeRowNumber]
          //     .times[
          //     this.$store.state.chartSecondData[
          //       this.$store.state.activeRowNumber
          //     ].times.length - 1
          //   ]
          // );
          const newTime = this.getNextMinute(
            this.$store.state.chartSecondData[this.$store.state.activeRowNumber]
              .times[
              this.$store.state.chartSecondData[
                this.$store.state.activeRowNumber
              ].times.length - 1
            ]
          );
          this.$store.state.chartData[
            this.$store.state.activeRowNumber
          ].times.push(newTime);
          this.$store.state.chartData[
            this.$store.state.activeRowNumber
          ].price.push(newPrice);
          this.$store.state.chartData[
            this.$store.state.activeRowNumber
          ].tradeVolume.push(this.accumulatedVolume); // 添加累计交易量

          // 重置秒数和累计交易量
          currentSecond = 0;
          this.accumulatedVolume = 0;
          this.$store.state.chartSecondData[
            this.$store.state.activeRowNumber
          ].times = [];
          this.$store.state.chartSecondData[
            this.$store.state.activeRowNumber
          ].price = [];
          this.$store.state.chartSecondData[
            this.$store.state.activeRowNumber
          ].tradeVolume = [];
        }

        // 更新图表
        this.updateChart();
      }, 1000);
    },
    async getCurrentDelegate() {
      try {
        const response = await axios.post(
          "http://localhost:5000/getCurrentDelegate",
          {
            userId: this.$store.state.activeUserId,
            futureId: this.$store.state.activeRowNumber,
          }
        );
        const CurrentDelegateData = response.data.map((data) => ({
          delegateId: data.delegateId,
          delegateTime: data.delegateTime,
          attribute: data.attribute,
          delegatePrice: data.delegatePrice,
          status: data.status,
          amount: data.amount,
        }));
        this.CurrentDelegateData = CurrentDelegateData;
      } catch (error) {
        console.error("Error getting current positions:", error);
      }
    },

    async getHistoricalDelegate() {
      try {
        const response = await axios.post(
          "http://localhost:5000/getHistoricalDelegate",
          {
            userId: this.$store.state.activeUserId,
            futureId: this.$store.state.activeRowNumber,
          }
        );
        const HistoricalDelegateData = response.data.map((data) => ({
          delegateId: data.delegateId,
          delegateTime: data.delegateTime,
          attribute: data.attribute,
          delegatePrice: data.delegatePrice,
          status: data.status,
          amount: data.amount,
        }));
        this.HistoricalDelegateData = HistoricalDelegateData;
      } catch (error) {
        console.error("Error getting Historical positions:", error);
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
      const currentDate = new Date(); // Get the current date and time

      for (let minute = 0; minute < 60; minute++) {
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

    //随机产生的期货价格数据，仅供前端测试所用
    generateStockData() {
      const data = [];
      if (this.$store.state.activeRowNumber == 0) {
        // console.log(
        //   "----------------------------------------------------------------"
        // );
        let price = 4970;
        for (let i = 0; i < 1 * 60; i++) {
          const change = Math.random() * 100 - 50;
          price += change;

          // 确保价格在指定范围内波动
          if (price < 4920) {
            price = 4920;
          } else if (price > 5020) {
            price = 5020;
          }

          data.push(Math.round(price * 100) / 100);
        }
      } else if (this.$store.state.activeRowNumber == 1) {
        let price = 4450; // 将初始价格设为 4450，在指定范围内
        for (let i = 0; i < 60; i++) {
          // 60个数据点
          const change = Math.random() * 600 - 300; // 改变值在 -300 到 300 之间
          price += change;

          // 确保价格在指定范围内波动
          if (price < 4300) {
            price = 4300;
          } else if (price > 4600) {
            price = 4600;
          }

          data.push(Math.round(price * 100) / 100);
        }
      } else if (this.$store.state.activeRowNumber == 2) {
        let price = 14500; // 将初始价格设为 14500，在指定范围内
        for (let i = 0; i < 60; i++) {
          // 60个数据点
          const change = Math.random() * 6000 - 3000; // 改变值在 -3000 到 3000 之间
          price += change;

          // 确保价格在指定范围内波动
          if (price < 13000) {
            price = 13000;
          } else if (price > 16000) {
            price = 16000;
          }

          data.push(Math.round(price * 100) / 100);
        }
      }
      return data;
    },

    //随机产生的交易量数据，仅供前端测试所用
    generateTradeVolumeData() {
      const data = [];
      for (let i = 0; i < 1 * 60; i++) {
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
          data: this.$store.state.chartData[this.$store.state.activeRowNumber]
            .times,
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
            min:
              Math.min(
                ...this.$store.state.chartData[
                  this.$store.state.activeRowNumber
                ].price
              ) - 100,
            // min: 4850,
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
            max:
              Math.max(
                ...this.$store.state.chartData[
                  this.$store.state.activeRowNumber
                ].tradeVolume
              ) * 5,
          },
        ],
        series: [
          {
            name: "价格",
            type: "line",
            data: this.$store.state.chartData[this.$store.state.activeRowNumber]
              .price,
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
            data: this.$store.state.chartData[this.$store.state.activeRowNumber]
              .tradeVolume, // 新编写的交易量数据生成函数
            symbol: "none",
            itemStyle: {
              color: (params) => {
                const dataIndex = params.dataIndex;
                // 访问Vue组件上下文，通过箭头函数
                const priceData =
                  this.$store.state.chartData[this.$store.state.activeRowNumber]
                    .price;
                if (
                  dataIndex > 0 &&
                  priceData[dataIndex] > priceData[dataIndex - 1]
                ) {
                  return "#f6465d"; // 价格上涨，柱子设置为红色
                } else {
                  return "#0ecb81"; // 价格下跌，柱子设置为绿色
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
      // console.log(option);
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
  height: 70vh;
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
  display: flex;
  flex-direction: column;
  align-items: center;
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
  border: 1px solid #2b3139;
}
.input {
  width: 50%;
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
  width: 50%;
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
  width: 50%;
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
.data-display {
  height: 100px;
  width: 80%;
  background-color: #181a20;
  border: 2px solid #2b3139;
  display: flex;
  align-items: center;
}
.current-price {
  color: #f6455d;
  font-size: 18px;
  position: relative;
  left: 30px;
  top: -2px;
}
.slider {
  width: 70%;
  margin: 0 auto;
}
</style>

