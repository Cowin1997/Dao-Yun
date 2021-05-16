<template>
  <div style="height:100%;width:100%;"> 
    <el-row>
        <el-button type="primary" plain  @click="addEditVisiable = true">+ 新增</el-button>
    </el-row>
    <el-table :data="dictList" style="width: 100%; margin-top:10px;">
        <el-table-column prop="dict.code" label="字典码"></el-table-column>
        <el-table-column prop="dict.type" label="字典名称"></el-table-column>
        <el-table-column prop="dict.details" label="用途描述"></el-table-column>
        <el-table-column fixed="right" label="操作">
            <template slot-scope="scope">
                <el-button icon="el-icon-edit" type="text" size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                <el-button icon="el-icon-setting" type="text" size="small" @click="handleSetting(scope.$index, scope.row)">字典配置</el-button>
                    <el-popconfirm title="确定删除吗？" @confirm="handleDelete(true,scope.$index,scope.row)"  @cancel="handleDelete(false,scope.$index,scope.row)">
                        <el-button icon="el-icon-delete" slot="reference" style="margin-left: 10px"  type="danger" size="small" >
                            删除
                        </el-button>
                    </el-popconfirm>
            </template>
      </el-table-column>
    </el-table>
    <side-list :display="sideListDisplay"  :pid ="dict.id" :subDictList="subDictList"  @close="handleSideList($event)"></side-list>

    <!-- 添加或者编辑表单 -->
    <el-dialog title="数据字典" :visible.sync="addEditVisiable" >
        <el-form :model="dict" :rules="dictRules" ref="form">
            <el-form-item label="字典码" prop="code">
              <el-input v-model="dict.code" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="字典名称" prop="type">
              <el-input v-model="dict.type" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="用途描述">
              <el-input v-model="dict.details" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="addEditVisiable = false;dict={};">取 消</el-button>
            <el-button v-if="isAdd" type="primary" @click="handleCreate(dict)">确 定</el-button>
            <el-button v-else type="primary"  @click="handleUpdate(dict)">确 定</el-button>
          </span>
    </el-dialog>

     <!-- 分页 -->
    <el-pagination style="text-align:center;margin-top:10px;"
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
import { getSysDict,addSysDict,updateSysDict,deleteSysDict }  from '@/api/sys/dict'
import SideList from './SideList'

export default {
    components: {
        SideList
    },
    data(){
        return {
        currentPage:1,
        pageSize:10,
        totalSize:0,
        dictList:[],
        subDictList:[],
        sideListDisplay: false, //侧边字典配置
        addEditVisiable:false, // 添加编辑
        dict:{
        },
        isAdd: true, //默认为添加操作
        dictRules:{
            code : [{ required: true, message: '请输入字典码', trigger: 'blur' }],
            type: [{ required: true, message: '请输入字典名称', trigger: 'blur' } ]    
        },
        currentIndex:0
        }
    },
       
  
    methods:{
        handleCurrentChange(currentPage){
            this.currentPage = currentPage
            getSysDict({page:this.currentPage,size:this.pageSize}).then(res =>{
                this.totalSize = res.data.totalSize
                this.dictList = res.data.pageData
            })},
        handleSizeChange(pageSize){this.pageSize = pageSize
          getSysDict({page:this.currentPage,size:this.pageSize}).then(res =>{
                this.totalSize = res.data.totalSize
                this.dictList = res.data.pageData
            })
        },
        handleSetting(index, row) {
            // this.dict = row
            this.subDictList = row.sub
            this.sideListDisplay = true
            this.dict  = row.dict
        },
        handleSideList(bol) { // 打开侧边栏
            this.sideListDisplay = bol
        },
        handleCreate(dict){ //添加字典
            this.$refs.form.validate( valid => {
                if(valid){
                    this.dict['creator'] = JSON.parse(localStorage.getItem('user')).user.id;
                    addSysDict(this.dict).then(res => {
                        if(res.success===true){ //成功
                            this.$message.success("添加成功!")
                            this.dict.id = res.data
                            this.dictList.push({dict:this.dict,sub:[]})
                            this.totalSize ++
                            this.addEditVisiable = false;
                            this.isAdd = true;
                }})}else{
                    alert("表单验证失败")
                }
                 getSysDict({page:this.currentPage,size:this.pageSize}).then(res =>{
                this.dictList = res.data.pageData
        })
            })
      
        },
        handleEdit(index,row){ // 打开更新对话框
            this.dict = row.dict
            this.currentIndex = index;
            this.isAdd = false
            this.addEditVisiable = true;
        },
        handleUpdate(dict){ // 更新字典
            this.$refs.form.validate( valid => {
                if(valid){
                    //请求接口
                    var requestData = {
                        code: this.dict.code,
                        details:this.dict.details,
                        id:this.dict.id,
                        type:this.dict.type,
                        reviser: JSON.parse(localStorage.getItem('user')).user.id
                    }
                    updateSysDict(requestData).then(res => {
                    if(res.success === true){ //请求成功
                            this.dictList[this.currentIndex].dict = this.dict  // 更新页面
                            this.$message.success("修改成功!");
                            this.isAdd = true;
                            this.addEditVisiable = false;
                    }else{
                        this.$message.error(res.error);
                        this.addEditVisiable = false;
                    }
                    })}else{
                        alert("表单验证失败")
                    }
            })
        },
        handleDelete(bol, index, row){
            if(bol==true){
                var id = row.dict.id;
                deleteSysDict(id).then(res => {
                    this.dictList.splice(index,1)
                })
            }else{
             
            }
        }
        

    },
    created(){
        getSysDict({page:this.currentPage,size:this.pageSize}).then(res =>{
          this.totalSize = res.data.totalSize
          this.dictList = res.data.pageData
        })
    }
}
</script>

<style>
.red {
    color: red;
}
</style>