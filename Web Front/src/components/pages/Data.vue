<template>
    <div style="height:100%;width:100%;"> 
         <el-form :inline="true"  class="demo-form-inline" style="border-radius: 30px;"> 
             <el-form-item label="分类名">
                <el-input placeholder="请输入分类名" v-model="input_name" clearable ></el-input>
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
            <el-table-column prop="key" label="分类名" align="center"></el-table-column>
            <el-table-column prop="value" label="值" align="center">
            </el-table-column>
            <el-table-column label="操作" style="width: 40%;" align="center" >
                <template slot-scope="scope">
                    <el-button type="text" icon="el-icon-edit" @click="edit(scope.row)">编辑</el-button>
                    <el-button type="text" icon="el-icon-delete" class="red">删除</el-button>
                </template>
            </el-table-column>
       </el-table>
       <el-dialog
        title="修改"
        :visible.sync="dialogVisible"
        width="30%"
        >
        <el-form>
        <el-form-item label="值">
            <el-input v-model="newValue"></el-input>
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
            code:''

        }
    },
    methods:{
        edit(row){//row.code
            this.dialogVisible = !this.dialogVisible;
            console.log(this.newValue)
            this.code = row.code
        },
        submitEdit(){
            this.dialogVisible = !this.dialogVisible;
             this.$http.post("/data-config",qs.stringify({
                "code":this.code,
                "value":this.newValue
            }),{
                headers:{"Content-Type":"application/x-www-form-urlencoded"}
            }).then(res => {
                if(res.status === 200 ){
                   this.$message.success("修改成功")
                   this.SearchAll()
                   
                }
                else{
                    this.$message.error("修改失败")
                }
           });
        },
        Search(){},
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
        }
    }

}
</script>

<style>

</style>