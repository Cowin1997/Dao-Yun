<template>
    <div style="height:100%;width:100%;"> 
      <el-form :inline="true"  class="demo-form-inline" style="border-radius: 30px;">
          <el-form-item>
              <el-button type="primary" @click="edit_add_Visiable = true" >+新增</el-button>
          </el-form-item>
      </el-form>
    
    <el-table :data="dictList" style="width: 100%">
     
      <el-table-column prop="code" label="字典码"></el-table-column>
      <el-table-column prop="type" label="字典名称"></el-table-column>
      <el-table-column prop="detail" label="用途描述"></el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button icon="el-icon-edit" @click="handleEdit(scope.$index, scope.row)" type="text" size="small">编辑</el-button>
          <el-button icon="el-icon-setting" @click="handleSetting(scope.$index, scope.row)" type="text" size="small">字典配置</el-button>
          <el-popconfirm title="确定删除吗？" @confirm="handleDelete(true,scope.$index,scope.row)" @cancel="handleDelete(false,scope.$index,scope.row)">
            <el-button icon="el-icon-delete" slot="reference" style="margin-left: 10px"  type="danger" size="small">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table> 
    <side-list :display="sideListDisplay" :subDictList="subDictList" :id="dict.id" @close="handleSideList($event)"></side-list>
  
    <el-dialog title="数据字典" :visible.sync="edit_add_Visiable">
        <el-form :model="dict">
            <el-form-item label="字典码" :label-width="formLabelWidth">
              <el-input v-model="dict.code" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="字典名称" :label-width="formLabelWidth">
              <el-input v-model="dict.type" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="用途描述" :label-width="formLabelWidth">
              <el-input v-model="dict.detail" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
            <el-button @click="edit_add_Visiable = false;dict = {}">取 消</el-button>
            <el-button v-if="isCrete" type="primary" @click="handleCreate(dict)">确 定</el-button>
            <el-button v-else type="primary" @click="handleUpdate(dict)">确 定</el-button>
          </span>
    </el-dialog>
  
  </div>
</template>

<script>
import SideList from './SideList'

export default {
  components: {
    SideList
  },
  data() {
    return {
      dict: {},
      subDictList:[],
      dictList: [],
      formLabelWidth: '120px',
      isCrete: true,
      sideListDisplay: false,
      edit_add_Visiable:false,
      currentIndex: 0,
    }
  },
  beforeCreate(){
      this.$http.get("/data-config/dict-param-list").then(res=>{
         if(res.status==200 && res.data.code==0) this.dictList = res.data.data;
      })
  },
  methods: {
    handleSetting(index, row) {
      this.dict = row
      if("subs" in row) this.subDictList = row.subs;
      this.sideListDisplay = true
    },
    handleSideList(bol) {
      this.sideListDisplay = bol
    },
    handleDelete(bol,index,row) {
      if (bol) {
        this.$http.delete("/data-config/dict-param/"+row.id).then(res => {
            if(res.status==200 && res.data.code==0){
              this.dictList.splice(index, 1);
              this.$message.success("删除成功!")
            }else{
              this.$message.error("删除失败!")
            }
        })


  

      }
    },
    handleCreate(dict) {
      this.$http.post("/data-config/dict-param",this.dict).then(res => {
        if(res.status==200 && res.data.code==0){
              dict.id = res.data.data;
              this.dictList.push(dict)
              this.isCrete = true
              this.dict = {}
              this.edit_add_Visiable = false
              this.$message.success("添加成功！")
        }else{
              this.isCrete = true
              this.edit_add_Visiable = false
              this.$message.error("添加失败！")
        }
      })
    },
    handleEdit(index, row) {
      this.edit_add_Visiable = true
      this.currentIndex = index
      this.dict = row
      this.isCrete = false
    },
    handleUpdate(dict) {
      this.$http.put("/data-config/dict-param",dict).then(res => {
         if(res.status==200 && res.data.code==0){
            this.dictList[this.currentIndex] = dict
            this.dict = {}
            this.edit_add_Visiable = false
            this.$message.success("修改成功！")
         }else{
            this.dict = {}
            this.edit_add_Visiable = false
            this.$message.error("修改失败！")
         }
      })



     
    }
  }
}
</script>

<style>

</style>
