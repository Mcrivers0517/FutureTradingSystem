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
              <el-avatar
                shape="square"
                :size="65"
                :src="avatarUrl"
                class="custom-avatar"
              ></el-avatar>
            </div>
          </div>
          <div class="user-info">
            <div class="user-name">
              <span>{{username}}</span>
            </div>
            <div class="user-id">
              <span>{{userid}}</span>
            </div>
          </div>
          <div class="main-content" id="main-content-1">
            <div class="assets-container">
              <span class="assets">总资产估值</span>
              <div class="assets-values">
                <span class="assets-value">{{totalAssets}}</span>
                <span class="assets-currency">RMB</span>
              </div>
              <div class="profit-and-loss">
                <span class="profit-and-loss-title">今日盈亏</span>
                <span class="profit-and-loss-value">{{dailyProfitLoss}}</span>
              </div>
            </div>
          </div>
          <div class="main-content" id="main-content-2">
            <div>
              <span class="posi">持仓情况</span>
              <el-table
                :data="PositionData"
                class="posi-table"
                :cell-style="tableRowClassName"
              >
                <el-table-column prop="type" label="品种" :width="80">
                </el-table-column>
                <el-table-column prop="BuyOrSell" label="买卖">
                </el-table-column>
                <el-table-column prop="Position" label="持仓">
                </el-table-column>
                <el-table-column prop="FloatingProfitOrLoss" label="浮动盈亏">
                </el-table-column>
                <el-table-column prop="ProfitOrLossRatio" label="盈亏比率">
                </el-table-column>
                <el-table-column prop="AverageOpeningPrice" label="开仓均价">
                </el-table-column>
                <el-table-column prop="CurrentPrice" label="现价">
                </el-table-column>
                <el-table-column
                  prop="LatestTransactionTime"
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
import axios from "axios";
export default {
  data() {
    return {
      PositionData: [],
      avatarUrl: require("@/assets/default-avatar.png"),
      username: "defaultName",
      userid: "defaultId",
      totalAssets: "totalAssets",
      dailyProfitLoss: "dailyProfitLoss",
    };
  },
  created()
  {
    this.getPositions();
    this.getUserInfo();
  },
  methods: {
    tableRowClassName() {
      return "background: #181a20";
    },
    async getPositions() {
      try {
        const response = await axios.get("localhost:5000/getPositions");
        const responseData = response.data;
        const tableData = responseData.Position;
        const PositionData = tableData.map((data) => ({
          type: data.type, //期货品种
          BuyOrSell: data.BuyOrSell, //买/卖
          Position: data.Position, //持仓
          FloatingProfitOrLoss: data.FloatingProfitOrLoss, //浮动盈亏
          ProfitOrLossRatio: data.ProfitOrLossRatio, //盈亏比率
          AverageOpeningPrice: data.AverageOpeningPrice, //开仓均价
          CurrentPrice: data.CurrentPrice, //现价
          LatestTransactionTime: data.LatestTransactionTime, //最新成交时间
        }));
        this.PositionData = PositionData;
      } catch (error) {
        console.error("Data Acquisition Failure:", error);
      }
    },
    async getUserInfo() {
      try {
        const response = await axios.get("localhost:5000/getUserInfo");
        const responseData = response.data;
        this.avatarUrl = responseData.avatarUrl;//用户头像url
        this.username = responseData.username;//用户名
        this.userid = responseData.userid;//用户id
        this.totalAssets = responseData.totalAssets;//用户总资产
        this.dailyProfitLoss = response.dailyProfitLoss;//今日盈亏
      } catch (error) {
        console.error("Data Acquisition Failure:", error);
      }
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
.custom-avatar {
  border-radius: 10px;
}
</style>