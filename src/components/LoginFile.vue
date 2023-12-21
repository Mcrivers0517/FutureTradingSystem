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
      </el-header>
      <el-container class="content-container">
        <el-main class="main">
          <div class="login-container">
            <div class="login-text-container">
              <span class="login-text">登录</span>
            </div>
            <form @submit.prevent="login">
              <div style="margin-bottom: 5px; font-size: 15px">
                <label for="username">请输入您的邮箱/手机号码</label>
              </div>
              <el-input
                id="username"
                v-model="username"
                placeholder="Username"
                style="margin-bottom: 20px"
              ></el-input>
              <div style="margin-bottom: 5px; font-size: 15px">
                <label for="password">请输入您的密码</label>
              </div>
              <el-input
                id="password"
                v-model="password"
                type="password"
                placeholder="Password"
                style="border-color: red"
              ></el-input>
              <div class="submit-button">
                <el-button
                  type="primary"
                  native-type="submit"
                  style="width: 100%"
                  >登录</el-button
                >
              </div>
            </form>
            <div class="register-link">
              <span class="register-link-text">
                未有账户？<router-link to="/register">点击注册</router-link>
              </span>
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
      username: "",
      password: "",
    };
  },
  methods: {
    login() {
      if (this.username === "" || this.password === "") {
        alert("用户名和密码不能为空，请填写完整！");
      }
    },
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

.logo-image {
  width: 45px;
  height: auto;
  margin-top: 8px;
  margin-left: 15px;
}
.login-container {
  width: 480px; /* 调整容器宽度 */
  height: 360px;
  padding: 20px;
  background-color: #181a20;
  border-radius: 12px;
  margin: 0 auto;
  border: 1px solid #2b3139;
  color: #eaecef; /* 文本颜色为白色 */
}

.login-text {
  font-size: 25px;
  font-weight: 600;
}
.login-text-container {
  margin-bottom: 30px;
}
/deep/.el-input__inner {
  height: 52px;
  background: #181a20;
  font-size: 14px;
  font-weight: 600;
  font-family: PingFangSC-Regular, PingFang SC;
  color: #eaecef;
  border: 1px solid #2b3139;
}
/deep/.el-input__inner:focus {
  border-color: #f0b90b;
}
.submit-button {
  margin-top: 20px;
  height: 42px;
  display: flex;
  justify-content: center;
}
.el-button--primary {
  background: #fcd535;
  border-color: #fcd535;
  color: #181a20;
  border-radius: 8px;
}
.el-button--primary:hover {
  background: #e5c333;
  border-color: #e5c333;
  color: #181a20;
  border-radius: 8px;
}
.register-link {
  margin-top: 13px;
}
span.register-link-text {
  text-decoration: none;
}
span:hover.register-link-text {
  text-decoration: underline;
  cursor: pointer;
}
</style>
