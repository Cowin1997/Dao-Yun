<template>
    <div style="height:100%;width:100%;"> 
    <el-form :inline="true"  class="demo-form-inline" style="border-radius: 30px;">
        <el-form-item label="学校" @focus="focusSchool">
            <el-select v-model="schoolcode" :loading="schoolLoading" placeholder="请选择学校" @change="SelectSchool" @focus="focusSchool">
                <el-option v-for="item in schools" :key="item.id" :label="item.sch_name" :value="item.sch_code" ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="学院">
            <el-select v-model="collegeid" placeholder="请选择学院" @change="SelectCollege" >
                <el-option v-for="item in colleges" :key="item.id" :label="item.co_collegename" :value="item.co_collegeid" ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="专业">
            <el-select v-model="majorcode" placeholder="请选择专业" @change="SelectMajor">
                <el-option v-for="item in majors" :key="item.value" :label="item.ma_majorname" :value="item.ma_majorid" ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="班级">
            <el-select v-model="classid" placeholder="请选择班级" @change="SelectClass">
                <el-option v-for="item in classes" :key="item.id" :label="item.cl_classname" :value="item.cl_classid" ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item><el-button type="primary" @click="query">查询</el-button></el-form-item>
        <el-form-item><el-button type="primary" >添加学生</el-button> </el-form-item>
       
        <el-form-item>
            <el-input placeholder="请输入学生学号" v-model="searchSid" clearable @keyup.native="proving"></el-input>
        </el-form-item>
        <el-form-item>
             <el-button type="primary" @click="SearchStudent" :disabled="!searchSid">查询学生</el-button>
        </el-form-item>
       
    </el-form>
    <el-table :data="studentPage" height="80%" border style="width: 100%;font-size:20px;">
        <el-table-column prop="sid" label="学号" align="center"></el-table-column>
        <el-table-column prop="sname" label="姓名" align="center"></el-table-column>
        <el-table-column prop="phone" label="电话" align="center"></el-table-column>
        <el-table-column prop="clocktimes" label="签到次数" align="center"></el-table-column>
        <el-table-column prop="clockscore" label="签到分数" align="center"></el-table-column>
        <el-table-column prop="lastclocktime" label="上次签到时间" align="center"></el-table-column>
        <el-table-column prop="lastclocksite" label="上次签到地点" align="center"></el-table-column>
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
    <el-dialog title="编辑" :visible.sync="editVisible" width="40%">
        <el-form  :model="editstu" label-width="70px">
            <el-form-item label="学号">
                <el-input v-model="editstu.sid"></el-input>
            </el-form-item>

            <el-form-item label="姓名">
                <el-input v-model="editstu.sname"></el-input>
            </el-form-item>

            <el-form-item label="电话">
                <el-input v-model="editstu.phone"></el-input>
            </el-form-item>

            <el-form-item label="签到次数" >
                <el-input v-model="editstu.clocktimes"></el-input>
            </el-form-item>

            <el-form-item label="签到分数">
                <el-input v-model="editstu.clockscore"></el-input>
            </el-form-item>

       
            <el-form-item label="上次签到时间"  width="40%">
                <el-input v-model="editstu.lastclocktime"></el-input>
            </el-form-item>

   
            <el-form-item label="上次签到地点" width="40%">
                <el-input v-model="editstu.lastclocksite"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="editVisible = false">取 消</el-button>
            <el-button type="primary" @click="editCommit">确 定</el-button>
        </span>
    </el-dialog>






  </div>




   
</template>

