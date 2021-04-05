<template>
    <div style="height:100%;width:100%;"> 
        <el-form :inline="true"  class="demo-form-inline" style="border-radius: 30px;">
            <el-form-item label="学校">
                <el-select v-model="schoolcode" placeholder="请选择学校" @change="ChangeSchool" @focus="focusSchool">
                    <el-option v-for="item in schools" :key="item.id" :label="item.name" :value="item.code" ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="专业">
                <el-select v-model="majorcode" placeholder="请选择专业" @change="ChangeMajor" >
                    <el-option v-for="item in majors" :key="item.id" :label="item.name" :value="item.majorcode" ></el-option>
                </el-select>
            </el-form-item>
             <el-form-item><el-button type="primary" @click="query">查询</el-button></el-form-item>
        </el-form>
    <el-table :data="classPage" height="80%" border style="width: 100%;font-size:20px;">
        <el-table-column prop="cid" label="课程号" align="center"></el-table-column>
        <el-table-column prop="cname" label="课程名" align="center"></el-table-column>
        <el-table-column prop="teacher.tname" label="任课教师" align="center"></el-table-column>
        <el-table-column prop="site" label="上课地点" align="center"></el-table-column>
        <el-table-column label="操作" style="width: 40%;" align="center" >
            <template slot-scope="scope">
                <el-button type="text" icon="el-icon-edit" @click="editStudent(scope.$index,scope.row)">编辑</el-button>
                <el-button type="text" icon="el-icon-delete" class="red" @click="deleteStudent(scope.$index,scope.row)">删除</el-button>
            </template>
        </el-table-column>
   </el-table>
      <el-pagination class="pagination"  
      @size-change="ChangeSize"
      @current-change="ChangePage"
      :current-page.sync="currentPage" 
      background 
      :page-size="pagesize" 
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
      </el-pagination> 
    </div>
</template>

<script>
export default {
data() {
      return {
        classes:[],
        classPage:[],
        schoolcode:'',
        schools:null,
        majorcode:'',
        majors:null,
        total:0,
        pagesize:10,
        currentPage:1,
      }
},
methods:{
     ChangeSize(size){
            this.pagesize = size;
            this.classPage = this.classes.slice((this.currentPage-1)*this.pagesize,this.currentPage * this.pagesize);
        }
        ,
        ChangePage(page){
            this.currentPage = page;
            this.classPage = this.classes.slice((this.currentPage-1)*this.pagesize,this.currentPage * this.pagesize);
        },
    query(){
        this.$http.get("/class/getClass",{params:{
                    "majorcode":this.majorcode,
                }}).then(res => {
                    console.log("res => /class/getClass");
                    console.log(res);
                    if(res.data.length > 0 ){
                        this.classes = res.data;
                        this.classPage = this.classes.slice((this.currentPage-1)*this.pagesize,this.currentPage * this.pagesize);
                    }
                    else{
                        this.classes = null;
                    }
            });
    },

     focusSchool(){
            console.log("focusSchool...");
            this.$http.get("/school/getAllSchool").then(res => {
            console.log("res => /school/getAllSchool");
            console.log(res);
            if(res.data.length > 0 ){
                this.schools = res.data;
                console.log("focusSchool")
                console.log(this.schools)
            }
            else{
                this.school = null;
           
            }
           });
        },
        ChangeSchool(){
            console.log("ChangeSchool....")
            console.log("brfore, get School's majors... SchoolCode:"+ this.schoolcode);
            this.$http.get("/major/getMajors",{params:{
                "schoolcode":this.schoolcode,
            }}).then(res => {
                console.log("res => /school/getAllSchool");
                console.log(res);
                if(res.data.length > 0 ){
                    this.majors = res.data;
                }
                else{
                    this.majors = null;
                }
           });
        },
        ChangeMajor(){
           console.log("ChangeMajor...");
           console.log("brfore, get Major's all classes .. majorcode:"+ this.majorcode);
        //    this.$http.get("/class/getClass",{params:{
        //         "majorcode":this.majorcode,
        //     }}).then(res => {
        //         console.log("res => /class/getClass");
        //         console.log(res);
        //         if(res.data.length > 0 ){
        //             this.classes = res.data;
        //         }
        //         else{
        //             this.classes = null;
        //         }
        //    });
        },
}
}
</script>

<style>

</style>