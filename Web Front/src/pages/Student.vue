<template>
    <div style="height:100%;width:100%;"> 
    <el-form :inline="true"  class="demo-form-inline" style="border-radius: 30px;">
        <el-form-item label="学校">
            <el-select v-model="school" placeholder="请选择学校"  @focus="loadingSchoolList">
                <el-option v-for="item in schoolList" :key="item.id" :label="item.sch_name" :value="item.sch_code" ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="学院">
            <el-select v-model="college" placeholder="请选择学院" @focus="loadingCollegeList">
                <el-option v-for="item in collegeList" :key="item.id" :label="item.co_name" :value="item.co_code" ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="专业">
            <el-select v-model="major" placeholder="请选择专业"  @focus="loadingMajorList">
                <el-option v-for="item in majorList" :key="item.value" :label="item.ma_name" :value="item.ma_code" ></el-option>
            </el-select>
        </el-form-item>

        <el-form-item label="班级">
            <el-select v-model="class_" placeholder="请选择班级" @focus="loadingClassList">
                <el-option v-for="item in classList" :key="item.id" :label="item.cs_name" :value="item.cs_id" ></el-option>
            </el-select>
        </el-form-item>
            <el-form-item><el-button type="primary" @click="doSearch" >查询</el-button></el-form-item>
            <el-form-item><el-button type="primary" @click="addStudent">+添加学生</el-button></el-form-item>
    </el-form>

    <el-table :data="studentPage" height="80%" border style="width: 100%;font-size:20px;">
        <el-table-column prop="st_id" label="学号" align="center"  style="width: 8%;"></el-table-column>
        <el-table-column prop="st_name" label="姓名" align="center" style="width: 8%;"></el-table-column>
        <el-table-column prop="st_sex" label="性别" align="center" style="width: 8%;"></el-table-column>
        <el-table-column prop="st_phone" label="电话" align="center" style="width: 8%;"></el-table-column>
        <el-table-column prop="st_email" label="邮箱" align="center" style="width: 15%;"></el-table-column>
        <el-table-column label="操作" style="width: 20%;" align="center" >
            <template slot-scope="scope">
                <el-button type="text" icon="el-icon-edit"   @click="editStudent(scope.$index,scope.row)">编辑</el-button>
                <el-button type="text" icon="el-icon-delete" class="red"  @click="deleteStudent(scope.$index,scope.row)">删除</el-button>
            </template>
        </el-table-column>
  </el-table>
      <el-pagination class="pagination"  
      @size-change="changeSize"
      @current-change="changePage"
      :current-page.sync="currentPage" 
      background 
      :page-size="pageSize" 
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalSize">
      </el-pagination> 




    <!-- 编辑弹出框 -->
    <el-dialog title="编辑" :visible.sync="add_edit_Visiable" width="40%">
        <el-form :model="student" label-width="70px" :rules="rules">
            <el-form-item label="学号" prop="st_id">
                <el-input v-model="student.st_id" :disabled="!isCrete"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="st_name">
                <el-input v-model="student.st_name"></el-input>
            </el-form-item>
            <el-form-item label="学生性别">
                <template>
                    <el-radio v-model="student.st_sex" label="男">男</el-radio>
                    <el-radio v-model="student.st_sex" label="女">女</el-radio>
                 </template>
            </el-form-item>
            <el-form-item label="电话" prop="st_phone">
                <el-input v-model="student.st_phone"></el-input>
            </el-form-item>
            <el-form-item label="邮箱" width="40%">
                <el-input v-model="student.st_email"></el-input>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="add_edit_Visiable = false">取 消</el-button>
            <el-button v-if="isCrete" type="primary" @click="handleCreate(student)">确 定</el-button>
            <el-button v-else type="primary" @click="handleUpdate(student)">确 定</el-button>
        </span>
    </el-dialog>



  </div>




   
</template>

