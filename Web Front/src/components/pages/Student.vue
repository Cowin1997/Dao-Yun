<template>
    <div style="height:100%;width:100%;"> 
    <el-form :inline="true"  class="demo-form-inline" style="border-radius: 30px;">
        <div v-if="user.us_roleid==1">
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
            <el-form-item><el-button  v-if="permissionList.includes(1004)" type="primary" @click="query" >查询</el-button></el-form-item>
        <el-form-item  v-if="permissionList.includes(1001)"><el-button type="primary" @click="add_student">添加学生</el-button> </el-form-item>
        </div>
            
        
        <div v-if="user.us_roleid==3">
            <el-form-item label="我的课程">
                <el-select v-model="classid" placeholder="下拉选择我的课程" @focus="loadingMyClass" >
                    <el-option v-for="item in myclasses" :key="item.id" :label="item.cl_classname" :value="item.cl_classid" ></el-option>
                </el-select>
            </el-form-item>
           
            <el-form-item><el-button  v-if="permissionList.includes(1004)" type="primary" @click="query" >查询</el-button></el-form-item>
        
        </div>

        <el-form-item v-if="permissionList.includes(1004)">
            <el-input placeholder="请输入学生学号" v-model="searchSid" clearable @keyup.native="proving"></el-input>
        </el-form-item>
        <el-form-item>
             <el-button v-if="permissionList.includes(1004)" type="primary"  @click="SearchStudent" >查询学生</el-button>
        </el-form-item>
      <!-- <el-form-item  v-if="permissionList.includes(1001)"><el-button type="primary" @click="add_student">添加学生</el-button> </el-form-item> -->
    </el-form>
    <el-table :data="studentPage" height="80%" border style="width: 100%;font-size:20px;">
        <el-table-column prop="st_id" label="学号" align="center"  style="width: 8%;"></el-table-column>
        <el-table-column prop="st_name" label="姓名" align="center" style="width: 8%;"></el-table-column>
        <el-table-column prop="st_sex" label="性别" align="center" style="width: 8%;"></el-table-column>
        <el-table-column prop="st_phone" label="电话" align="center" style="width: 8%;"></el-table-column>
        <el-table-column prop="st_email" label="邮箱" align="center" style="width: 15%;"></el-table-column>
        <el-table-column prop="st_checkscore" label="签到分数" align="center" style="width: 8%;"></el-table-column>
        <el-table-column prop="st_checkcount" label="签到次数" align="center" style="width: 8%;"></el-table-column>
        <el-table-column prop="st_lastchecktime" label="上次签到时间" align="center" style="width: 8%;"></el-table-column>
        <el-table-column prop="st_lastcheckloc" label="上次签到地点" align="center" style="width: 8%;"></el-table-column>
        <el-table-column label="操作" style="width: 10%;" align="center" >
            <template  slot-scope="scope">
                <el-button type="text" icon="el-icon-edit" @click="checkLogDetail(scope.row)">查看签到记录</el-button>
            </template>
        </el-table-column>
        <el-table-column label="操作" style="width: 20%;" align="center" >
            <template slot-scope="scope">
                <el-button v-if="permissionList.includes(1003)" type="text" icon="el-icon-edit"  :v-if="'1003' in permissionList" @click="editStudent(scope.$index,scope.row)">编辑</el-button>
                <el-button v-if="permissionList.includes(1002)" type="text" icon="el-icon-delete" class="red" :v-if="1002 in permissionList"  @click="deleteStudent(scope.$index,scope.row)">删除</el-button>
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

     <!-- 编辑弹出框 -->
    <el-dialog title="签到记录" :visible.sync="checkDetailShow" width="80%">
        <el-table :data="checklog">
            <el-table-column prop="id" label="签到任务ID" align="center"></el-table-column>
            <el-table-column prop="create_time" label="签到创建时间" align="center"></el-table-column>
            <el-table-column prop="ch_checkscore" label="签到分数" align="center">
                 <template slot-scope="scope">
                        <el-tag v-if="scope.row.log!=null">{{scope.row.log!=null?"+"+scope.row.log.ch_checkscore:""}}</el-tag>
                 </template>
            </el-table-column>
             <el-table-column prop="status" label="是否停止签到" align="center">
                 <template slot-scope="scope">
                        <el-tag :type="scope.row.status===true?'success':'danger'">{{scope.row.status===true?'正常':'停止'}}</el-tag>
                    </template>
             </el-table-column>
            <el-table-column prop="status" label="是否签到" align="center">
                 <template slot-scope="scope">
                        <el-tag :type="scope.row.check===true?'success':'danger'">{{scope.row.check===true?'已签到':'未签到'}}</el-tag>
                 </template>
             </el-table-column>
             <el-table-column prop="log" label="签到时间" align="center">
                 <template slot-scope="scope">
                        <el-tag v-if="scope.row.log!=null">{{scope.row.log!=null?scope.row.log.ch_checktime:""}}</el-tag>
                 </template>
             </el-table-column>
             <el-table-column label="补签" align="center">
                <template slot-scope="scope">
                    <el-button  :disabled="scope.row.check" @click="reCheck(scope.$index,scope.row)">补签</el-button>
                </template>
             </el-table-column>

            </el-table>
    </el-dialog>
    <el-dialog :visible.sync="addShow" title="添加学生" width="40%">
      <el-form ref="ruleForm" :model="student" :rules="rules"  label-width="120px"  width="70%"> 
        <el-form-item label="学生学号" prop="st_id">
             <el-input v-model="student.st_id"></el-input>
        </el-form-item>
        <el-form-item label="学生姓名" prop="st_name">
             <el-input v-model="student.st_name"></el-input>
        </el-form-item>
        <el-form-item label="学生性别">
          <template>
               <el-radio v-model="student.st_sex" label="男">男</el-radio>
               <el-radio v-model="student.st_sex" label="女">女</el-radio>
          </template>
        </el-form-item>
        <el-form-item label="学生手机号码" prop="st_phone">
           <el-input v-model="student.st_phone"></el-input>
        </el-form-item>
        <el-form-item label="学生邮箱">
           <el-input v-model="student.st_email"></el-input>
        </el-form-item>
        <el-form-item label="请选择学校" prop="st_schoolcode">
            <el-select v-model="student.st_schoolcode"  placeholder="请选择学校"  @focus="loadSchoolList">
                <el-option v-for="item in schoolList" :key="item.id" :label="item.sch_name" :value="item.sch_code" ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="请选择学院" prop="st_collegecode">
            <el-select v-model="student.st_collegecode"  placeholder="请选择学院" @focus="loadCollegeList">
                <el-option v-for="item in collegeList" :key="item.id" :label="item.co_collegename" :value="item.co_collegeid" ></el-option>
            </el-select>
        </el-form-item>
       <el-form-item label="请选择专业" prop="st_majorcode">
            <el-select v-model="student.st_majorcode"  placeholder="请选择专业" @focus="loadMajorList">
                <el-option v-for="item in majorList" :key="item.id" :label="item.ma_majorname" :value="item.ma_majorid" ></el-option>
            </el-select>
        </el-form-item>
         <el-form-item label="学生备注">
            <el-input type="textarea" autosize placeholder="请输入备注" v-model="student.st_info"></el-input>
        </el-form-item>
        <el-form-item>
               <el-button type="primary" @click="onCreateStudent('ruleForm')">立即创建</el-button>
               <el-button @click="createCancel('ruleForm')">取消</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>


  </div>




   
