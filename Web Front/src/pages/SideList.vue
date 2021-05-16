<template>
  <div>
    <div class="mask" v-if="sideDiaplay" @click="handleClose()"></div>
    <el-card class="box-card" v-if="sideDiaplay">
      <div slot="header" class="clearfix">
        <span>字典列表</span>
        <el-button icon="el-icon-close" @click="handleClose()" style="float: right; padding: 3px 0" type="text"></el-button>
      </div>
      <el-button style="float: right" type="primary" @click="subDict={};dialogFormVisible = true">新增</el-button>
      <el-table :data="subDictList" style="width: 100%">
        <el-table-column prop="type" label="名称"></el-table-column>
         <el-table-column prop="detail" label="用途描述"></el-table-column>
        <el-table-column prop="value" label="数据值"></el-table-column>
        <el-table-column ixed="right" label="操作">
          <template slot-scope="scope">
            <el-button @click="handleEdit(scope.$index, scope.row)" type="text" size="small">编辑</el-button>
            <el-popconfirm title="这是一段内容确定删除吗？" @confirm="handleDelete(true,scope.$index,scope.row)">
              <el-button slot="reference" style="margin-left: 10px" @click="handleDelete(false,scope.$index,scope.row)" type="danger" size="small" >删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form :model="subDict">
        <el-form-item label="名称" :label-width="formLabelWidth">
          <el-input v-model="subDict.type" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="数据值" :label-width="formLabelWidth">
          <el-input v-model="subDict.value" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用途描述" :label-width="formLabelWidth">
          <el-input v-model="subDict.detail" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false;subDict = {}">取 消</el-button>
        <el-button v-if="isCrete" type="primary" @click="handleCreate(subDict)">确 定</el-button>
        <el-button v-else type="primary" @click="handleUpdate(subDict)">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'SideList',
  props: {
    display: {
      default: false,
      type: Boolean,
      deep: true
    },
    subDictList: {
      default:[],
      type: Array,
      deep: true
    },
    id:{
      type:Number,
      deep:true
    }
  },
  data() {
    return {
      isCrete: true,
      formLabelWidth: '120px',
      dialogFormVisible: false,
      currentIndex: 0,
      sideDiaplay: false,
      subDict:{}
    }
  },

  watch:{
    display(val, oldVal) {
      this.sideDiaplay = val
    } 
  },
  created() {
    // console.log(this.display)
  },
  methods: {
    handleClose() {
      //1、父组件可以使用 props 把数据传给子组件。
      //2、子组件可以使用 $emit 触发父组件(@xxx)的自定义事件。
      this.sideDiaplay = false
      this.$emit('close', false)
    },
    handleEdit(index, row) {
      this.dialogFormVisible = true
      this.currentIndex = index
      this.subDict = row
      this.isCrete = false
    },
    handleDelete(bol, index,row) {
      if (bol) {
        this.$http.delete("/data-config/dict-param/"+row.id).then(res => {
          if(res.status==200 && res.data.code==0){
              this.subDictList.splice(this.currentIndex, 1);
              this.$message.success("删除成功!")
            }else{
                this.$message.error("删除失败!")
            }})} 
          else {
        this.currentIndex = index
      } 
    },
    handleCreate(subDict) {
        subDict.type_id = this.id
        this.$http.post("/data-config/dict-param",{"subs":[subDict]}).then(res => {
        if(res.status==200 && res.data.code==0){
              subDict.id = res.data.data;
              this.subDictList.push(subDict)
              this.isCrete = true
              this.subDict = {}
              this.dialogFormVisible = false
              this.$message.success("添加成功！")
        }else{
              this.isCrete = true
              this.edit_add_Visiable = false
              this.$message.error("添加失败！")
        }
      })
    },
    handleUpdate(subDict) {
      this.$http.put("/data-config/dict-param",subDict).then(res => {
         if(res.status==200 && res.data.code==0){
            this.subDictList[this.currentIndex] = subDict
            this.subDict = {}
            this.dialogFormVisible = false
            this.$message.success("修改成功！")
         }else{
            this.subDict = {}
            this.dialogFormVisible = false
            this.$message.error("修改失败！")
         }
      })
    }
  }
}
</script>

<style>
 .mask {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    overflow: auto;
    margin: 0;
    opacity: 0.5;
    background-color: black;
    z-index: 1001;
  }
  .text {
    font-size: 16px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    position: fixed;
    top: 5px;
    z-index: 1002;
    right: 10px;
    width: 600px;
    height: 98%;
    animation: show .5s
  }
  
</style> 

