<template>
  <div style="height:100%;width:100%;">
    <el-row>
      <el-button type="primary" plain>添加用户</el-button>
    </el-row>
      <!--角色数据-->
      <el-table :data="userList" height="80%" border style="width: 100%;font-size:15px;margin-top:10px;">
        <el-table-column prop="user.nickname" label="用户名" align="center" ></el-table-column>
        <el-table-column prop="user.phone" label="电话号码" align="center" ></el-table-column>
        <el-table-column prop="userRole.name" label="账户类型" align="center" >
          <template slot-scope="scope" >
            <el-tag type="success">{{scope.row.userRole.name}}</el-tag>
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
                <el-button type="text" icon="el-icon-edit"  >编辑</el-button>
                <el-button type="text" icon="el-icon-delete" class="red"  >删除</el-button>
            </template>
        </el-table-column>
      </el-table>


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
import { getUserList }  from '@/api/sys/user'

export default {
 data(){
    return {
      currentPage:1,
      pageSize:10,
      totalSize:0,
      userList:[],
      user: JSON.parse(localStorage.getItem("user")).user,
      userStatus:{
        true: '启用',
        false: '禁用'
      }
    }
  },
  methods:{
    handleCurrentChange(currentPage){this.currentPage = currentPage},
    handleSizeChange(pageSize){this.pageSize = pageSize},
    // 改变状态
    changeEnabled(data, val) {
      this.$confirm('此操作将 "' + this.userStatus[val] + '" ' + data.user.nickname + ', 是否继续？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
      
      }
      ).catch(() => {
        data.user.enabled = !data.user.enabled
      })
    },
  },
  created(){
    getUserList( { page:this.currentPage,size:this.pageSize } ).then(res => {
  
      console.log(res)
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

