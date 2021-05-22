<template>
  <div class="header">
        <!-- 折叠按钮 -->
        <div class="collapse-btn" @click="collapseChage">
            <i v-if="!collapse" class="el-icon-s-fold"></i>
            <i v-else class="el-icon-s-unfold"></i>
        </div>
        <!-- 标题 -->
        <div class="logo">到云后台管理系统</div>
        <!-- 右上角  -->
        <div class="header-right">
            <div class="header-user-con">
                <!-- 全屏显示 -->
                <div class="btn-fullscreen" @click="handleFullScreen">
                    <el-tooltip effect="dark" :content="fullscreen?`取消全屏`:`全屏`" placement="bottom">
                        <i class="el-icon-rank"></i>
                    </el-tooltip>
                </div>
                <!-- 消息中心 -->
                <div class="btn-bell">
                    <el-tooltip
                        effect="dark"
                        :content="message?`有${message}条未读消息`:`消息中心`"
                        placement="bottom"
                    >
                        <router-link to="/tabs">
                            <i class="el-icon-bell"></i>
                        </router-link>
                    </el-tooltip>
                    <span class="btn-bell-badge" v-if="message"></span>
                </div>
                <!-- 用户头像 -->
                <div class="user-avator">
                    <img src="../assets/img/img.jpg" />
                </div>
                <!-- 用户名下拉菜单 -->
                <el-dropdown class="user-name" trigger="click">
                    <span class="el-dropdown-link">
                        {{username}}
                        <i class="el-icon-caret-bottom"></i>
                    </span>
                    <el-dropdown-menu slot="dropdown">
                        <a href="http://github.com/cowin1997/Dao-Yun" target="_blank">
                            <el-dropdown-item>项目仓库</el-dropdown-item>
                        </a>
                         <el-dropdown-item divided @click.native="changePwd">修改密码</el-dropdown-item>
                        <el-dropdown-item divided @click.native="handleLoginOut">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>
     <el-dialog title="修改密码" :visible.sync="changePwdVisiable" width="40%">
        <el-form :model="pwd" label-width="150px" :rules="pwdRules" ref="pwdform">
          <el-form-item label="旧密码" prop="oldPass">
                <el-input type="password" v-model="pwd.oldPass"></el-input>
          </el-form-item>
           <el-form-item label="新密码" prop="newPass">
                <el-input type="password" v-model="pwd.newPass"></el-input>
          </el-form-item>
            <el-form-item label="请再一次输入新密码" prop="newPassAgain">
                <el-input type="password" v-model="pwd.newPassAgain"></el-input>
          </el-form-item>
           </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="changePwdVisiable = false">取 消</el-button>
            <el-button type="primary" @click="handleChangePwd()" >确 定</el-button>
        </span>
    
     </el-dialog>
  </div>
</template>

