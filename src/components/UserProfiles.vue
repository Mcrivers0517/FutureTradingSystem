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
          <el-menu-item index="1">处理中心</el-menu-item>
          <el-submenu index="2">
            <template slot="title">我的工作台</template>
            <el-menu-item index="2-1">选项1</el-menu-item>
            <el-menu-item index="2-2">选项2</el-menu-item>
            <el-menu-item index="2-3">选项3</el-menu-item>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项1</el-menu-item>
              <el-menu-item index="2-4-2">选项2</el-menu-item>
              <el-menu-item index="2-4-3">选项3</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-menu-item index="3">消息中心</el-menu-item>
          <el-menu-item index="4">订单管理</el-menu-item>
        </el-menu>
      </el-header>
      <el-container class="content-container">
        <el-aside class="aside" width="200px">
          <el-menu
            default-active=""
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#181a20"
            text-color="#848e8c"
            active-text-color="#f0b90b"
            width="10px"
          >
            <el-submenu index="1">
              <template slot="title">
                <i class="el-icon-location"></i>
                <span>导航一</span>
              </template>
              <el-menu-item-group>
                <template slot="title">分组一</template>
                <el-menu-item index="1-1">选项1</el-menu-item>
                <el-menu-item index="1-2">选项2</el-menu-item>
              </el-menu-item-group>
              <el-menu-item-group title="分组2">
                <el-menu-item index="1-3">选项3</el-menu-item>
              </el-menu-item-group>
              <el-submenu index="1-4">
                <template slot="title">选项4</template>
                <el-menu-item index="1-4-1">选项1</el-menu-item>
              </el-submenu>
            </el-submenu>
            <el-menu-item index="2">
              <i class="el-icon-menu"></i>
              <span slot="title">导航二</span>
            </el-menu-item>
            <el-menu-item index="3">
              <i class="el-icon-document"></i>
              <span slot="title">导航三</span>
            </el-menu-item>
            <el-menu-item index="4">
              <i class="el-icon-setting"></i>
              <span slot="title">导航四</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main class="main">
          <div class="user-avatar">
            <div class="block">
              <el-avatar shape="square" :size="65" :src="squareUrl"></el-avatar>
            </div>
          </div>
          <div class="user-info">
            <div class="user-name">
              <span>这里是用户名</span>
            </div>
            <div class="user-id">
              <span>这里是用户id</span>
            </div>
          </div>
          <div class="main-content" id="main-content-1">
            <p>这是一条测试信息</p>
          </div>
          <div class="main-content" id="main-content-2">
            <p>这是一条测试信息2</p>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
};
</script>

<style scoped>
.common-layout {
  background-color: #eaecef;
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
  border-radius: 0 0 12px 0;
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
  position: relative;
  border: 1px solid #2b3139;
  padding: 20px; /* 增加边框与内容之间的间距 */
  width: 56%;
  height: 20%;
  top: 3%;
  left: 10%;
  border-radius: 12px;
  color: #eaecef;
}
#main-content-2 {
  position: relative;
  border: 1px solid #2b3139;
  padding: 20px; /* 增加边框与内容之间的间距 */
  width: 56%;
  height: 40%;
  top: 8%;
  left: 10%;
  border-radius: 12px;
  color: #eaecef;
}
.user-avatar {
  position: absolute;
  top: 5%;
  left: 12%;
}
.user-info {
  position: absolute;
  top: 6%;
  left: 20%;
  color: #eaecef;
  display: flex;
  align-items: center;
  flex-direction: column;
}
.user-id {
  margin-top: 8px;
}
</style>