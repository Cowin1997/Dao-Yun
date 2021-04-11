<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">到云后台管理系统</div>
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
        </div>
    </div>
</template>

<script>
import md5 from 'js-md5';
export default {
    data: function() {
        return {
            param: {
                username: 'admin',
                password: 'admin',
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
                        password:(this.param.password)
                    }).then(res => {
                        if(res.data.status === 0){
                            this.$message.success('登录成功');
                            localStorage.setItem("user",JSON.stringify(res.data.datas));
                         this.$http.get("/menu").then(res => {
                            if(res.status === 200){
                               global.items = res.data;
                                if(!localStorage.getItem("route")){
                                var plist = []
                                for(var i=0;i<res.data.length;i++){
                                    var m = {
                                        "index":res.data[i]['index'],
                                        "title":res.data[i]['title']
                                    }
                                    plist.push(m)
                                    if("subs" in res.data[i]){
                                        for(var j=0;j<res.data[i]['subs'].length;j++){
                                            var m = {
                                                "index":res.data[i]['subs'][j]['uri'],
                                                "title":res.data[i]['subs'][j]['title']
                                            }
                                    plist.push(m)
                                        }
                                    }

                                }
                                        global.permitList = plist;
                                        localStorage.setItem("route",JSON.stringify(plist))
                                }
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
</style>