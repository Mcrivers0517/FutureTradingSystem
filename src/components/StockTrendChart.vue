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
        <el-footer class="footer" height="95vh">
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
                  <el-button type="primary" native-type="submit" id="buyButton"
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
                    width="80px"
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
                    default-active="1"
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
                    :data="PositionData"
                    class="posi-table"
                    :cell-style="tableRowClassName"
                  >
                    <el-table-column prop="type" label="品种">
                    </el-table-column>
                    <el-table-column prop="Date" label="日期">
                    </el-table-column>
                    <el-table-column prop="BuyOrSell" label="买/卖">
                    </el-table-column>
                    <el-table-column prop="Price" label="价格">
                    </el-table-column>
                    <el-table-column prop="Quantity" label="数量">
                    </el-table-column>
                    <el-table-column prop="Amount" label="金额">
                    </el-table-column>
                    <el-table-column prop="Delete" label="撤单" :width="60">
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
// import axios from "axios";
export default {
  data() {
    return {
      PositionData: [
        {
          type: "商品A",
          Date: "2023-01-05",
          BuyOrSell: "买入",
          Price: 100.5,
          Quantity: 10,
          Amount: 1005.0,
          Delete: "撤单",
        },
        {
          type: "商品B",
          Date: "2023-01-06",
          BuyOrSell: "卖出",
          Price: 98.75,
          Quantity: 8,
          Amount: 790.0,
          Delete: "撤单",
        },
        {
          type: "商品C",
          Date: "2023-01-07",
          BuyOrSell: "买入",
          Price: 55.25,
          Quantity: 15,
          Amount: 828.75,
          Delete: "撤单",
        },
      ],
      myChart: {},
      buyPrice: null,
      buyQuantity: null,
      sellPrice: null,
      sellQuantity: null,
      buyAmount: null,
      sellAmount: null,
    };
  },
  mounted() {
    this.drawChart();
    document
      .getElementById("price-trend-chart")
      .addEventListener("wheel", this.handleMouseWheel);
  },
  methods: {
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
    //随机产生的时间数据，仅作前端测试所用
    drawChart() {
      let myChart = this.$echarts.init(
        document.getElementById("price-trend-chart")
      );
      this.myChart = myChart;
      function generateRandomHour() {
        const times = [];
        for (let hour = 0; hour < 24; hour++) {
          for (let minute = 0; minute < 60; minute++) {
            const hourStr = hour < 10 ? `0${hour}` : `${hour}`;
            const minuteStr = minute < 10 ? `0${minute}` : `${minute}`;
            times.push(`${hourStr}:${minuteStr}`);
          }
        }
        return times;
      }
      //随机产生的期货价格数据，仅作前端测试所用
      function generateStockData() {
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
      }
      let option = {
        title: {},
        tooltip: {
          trigger: "axis",
        },
        legend: {
          data: ["价格"],
          show: false,
        },
        xAxis: {
          type: "category",
          data: generateRandomHour(),
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
              lineStyle: {},
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: "#1e2227",
              },
            },
          },
        ],
        series: [
          {
            name: "价格",
            type: "line",
            data: generateStockData(),
          },
        ],
      };
      myChart.setOption(option);
    },
    formatTooltip(val) {
      return val + "%";
    },
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
</style>