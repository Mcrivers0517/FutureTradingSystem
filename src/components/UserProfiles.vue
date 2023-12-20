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
          <div class="user-avatar">
            <div class="block">
              <el-avatar shape="square" :size="65" :src="defaultAvatar"></el-avatar>
            </div>
          </div>
          <div class="user-info">
            <div class="user-name">
              <span>Mcrivers</span>
            </div>
            <div class="user-id">
              <span>202103150618</span>
            </div>
          </div>
          <div class="main-content" id="main-content-1">
            <div class="assets-container">
              <span class="assets">总资产估值</span>
              <div class="assets-values">
                <span class="assets-value">429.1181194</span>
                <span class="assets-currency">RMB</span>
              </div>
              <div class="profit-and-loss">
                <span class="profit-and-loss-title">今日盈亏</span>
                <span class="profit-and-loss-value">+4.67%(1.55%)</span>
              </div>
            </div>
          </div>
          <div class="main-content" id="main-content-2">
            <div>
              <span class="posi">持仓情况</span>
              <el-table
                :data="tableData"
                class="posi-table"
                :cell-style="tableRowClassName"
              >
                <el-table-column prop="type" label="品种" :width="80"> </el-table-column>
                <el-table-column prop="buy/sell" label="买卖">
                </el-table-column>
                <el-table-column prop="Position" label="持仓">
                </el-table-column>
                <el-table-column prop="Floating Profit/Loss" label="浮动盈亏">
                </el-table-column>
                <el-table-column prop="Profit/Loss Ratio" label="盈亏比率">
                </el-table-column>
                <el-table-column prop="Average Opening Price" label="开仓均价">
                </el-table-column>
                <el-table-column prop="Current Price" label="现价">
                </el-table-column>
                <el-table-column
                  prop="Latest Transaction Time"
                  label="最新成交时间"
                  :width="175"
                >
                </el-table-column>
              </el-table>
            </div>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [
        {
          type: "A",
          "buy/sell": "Buy",
          Position: 100,
          "Floating Profit/Loss": 150.25,
          "Profit/Loss Ratio": 0.075,
          "Average Opening Price": 50.75,
          "Current Price": 52.0,
          "Latest Transaction Time": "2023-12-19 14:30:45",
        },
        {
          type: "B",
          "buy/sell": "Sell",
          Position: 20000,
          "Floating Profit/Loss": -500.8,
          "Profit/Loss Ratio": -0.025,
          "Average Opening Price": 1.1205,
          "Current Price": 1.118,
          "Latest Transaction Time": "2023-12-19 15:45:20",
        },
        {
          type: "C",
          "buy/sell": "Buy",
          Position: 500,
          "Floating Profit/Loss": 75.4,
          "Profit/Loss Ratio": 0.03,
          "Average Opening Price": 150.8,
          "Current Price": 152.0,
          "Latest Transaction Time": "2023-12-19 16:15:10",
        },
      ],
      defaultAvatar:"../assets/default-avatar.png",
    };
  },
  methods: {
    tableRowClassName() {
      return "background: #181a20";
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

.aside {
  background-color: #181a20;
  color: #eaecef;
  height: 89vh;
  border-radius: 0 0 0 12px;
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
#main-content-1 {
  /* position: relative; */
  border: 1px solid #2b3139;
  padding: 20px; /* 增加边框与内容之间的间距 */
  position: absolute;
  left: 50%;
  width: 50%;
  height: 20%;
  top: 32%;
  border-radius: 12px;
  color: #eaecef;
  transform: translate(-50%, -50%);
}
#main-content-2 {
  /* position: relative; */
  border: 1px solid #2b3139;
  padding: 20px; /* 增加边框与内容之间的间距 */
  position: absolute;
  width: 50%;
  height: 40%;
  top: 72%;
  border-radius: 12px;
  color: #eaecef;
  left: 50%;
  transform: translate(-50%, -50%);
}
.user-avatar {
  position: absolute;
  top: 5%;
  left: 25%;
}
.user-info {
  position: absolute;
  top: 7%;
  left: 31%;
  color: #eaecef;
  display: flex;
  align-items: center;
  flex-direction: column;
}
.user-id {
  margin-top: 8px;
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
.el-table tbody tr:hover > td {
  background-color: red;
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
</style>