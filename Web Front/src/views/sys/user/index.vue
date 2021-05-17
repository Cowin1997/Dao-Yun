<template>
  <div style="height:100%;width:100%;">
    <el-row>
       <el-col :span="8" style="margin-right:5px;">
              <el-input placeholder="请输入用户名称或手机号码进行搜索" v-model="searchInput" clearable></el-input>
          </el-col>
          <el-col :span="2" style="margin-right:10px;">
                <el-button type="success" icon="el-icon-search" plain @click="Search()" >搜索</el-button>
          </el-col>
          <el-col :span="3">
              <el-button type="primary" plain @click="addVisible=true;">+ 新增</el-button>
          </el-col>
    </el-row>
      <!--角色数据-->
      <el-table :data="userList" height="80%" border style="width: 100%;font-size:15px;margin-top:10px;">
        <el-table-column prop="user.nickname" label="用户名" align="center" ></el-table-column>
        <el-table-column prop="user.phone" label="电话号码" align="center" ></el-table-column>
        <el-table-column prop="userRole==null||userRole==undefined?'undefined':userRole.name" label="账户类型" align="center" >
          <template slot-scope="scope" >
            <el-tag type="success">{{scope.row.userRole==null||scope.row.userRole==undefined?'undefine':scope.row.userRole.name}}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="user.enabled" label="账户状态" align="center">
          <template slot-scope="scope">
              <el-switch
                v-model="scope.row.user.enabled"
                :disabled="user.id === scope.row.user.id"
                active-color="#409EFF"
                inactive-color="#F56C6C"
                @change="changeEnabled(scope.row, scope.row.user.enabled)"
              />
          </template>
        </el-table-column>
        <el-table-column prop="user.gmt_create" label="创建时间" align="center" width="180"></el-table-column>
        <el-table-column label="操作" style="width: 20%;" align="center" >
            <template slot-scope="scope">
                <el-button type="text" icon="el-icon-edit"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
               <el-popconfirm title="确定删除吗？" @confirm="handleDelete(true,scope.$index,scope.row)"  @cancel="handleDelete(false,scope.$index,scope.row)">
                    <el-button icon="el-icon-delete" slot="reference" style="margin-left: 10px"  type="danger" size="small" >
                        删除
                    </el-button>
                </el-popconfirm>
            </template>
        </el-table-column>
      </el-table>

      <el-dialog title="用户编辑管理" :visible.sync="editVisible" width="30%">
          <el-form :model="editUser" :rules="editRules" ref="editform">
            <el-form-item  label="用户名" clearable prop="nickname">
                <el-input v-model="editUser.nickname" clearable></el-input>
            </el-form-item>
            <el-form-item label="电话号码" prop="phone">
                  <el-input v-model="editUser.phone" clearable></el-input>
            </el-form-item>
              <el-form-item label="密码">
                  <el-input type="password" placeholder="需要修改密码则输入" v-model="editUser.password" clearable></el-input>
            </el-form-item>
             </el-form>
              <span slot="footer" class="dialog-footer">
                  <el-button @click="editVisible = false">取 消</el-button>
                  <el-button type="primary"  @click="handleUpdate(editUser)">确 定</el-button>
              </span>
         
        </el-dialog>




        <el-dialog title="用户添加管理" :visible.sync="addVisible" width="30%">
          <el-form :model="addUser" :rules="addRules" ref="addform">
            <el-form-item label="用户名" clearable prop="nickname">
                <el-input v-model="addUser.nickname" clearable></el-input>
            </el-form-item>
            <el-form-item label="电话号码" prop="phone">
                  <el-input v-model="addUser.phone" clearable></el-input>
            </el-form-item>
             <el-form-item label="角色类型" prop="role">
                <el-select v-model="addUser.role" placeholder="请选择角色类型" @focus="loadingRoleList">
                  <el-option v-for="item in roleList" :key="item.id" :label="item.name" :value="item.id" ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="状态" prop="enabled">
              <template >
                <el-radio v-model="addUser.enabled" :label="true">启用</el-radio>
                <el-radio v-model="addUser.enabled" :label="false">禁用</el-radio>
                </template>
            </el-form-item>


            
             </el-form>
              <span slot="footer" class="dialog-footer">
                  <el-button @click="addVisible = false">取 消</el-button>
                  <el-button type="primary"  @click="handleCreate(addUser)">确 定</el-button>
              </span>
         
        </el-dialog>

    <!-- 分页 -->
    <el-pagination class="pagination"  
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 30, 40]"
      :page-size= pageSize
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalSize">
    </el-pagination>
  </div>
</template>

