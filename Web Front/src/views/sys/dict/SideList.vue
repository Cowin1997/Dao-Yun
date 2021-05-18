<template>
  <div>
    <div class="mask" v-if="sideDiaplay" @click="handleClose()"></div>
    <el-card class="box-card" v-if="sideDiaplay">
      <div slot="header" class="clearfix">
        <span>字典列表</span>
        <el-button icon="el-icon-close" @click="handleClose()" style="float: right; padding: 3px 0" type="text"></el-button>
      </div>
      <el-button style="float: right" type="primary"  :disabled="!hasPerm('dict:add')" @click="handleAdd()">新增</el-button>
      <el-table :data="subDictList" style="width: 100%">
        <el-table-column prop="details" label="名称"></el-table-column>
        <el-table-column prop="value" label="数据值"></el-table-column>
        <el-table-column ixed="right" label="操作" >
          <template slot-scope="scope">
            <el-button type="text" size="small"  :disabled="!hasPerm('dict:edit')" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-popconfirm title="这是一段内容确定删除吗" @confirm="handleDelete(true,scope.$index,scope.row)" @cancel="handleDelete(false,scope.$index,scope.row)"> 
              <el-button slot="reference"  :disabled="!hasPerm('dict:del')" style="margin-left: 10px" type="danger" size="small" >删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog title="编辑" :visible.sync="dialogFormVisible">
      <el-form :model="subDict" :rules="subDictRules" ref="form">
        <el-form-item label="名称" :label-width="formLabelWidth" prop="details">
          <el-input v-model="subDict.details" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="数据值" :label-width="formLabelWidth" prop="value">
          <el-input v-model="subDict.value" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible=false">取 消</el-button>
        <el-button v-if="isAdd" type="primary" @click="handleCreate(subDict)">确 定</el-button>
        <el-button v-else type="primary" @click="handleUpdate(subDict)">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { updateSubSysDict,addSubSysDict,deleteSubSysDict }  from '@/api/sys/dict'

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
    pid:{
      type:Number,
      deep:true
    }
  },
  data() {
    return {
      isAdd: true,
      formLabelWidth: '120px',
      dialogFormVisible: false,
      currentIndex: 0,
      sideDiaplay: false,
      subDict:{},
      subDictRules:{
            details : [{ required: true, message: '字典项名称不能为空', trigger: 'blur' }],
            value: [{ required: true, message: '字典项值不能为空', trigger: 'blur' } ]    
        },
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
    handleEdit(index, row){
      this.currentIndex = index
      this.subDict = row;
      this.isAdd = false
      this.dialogFormVisible = true
    },
    handleUpdate(subDict){ //更新字典项
        this.$refs.form.validate( valid => {
          if(valid){
              var requestData = {
                reviser: JSON.parse(localStorage.getItem('user')).user.id,
                id:this.subDict.id,
                value:this.subDict.value,
                details:this.subDict.details
            }
              updateSubSysDict(requestData).then(res => {
                    if(res.success === true){
                      this.$message.success("修改成功!");
                      this.isAdd = true;
                      this.dialogFormVisible = false;
                    }else{
                      this.$message.error(res.error);
                      this.dialogFormVisible = false;
                    }
                })
          }else{
            alert("表单验证失败")
          }
        })
    },

    handleAdd(){
        this.subDict = {},
        this.isAdd = true;
        this.dialogFormVisible = true
    },
    handleCreate(subDict){ //添加字典项
        this.$refs.form.validate( valid => {
          if(valid){
            var questData = {
              creator: JSON.parse(localStorage.getItem('user')).user.id,
              details: this.subDict.details,
              value: this.subDict.value,
              parent_id: this.pid
            }
              addSubSysDict(questData).then( res => {
                if(res.success === true){
                    this.$message.success("添加成功!");
                    this.isAdd = true;
                    this.subDict.id = res.data
                    this.subDictList.push(this.subDict)
                    this.dialogFormVisible = false;
                }else{
                   this.$message.error(res.error);
                    this.dialogFormVisible = false;
                }
              })
          }else{
            alert("表单验证失败")
          }
        })
    },
    handleDelete(bol,index,row){
      if(bol==true){
        //row.id
          deleteSubSysDict(row.id).then(res => {
            if(res.success == true){
              this.subDictList.splice(index,1);
              this.$message.success("删除成功!");
            }else{
               this.$message.error(res.error);
            }
          })
      }else{
       
      }
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

