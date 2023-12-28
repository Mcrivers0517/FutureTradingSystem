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
          <div class="register-container">
            <div class="register-text-container">
              <span class="register-text">注册</span>
            </div>
            <form @submit.prevent="register">
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
                style="margin-bottom: 20px"
              ></el-input>
              <div style="margin-bottom: 5px; font-size: 15px">
                <label for="password">请再次输入您的密码</label>
              </div>
              <el-input
                id="repassword"
                v-model="repassword"
                type="password"
                placeholder="Repassword"
              ></el-input>
              <div class="submit-button">
                <input type="submit" style="display: none" />
                <el-button
                  type="primary"
                  native-type="submit"
                  style="width: 100%; margin-top: 10px; height: 42px"
                  >注册</el-button
                >
              </div>
            </form>
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
      repassword: "",
      showErrorMessage: false, // 控制错误提示显示
    };
  },
  methods: {
    register() {
      if (this.password !== this.repassword) {
        alert("两次输入的密码不相同，请重新输入。");
        return;
      }

      const request = {
        username: this.username,
        password: this.password,
      };

      axios
        .post("http://localhost:5000/register", request)
        .then((response) => {
          const result = response.data;
          console.log(result);
          if (result.isSuccess) {
            alert("注册成功，请返回登录界面登录");
            this.$router.push("/Login");
          } else {
            alert("注册失败：该用户名已存在");
          }
        })
        .catch((error) => {
          console.error("注册请求失败", error);
        });
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

.register-container {
  width: 480px; /* 调整容器宽度 */
  height: 420px;
  padding: 20px;
  background-color: #181a20;
  border-radius: 12px;
  margin: 0 auto;
  border: 1px solid #2b3139;
  color: #eaecef; /* 文本颜色为白色 */
}

.register-text {
  font-size: 25px;
  font-weight: 600;
}
.register-text-container {
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
</style>

