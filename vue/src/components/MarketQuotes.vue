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
          :default-active="activeIndex2"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
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
          <el-table
            :data="MarketData"
            class="futures-table"
            :cell-style="tableRowClassName"
            :default-sort="{ prop: 'CurrentPrice', order: 'descending' }"
          >
            <el-table-column prop="type" label="品种" :width="80" sortable>
            </el-table-column>
            <el-table-column prop="BuyOrSell" label="买/卖" sortable>
            </el-table-column>
            <el-table-column prop="Position" label="持仓" sortable>
            </el-table-column>
            <el-table-column
              prop="FloatingProfitOrLoss"
              label="浮动盈亏"
              sortable
            >
            </el-table-column>
            <el-table-column prop="ProfitOrLossRatio" label="盈亏比率" sortable>
            </el-table-column>
            <el-table-column
              prop="AverageOpeningPrice"
              label="开仓均价"
              sortable
            >
            </el-table-column>
            <el-table-column prop="CurrentPrice" label="现价" sortable>
            </el-table-column>
            <el-table-column
              prop="LatestTransactionTime"
              label="最新成交时间"
              :width="175"
              sortable
            >
            </el-table-column>
          </el-table>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      MarketData: [
        {
          type: "商品A",
          BuyOrSell: "买入",
          Position: 100,
          FloatingProfitOrLoss: 50.25,
          ProfitOrLossRatio: "5%",
          AverageOpeningPrice: 98.75,
          CurrentPrice: 103.0,
          LatestTransactionTime: "2023-01-05 09:45:00",
        },
        {
          type: "商品B",
          BuyOrSell: "卖出",
          Position: 50,
          FloatingProfitOrLoss: -25.8,
          ProfitOrLossRatio: "-2%",
          AverageOpeningPrice: 105.5,
          CurrentPrice: 103.25,
          LatestTransactionTime: "2023-01-05 14:20:00",
        },
        {
          type: "商品C",
          BuyOrSell: "买入",
          Position: 75,
          FloatingProfitOrLoss: 30.1,
          ProfitOrLossRatio: "3.5%",
          AverageOpeningPrice: 55.0,
          CurrentPrice: 57.1,
          LatestTransactionTime: "2023-01-06 10:15:00",
        },
      ],
    };
  },
  methods: {
    handleSelect(index) {
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
    tableRowClassName() {
      return "background: #181a20; height: 80px";
    },
  },
};
</script>

<style scoped>
/* 你之前提供的样式 */
.common-layout {
  background-color: #181a20;
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
  height: 89vh;
  border-radius: 0 0 12px 12px;
  display: flex;
  justify-content: center;
  flex-direction: column;
  position: relative;
}
.el-menu {
  border: 0 !important;
}
.content-container {
  flex: 1;
}

.logo-image {
  width: 45px;
  height: auto;
  margin-top: 8px;
  margin-left: 15px;
}

.futures-table {
  width: 100%;
  background-color: transparent !important;
}
.futures-table::v-deep .el-table th.el-table__cell,
::v-deep .el-table th,
::v-deep .el-table tr {
  color: #efebea;
  background-color: transparent !important;
  border-bottom-color: #2b3139 !important;
}
::v-deep .futures-table td,
::v-deep .futures-table th.is-leaf {
  border-bottom: 1px solid #2b3139;
}
.el-table--border::after,
.el-table--group::after,
.el-table::before {
  background-color: #2b3139;
}
.el-table tbody tr:hover > td {
  background-color: red;
}
</style>