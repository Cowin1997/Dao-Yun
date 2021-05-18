<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">到云后台管理系统</div>
            <el-tabs stretch v-model="activeTab" @tab-click="handleClick">
              <el-tab-pane label="密码登录" name="local">
               <el-form :model="loginQuery" :rules="localRules" ref="loginForm1" label-width="0px" class="ms-content">
                <el-form-item prop="identifier">
                    <el-input v-model="loginQuery.identifier" placeholder="username">
                        <el-button slot="prepend" icon="el-icon-user"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="credential">
                    <el-input
                        type="password"
                        placeholder="password"
                        v-model="loginQuery.credential"
                        @keyup.enter.native="submitForm()"
                    >
                        <el-button slot="prepend" icon=" el-icon-lock"></el-button>
                    </el-input>
                </el-form-item>
                <el-form-item prop="code">
                    <el-input v-model="loginQuery.code" auto-complete="off" placeholder="验证码" style="width: 63%" @keyup.enter.native="submitForm()">
                    </el-input>
                <div class="login-code">
                    <img :src="codeImg">
                </div>
                </el-form-item>
                <div class="login-btn">
                    <el-button :loading="loading" type="primary" @click="submitForm()">
                    <span v-if="!loading">登 录</span>
                    <span v-else>登 录 中...</span>
                  </el-button>
                </div>
                <!-- <div  style="float:left;">              
                <el-link class="login-tips"  >忘记密码</el-link></div>
                <div style="float:right;"><el-link class="login-tips" >还没有账号？点击注册</el-link>     
                </div>	 -->
                  </el-form>
                </el-tab-pane>

                <el-tab-pane label="短信验证码登录" name="phone">
             <el-form :model="loginQuery" :rules="phoneRules" ref="loginForm2" label-width="0px" class="ms-content">
               <el-form-item prop="identifier">
                 <el-input v-model="loginQuery.identifier" placeholder="请输入手机号"></el-input>
              </el-form-item>
               <el-form-item prop="credential">
               <el-input @keyup.enter.native="submitForm()" v-model="loginQuery.credential" placeholder="请输入验证码">
                  <el-button v-if="sending" slot="append" >获取验证码</el-button>
                  <el-button v-else slot="append" :disabled="disabled">{{second}}秒后获取</el-button>
               </el-input>
               </el-form-item>
                <el-form-item prop="code">
                    <el-input v-model="loginQuery.code" auto-complete="off" placeholder="验证码" style="width: 63%" @keyup.enter.native="submitForm()">
                    </el-input>
                    <div class="login-code">
                        <img :src="codeImg">
                    </div>
                </el-form-item>
               <el-form-item>
                <div class="login-btn">
                  <el-button :loading="loading" type="primary" @click="submitForm()">
                    <span v-if="!loading">登 录</span>
                    <span v-else>登 录 中...</span>
                  </el-button>
                </div>
               </el-form-item>
            </el-form>
                </el-tab-pane>
            </el-tabs>
        </div>
    </div>
</template>

<script>
import { getCodeImg,login, getBtnPerm} from '@/api/login'

export default {
    data: function() {
        return {
            activeTab: 'local',
            loginQuery:{
                code:'2', // 验证码
                identifier:'admin', 
                credential: '123456',
                type: '',
                uuid:'',
                type: 1
            },
            loading:false,
            codeImg:'',
            localRules: {
                identifier: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                credential: [{ required: true, message: '请输入密码', trigger: 'blur' }],
                code: [{ required: true, message: '请输入验证码', trigger: 'blur' }]
            },
            phoneRules:{
                identifier: [{ required: true, message: '请输入手机号', trigger: 'blur' },
                { pattern: /^1[3|5|7|8|9]\d{9}$/, message: '请输入正确的号码格式', trigger: 'blur'}],
                credential: [{ required: true, message: "请输入验证码", trigger: "blur" }],
                code: [{ required: true, message: '请输入验证码', trigger: 'blur' }]
            },
            sending:true,
            disabled: false,  //是否禁发验证码
            second:59,        //倒计时间
        };
    },
    created(){
        // 获取验证码
        this.getCode()
    },
    methods: {
        getCode() {// 获取验证码
            getCodeImg().then(res => {
            this.codeImg = res.data.img  
            this.loginQuery.uuid = res.data.uuid
            })
        },
        submitForm(){
            if(this.activeTab == 'local'){
                this.$refs.loginForm1.validate( valid =>{
                    if(valid){
                        this.loading = true;
                       //登录
                        login(this.loginQuery).then((res) =>{
                            this.loading = false;
                        if(res.success==true){
                            // 保存个人信息
                            localStorage.setItem("user", JSON.stringify(res.data.user));
                            // 保存token
                            localStorage.setItem("token", JSON.stringify(res.data.token));
                            // btnperm
                            getBtnPerm(JSON.parse(localStorage.getItem('user'))['user']['role_id']).then(res =>{
                               localStorage.setItem("perm", JSON.stringify(res.data));
                            })
                            // 跳转
                           this.$router.push({path: "/"})
                        }
                           
                       }).catch( () => {
                           this.loading = false
                            this.getCode()
                       }
                       )
                    }else{
                        alert("表单验证失败")
                    }
                })
            }else if(this.activeTab == 'phone'){
                this.$refs.loginForm2.validate( valid =>{
                    if(valid){
                       this.loading = true;
                       //登录
                        this.$store.dispatch("Login", this.loginQuery).then( () =>{
                            this.loading = false;
                          
                       })
                    }else{
                        alert("表单验证失败")
                    }
                })
            }
        },
        handleClick(tab, event) {
            this.loginQuery={
                    code:'', // 验证码
                    identify:'', 
                    credential: '',
                    type: '',
                    uuid:this.loginQuery.uuid
            };
            if(tab.name == 'local') this.loginQuery.type = 1;
            if(tab.name == 'phone') this.loginQuery.type = 2;
            self.activeTab = tab.name;
        },
    timeDown() {
      let result = setInterval(()=>{
      --this.second;
      if(this.second < 0) {
        clearInterval(result);
        this.second = 59;
        this.disabled = false;
        this.sending = true;
        }   
      }, 1000);
    },
    }
   
};
</script>

<style lang="css" scoped>
.login-code {
    width: 33%;
    display: inline-block;
    height: 38px;
    float: right;
}
.login-wrap {
    position: relative;
    width: 100%;
    height: 100%;
    background-image: url(../assets/img/login-bg.jpg);
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