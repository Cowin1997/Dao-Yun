<template>
    <div style="height:100%;width:100%;"> 
        <el-row>
            <el-col :span="9" style="margin-right:5px;">
                <el-input placeholder="请输入键或说明进行搜索" v-model="searchInput" clearable></el-input>
            </el-col>
            <el-col :span="3" style="margin-left:5px;">
                 <el-button type="success" icon="el-icon-search" plain @click="Search()" >搜索</el-button>
            </el-col>
        
            <el-col :span="1">
                <el-button :disabled="!hasPerm('param:add')" type="primary" plain @click="addEditVisible = true;sysParam={};isAdd=true">+ 新增</el-button>
            </el-col>
        </el-row>
        <el-table :data="paramList" height="80%" border style="width: 100%;font-size:15px;margin-top:10px;">
            <el-table-column prop="id" label="ID" align="center"></el-table-column>
            <el-table-column prop="arg_key" label="键" align="center"></el-table-column>
            <el-table-column prop="arg_value" label="值" align="center"></el-table-column>
            <el-table-column prop="arg_desc" label="说明" align="center"></el-table-column>
            <el-table-column prop="gmt_create" label="创建时间" align="center"></el-table-column>
            <el-table-column prop="gmt_modified" label="修改时间" align="center"></el-table-column>
            <el-table-column label="操作" style="width: 40%;" align="center" >
                <template slot-scope="scope">
                    <el-button type="text" icon="el-icon-edit" :disabled="!hasPerm('param:edit')"  @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-popconfirm title="确定删除吗？" @confirm="handleDelete(true,scope.$index,scope.row)"  @cancel="handleDelete(false,scope.$index,scope.row)">
                        <el-button icon="el-icon-delete" :disabled="!hasPerm('param:del')"  slot="reference" style="margin-left: 10px"  type="danger" size="small" >
                            删除
                        </el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>


        <el-dialog title="系统参数" :visible.sync="addEditVisible" width="30%">
                <el-form :model="sysParam" :rules="sysParamRules" ref="form">
                <el-form-item label="键" prop="arg_key">
                    <el-input v-model="sysParam.arg_key"></el-input>
                </el-form-item>
                <el-form-item label="值" prop="arg_value">
                    <el-input v-model="sysParam.arg_value"></el-input>
                </el-form-item>
                <el-form-item label="说明">
                    <el-input v-model="sysParam.arg_desc"></el-input>
                </el-form-item>
                </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addEditVisible = false">取 消</el-button>
                <el-button v-if="isAdd" type="primary" @click="handleCreate(sysParam)">确 定</el-button>
                <el-button v-else type="primary"  @click="handleUpdate(sysParam)">确 定</el-button>
            </span>
        </el-dialog>
        
        <!-- 分页 -->
        <el-pagination style="text-align:center;"
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
import { getSysParam,addSysParam,updateSysParam,deleteSysParam}  from '@/api/sys/param'

export default {
    data(){
        return {
            currentPage:1,
            pageSize:10,
            totalSize:0,
            paramList:[],
            sysParam:{},
            isAdd:true,
            addEditVisible:false,
            sysParamRules:{
                arg_key : [{ required: true, message: '键不能为空', trigger: 'blur' }],
                arg_value: [{ required: true, message: '值不能为空', trigger: 'blur' } ]    
            },
            currentIndex:0,
            searchInput:''


        }
    },
    methods:{
        Search(){
            if(this.searchInput.trim()!=''){
            getSysParam({page:this.currentPage,size:this.pageSize,search:this.searchInput}).then(res =>{
                                        this.totalSize = res.data.totalSize;
                                        this.paramList = res.data.pageData})}
            else{
                 getSysParam({page:this.currentPage,size:this.pageSize}).then(res =>{
                                        this.totalSize = res.data.totalSize;
                                        this.paramList = res.data.pageData})}
           
        },
   
    
        handleCurrentChange(currentPage){this.currentPage = currentPage;
        getSysParam({page:this.currentPage,size:this.pageSize}).then(res =>{
                                    this.totalSize = res.data.totalSize;
                                    this.paramList = res.data.pageData})},
        handleSizeChange(pageSize){this.pageSize = pageSize;getSysParam({page:this.currentPage,size:this.pageSize}).then(res =>{
                                    this.totalSize = res.data.totalSize;
                                    this.paramList = res.data.pageData})},
        handleCreate(sysParam){// 添加
            if(this.isAdd){
                this.$refs.form.validate( valid =>{
                    if(valid){
                        sysParam['creator']  = JSON.parse(localStorage.getItem('user')).user.id;
                        addSysParam(sysParam).then(res => {
                            if(res.success ==true){
                                sysParam['id'] = res.data
                                this.paramList.push(sysParam)
                                this.totalSize ++
                                this.addEditVisible = false;
                                this.isAdd = true;
                                getSysParam({page:this.currentPage,size:this.pageSize}).then(res =>{
                                    this.totalSize = res.data.totalSize;
                                    this.paramList = res.data.pageData})
                                this.$message.success("添加成功!")
                            }else{
                                this.$message.error(res.error);
                                this.addEditVisible = false;
                            }
                        })
                    }else{
                        alert("表单验证失败")
                    }
                })
            }

        },
        handleEdit(index,row){
            this.sysParam = row
            this.currentIndex = index;
            this.isAdd = false
            this.addEditVisible = true;
        },
        handleUpdate(sysParam){
            if(!this.isAdd){
                this.$refs.form.validate(valid => {
                    if(valid){
                        sysParam['reviser'] = JSON.parse(localStorage.getItem('user')).user.id;
                        updateSysParam(sysParam).then(res => {
                            if(res.success==true){
                                this.paramList[this.currentIndex] = sysParam;
                               this.addEditVisible = false;
                                this.isAdd = true;
                                getSysParam({page:this.currentPage,size:this.pageSize}).then(res =>{
                                    this.totalSize = res.data.totalSize;
                                    this.paramList = res.data.pageData})
                                this.$message.success("修改成功!");
                            }else{
                                this.$message.error(res.error);
                                this.addEditVisible = false;
                            }
                        })
                    }else{
                        alert("表单验证失败")
                    }
                })



            }










        },
        handleDelete(bol,index,row){
            if(bol==true){
                deleteSysParam(row.id).then(res => {
                    if(res.success===true){
                        this.paramList.splice(index,1);
                        this.totalSize -= 1
                        getSysParam({page:this.currentPage,size:this.pageSize}).then(res =>{
                                    this.totalSize = res.data.totalSize;
                                    this.paramList = res.data.pageData});
                        this.$message.success("删除成功");

                    }else{
                        this.$message.error(res.error)
                    }
                })
            }else{
           
            }
        }



    },
    created(){
        getSysParam({page:this.currentPage,size:this.pageSize}).then(res =>{
            this.totalSize = res.data.totalSize;
            this.paramList = res.data.pageData
        })
    }
}
</script>

<style>
.red {
    color: red;
}
</style>