<script>
import { getUserList,lock,unlock,editUserx,addUserx  }  from '@/api/sys/user'
import { getRoleList }  from '@/api/sys/role'
export default {
 data(){
    return {
      addVisible:false,
      addUser:{},
      roleList:[],
      currentPage:1,
      pageSize:10,
      totalSize:0,
      userList:[],
      user: JSON.parse(localStorage.getItem("user")).user,
      userStatus:{
        true: '启用',
        false: '禁用'
      },
      searchInput: '',
      editUser:{},
      addUser:{
        enabled:true
      },
      editVisible:false,
      addVisible:false,
      editRules:{
          phone :[{ required: true, message: '请输入手机号', trigger: 'blur' },
            { pattern: /^1[3|5|7|8|9]\d{9}$/, message: '请输入正确的号码格式', trigger: 'blur'}],
          nickname: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
      },
      addRules:{
           nickname: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
           phone :[{ required: true, message: '请输入手机号', trigger: 'blur' },
            { pattern: /^1[3|5|7|8|9]\d{9}$/, message: '请输入正确的号码格式', trigger: 'blur'}],
          role: [{ required: true, message: '请输入选择用户类型', trigger: 'blur' }],
          enabled:[{ required: true, message: '请输入选择用户状态', trigger: 'blur' }]
      }
    }
  },
  methods:{

    handleCreate(addUser){
        console.log(addUser)

       this.$refs.addform.validate(valid => {
          if(valid){
              addUserx({name:addUser.nickname, ...addUser}).then(res => {
                console.log(res)
                if(res.success==true){
                getUserList( { page:this.currentPage,size:this.pageSize } ).then(res => {
                      this.userList = res.data.pageData;
                      this.totalSize = res.data.totalSize
                    })
                this.$message.success("添加成功")
                this.addVisible = false;
                }else{
                   this.$message.error(res.error)
                    this.addVisible = false;
                }
              })

          }else{
            alert("表单验证失败")
          }





       })






        this.addVisible = false;
    },

    loadingRoleList(){ //加载角色列表
        getRoleList({page:1,size:50}).then(res => {
          this.roleList = res.data.pageData
        })
    },
    Search(){ //进行模糊搜索
        if(this.searchInput==null ||this.searchInput.trim()==''){
            getUserList( { page:this.currentPage,size:this.pageSize} ).then(res => {
                    this.userList = res.data.pageData;
                    this.totalSize = res.data.totalSize
                  })
        }else{
            getUserList( { page:this.currentPage,size:this.pageSize,search:this.searchInput} ).then(res => {
              this.userList = res.data.pageData;
              this.totalSize = res.data.totalSize
            })
        
        }
    },
    handleDelete(bol,index,row){
      if(bol==true){ //删除

          lock({id: row.user.id}).then(res => {
             if(res.success === true) {
              row.user.enabled = false;
             this.$message.success("删除成功");
             
             }
             else{this.$message.success("删除失败");data.user.enabled = !data.user.enabled} })



      }


    },



    handleUpdate(editUser){
        console.log(editUser);

        this.$refs.editform.validate( valid => {
          if(valid){
             editUserx({
          id:editUser.id,
          name:editUser.nickname,
          phone:editUser.phone,
          password:editUser.password
          }).then(res => {
            if(res.success==true){
              this.$message.success("修改成功!")
           
              getUserList( { page:this.currentPage,size:this.pageSize } ).then(res => {
                    this.userList = res.data.pageData;
                    this.totalSize = res.data.totalSize
                  })

                 this.editVisible = false;

            }else{
              this.$message.error("修改失败!")
              this.editVisible = false;
            }
          })
          }else{
            alert("表单验证失败")
          }
        }) 













        // editUser({
        //   id:editUser.id,
        //   name:editUser.nickname,
        //   phone:editUser.phone,
        //   password:editUser.password
        // })
    },
    handleEdit(index,row){ //更新
        this.editUser = row.user;
  
        this.editVisible = true
        
    },
    handleCurrentChange(currentPage){this.currentPage = currentPage;getUserList( { page:this.currentPage,size:this.pageSize } ).then(res => {
      this.userList = res.data.pageData;
      this.totalSize = res.data.totalSize
    })},
    handleSizeChange(pageSize){this.pageSize = pageSize;getUserList( { page:this.currentPage,size:this.pageSize } ).then(res => {
      this.userList = res.data.pageData;
      this.totalSize = res.data.totalSize
    })},
    // 改变状态
    changeEnabled(data, val) {
      this.$confirm('此操作将 "' + this.userStatus[val] + '" ' + data.user.nickname + ', 是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { //确定
        // data.user.id
        if(val){ //启用
          unlock({id: data.user.id}).then(res => { 
            if(res.success === true){ 
              this.$message.success("启用成功"); 
              }
              else{
                this.$message.success("启用失败");
                data.user.enabled = !data.user.enabled;
              }});
        }
        else{
          lock({id: data.user.id}).then(res => {
             if(res.success === true) {
             this.$message.success("禁用成功");}
             else{this.$message.success("禁用失败");data.user.enabled = !data.user.enabled} })
        }
      }
      ).catch(() => { //取消
        data.user.enabled = !data.user.enabled
      })
    },
  },
  created(){
    getUserList( { page:this.currentPage,size:this.pageSize } ).then(res => {
      this.userList = res.data.pageData;
      this.totalSize = res.data.totalSize
    })
  }
}
</script>

<style scoped>
.pagination{
  text-align:center;
}
.red{
  color: red;
}




</style>

