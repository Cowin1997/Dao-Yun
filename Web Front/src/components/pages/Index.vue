<template>
  <div>
      <el-row :gutter="20" >
        <el-col :span="10" >
          <el-card shadow="hover" class="mgb20" style="height:252px;">
                    <div class="user-info">
                        <img src="../../assets/img/img.jpg" class="user-avator-2" alt />
                        <div class="user-info-cont">
                            <div class="user-info-name">
                                {{user.us_username}}
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <span>
                                <el-button  type="primary" @click="changePassword">修改密码</el-button>
                                </span>
                                </div>
                            
                        </div>
                    </div>
                    <div class="user-info-list">
                        上次登录时间：
                        <span>2019-11-01</span>
                    </div>
                    <div class="user-info-list">
                        上次登录地点：
                        <span>东莞</span>
                    </div>
                </el-card>
                <el-card shadow="hover" style="height:90%;">
                    <el-calendar>
                      <template slot="dateCell" slot-scope="{data}">
                        <p :class="data.isSelected ? 'is-selected' : ''">
                          {{ data.day.split('-').slice(1).join('-') }} {{data.day==today ? '✔️' : ''}}
                        </p>
                      </template>
                    </el-calendar>
                </el-card>
        </el-col>
        <el-col :span="14" >
            <el-card shadow="hover" >
                    <div slot="header" class="clearfix">
                        <span>待办事项</span>
                        <el-button style="float: right; padding: 3px 0" type="text" @click="addTodo">添加</el-button>
                    </div>
                    <el-table :show-header="false" :data="todoList"  height="878"  style="width:100%;">
                        <el-table-column width="40">
                            <template slot-scope="scope">
                                <el-checkbox v-model="scope.row.status"></el-checkbox>
                            </template>
                        </el-table-column>
                        <el-table-column>
                            <template slot-scope="scope">
                                <div
                                    class="todo-item"
                                    :class="{'todo-item-del': scope.row.status}"
                                >{{scope.row.title}}</div>
                            </template>
                        </el-table-column>
                        <el-table-column width="60">
                            <template slot-scope="scope">
                                <i class="el-icon-edit"  @click="editTodo(scope)"></i>&nbsp;&nbsp;
                                <i class="el-icon-delete"  @click="deleteTodo(scope)"></i>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-card>
        </el-col>
      </el-row>

    <el-dialog title="修改密码" :visible.sync="changePasswordVisible" width="40%">
      <el-form :model="Pwd" label-width="150px">
        <el-form-item label="请输入旧密码">
                <el-input v-model="Pwd.oldPassword" type="password"></el-input>
        </el-form-item>
        <el-form-item label="请输入新密码">
                <el-input v-model="Pwd.newPassword" type="password"></el-input>
        </el-form-item>
        <el-form-item label="请再次输入新密码">
                <el-input v-model="Pwd.newPasswordAgain" type="password"></el-input>
        </el-form-item>
      </el-form>
           <span slot="footer" class="dialog-footer">
            <el-button @click="changePasswordVisible = false">取 消</el-button>
            <el-button type="primary" @click="doChangePassword">确 定</el-button>
        </span>
    </el-dialog>


  </div>
</template>

<script>
export default {
  data() {

    return {
            changePasswordVisible:false,
            user:JSON.parse(localStorage.getItem("user")),
            // name: 'admin',
            today:new Date().getMonth()+ '-' + new Date().getDate(),
            Pwd:{
                newPassword:'',
                newPasswordAgain:'',
                oldPassword:'',
                userid:this.user.id
            },
            todoList: [
            ]
          };    
  },
  created(){
       var date = new Date(); 
        var mon = date.getMonth() + 1;
        var day = date.getDate();
        var nowDay = date.getFullYear() + "-" + (mon<10?"0"+mon:mon) + "-" +(day<10?"0"+day:day);
        this.today = nowDay;
  },
  beforeCreate(){
      this.user=JSON.parse(localStorage.getItem("user")),
     
      console.log(this.user)
  },
  methods:{
     doChangePassword(){
        console.log(this.Pwd)
        if(this.Pwd.oldPassword!=this.user.us_password) {alert("旧密码错误!");return;}
        if(this.Pwd.newPassword!=this.Pwd.newPasswordAgain){alert("密码不一致!");return;}

       this.$http.post("/user/"+this.Pwd.userid,this.Pwd).then(res =>{
         if(res.status==200 &&res.data.datas==true){
           this.$message({type: 'success',  message: '修改成功!'});
         }else{
            this.$message({type: 'error',  message: '修改失败!'});
         }
       });
        this.changePasswordVisible=!this.changePasswordVisible
     },
      changePassword(){
          this.changePasswordVisible=!this.changePasswordVisible;
        
      },
      deleteTodo(e){
        var index = e.$index;
        var title = e.row.title;
        var status = e.row.status;
        this.$confirm('删除该事项, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
           this.$delete(this.todoList,index);
           this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      },
      editTodo(e){
        var index = e.$index;
        var title = e.row.title;
        var status = e.row.status;
         this.$prompt('请编辑待办事项', '编辑', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputPlaceholder:title
        }).then(({ value }) => {
        this.$set(this.todoList,index,{
            title:value,
            status:status
        });
          this.$message({
            type: 'success',
            message: '编辑待办事项: ' + value,
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });       
        });
      },
      addTodo(){
          this.$prompt('请输入待办事项', '添加', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          //inputPattern:,
          //inputErrorMessage: '邮箱格式不正确'
        }).then(({ value }) => {
          this.todoList.push({
                title: value,
                status: false
            });
          this.$message({
            type: 'success',
            message: '添加待办事项: ' + value,
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });       
        });
      }
  }
  
}
</script>

<style>
.el-row {
    margin-bottom: 20px;
}

.grid-content {
    display: flex;
    align-items: center;
    height: 100px;
}

.grid-cont-right {
    flex: 1;
    text-align: center;
    font-size: 14px;
    color: #999;
}

.grid-num {
    font-size: 30px;
    font-weight: bold;
}

.grid-con-icon {
    font-size: 50px;
    width: 100px;
    height: 100px;
    text-align: center;
    line-height: 100px;
    color: #fff;
}

.grid-con-1 .grid-con-icon {
    background: rgb(45, 140, 240);
}

.grid-con-1 .grid-num {
    color: rgb(45, 140, 240);
}

.grid-con-2 .grid-con-icon {
    background: rgb(100, 213, 114);
}

.grid-con-2 .grid-num {
    color: rgb(45, 140, 240);
}

.grid-con-3 .grid-con-icon {
    background: rgb(242, 94, 67);
}

.grid-con-3 .grid-num {
    color: rgb(242, 94, 67);
}

.user-info {
    display: flex;
    align-items: center;
    padding-bottom: 20px;
    border-bottom: 2px solid #ccc;
    margin-bottom: 20px;
}

.user-avator-2 {
    width: 120px;
    height: 120px;
    border-radius: 50%;
}

.user-info-cont {
    padding-left: 50px;
    flex: 1;
    font-size: 14px;
    color: #999;
}

.user-info-cont div:first-child {
    font-size: 30px;
    color: #222;
}

.user-info-list {
    font-size: 14px;
    color: #999;
    line-height: 25px;
}

.user-info-list span {
    margin-left: 70px;
}

.mgb20 {
    margin-bottom: 20px;
}

.todo-item {
    font-size: 14px;
}

.todo-item-del {
    text-decoration: line-through;
    color: #999;
}

.schart {
    width: 100%;
    height: 300px;
}
</style>