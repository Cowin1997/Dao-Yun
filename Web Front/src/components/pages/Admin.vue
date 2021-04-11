<template>
  <div>
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
                <el-table-column prop="us_username" label="用户名" width="180" align="center"></el-table-column>
                 <el-table-column prop="us_phone" label="绑定手机号" width="180" align="center"></el-table-column>
                <el-table-column prop="us_roleid" label="类型" align="center" width="180">
                         <template slot-scope="scope">
                        <el-tag >{{scope.row.us_roleid===1?'超级管理员':'普通管理员'}}</el-tag>
                    </template>
                </el-table-column>

                <el-table-column prop="us_status" label="账户状态" align="center" width="180">
                    <template slot-scope="scope">
                        <el-tag :type="scope.row.us_status===true?'success':'danger'">{{scope.row.us_status===true?'正常':'禁用'}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" style="width: 100%;" align="center">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-edit" @click="editAdmin(scope.row)">编辑</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red" @click="deleteAdmin(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
          </el-table>
    <!-- 编辑弹出框 -->
    <el-dialog title="编辑管理员" :visible.sync="editVisible" width="40%">
        <el-form  :model="edit_admin" >
            <el-form-item label="编辑账户用户名">
                <el-input v-model="edit_admin.us_username"></el-input>
            </el-form-item>
            <el-form-item label="编辑账户密码">
                <el-input v-model="edit_admin.us_password"></el-input>
            </el-form-item>
             <el-form-item label="编辑账户手机号">
                <el-input v-model="edit_admin.us_phone"></el-input>
            </el-form-item>
             <el-form-item label="编辑账户邮箱">
                <el-input v-model="edit_admin.us_email"></el-input>
            </el-form-item>
            <el-form-item label="编辑管理员类型">
                <template>
                    <el-radio v-model="edit_admin.us_roleid" :label="1">超级管理员</el-radio>
                    <el-radio v-model="edit_admin.us_roleid" :label="2">普通管理员</el-radio>
                </template>
            </el-form-item>
            <el-form-item label="编辑账户状态">
                <template>
                    <el-radio v-model="edit_admin.us_status" :label="true">正常</el-radio>
                    <el-radio v-model="edit_admin.us_status" :label="false">禁用</el-radio>
                </template>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="editVisible = false">取 消</el-button>
            <el-button type="primary" @click="doEditAdmin">确 定</el-button>
        </span>
    </el-dialog>

    <!-- 编辑弹出框 -->
    <el-dialog title="添加管理员" :visible.sync="addVisible" width="40%">
        <el-form  :model="add_admin" >
            <el-form-item label="添加账户名">
                <el-input v-model="add_admin.us_username"></el-input>
            </el-form-item>
            <el-form-item label="账户密码">
                <el-input v-model="add_admin.us_password"></el-input>
            </el-form-item>
             <el-form-item label="账户手机号">
                <el-input v-model="add_admin.us_phone"></el-input>
            </el-form-item>
             <el-form-item label="账户邮箱">
                <el-input v-model="add_admin.us_email"></el-input>
            </el-form-item>
            <el-form-item label="管理员类型">
                <template>
                    <el-radio v-model="add_admin.us_roleid" :label="1">超级管理员</el-radio>
                    <el-radio v-model="add_admin.us_roleid" :label="2">普通管理员</el-radio>
                </template>
            </el-form-item>
            <el-form-item label="账户状态">
                <template>
                    <el-radio v-model="add_admin.us_status" :label="true">正常</el-radio>
                    <el-radio v-model="add_admin.us_status" :label="false">禁用</el-radio>
                </template>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="addVisible = false">取 消</el-button>
            <el-button type="primary" @click="doAddAdmin">确 定</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
    data(){
        return {
            user:JSON.parse(localStorage.getItem("user")),
            loading:false,
            admins:[],
            user:null,
            addVisible:false,
            editVisible:false,
            edit_admin:{
                us_username:'',
                us_password:'',
                us_phone:'',
                us_email:'',
                us_status:true,
                us_roleid:1
            },
            add_admin:{
                us_username:'',
                us_password:'',
                us_phone:'',
                us_email:'',
                us_status:true,
                us_roleid:1
            }

        }
    },
    methods:{

        deleteAdmin(row){
            this.$confirm("确定是否删除",'提示',{
                 confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(()=>{
                 this.$http.delete("/user/admin",{
                     params:{
                         userId:row.id
                     }
                 }).then(res=>{
                     if(res.status===200 && res.data.datas==true){
                        this.$message({type: 'success',message: '删除成功!'});
                        this.getAllAdmin()
                     }else{
                        this.$message({ type: 'info',message: '已取消删除' });  
                     }
                 })
                
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
          });   
        })
        },
        doEditAdmin(){
            console.log(this.edit_admin)
            this.$http.put("/user/admin",this.edit_admin).then(res => {
               if(res.status==200 && res.data.datas==true){
                   this.editVisible=!this.editVisible;
                   this.$message.success("提交成功!")
               }else{
                   this.editVisible=!this.editVisible;
                   alert("提交失败")
               }
            })
        },
        editAdmin(row){
            this.edit_admin = row;
            this.editVisible=!this.editVisible;
        },
        doAddAdmin(){
            this.$http.post("/user/admin",this.add_admin).then(res => {
               if(res.status===200 && res.data.datas===true){
                   this.$message.success("添加成功");
                       this.getAllAdmin()
               }else{
                     this.$message.error("添加失败");
                 
               }
               this.addVisible = !this.addVisible;
            })
        },
        getAllAdmin(){
            this.loading = true;
            this.$http.get("/user/admin").then(res => {
            if(res.data != null){
                this.admins = res.data.datas;
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
            localStorage.removeItem('user');
            this.$http.get("/admin/logout");
        }
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