<script>
export default {
    data() {
      return {
          //student={};isCrete=true;add_edit_Visiable=true;
            user:JSON.parse(localStorage.getItem("user")),
            schoolList:[],
            school:'',
            collegeList:[],
            college:'',
            majorList:[],
            major:'',
            classList:[],
            class_:'',
            studentPage:[],
            student: {},
            pageSize:10,
            currentPage:1,
            totalSize:0,
            add_edit_Visiable:false,
            isCrete:true,
            rules: {
               st_id:[
                    {required: true, message: '请输入学生学号', trigger: 'blur' },
                    {pattern: '^[0-9]*$', message: '请输入数字', trigger: 'blur' }
               ],
               st_name:[
                     {required: true, message: '请输入学生姓名', trigger: 'blur' },
                     {min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
               ],
               st_phone:[
                    {required: true, message: '请输入学生手机号', trigger: 'blur'},
                    {pattern: '1[0-9]{10}$', message: '请输入手机号', trigger: 'blur' }
               ]
           },

        
      }
    },

    methods:{
        loadingSchoolList(){ //加载学校列表
            this.$http.get("/school?page=1&size=50").then(res => {
                if(res.status==200&&res.data.code===0){
                    this.schoolList = res.data.data.pageData;
            }});
        },
        loadingCollegeList(){ //加载学院列表
            this.$http.get("/college?page=1&size=50&sch_code="+this.school).then(res => {
                if(res.status==200&&res.data.code===0) this.collegeList = res.data.data.pageData;});
        },
        loadingMajorList(){//加载专业列表
            this.$http.get("/major?page=1&size=50&sch="+this.school+"&col="+this.college).then(res => {
                if(res.status==200&&res.data.code===0) this.majorList = res.data.data.pageData;})
        },
        loadingClassList(){//加载班课列表
            this.$http.get("/course/list?sch="+this.school+"&col="+this.college+"&maj="+this.major).then(res => {
                if(res.status==200&&res.data.code===0){
                 this.classList = res.data.data;
            }})
        },
        doSearch(){//搜索指定班课的学校的学生
            // this.$http.get("/student?sch="+this.school+"&col="+this.college+"&maj="+this.major+"&cid="+this.class_+"&page="+this.currentPage+"&size="+this.pageSize).then(res =>{
            //       if(res.status==200&&res.data.code===0) {
            //           this.studentPage = res.data.data.pageData;
            //           this.totalSize = res.data.data.totalSize
            //       }
            // })
            
            var a = {
                "phone":"13015959118"
            };
            this.$http.post("/user/sms",a).then(res =>{
                console.log(res)
            })

        },

        addStudent(){
                var a = {
                    "email": "1134956893@qq.com",
                    "identity": "200327168",
                    "msgcode": 4169,
                    "password": "lxb19970821",
                    "phone": "13015959118",
                    "type": 4,
                    "username": "200327168"
            };
            this.$http.post("/user/regist",a).then(res =>{
                console.log(res)
            })
        },
        changeSize(size){this.pageSize = size;} ,
        changePage(page){this.currentPage = page;},

       










        reCheck(index,row){
            console.log(this.student)
            console.log(row)
            this.$http.post("/check",{
                   "studentId":this.student.st_id,
                   "taskId":row.id,
                   "classId":row.class_id,
                   "location":"",
                   "password":""
            }).then(res => {
                if(res.status==200&&res.data.status==0){
                    this.$message.success("补签成功!");
            //        this.$http.get("/check/task-list",{params:{
            //         "sid":row.st_id,
            //         "cid":row.st_classid
            //     }}).then(res => {
            //         if(res.data.status === 0 ){
            //             this.checklog = res.data.datas;
            //         }
            // });
                }else{
                    this.$message.erroe("补签失败!");
                }
            })
        },
        query2(){   
            
        },
        loadingMyClass(){
            this.$http.get("/course/teacher/"+this.teacher.id).then(res =>{
                this.myclasses = res.data.datas
            })
        },
       add_student(){
           this.addShow = !this.addShow;
       },

        
          loadMajorList(){
               if(this.student.st_schoolcode!=null && this.student.st_collegecode!=null){
                    this.$http.get("/major",{
                         params:{
                              sch_code:this.student.st_schoolcode,
                              col_id:this.student.st_collegecode
                         }
                    }).then(res => {
                          if(res.data.status ===0){
                              this.majorList = res.data.datas;
                         }
                    })
               }
           },
           onCreateStudent(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                         this.$http.post('/student',(this.student)
                   //      {headers:{"Content-Type":"application/x-www-form-urlencoded"}}
            ).then(res => {
                              if(res.data.status === 0 ){
                                   alert('submit!');
                              }else{
                                   alert("wrong!")
                              }
                         });  
                    } else 
                    {
                         console.log('error submit!!');
                         return false;
                    }});

           },
           createCancel(formName){
               this.student = {
               st_id:null,
               st_name:null,
               st_sex:'男',
               st_phone:null,
               st_email:null,
               st_schoolcode:null,
               st_collegecode:null,
               st_majorcode:null,
               st_info:null
           };
            this.$refs[formName].resetFields();
            this.addShow =!this.addShow
           },
       checkLogDetail(row){
           this.student = row
            this.$http.get("/check/task-list",{params:{
                    "sid":row.st_id,
                    "cid":row.st_classid
                }}).then(res => {
                    if(res.data.status === 0 ){
                        this.checklog = res.data.datas;
                    }
            });
            this.checkDetailShow = ! this.checkDetailShow;
            },
       SearchStudent(){
           console.log("SearchStudent..");
           console.log("/student/"+this.searchSid);
            this.$http.get("/student/"+this.searchSid).then(res => {
            if(res.data.status ===0 ){
                this.studentPage = res.data.datas;
                this.$message.success("查询成功！")
                this.total = this.students.length;
                }
            else{
                this.$message.error("查无此人！ ")
            }
           });
       },
       proving() {
    	this.searchSid =this.searchSid.replace(/[^\.\d]/g,'');
    	this.searchSid = this.searchSid.replace('.','');
        },
        
        getStudentPage(){
            this.$http.get("/student",{params:{
                "classid":this.classid,
                "page":this.currentPage,
                "size":this.pagesize
            }}).then(res => {
                console.log(res.status)
                console.log(res.data)
                if(res.data.datas.length > 0 ){
                    this.studentPage = res.data.datas;
                    this.total =  res.data.total;
                    console.log("studentPage:");
                    console.log( this.studentPage);
                }
                else{
                    this.studentPage = null;
                }
           });
        },
        focusSchool(){
            console.log("focusSchool...");
            this.$http.get("/school").then(res => {
            console.log("res => /school");
            console.log(res);
            if(res.data.status === 0 ){
                this.schools = res.data.datas;
            }});
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
        SelectClass(){
            console.log("SelectClass...");
            console.log(this.classid);
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