<script>
// 导入bus,用于组件间通信
import {pwdRest} from '@/api/pwd'
import bus from './bus';
export default {
    data(){
        var validatePass = (rule, value, callback) => {
        if (value === '') {
            callback(new Error('请输入密码'));
        } else {
        if (this.pwd.newPassAgain !== '') {
     this.$refs.pwdform.validateField('newPassAgain');
    }
   callback();
  }
 };
 var validatePass2 = (rule, value, callback) => {
   if (value === '') {
     callback(new Error('请再次输入密码'));
   } else if (value !== this.pwd.newPass) {
     callback(new Error('两次输入密码不一致!'));
   } else {
     callback();
   }
 };
        return {
            collapse: false,
            fullscreen: false,
            username: JSON.parse(localStorage.getItem("user")).user.nickname,
            message: 2,
            changePwdVisiable:false,
            pwd:{
                oldPass:'',
                newPass:'',
                newPassAgain:''
            },
            pwdRules:{
                oldPass:[{required: true, message: '请输入旧密码', trigger: 'blur' }],
                newPass:[{required: true, message: '请输入新密码', trigger: 'blur' },
                { validator: validatePass, trigger: 'blur' },{
                    min: 6, max: 16, message: '长度在 6 到 16 个字符'
                }],
                newPassAgain:[{required: true, message: '请再一次输入新密码', trigger: 'blur' },
                { validator: validatePass2, trigger: 'blur', required: true },{
                    min: 6, max: 16, message: '长度在 6 到 16 个字符'
                }]
            },
        };
    },
    methods: {
        handleChangePwd(){
                this.$refs.pwdform.validate(valid =>{
                    if(valid){
                        var uid = JSON.parse(localStorage.getItem("user"))['userAuth']['id']
                        pwdRest({
                            uid:uid,
                            newPass:this.pwd.newPass,
                            oldPass:this.pwd.oldPass
                        }).then(res => {
                            console.log(res)
                            if(res.success==true){
                                this.$message.success("修改密码成功")
                                this.changePwdVisiable = false
                            }else{
                                this.$message.error(res.error)
                                this.changePwdVisiable = false
                            }
                        })
                    }else{
                        alert("表单验证失败")
                    }
                })
        },
        changePwd(){
            this.changePwdVisiable = true
        },
        handleLoginOut(){
            localStorage.clear();
            window.location.reload()
            this.$router.push("/login")
            
            
        },
        // 全屏事件
        handleFullScreen() {
            let element = document.documentElement;
            if (this.fullscreen) {
                if (document.exitFullscreen) {
                    document.exitFullscreen();
                } else if (document.webkitCancelFullScreen) {
                    document.webkitCancelFullScreen();
                } else if (document.mozCancelFullScreen) {
                    document.mozCancelFullScreen();
                } else if (document.msExitFullscreen) {
                    document.msExitFullscreen();
                }
            } else {
                if (element.requestFullscreen) {
                    element.requestFullscreen();
                } else if (element.webkitRequestFullScreen) {
                    element.webkitRequestFullScreen();
                } else if (element.mozRequestFullScreen) {
                    element.mozRequestFullScreen();
                } else if (element.msRequestFullscreen) {
                    // IE11
                    element.msRequestFullscreen();
                }
            }
            this.fullscreen = !this.fullscreen;
            
        },
        // 侧边栏折叠
        collapseChage() {
            this.collapse = !this.collapse;
            //触发组件间通信,产生collapse事件
            bus.$emit('collapse', this.collapse);
        },
        // 宽度小于1500,侧边栏改变
        mounted() {
            if (document.body.clientWidth < 1500) {
                this.collapseChage();
            }
    }
    } //methods
     
    
}
</script>

<style>
.header {
    position: relative;
    box-sizing: border-box;
    width: 100%;
    height: 70px;
    font-size: 22px;
    color: #fff;
}
.collapse-btn {
    float: left;
    padding: 0 21px;
    cursor: pointer;
    line-height: 70px;
}
.header .logo {
    float: left;
    width: 250px;
    line-height: 70px;
}
.header-right {
    float: right;
    padding-right: 50px;
}
.header-user-con {
    display: flex;
    height: 70px;
    align-items: center;
}
.btn-fullscreen {
    transform: rotate(45deg);
    margin-right: 5px;
    font-size: 24px;
}
.btn-bell,
.btn-fullscreen {
    position: relative;
    width: 30px;
    height: 30px;
    text-align: center;
    border-radius: 15px;
    cursor: pointer;
}
.btn-bell-badge {
    position: absolute;
    right: 0;
    top: -2px;
    width: 8px;
    height: 8px;
    border-radius: 4px;
    background: #f56c6c;
    color: #fff;
}
.btn-bell .el-icon-bell {
    color: #fff;
}
.user-name {
    margin-left: 10px;
}
.user-avator {
    margin-left: 20px;
}
.user-avator img {
    display: block;
    width: 40px;
    height: 40px;
    border-radius: 50%;
}
.el-dropdown-link {
    color: #fff;
    cursor: pointer;
}
.el-dropdown-menu__item {
    text-align: center;
}
</style>