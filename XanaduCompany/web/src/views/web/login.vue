<template>
  <div class="container">
    <div id="app">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item
          label="账号"
          prop="username"
          :rules="[{ required: true, message: '账号不能为空' }]"
        >
          <el-input v-model.number="form.username"></el-input>
        </el-form-item>
        <el-form-item
          label="密码"
          prop="password"
          :rules="[{ required: true, message: '密码不能为空' }]"
        >
          <el-input type="password" v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('form')">提交</el-button>
          <el-button @click="resetForm('form')" type="danger">重置</el-button>
          <!-- 注册功能取消 -->
          <el-button type="warning" @click="register">我是管理员</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import httpRequest from "@/axios/index-dev.js";
export default {
  name: "login",

  data() {
    return {
      flagAdmin: false,
      form: {
        //flagAdmin:flagAdmin,   //标准位
        username: "student123",
        password: "123456",
      },
    };
  },

  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(async (valid) => {
        if (valid) {
          const { data: res } = await httpRequest({
            url: "admin/login",
            data: this.form,
            method: "POST",
          });

          console.log("res", res);
          if (res.meta.code == 404) {
            this.$message("请输入正确的用户名和密码！");
          } else if (res.meta.code == 200) {
            this.$message({
              message: "恭喜你，登录成功",
              type: "success",
            });
            window.sessionStorage.setItem("token", res.data.token);
            this.$router.push("/success"); //跳转到管理界面
          }
        } else {
          this.$message("请输入正确的用户名和密码！");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    register() {
      this.flagAdmin = true;
      this.$message({
        message: "设置管理员成功，点击输入正确的管理员账号及密码！",
        type: "success",
      });
      // this.$router.push('/register')
    },
  },
};
</script>
<style>
</style>

<style scoped>
.container {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: rgb(53, 47, 47);
}
#app {
  /* 子定位flex布局 */
  display: flex;
  justify-content: center;
  align-items: center;

  /* 自己的定位 位于全窗口左右居中，上200px*/
  width: 400px;
  height: 280px;
  position: relative;
  margin-left: auto;
  margin-right: auto;
  margin-top: 200px;
  background-color: #fff;
}
</style>
