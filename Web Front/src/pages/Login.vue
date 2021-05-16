<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">到云后台管理系统</div>
            <el-tabs stretch v-model="activeName" @tab-click="handleClick">
              <el-tab-pane label="用户名登录" name="uname">
               <el-form :model="param" :rules="rules1" ref="login1" label-width="0px" class="ms-content">
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
                <div style="float:right;"><el-link class="login-tips" >还没有账号？点击注册</el-link>     
                </div>	
                  </el-form>
                </el-tab-pane>

                <el-tab-pane label="短信验证码登录" name="uphone">
             <el-form :model="param" :rules="rules2" ref="login2" label-width="0px" class="ms-content">
               <el-form-item  prop="mobile">
                 <el-input v-model="param.mobile" placeholder="请输入手机号"></el-input>
              </el-form-item>
               <el-form-item  prop="code">
               <el-input @keyup.enter.native="submitForm()" v-model="param.code" placeholder="请输入验证码">
                  <el-button v-if="sending" slot="append" @click="getCode()">获取验证码</el-button>
                  <el-button v-else slot="append" :disabled="disabled">{{second}}秒后获取</el-button>
               </el-input>
               </el-form-item>
               <el-form-item>
                <div class="login-btn">
                  <el-button  type="primary" @click="submitForm()">登录</el-button>
                </div>
               </el-form-item>
            </el-form>
                </el-tab-pane>
            </el-tabs>
        </div>
    </div>
</template>

<script>
import md5 from 'js-md5';
export default {
    data: function() {
        const validate_mobile = (rule, value, callback) => {
        let reg = /^[1]([3-9])[0-9]{9}$/;
        if (!value) {
          callback("请输入手机号");
        } else if (!reg.test(value)) {
          callback("请输入正确的手机号");
        } else {
          callback();
        }
      };
        return {
            activeName: 'uname',
       
            param: {
                username: 'admin',
                password: 'admin',
            },
            rules1: {
                username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
            },
            rules2:{
                mobile: [{ required: true,validator: validate_mobile, trigger: "blur" }],
                code: [{ required: true, message: "请输入验证码", trigger: "blur" }]
            },
            sending: true,    //是否发送验证码
            disabled: false,  //是否禁发验证码
            second:59,        //倒计时间
        };
    },
    methods: {
    handleClick(tab, event) {
        console.log(tab)
        if(tab.name === 'uname'){
            self.param = {
                username: '',
                password: '',
            }
        self.activeName = "uname";
      }
        else if(tab.name === 'uphone'){
            self.activeName = "uphone";
            self.param = {
                mobile: '',
                code: '',
        }
      }
    },
    timeDown() {
      let result = setInterval(()=>{
      --this.second;
      if(this.second < 0) {
        clearInterval(result);
        this.sending  = true;
        this.disabled = false;
        this.second = 59;
        }   
      }, 1000);
    },
    submitForm() {
            if(this.activeName=="uname"){
                this.$refs.login1.validate(valid => {
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
            }else{
            this.$refs.login2.validate(valid => {
                if (valid) {
                    this.$http.post("/user/login",{
                        phone:this.param.mobile,
                        code:this.param.code
                    }).then(res => {
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
                             this.$message.error(res.data.data.msg);
                             return false;
                        }
                    });
                } else {
                    return false;
                }
            });
            }
        
        },
         //获取验证码
    getCode(){
      if(!this.sending){
        return;
      }
      if(this.param.mobile == ''){
        this.$message.error('请输入手机号')
      }else{
      //api接口封装请求
        this.$http.post('/user/sms',{
          phone:this.param.mobile
        }).then(res=>{
            if(res.status==200 &&res.data.code===0){
                this.sending  = false;
                this.disabled = true;
                this.timeDown();
                this.$message.success(res.data.msg);
            }else{
                 this.$message.error(res.data.msg);
            }


        })
      }
    },
    }
   
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