</template>

<script>
export default {
    data() {
      return {
          myclass:'',
          myclasses:[],
           majorList:null,
           schoolList:null,
           collegeList:null,
           student:{
               st_id:null,
               st_name:null,
               st_sex:'男',
               st_phone:null,
               st_email:null,
               st_schoolcode:null,
               st_collegecode:null,
               st_majorcode:null,
               st_info:null
           },
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
               ],
               st_schoolcode:[
                     {required: true,message:'请选择学校',trigger: 'blur'}
               ],
               st_collegecode:[
                    {required: true, message:'请选择学院',trigger: 'blur'}
               ],
               st_majorcode:[
                    {required: true,message:'请选择专业',trigger: 'blur'}
               ]
           },
        addShow:false,
        editstu:{
            id:'',
            name:'',
        },
        // checklog:[{
        //     ch_checktime:'',
        //     ch_checkloc:'',
        //     ch_checkscore:''
        // }],
        checklog:[],
        checkDetailShow:false,
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
        teacher:null,
        students: [],
        studentPage:[],
        total:0,
        pagesize:10,
        currentPage:1,
        major:null,
        majors:[],
        colleges:[],
        collegeid:'',
        sid:'',
        permissionList:[],
        user:JSON.parse(localStorage.getItem("user"))
      }
    },
    beforeCreate(){
        console.log("beforeCreate")
         this.user=JSON.parse(localStorage.getItem("user"))
        const uri ="/"+this.$route.path.replace('/', '');
        console.log(uri)
        this.$http.get("/permission",{
            params:{
                "uri":uri
            }
        }).then(res => {
                    if(res.data.status === 0 ){
                   //      this.permissionList = Object.assign([], res.data.datas);
                          this.permissionList = res.data.datas;
                         console.log(this.permissionList)
                    }});


        if(this.user.us_roleid==3){
                this.$http.get("/teacher/user/"+this.user.id).then(res =>{
                    if(res.status==200&&res.data.status==0){
                        this.teacher = res.data.datas;
                        console.log(this.teacher)
                        console.log('teacher')
                    }
                })
        }
    },
    methods:{
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
        loadSchoolList(){
                this.$http.get("/school").then(res => {
                    if(res.data.status === 0 ){
                         this.schoolList = res.data.datas;
                    }});
           },
        loadCollegeList(){
               if(this.student.st_schoolcode!=null){
                    this.$http.get("/college",
                    {
                         params:
                         {
                              "sch_code":this.student.st_schoolcode,
                         }
                    }).then(res => {
                         if(res.data.status ===0){
                              this.collegeList = res.data.datas;
                         }
                    });
               }
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
        ChangeSize(size){
            console.log("Current size："+size);
            console.log("ChangeSize...");
            this.pagesize = size;
            this.studentPage = this.students.slice((this.currentPage-1)*this.pagesize,this.currentPage * this.pagesize);
        }
        ,
        ChangePage(page){
            this.currentPage = page;
            this.getStudentPage();
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
        SelectClass(){
            console.log("SelectClass...");
            console.log(this.classid);
        },
        query(){
            console.log("query...");
            console.log(this.permissionList)
            this.getStudentPage();
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