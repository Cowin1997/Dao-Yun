<template>
  <div>
      <el-row>
        <el-col>
          <el-card shadow="hover" class="mgb20" style="height:270px;">
                    <div class="user-info">
                        <img src="../../assets/img/img.jpg" class="user-avator-2" alt />
                        <div class="user-info-cont">
                            <div class="user-info-name">{{user==null?'':user.account}}</div>
                            <div style="padding-bottom:5px;">{{user==null?'':user.role}}</div>
                            <div >状态<el-tag :type="user&&user.status==true?'success':'danger'" style="margin:5px;" >{{user&&user.status==true?'正常':'禁用'}}</el-tag></div>
                        </div>
                        <div>
                            <el-col>
                                <el-row style='padding-bottom:15px;'>
                                   <el-button type="success" round @click="logout">退出登录</el-button>
                                </el-row>
                                <el-row>
                                    <el-button type="danger" round>修改密码</el-button>
                                </el-row>
                            </el-col>
                        </div>

                    </div>
                    <div class="user-info-list">
                        上次登录时间：
                        <span>{{!user?'':user.last_time.replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')}}</span>
                    </div>
                    <div class="user-info-list">
                        上次登录IP：
                        <span>{{!user?'':user.last_ip}}</span>
                    </div>
                    <div class="user-info-list">
                        管理员手机：
                        <span>{{!user?'':user.phone}}</span>
                    </div>
                </el-card>
        </el-col>
      </el-row>
      <el-row>
          <el-form :inline="true"  class="demo-form-inline" style="border-radius: 30px;">
               <el-form-item><el-button type="primary" @click="getAllAdmin">查询所有管理员</el-button></el-form-item>
               <el-form-item><el-button type="success" @click="addAdmin">添加管理员</el-button></el-form-item>
          </el-form>
          <el-table :data="admins" height="500" border style="width: 100%;font-size:20px;"
                v-loading="loading"
                element-loading-text="拼命加载中"
                element-loading-spinner="el-icon-loading"
                element-loading-background="rgba(0, 0, 0, 0.8)"
          >
                <el-table-column prop="id" label="ID" width="180" align="center"></el-table-column>
                <el-table-column prop="account" label="用户名" width="180" align="center"></el-table-column>
                 <el-table-column prop="phone" label="绑定手机号" width="180" align="center"></el-table-column>
                <el-table-column prop="roles" label="类型" align="center" width="180"></el-table-column>
                <el-table-column prop="last_ip" label="最后一次登录IP" align="center" width="220"></el-table-column>
                <el-table-column prop="last_time" label="最后一次登录时间"  align="center" width="400"></el-table-column>
                <el-table-column prop="status" label="账户状态" align="center" width="180">
                    <template slot-scope="scope">
                        <el-tag :type="scope.row.status===true?'success':'danger'">{{scope.row.status===true?'正常':'禁用'}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" style="width: 100%;" align="center">
                    <template>
                        <el-button type="text" icon="el-icon-edit" >编辑</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red">删除</el-button>
                    </template>
                </el-table-column>
          </el-table>
      </el-row>
    <!-- 编辑弹出框 -->
    <el-dialog title="编辑" :visible.sync="addVisible" width="40%">
        <el-form  :model="admin" >
            <el-form-item label="添加账户名">
                <el-input v-model="admin.account"></el-input>
            </el-form-item>
            <el-form-item label="账户密码">
                <el-input v-model="admin.password"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="editVisible = false">取 消</el-button>
            <el-button type="primary">确 定</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
    data(){
        return {
            loading:false,
            admins:[],
            user:null,
            addVisible:false,
            admin:{
                account:'1',
                password:'1'
            }

        }
    },
    methods:{
        getAllAdmin(){
            this.loading = true;
            this.$http.get("/admin/getAllAdmin").then(res => {
            console.log("/admin/getAllAdmin")
            console.log(res)
            if(res.data != null){
                this.admins = res.data;
                this.$message.success('数据加载完成！');
                this.loading = false;
            }else{
                 this.$message.error('数据加载失败！');
            }
            }); 
        },
        addAdmin(){
            this.addVisible = !this.addVisible;
        },
        logout(){
            console.log('logout...');
            
            localStorage.removeItem('user');
            this.$http.get("/admin/logout");
        }
    },
    beforeCreate(){
            this.$http.get("/admin/getInfo").then(res => {
            console.log("/admin/getInfo")
            console.log(res)
            this.user = res.data;
        });
    }
}
</script>

<style>

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
.user-avator-2 {
    width: 120px;
    height: 120px;
    border-radius: 50%;
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



.mgb20 {
    margin-bottom: 20px;
}
.red {
    color: red;
}

</style>