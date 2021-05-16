<template>
  <div style="height:100%;width:100%;">
      <el-row>
      <el-button type="primary" plain>主要按钮</el-button>
      </el-row>
      <!--角色数据-->
      <el-table :data="roleList" height="80%" border style="width: 100%;font-size:15px;margin-top:10px;">
        <el-table-column prop="id" label="id" align="center" ></el-table-column>
        <el-table-column prop="name" label="角色名称" align="center" ></el-table-column>
        <el-table-column prop="description" label="角色描述" align="center" ></el-table-column>
        <el-table-column prop="gmt_create" label="创建时间" align="center" width="180"></el-table-column>
        <el-table-column  label="权限查询与设置" align="center" width="180">
            <template slot-scope="scope">
               <el-button>权限查询与设置</el-button>
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
    <!-- 权限编辑 -->
  </div>
</template>

<script>
import { getRoleList }  from '@/api/sys/role'

export default {
  data(){
    return {
      currentPage:1,
      pageSize:10,
      totalSize:0,
      roleList:[]
    }
  },
  methods:{
    handleCurrentChange(currentPage){this.currentPage = currentPage},
    handleSizeChange(pageSize){this.pageSize = pageSize}
  },
  created(){
    getRoleList().then(res => {
      console.log(res)
      this.roleList = res.data;
      this.totalSize = res.data.length
    })
  }
  
}
</script>

<style scope>
.pagination{
  text-align:center;
}
</style>