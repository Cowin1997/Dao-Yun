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
              <el-button type="primary" plain @click="addEditVisible = true;sysParam={};isAdd=true">+ 新增</el-button>
          </el-col>
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
        <el-table-column label="操作" style="width: 40%;" align="center" >
            <template slot-scope="scope">
                <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-popconfirm title="确定删除吗？" @confirm="handleDelete(true,scope.$index,scope.row)"  @cancel="handleDelete(false,scope.$index,scope.row)">
                    <el-button icon="el-icon-delete" slot="reference" style="margin-left: 10px"  type="danger" size="small" >
                        删除
                    </el-button>
                </el-popconfirm>
            </template>
        </el-table-column>
      </el-table>


      <el-dialog title="角色管理" :visible.sync="addEditVisible" width="30%">
        <el-form :model="role" :rules="roleRules" ref="form">
          <el-form-item label="角色名称" prop="arg_key">
              <el-input v-model="sysParam.arg_key"></el-input>
          </el-form-item>
          <el-form-item label="角色描述" prop="arg_value">
              <el-input v-model="sysParam.arg_value"></el-input>
          </el-form-item>
        </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addEditVisible = false">取 消</el-button>
                <el-button v-if="isAdd" type="primary" @click="handleCreate(sysParam)">确 定</el-button>
                <el-button v-else type="primary"  @click="handleUpdate(sysParam)">确 定</el-button>
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
import { getRoleList }  from '@/api/sys/role'

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
      role: {},
      currentIndex:0,
      roleRules:{
       
      }


    }
  },
  methods:{
    Search(){





    },


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