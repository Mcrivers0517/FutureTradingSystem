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
                <input type="submit" style="display: none" />
                <!--该文本框用来实现回车提交-->
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
                <router-link to="/register" class="register-link"
                  >没有账户？点击注册</router-link
                >
              </span>
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
      username: "",
      password: "",
    };
  },
  methods: {
    async login() {
      if (this.username === "" || this.password === "") {
        alert("用户名或密码不能为空，请填写完整！");
      }
      const request = {
        username: this.username,
        password: this.password,
      };
      console.log(request);
      // else {
      //   this.$router.push("/");
      // }
      axios
        .post("http://localhost:5000/login", request)
        .then((response) => {
          const result = response.data;

          if (result.isSuccess) {
            alert("登录成功");
            this.$store.state.activeUserId = result.userID;
            this.$router.push(
              `/?username=${request.username}&userID=${result.userID}`
            );
            console.log(result);
          } else {
            alert("用户名或密码错误，请重新输入");
            console.log(result);
          }
        })
        .catch((error) => {
          console.error("登录请求失败", error);
        });
    },
  },
};
</script>

<style scoped>
/* eslint-disable*/
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
/deep/.el-input__inner:hover {
  border-color: #f0b90b;
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
.register-link {
  color: white !important; /* 设置链接文本颜色为白色 */
  text-decoration: none; /* 移除下划线 */
}
</style>
