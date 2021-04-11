<template>
    <div style="height:100%;width:100%;"> 
         <el-form :inline="true"  class="demo-form-inline" style="border-radius: 30px;"> 
             <el-form-item label="分类名">
                <el-input placeholder="请输入分类码" v-model="code" clearable ></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="Search">查询</el-button>
            </el-form-item>
             <el-form-item>
                <el-button type="primary" @click="add">+ 新增</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="SearchAll">查询所有</el-button>
            </el-form-item>
         </el-form>
          <el-table :data="items" height="80%" border style="width: 100%;font-size:20px;">
            <el-table-column prop="key" label="键" align="center"></el-table-column>
            <el-table-column prop="code" label="分类码" align="center"></el-table-column>
            <el-table-column prop="value" label="值" align="center"></el-table-column>
            <el-table-column prop="info" label="说明" align="center"></el-table-column>
            <el-table-column label="操作" style="width: 40%;" align="center" >
                <template slot-scope="scope">
                    <el-button type="text" icon="el-icon-edit" @click="edit(scope.row)">编辑</el-button>
                    <el-button type="text" icon="el-icon-delete" class="red" @click="del(scope.$index,scope.row)">删除</el-button>
                </template>
            </el-table-column>
       </el-table>
       <el-dialog
        title="修改"
        :visible.sync="dialogVisible"
        width="30%"
        >
        <el-form :model="row">
        <el-form-item label="分类码">
            <el-input v-model="row.code" disabled='true'></el-input>
        </el-form-item>
        <el-form-item label="键">
            <el-input v-model="row.key"></el-input>
        </el-form-item>
        <el-form-item label="值">
            <el-input v-model="row.value"></el-input>
        </el-form-item>
        <el-form-item label="说明">
            <el-input v-model="row.info"></el-input>
        </el-form-item>
        </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="submitEdit">确 定</el-button>
    </span>
</el-dialog>
    </div>
    
</template>

<script>
import qs from 'qs'
export default {
    data(){
        return {

            input_name:'',
            items:[],
            newValue:'',
            dialogVisible:false,
            code:'',
            row:'',
            id:''

        }
    },
    methods:{
        edit(row){//row.code
            this.dialogVisible = !this.dialogVisible;
            console.log(this.newValue)
            this.id = row.id
            this.row = row
        },
        submitEdit(){
            this.dialogVisible = !this.dialogVisible;
             this.$http.post("/data-config",this.row).then(res => {
                if(res.status === 200 ){
                    console.log(this.row)
                   this.$message.success("修改成功")
                   this.SearchAll()
                   
                }
                else{
                    this.$message.error("修改失败")
                }
           });
        },
        add(){},
        SearchAll(){
            this.$http.get("/data-config").then(res => {
            if(res.status === 200 &&res.data.status===0 ){
                this.items = res.data.datas;
            }
            else{
                this.items = null;
            }
           });
        },
        del(index,row){
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            }).then(() => {
            this.$http.delete("/data-config/"+row.id).then(res =>{
                if(res.status==200 && res.data.status==0){
                    this.$message.success("删除成功!")
                    this.SearchAll()
                }
            })
            }).catch((e) => {})
        
        }
    }

}
</script>

<style>

</style>