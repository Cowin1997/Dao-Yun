<template>
   <div style="height:100%;width:100%;"> 
       <el-form :inline="true"  class="demo-form-inline" style="border-radius: 30px;"> 
            <el-form-item label="姓名：">
                <el-input placeholder="请输入教师姓名" v-model="tname" clearable ></el-input>
            </el-form-item>
            <el-form-item label="教工号：">
                <el-input placeholder="请输入教工号" v-model="tid" clearable ></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="Search">查询</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="addTeacher">+ 新增</el-button>
            </el-form-item>
       </el-form>
       <el-table :data="teacherPage" height="80%" border style="width: 100%;font-size:20px;">
            <el-table-column prop="tid" label="教工号" align="center"></el-table-column>
            <el-table-column prop="tname" label="姓名" align="center"></el-table-column>
             <el-table-column  prop="phone" label="电话" align="center"></el-table-column>
            <el-table-column  prop="edu" label="学历" align="center"></el-table-column>
            <el-table-column  prop="rank"  label="职称" align="center"></el-table-column>
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

 <!-- 编辑弹出框 -->
    <el-dialog title="添加" :visible.sync="addVisible" width="40%">
        <el-form :model="addTeach" label-width="70px">
          <el-form-item label="教工号">
                <el-input ></el-input>
          </el-form-item>
          <el-form-item label="姓名">
                <el-input ></el-input>
          </el-form-item>
          <el-form-item label="手机号码">
                <el-input ></el-input>
          </el-form-item>
        <el-form-item label="学历">
          <el-select v-model="edu" placeholder="请选择" @focus="getEdus">
            <el-option
            v-for="item in edus"
            :key="item.id"
            :label="item.value"
            :value="item.id">
            </el-option>
           </el-select>
        </el-form-item>
        <el-form-item label="职称">
          <el-select v-model="rank" placeholder="请选择" @focus="getRanks">
            <el-option
            v-for="item in ranks"
            :key="item.id"
            :label="item.value"
            :value="item.id">
            </el-option>
           </el-select>
            </el-form-item>
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
      </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="addCancel">取 消</el-button>
            <el-button type="primary" >确 定</el-button>
        </span>
    </el-dialog>
   </div>
</template>

<script>
export default {
    data() {
        return {
            schools:[],
            majors:[],
            school:'',
            schoolcode:'',
            majorcode:'',
            tname:'',
            tid:'',
            teachers:[],
            teacherPage:[],
            addVisible:false,
            addTeach:null,
            total:0,
            currentPage:1,
            pagesize:10,
            edus:[
            ],
            edu:'',
            rank:'',
            ranks:[
            ]



        }
    },
    methods:{
          getEdus(){
            this.$http.get("/config/get", {params:{
                name:'学历'
            }}).then(res => {
            if(res.data.length > 0 ){
                this.edus = res.data;
            }
            else{
                this.edus = null;
            }
           });
          },
          getRanks(){
            this.$http.get("/config/get", {params:{
                            name:'职称'
                        }}).then(res => {
                        if(res.data.length > 0 ){
                            this.ranks = res.data;
                        }
                        else{
                            this.ranks = null;
                        }
                    });
          },
          focusSchool(){   
            this.$http.get("/school/getAllSchool").then(res => {
            if(res.data.length > 0 ){
                this.schools = res.data;
            }
            else{
                this.schools = null;
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
           this.$http.get("/class/getClass",{params:{
                "majorcode":this.majorcode,
            }}).then(res => {
                console.log("res => /class/getClass");
                console.log(res);
                if(res.data.length > 0 ){
                    this.classes = res.data;
                }
                else{
                    this.classes = null;
                }
           });
        },
        trim(pValue){return pValue.replace(/^[\s]*/gi,"").replace(/[\s]*$/gi,"")},

         ChangeSize(size){
            this.pagesize = size;
            this.teacherPage = this.teachers.slice((this.currentPage-1)*this.pagesize,this.currentPage * this.pagesize);
        }
        ,
        ChangePage(page){
            this.currentPage = page;
            this.teacherPage = this.teachers.slice((this.currentPage-1)*this.pagesize,this.currentPage * this.pagesize);
        },
        
        addTeacher(){
            this.addVisible = !this.addVisible;
        },
        addCancel(){
             this.addVisible = !this.addVisible;
        },
        Search(){
            this.tid = this.trim(this.tid);
            this.tname =  this.trim(this.tname);
            if(this.tid ==''){ //教工号不存在
                if(this.tname=='') return; //姓名不存在
                else{
                    this.$http.get("/teacher/getTeacherByName",{params:{
                        "name":this.tname
                    }}).then(res => {
                        if( res.data.length > 0 ){
                            this.teachers = res.data;
                            this.$message.success("查询成功!");
                            this.teacherPage = this.teachers.slice((this.currentPage-1)*this.pagesize,this.currentPage * this.pagesize);
                        }
                        else{
                             this.$message.error("查询失败!");
                        }
                     });
                }
            }else{ //教工号存在
                 if(this.tname==''){//姓名不存在
                    this.$http.get("/teacher/getTeacherById",{params:{
                        "id":this.tid
                    }}).then(res => {
                        if( res.data.length > 0 ){
                            this.teachers = res.data;
                            this.$message.success("查询成功!");
                            this.teacherPage = this.teachers.slice((this.currentPage-1)*this.pagesize,this.currentPage * this.pagesize);

                        }
                        else{
                             this.$message.error("查询失败!");
                        }
                     });
                 } else{
                        this.$http.get("/teacher/getTeacherByIdAndName",{params:{
                        "name":this.tname,
                        "id":this.tid
                    }}).then(res => {
                         console.log(res);
                        if( res.data.length > 0 ){
                            this.teachers = res.data;
                            this.$message.success("查询成功!");
                        
                            this.teacherPage = this.teachers.slice((this.currentPage-1)*this.pagesize,this.currentPage * this.pagesize);
                        }
                        else{
                             this.$message.error("查询失败!");
                        }
                     });
                 }
            }
        },
       

}

}
</script>

<style>
.pagination{
  text-align:center;
}
.red {
    color: #ff0000;
}
</style>