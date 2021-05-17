<template>
  <div style="height:100%;width:100%;">
        <el-row>
          <el-col :span="8" style="margin-right:5px;">
              <el-input placeholder="请输入角色名称或描述进行搜索" v-model="searchInput" clearable></el-input>
          </el-col>
          <el-col :span="2" style="margin-right:10px;">
                <el-button type="success" icon="el-icon-search" plain @click="Search()" >搜索</el-button>
          </el-col>
          <el-col :span="3">
              <el-button type="primary" plain @click="addEditVisible=true;isAdd=true;">+ 新增</el-button>
          </el-col>
        </el-row>
      <!--角色数据-->
      <el-table :data="roleList" height="80%" border style="width: 100%;font-size:15px;margin-top:10px;">
        <el-table-column prop="id" label="id" align="center" ></el-table-column>
        <el-table-column prop="name" label="角色名称" align="center" ></el-table-column>
        <el-table-column prop="description" label="角色描述" align="center" ></el-table-column>
        <el-table-column prop="level" label="角色级别" align="center" ></el-table-column>
        <el-table-column prop="gmt_create" label="创建时间" align="center" width="180"></el-table-column>
        <el-table-column  label="权限查询与设置" align="center" width="180">
            <template slot-scope="scope">
               <el-button>权限查询与设置</el-button>
            </template>
        </el-table-column>
        <el-table-column label="操作" style="width: 40%;" align="center" >
            <template slot-scope="scope">
                <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-popconfirm title="删除该角色将锁定该角色所有用户, 确定删除吗？" @confirm="handleDelete(true,scope.$index,scope.row)"  @cancel="handleDelete(false,scope.$index,scope.row)">
                    <el-button icon="el-icon-delete" slot="reference" style="margin-left: 10px"  type="danger" size="small" >
                        删除
                    </el-button>
                </el-popconfirm>
            </template>
        </el-table-column>
      </el-table>


      <el-dialog title="角色管理" :visible.sync="addEditVisible" width="30%">
        <el-form :model="role" :rules="roleRules" ref="form">
          <el-form-item  label="角色名称" clearable prop="name">
              <el-input v-model="role.name" clearable></el-input>
          </el-form-item>
          <el-form-item label="角色级别">
            <template>
                <el-input-number v-model="role.level" @change="handleChange" :min="1" :max="20" label="角色级别"></el-input-number>
            </template>
          </el-form-item>
          <el-form-item label="描述信息">
              <el-input v-model="role.description" clearable></el-input>
          </el-form-item>
        </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addEditVisible = false">取 消</el-button>
                <el-button v-if="isAdd" type="primary" @click="handleCreate(role)">确 定</el-button>
                <el-button v-else type="primary"  @click="handleUpdate(role)">确 定</el-button>
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
    <!-- 权限编辑 -->
  </div>
</template>

<script>
import { getRoleList,addRole,updateRole,deleteRole }  from '@/api/sys/role'

export default {
  data(){
    return {
      currentPage:1,
      pageSize:10,
      totalSize:0,
      roleList:[],
      searchInput:'',
      isAdd: true,
      addEditVisible:false,
      role: {
        level:1
      },
      currentIndex:0,
      roleRules:{
        'name':[{ required: true, message: '角色名字不能为空', trigger: 'blur' }],
        'level':[{ required: true, message: '角色等级不能为空', trigger: 'blur' }]
      },
      


    }
  },
  methods:{
    handleDelete(bol,index,row){
        if(bol==true){
          deleteRole(row.id).then(res => {
            if(res.success==true){
              this.$message.success("删除角色成功,该角色类型用户默认被禁用")
              this.roleList.splice(index,1)
            }else{
              this.$message.error(res.error)
            }
          })

        }else{
          console.log('删除取消')
        }
    },


    handleEdit(index, row){
        this.isAdd = false;
        this.addEditVisible = true;
        this.role = row
       // console.log(this.role)
    },
   handleUpdate(role){
      if(!this.isAdd){ // 添加
         this.$refs.form.validate(valid => {
           if(valid){
             updateRole(role).then(res => {
               if(res.success===true){
                    getRoleList({page:this.currentPage,size:this.pageSize}).then(res => {
          this.roleList = res.data.pageData;
          this.totalSize = res.data.totalSize
      }) ;           
                   // this.$options.methods.reload22(this.currentPage,this.pageSize);
                    this.addEditVisible = false;
                    this.$message.success(修改成功);
                    this.isAdd = true;
               }else{
                 this.addEditVisible = false;
                 this.$message.error(res.error)
               }
             })
           }else{
             alert("表单验证失败")
           }
         })
      }
    },
    Search(){
      if(this.searchInput!=''){
      getRoleList({page:this.currentPage,size:this.pageSize,search:this.searchInput}).then(res => {
      this.roleList = res.data.pageData;
      this.totalSize = res.data.totalSize
      })
      }else{
         getRoleList({page:this.currentPage,size:this.pageSize}).then(res => {
      this.roleList = res.data.pageData;
      this.totalSize = res.data.totalSize
      })}
      },
    handleCreate(role){
      if(this.isAdd){// 添加
           this.$refs.form.validate( valid => {
             if(valid){
                role['creator'] = JSON.parse(localStorage.getItem('user')).user.id;
                  addRole(role).then( res => {
                  if(res.success===true){
                     
                      getRoleList({page:this.currentPage,size:this.pageSize}).then(res => {this.roleList = res.data.pageData;
                      this.totalSize = res.data.totalSize
                        })
                      this.$message.success("添加成功")
                      this.addEditVisible = false;
                      
                  }else{
                      this.$message.error(res.error);
                      this.addEditVisible = false;
                  }
                })
             }else{
               alert("表单验证失败!")
             }
           })
      }
    },
 
    handleChange(value){this.role.level = value;},

    handleCurrentChange(currentPage){this.currentPage = currentPage;getRoleList({page:this.currentPage,size:this.pageSize}).then(res => {this.roleList = res.data.pageData;
                      this.totalSize = res.data.totalSize
                        })},
    handleSizeChange(pageSize){this.pageSize = pageSize; getRoleList({page:this.currentPage,size:this.pageSize}).then(res => {this.roleList = res.data.pageData;
                      this.totalSize = res.data.totalSize
                        })},
    reload22(page,size){
       getRoleList({page:page,size:size}).then(res => {
          this.roleList = res.data.pageData;
          this.totalSize = res.data.totalSize
      })
    }
  },
  created(){
    getRoleList({page:this.currentPage,size:this.pageSize}).then(res => {
      this.roleList = res.data.pageData;
      this.totalSize = res.data.totalSize
    })
  }
  
}
</script>

<style scope>
.pagination{
  text-align:center;
}
</style>