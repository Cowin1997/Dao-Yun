<template>
    <div style="height:100%;width:100%;"> 
            <el-form :inline="true"  class="demo-form-inline" style="border-radius: 30px;">
                <el-form-item>
                    <el-button type="primary" @click="addVisiable = true" >+新增</el-button></el-form-item>
            </el-form>

          <el-table :data="sysParamList" height="80%" border style="width: 100%;font-size:20px;">
            <el-table-column prop="id" label="ID" align="center"></el-table-column>
            <el-table-column prop="arg_key" label="键" align="center"></el-table-column>
            <el-table-column prop="arg_value" label="值" align="center"></el-table-column>
            <el-table-column prop="arg_desc" label="说明" align="center"></el-table-column>
            <el-table-column label="操作" style="width: 40%;" align="center" >
                <template slot-scope="scope">
                    <el-button type="text" icon="el-icon-edit" @click="Edit(scope.row)">编辑</el-button>
                    <el-button type="text" icon="el-icon-delete" class="red" @click="Delete(scope.$index,scope.row)">删除</el-button>
                </template>
            </el-table-column>
       </el-table>
       <el-dialog title="修改" :visible.sync="editVisiable" width="30%">
                <el-form :model="sysParam">
                <el-form-item label="ID">
                    <el-input v-model="sysParam.id" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="键">
                    <el-input v-model="sysParam.arg_key"></el-input>
                </el-form-item>
                <el-form-item label="值">
                    <el-input v-model="sysParam.arg_value"></el-input>
                </el-form-item>
                <el-form-item label="说明">
                    <el-input v-model="sysParam.arg_desc"></el-input>
                </el-form-item>
                </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="editVisiable = false">取 消</el-button>
            <el-button type="primary" @click="doEdit">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="添加" :visible.sync="addVisiable" width="30%">
                <el-form :model="sysParam">
                <el-form-item label="键">
                    <el-input v-model="sysParam.arg_key"></el-input>
                </el-form-item>
                <el-form-item label="值">
                    <el-input v-model="sysParam.arg_value"></el-input>
                </el-form-item>
                <el-form-item label="说明">
                    <el-input v-model="sysParam.arg_desc"></el-input>
                </el-form-item>
                </el-form>
            <span slot="footer" class="dialog-footer">
            <el-button @click="addVisiable = false">取 消</el-button>
            <el-button type="primary" @click="doAdd">确 定</el-button>
            </span>
        </el-dialog>


    </div>
    
</template>

<script>
export default {
    data(){
        return {
            sysParamList:[],
            editVisiable:false,
            addVisiable:false,
            sysParam:{}
        }
    },
    beforeCreate(){
        this.$http.get("/data-config/sys-param-list",{
            params:{
                page:1,
                size:20
            }
        }).then(res =>{
        if(res.status==200 && res.data.code==0) this.sysParamList = res.data.data.pageData;})
    },
    methods:{
        Edit(row){
            this.sysParam=row;
            this.editVisiable=!this.editVisiable;
        },
        doEdit(){
             this.$http.put("/data-config/sys-param",this.sysParam).then(res => {
                if(res.status==200 && res.data.code==0){
                    this.editVisiable=!this.editVisiable;
                    this.$message.success("修改参数成功!")
                }else{
                     this.$message.error();("修改参数失败!")
                }
             })
        },
        Delete(index,row){
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'}).then(() => {
                    this.$http.delete("/data-config/sys-param/"+row.id).then(res => {
                        if(res.status==200 && res.data.code==0){
                            this.$message.success("删除成功!")
                            this.sysParamList.splice(index,1)
                        }else{
                            this.$message.error("删除失败!")
                        }
                    })
                }).catch((e) => {});
        },
        doAdd(){
            this.$http.post("/data-config/sys-param",this.sysParam).then(res =>{
                 if(res.status==200 && res.data.code==0){
                    this.addVisiable=!this.addVisiable;
                    this.$message.success("添加参数成功!");
                     this.$http.get("/data-config/sys-param-list",{
                         params:{
                             page:1,
                             size:20
                         }
                     }).then(res =>{
                    if(res.status==200 && res.data.code==0) {
                        this.sysParamList = res.data.data;
                     }})
                }else{
                     this.$message.error();("添加参数失败!")
                }
            })
        }
    },


}
</script>

<style>
.red{
    color: red;
}
</style>