<script>
export default {
    data() {
      return {
        editstu:{
            id:'',
            name:'',
        },
       
        searchSid:'',
        schoolLoading:false,
        editVisible:false,
        editIndex:'',
        schoolcode:'',
        schools:null,
        majorcode:'',
        majors:null,
        classid:'',
        classes:null,
        students: [],
        studentPage:[],
        total:0,
        pagesize:10,
        currentPage:1,
        major:null,
        majors:[],
        colleges:[],
        collegeid:'',


      }
    },

    methods:{
       SearchStudent(){
           console.log("SearchStudent..");
           console.log("Search StudentID:"+this.searchSid);
            this.$http.get("/student/getStudentsByStudentId",{params:{
                sid:this.searchSid
            }}).then(res => {
            console.log("res =>/student/getStudentsByStudentId");
            console.log(res);
            if(res.data.length > 0 ){
                this.students = res.data;
                this.studentPage = this.students.slice((this.currentPage -1) * this.pagesize,this.currentPage * this.pagesize);
                this.$message.success("查询成功！")
                this.total = this.students.length;
                }
            else{
                this.$message.error("查无此人！")
            }
           });
       },
       proving() {
    	this.searchSid =this.searchSid.replace(/[^\.\d]/g,'');
    	this.searchSid = this.searchSid.replace('.','');
        },
        ChangeSize(size){
            console.log("Current size："+size);
            console.log("ChangeSize...");
            this.pagesize = size;
            this.studentPage = this.students.slice((this.currentPage-1)*this.pagesize,this.currentPage * this.pagesize);
        }
        ,
        ChangePage(page){
            console.log("Current Page："+page);
            console.log("ChangePage...");
            this.currentPage = page;
            this.studentPage = this.students.slice((this.currentPage-1)*this.pagesize,this.currentPage * this.pagesize);
            console.log("studentPage:");
            console.log( this.studentPage)
        },
        focusSchool(){
            console.log("focusSchool...");
            this.schoolLoading = !this.schoolLoading;
            this.$http.get("/school").then(res => {
            console.log("res => /school");
            console.log(res);
            if(res.data.status === 0 ){
                this.schoolLoading = !this.schoolLoading;
                this.schools = res.data.datas;
            }
            else{
                this.schools = null;
                this.schoolLoading = !this.schoolLoading;
            }
           });
        },
        SelectSchool(){
            console.log("SelectSchool....")
            console.log("brfore, get School's majors... SchoolCode:"+ this.schoolcode);
            this.$http.get("/college",{params:{
                "sch_code":this.schoolcode,
            }}).then(res => {
                console.log("res => /school");
                console.log(res);
                if(res.data.status === 0 ){
                    this.colleges = res.data.datas;
                }
                else{
                    this.colleges = null;
                }
           });
        },
        SelectCollege(){
           console.log("SelectCollege..."+this.collegeid);
           this.$http.get("/major",{params:{
                sch_code:this.schoolcode,
                col_id:this.collegeid
            }}).then(res => {
                console.log("res => /class/getClass");
                console.log(res);
                if(res.data.status === 0 ){
                    this.majors = res.data.datas;
                }
                else{
                    this.majors = null;
                }
           });
        },
        SelectMajor(){
           console.log("SelectMajor..."+ this.majorcode);
           this.$http.get("/course",{params:{
                "sch_code":this.schoolcode,
                "col_id":this.collegeid,
                "maj_id":this.majorcode
            }}).then(res => {
                console.log(res);
                if(res.data.status=== 0 ){
                    this.classes = res.data.datas;
                }
                else{
                    this.classes = null;
                }
           });
        },
        ChangeClass(){
            console.log("ChangeClass...");
            console.log(this.classid);
        },
        query(){
             console.log("query...");
             this.$http.get("/student/getStudentsByClassId",{params:{
                "classid":this.classid,
            }}).then(res => {
                console.log("res => /student/getStudentsByClassId");
                console.log(res);
                if(res.data.length > 0 ){
                    this.students = res.data;
                    this.total = res.data.length;
                    console.log("students:");
                    console.log( this.students);
                    this.studentPage = this.students.slice((this.currentPage -1) * this.pagesize,this.currentPage * this.pagesize);
                    console.log("studentPage:");
                    console.log( this.studentPage);
                }
                else{
                    this.students = null;
                }
           });
        },

        editStudent(index, row){
            console.log("editStudent...");
            console.log(row);
            this.editIndex = index+ (this.currentPage-1) * this.pagesize;
            console.log("this.editIndex:"+this.editIndex);
            this.editstu = row;
            this.editVisible = !this.editVisible;
        },
        deleteStudent(index,row){
            console.log("deleteStudent...");
            console.log(index);
            console.log(row);
            this.editIndex = index+ (this.currentPage-1) * this.pagesize;
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            }).then(() => {
                console.log("then....")
                this.$http.get("/course/delStuBySid",{params:{
                    "sid":row.sid
                    }}).then(res => {
                        console.log("res =>/course/delStuBySid");
                        console.log(res);
                        if(res.data.success==true ){
                            console.log('删除前students:')
                            console.log(this.students)
                            this.students.splice(this.editIndex,1);
                            console.log('删除后students:')
                            console.log(this.students)
                            this.studentPage = this.students.slice((this.currentPage -1) * this.pagesize,this.currentPage * this.pagesize);
                            this.$message.success('删除成功!');
                            this.total -=1;  
                        }
                        else{
                            this.$message.success('删除失败!');
                        }});
                    
                   
                }).catch((e) => {
                    console.log(e);
                }
            )
        },
        editCommit(){
            console.log("editCommit...");
            this.students[this.editIndex] = this.editstu;
            this.studentPage = this.students.slice((this.currentPage -1) * this.pagesize,this.currentPage * this.pagesize);
            this.$message.success("修改成功！")
            this.editVisible = !this.editVisible;
        }
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