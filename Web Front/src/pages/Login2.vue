<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">到云后台管理系统</div>
            <el-tabs type="border-card" class="tabs">
                <el-tab-pane label="消息中心"  class="tabs">
                <el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="param.username" placeholder="username">
                        <el-button slot="prepend" icon="el-icon-user"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input
                        type="password"
                        placeholder="password"
                        v-model="param.password"
                        @keyup.enter.native="submitForm()"
                    >
                        <el-button slot="prepend" icon=" el-icon-lock"></el-button>
                    </el-input>
                </el-form-item>
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm()">登录</el-button>
                </div>
                <div  style="float:left;">              
                <el-link class="login-tips"  >忘记密码</el-link></div>
                <div style="float:right;"><el-link class="login-tips" >还没有账号？点击注册</el-link>              </div>	
            </el-form>


                </el-tab-pane>
                <el-tab-pane label="角色管理"  class="tabs">角色管理</el-tab-pane>
            </el-tabs>

           
        </div>
    </div>
</template>

<script>
import md5 from 'js-md5';
export default {
    data: function() {
        return {
            param: {
                username: '123456',
                password: '123456',
            },
            rules: {
                username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
            },
        };
    },
    methods: {
        submitForm() {
            this.$refs.login.validate(valid => {
                if (valid) {
                    this.$http.post("/user/login",{
                        username:this.param.username,
                        password:this.param.password,
                        type:1
                    }).then(res => {
                        console.log(res)
                        if(res.data.code === 0){
                            this.$message.success('登录成功');
                            let Base64 = require('js-base64').Base64
                            localStorage.setItem("token",res.data.data.token)
                            var user = Base64.decode(res.data.data.token.split(".")[1])
                            localStorage.setItem("user",user);
                            var user = JSON.parse(user)
                            console.log(user.type)
                         this.$http.get("/menu",{params:{
                             type:user.type
                         }}).then(res => {
                            if(res.status === 200){

                                localStorage.setItem("route",JSON.stringify(res.data))
                                this.$router.push('/index');
                            }
                         });

                            return true;
                        }
                        else{
                             this.$message.error('账号或密码错误');
                             return false;
                        }
                    });
                } else {
                    this.$message.error('请输入账号和密码');
                    console.log('error submit!!');
                    return false;
                }
            });
        },
    },
};
</script>

<style scoped>
.login-wrap {
    position: relative;
    width: 100%;
    height: 100%;
    background-image: url(../../assets/img/login-bg.jpg);
    background-size: 100%;
}
.ms-title {
    width: 100%;
    line-height: 50px;
    text-align: center;
    font-size: 20px;
    color: #fff;
    border-bottom: 1px solid #ddd;
}
.ms-login {
    position: absolute;
    left: 50%;
    top: 50%;
    width: 350px;
    margin: -190px 0 0 -175px;
    border-radius: 5px;
    background: rgba(255, 255, 255, 0.3);
    overflow: hidden;
}
.ms-content {
    padding: 30px 30px;
}
.login-btn {
    text-align: center;
}
.login-btn button {
    width: 100%;
    height: 36px;
    margin-bottom: 10px;
}
.login-tips {
    font-size: 6px;
    line-height: 30px;
    color: #fff;
}
.tabs{
    background: unset;
    border-bottom:unset;
}
.el-tabs--border-card{
    border:0px;
}
</style>