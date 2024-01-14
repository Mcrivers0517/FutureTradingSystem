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
          default-active="1"
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
          <div class="user-avatar">
            <el-upload
              class="avatar-uploader"
              action="http://localhost:5000/uploadAvatar"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :data="{ username: this.username }"
            >
              <img v-if="avatarUrl" :src="avatarUrl" class="avatar" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </div>
          <div class="user-info">
            <div class="user-name">
              <span>{{ username }}</span>
            </div>
            <div class="user-id">
              <span>{{ $store.state.activeUserId }}</span>
            </div>
          </div>
          <div class="main-content" id="main-content-1">
            <div class="assets-container">
              <span class="assets">总资产估值</span>
              <div class="assets-values">
                <span class="assets-value">{{ totalAssets.toFixed(2) }}</span>
                <span class="assets-currency">RMB</span>
              </div>
              <div class="profit-and-loss">
                <span class="profit-and-loss-title">总盈亏</span>
                <span
                  :class="{
                    'profit-and-loss-value': true,
                    'red-text': totalProfitLoss > 0,
                    'green-text': totalProfitLoss < 0,
                    'white-text': totalProfitLoss === 0,
                  }"
                >
                  {{ totalProfitLoss > 0 ? "+" : ""
                  }}{{ totalProfitLoss.toFixed(2) }}
                </span>
                <span
                  :class="{
                    'profit-and-loss-value': true,
                    'red-text': totalProfitLossRatio > 0,
                    'green-text': totalProfitLossRatio < 0,
                    'white-text': totalProfitLossRatio === 0,
                  }"
                >
                  ({{ totalProfitLossRatio > 0 ? "+" : ""
                  }}{{ totalProfitLossRatio.toFixed(2) }}%)
                </span>
              </div>
              <div class="profit-and-loss">
                <span class="profit-and-loss-title">保证金</span>
                <span class="deposit">{{ deposit.toFixed(2) }} RMB</span>
                <el-input
                  v-model="rechargeAmount"
                  placeholder="增加保证金数额"
                  class="increase-deposit"
                  style="margin-top: 10px"
                ></el-input>
                <el-button
                  type="primary"
                  icon="el-icon-check"
                  class="increase-button"
                  circle
                  @click="recharge()"
                ></el-button>
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
                height="290"
                :header-cell-style="{ 'text-align': 'center' }"
              >
                <el-table-column
                  v-if="false"
                  prop="positionId"
                  label="持仓ID"
                  align="center"
                >
                </el-table-column>
                <el-table-column
                  prop="futureId"
                  label="品种"
                  align="center"
                  :width="80"
                >
                </el-table-column>
                <el-table-column prop="attribute" label="属性" align="center">
                </el-table-column>
                <el-table-column prop="amount" label="数量" align="center">
                </el-table-column>
                <el-table-column
                  prop="currentPrice"
                  label="现价"
                  align="center"
                >
                </el-table-column>
                <el-table-column prop="costPrice" label="成本价" align="center">
                </el-table-column>
                <el-table-column
                  prop="ProfitLoss"
                  label="浮动盈亏"
                  align="center"
                >
                </el-table-column>
                <el-table-column
                  prop="ProfitLossRatio"
                  label="浮动盈亏率"
                  align="center"
                >
                </el-table-column>
                <el-table-column label="平仓数量" align="center">
                  <template slot-scope="scope">
                    <el-input
                      v-model="scope.row.closeAmount"
                      placeholder="平仓数量"
                      class="input"
                      style="margin-top: 10px"
                      @input="updateCloseAmount(scope.row.closeAmount)"
                    ></el-input>
                  </template>
                </el-table-column>
                <el-table-column label="平仓" align="center">
                  <template slot-scope="scope">
                    <el-button
                      type="primary"
                      icon="el-icon-check"
                      circle
                      @click="closePosition(scope.row)"
                    ></el-button>
                  </template>
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
      avatarUrl: "",
      username: "",
      userid: this.$store.state.activeUserId,
      totalAssets: "totalAssets",
      totalProfitLoss: "totalProfitLoss",
      totalProfitLossRatio: "totalProfitLossRatio",
      deposit: "deposit",
      closeAmount: "",
      rechargeAmount: "",
    };
  },
  created() {
    this.getPositions();
    // this.getUserInfo();
    this.getAssets();
    setTimeout(() => {
      this.fetchAvatar();
    }, 100);
  },
  methods: {
    closePosition(row) {
      const timestamp = Date.now(); // 毫秒时间戳
      const date = new Date(timestamp);

      const offset = 8 * 60; // UTC+8
      const dateTimeString = new Date(date.getTime() + offset * 60000)
        .toISOString()
        .replace("Z", `+${String(Math.abs(offset / 60)).padStart(2, "0")}:00`);

      if (this.closeAmount <= 0) {
        alert("平仓数量不能小于等于0！");
        return;
      }

      const request = {
        positionId: row.positionId,
        closeAmount: this.closeAmount,
        delegateTime: dateTimeString,
      };
      console.log(request);
      axios
        .post("http://localhost:5000/closePosition", request)
        .then((response) => {
          const result = response.data;
          console.log(result);
        })
        .catch((error) => {
          console.error("平仓请求失败", error);
        });
    },
    recharge() {
      const request = {
        userId: this.$store.state.activeUserId,
        rechargeAmount: this.rechargeAmount,
      };
      console.log(request);
      axios
        .post("http://localhost:5000/recharge", request)
        .then((response) => {
          if (response.data.result) {
            alert("保证金充值成功！");
          }
        })
        .catch(() => {
          alert("保证金充值失败");
        });
    },
    updateCloseAmount(value) {
      // 将值传递到外部
      this.closeAmount = value;
    },
    logout() {
      this.$router.push("/Login");
    },
    handleSelect(index) {
      // 处理菜单项点击事件，你可以在这里进行路由跳转
      if (index === "2-1") {
        this.$router.push("/CurrentDelegate"); // 跳转到当前委托页
      } else if (index === "2-2") {
        this.$router.push("/HistoricalDelegate"); // 跳转到历史委托页
      } else if (index === "3") {
        this.$router.push("/MarketQuotes"); // 跳转到行情页
      } else if (index === "4") {
        this.$router.push("/message-center"); // 跳转到消息中心页
      }
    },
    tableRowClassName() {
      return "background: #181a20";
    },
    async getPositions() {
      try {
        const userId = this.$store.state.activeUserId; // 获取用户ID
        const response = await axios.post("http://localhost:5000/getPosition", {
          userId,
        });

        console.log(response.data);
        const PositionData = response.data.map((data) => ({
          positionId: data.positionId,
          futureId: data.futureId, // 期货品种
          attribute: data.attribute, // 买/卖
          amount: data.amount, // 持仓
          currentPrice: data.currentPrice, // 浮动盈亏
          costPrice: data.costPrice, // 盈亏比率
          ProfitLoss: data.profitLoss, // 开仓均价
          ProfitLossRatio: data.profitLossRatio, // 现价
          LastUpdated: data.lastUpdated, // 最新成交时间
        }));
        this.PositionData = PositionData;
        console.log("PositionData", this.PositionData);
      } catch (error) {
        console.error("Data Acquisition Failure:", error);
      }
    },

    async getAssets() {
      const userId = this.$store.state.activeUserId; // 设置 userId
      console.log(userId);
      const response = await axios.post("http://localhost:5000/getAssets", {
        userId,
      });
      const responseData = response.data;
      this.username = responseData.username;
      this.totalAssets = responseData.currentCapital; // 用户总资产
      this.totalProfitLoss = responseData.totalProfitLoss; // 总计盈亏
      this.totalProfitLossRatio = responseData.totalProfitLossRatio;
      this.deposit = responseData.deposit;
      console.log("responseData", response.data);
    },
    // async getUserInfo() {
    //   try {
    //     const response = await axios.get("localhost:5000/getUserInfo");
    //     const responseData = response.data;
    //     this.avatarUrl = responseData.avatarUrl; //用户头像url
    //     this.username = responseData.username; //用户名
    //     this.userid = responseData.userid; //用户id
    //     this.totalAssets = responseData.totalAssets; //用户总资产
    //     this.totalProfitLoss = response.totalProfitLoss; //今日盈亏
    //   } catch (error) {
    //     console.error("Data Acquisition Failure:", error);
    //   }
    // },
    handleAvatarSuccess(res, file) {
      this.avatarUrl = URL.createObjectURL(file.raw);
    },
    fetchAvatar() {
      // 发送请求到后端获取图像
      console.log("---------------------------------------", this.username);
      axios
        .post(
          "/getUserAvatar",
          { username: this.username },
          { responseType: "arraybuffer" }
        )
        .then((response) => {
          const imageBlob = new Blob([response.data], {
            type: response.headers["content-type"],
          });
          const imageUrl = URL.createObjectURL(imageBlob);
          this.avatarUrl = imageUrl;
        })
        .catch((error) => {
          console.error("Error fetching avatar:", error);
        });
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
/*取消菜单下方的白线（边界线）*/
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
  width: 60%;
  height: 23%;
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
  width: 60%;
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
  left: 20%;
}
.user-info {
  position: absolute;
  top: 7%;
  left: 26%;
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
  margin-top: 1.9%;
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
.deposit {
  margin-top: 1.6%;
  font-size: 15px;
  font-weight: 600;
  margin-left: 1%;
  color: #d9d9d9;
}
.custom-avatar {
  border-radius: 10px;
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
.el-button--primary {
  background: #fcd535;
  border-color: #fcd535;
  color: #181a20;
  border-radius: 4px;
}
.el-button--primary:hover {
  background: #e5c333;
  border-color: #e5c333;
  color: #181a20;
  border-radius: 4px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 70px;
  height: 70px;
  line-height: 70px;
  text-align: center;
}
.avatar {
  width: 70px;
  height: 70px;
  display: block;
}
.input {
  width: 100%;
  height: 50%;
  margin-bottom: 10px;
}
/deep/.el-input__inner {
  height: 45px;
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
/deep/.el-input__inner:focus {
  border-color: #f0b90b;
}
.red-text {
  color: #f6465d;
}
.green-text {
  color: #0ecb81;
}
.white-text {
  color: #e8ffff;
}
.increase-deposit {
  margin-left: 10px;
  width: 11.5%;
  height: 10%;
}
.increase-deposit /deep/.el-input__inner {
  height: 35px;
  background: #2a2d35;
  font-size: 12px;
  font-weight: 600;
  font-family: PingFangSC-Regular, PingFang SC;
  color: #eaecef;
  border: 1px solid #2b3139;
  margin-top: 0px;
  margin-left: 20px;
}
.increase-deposit /deep/.el-input__inner:hover {
  border-color: #f0b90b;
}
.increase-deposit /deep/.el-input__inner:focus {
  border-color: #f0b90b;
}
.increase-button {
  width: 50px;
  height: 40px;
  margin-left: 50px;
  margin-top: 8px;
}
</style>