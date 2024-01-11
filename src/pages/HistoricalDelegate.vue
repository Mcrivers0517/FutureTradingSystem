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
          default-active="2-2"
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
          <el-table
            :data="HistoricalDelegateData"
            class="futures-table"
            :cell-style="tableRowClassName"
            :default-sort="{ prop: 'type', order: 'ascending' }"
            @row-click="handleRowClick"
            height="600"
          >
            <el-table-column prop="futureId" label="品种"> </el-table-column>
            <el-table-column prop="delegateId" label="流水号">
            </el-table-column>
            <el-table-column prop="delegateTime" label="日期">
            </el-table-column>
            <el-table-column prop="attribute" label="属性"> </el-table-column>
            <el-table-column prop="delegatePrice" label="价格">
            </el-table-column>
            <el-table-column prop="status" label="状态"> </el-table-column>
            <el-table-column prop="amount" label="数量"> </el-table-column>
            <el-table-column label="金额">
              <template slot-scope="scope">
                {{ scope.row.delegatePrice * scope.row.amount }}
              </template>
            </el-table-column>
          </el-table>
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
      HistoricalDelegateData: [],
    };
  },
  mounted() {
    this.getHistoricalDelegate();
  },
  methods: {
    async getHistoricalDelegate() {
      try {
        const response = await axios.post(
          "http://localhost:5000/getHistoricalDelegate",
          {
            userId: this.$store.state.activeUserId,
            futureId: -1,
          }
        );
        console.log(response.data);
        const HistoricalDelegateData = response.data.map((data) => ({
          futureId: data.futureId,
          delegateId: data.delegateId,
          delegateTime: data.delegateTime,
          attribute: data.attribute,
          delegatePrice: data.delegatePrice,
          status: data.status,
          amount: data.amount,
        }));
        this.HistoricalDelegateData = HistoricalDelegateData;
        console.log("HistoricalDelegateData", this.HistoricalDelegateData);
      } catch (error) {
        console.error("Error getting historical positions:", error);
      }
    },

    logout() {
      this.$router.push("/Login");
    },
    handleSelect(index) {
      // 处理菜单项点击事件，你可以在这里进行路由跳转
      if (index === "1") {
        this.$router.push("/"); // 跳转到主页
      } else if (index === "2-1") {
        this.$router.push("/CurrentDelegate"); // 跳转到当前委托页
      } else if (index === "3") {
        this.$router.push("/MarketQuotes"); // 跳转到行情页
      } else if (index === "4") {
        this.$router.push("/message-center"); // 跳转到消息中心页
      }
    },
    handleRowClick(row) {
      // 处理行点击事件，你可以在这里进行路由跳转
      if (row.type == "金") {
        this.$store.state.activeRowNumber = 0;
        this.$router.push({
          path: "/StockTrendChart",
        });
      } else if (row.type == "银") {
        this.$store.state.activeRowNumber = 1;
        this.$router.push({
          path: "/StockTrendChart",
        });
      } else if (row.type == "铝") {
        this.$store.state.activeRowNumber = 2;
        this.$router.push({
          path: "/StockTrendChart",
        });
      }
    },
    tableRowClassName() {
      return "background: #181a20; height: 80px";
    },
    formatDailyChangeRatio(row, column) {
      // row 是当前行的数据对象，column 是当前列的配置对象
      const ratio = row[column.property];
      if (typeof ratio === "number") {
        return (ratio * 100).toFixed(2) + "%"; // 将数字转换为百分数并保留两位小数
      }
      return ratio; // 如果不是数字，则返回原始值
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
.el-button.revoke {
  background-color: #181a20;
  color: #848e9c;
  border: none;
}
</style>