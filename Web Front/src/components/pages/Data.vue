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
                <template slot-scope="scope">
                <el-select  :value="v" clearable placeholder="请点击查看">
                    <el-option
                    v-for="item in items[scope.$index]['value']"
                    :key="item"
                    :label="item"
                    :value="item">
                    </el-option>
                </el-select>
                </template>
            </el-table-column>
            <el-table-column label="操作" style="width: 40%;" align="center" >
                <template slot-scope="scope">
                    <el-button type="text" icon="el-icon-edit" >编辑</el-button>
                    <el-button type="text" icon="el-icon-delete" class="red">删除</el-button>
                </template>
            </el-table-column>
       </el-table>
    </div>
</template>

<script>
export default {
    data(){
        return {
            input_name:'',
            items:[],
            v:''

        }
    },
    methods:{
        Search(){},
        add(){},
        SearchAll(){
            this.$http.get("/config/getAll").then(res => {
            if(res.data.length > 0 ){
                this.items = res.data;
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