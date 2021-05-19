<template>
    <div style="height:100%;width:100%;"> 
      <el-form :inline="true"  class="demo-form-inline" style="border-radius: 30px;">
        <el-form-item label="学校">
            <el-select  filterable v-model="school" placeholder="请选择学校"  @focus="loadingSchoolList()">
                <el-option v-for="item in schoolList" :key="item.id" :label="item.schName" :value="item.schCode" ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="学院">
            <el-select v-model="college"  filterable placeholder="请选择学院" @focus="loadingCollegeList(school)">
                <el-option v-for="item in collegeList" :key="item.id" :label="item.colName" :value="item.colCode" ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="专业">
            <el-select v-model="major" filterable placeholder="请选择专业"  @focus="loadingMajorList(school,college)">
                <el-option v-for="item in majorList" :key="item.value" :label="item.majName" :value="item.majCode" ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="search()">查询</el-button>
        </el-form-item>
       <div class='el-form-item el-form-item--small' >
        <el-form-item>
            <el-input placeholder="请输入学号或姓名进行模糊搜索" v-model="searchInput" clearable></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="success" @click="searchBy()">查询</el-button>
        </el-form-item>
       </div>
        


      </el-form>


        <el-table :data="studentList" height="80%" border style="width: 100%;font-size:15px;">
            <el-table-column prop="sid" label="学号" align="center"  style="width: 8%;"></el-table-column>
            <el-table-column prop="name" label="姓名" align="center" style="width: 8%;"></el-table-column>
            <el-table-column prop="gender" label="性别" align="center" style="width: 8%;"></el-table-column>
            <el-table-column prop="phone" label="电话" align="center" style="width: 8%;"></el-table-column>
            <el-table-column label="操作" style="width: 20%;" align="center" >
                <template slot-scope="scope">
                    <el-button type="text" icon="el-icon-edit" @click="editStudent(scope.$index,scope.row)" >编辑</el-button>
                    <el-button type="text" icon="el-icon-delete" class="red"  >删除</el-button>
                </template>
            </el-table-column>
        </el-table>



  <!-- 编辑弹出框 -->
    <el-dialog title="学生编辑" :visible.sync="editVisible" width="40%">
        <el-form :model="student" label-width="70px" :rules="editRules" ref="editform">
            <el-form-item label="学号" prop="sid">
                <el-input v-model="student.sid" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
                <el-input v-model="student.name"></el-input>
            </el-form-item>
            <el-form-item label="学生性别">
                <template>
                    <el-radio v-model="student.gender" label="男">男</el-radio>
                    <el-radio v-model="student.gender" label="女">女</el-radio>
                 </template>
            </el-form-item>
            <el-form-item label="电话" prop="phone">
                <el-input v-model="student.phone"></el-input>
            </el-form-item>
            <el-form-item label="学校">
            <el-select  filterable v-model="student.school_code" placeholder="请选择学校"  @focus="loadingSchoolList">
                <el-option v-for="item in schoolList" :key="item.id" :label="item.schName" :value="item.schCode" ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="学院">
            <el-select v-model="student.college_code"  filterable placeholder="请选择学院" @focus="loadingCollegeList(student.school_code)">
                <el-option v-for="item in collegeList" :key="item.id" :label="item.colName" :value="item.colCode" ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="专业">
            <el-select v-model="student.major_code" filterable placeholder="请选择专业"  @focus="loadingMajorList(student.school_code,student.major_code)">
                <el-option v-for="item in majorList" :key="item.value" :label="item.majName" :value="item.majCode" ></el-option>
            </el-select>
        </el-form-item>


        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="editVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleUpdate()">确 定</el-button>
        </span>
    </el-dialog>








        

        <!-- 分页 -->
        <el-pagination style="text-align:center;margin-top:10px;"
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
import { getSchoolList, getCollegeList,getMajList}  from '@/api/student/list'
import { getStudentList, updateStudent,searchStudent}  from '@/api/student/list'

export default {
    data(){
        return {
            school: '', //下拉框选中的学校
            schoolList:[], //学校列表
            college:'',//下拉框选中的学院
            collegeList:[], //学院列表
            major:'',//下拉框选中的专业
            majorList:'',//专业列表
            studentList:[],
            student:{},
            currentPage:1,
            pageSize:10,
            totalSize:0,
            editVisible:false,
            editRules:{
               sid: [{required: true, message: '请输入学生学号', trigger: 'blur' }],
               name:[{required: true, message: '请输入学生姓名', trigger: 'blur' }],
               phone:[{required: true, message: '请输入学生手机号', trigger: 'blur'},
                    {pattern: '1[0-9]{10}$', message: '请输入手机号', trigger: 'blur'}]
            },
            searchInput:''
            







        }
    },
    methods:{
        searchBy(){
            searchStudent( {
                search:this.searchInput,
                page:this.currentPage,
                size:this.pageSize
            }).then(res => {
                if(res.success== true){
                this.studentList = res.data.pageData;
                this.totalSize = res.data.totalSize
              }else{
                  this.$message.error("接口请求失败")
              }
            })
        },
        loadingSchoolList(){
            getSchoolList({page:1,size:50}).then(res =>{
                if(res.success==true){
                    this.schoolList = res.data.pageData;
                }else{
                    this.$message.error("请求接口失败")
                }
            })
        },
        loadingCollegeList(school){
            if(school!=''){
                getCollegeList({page:1,size:50,schCode:school}).then(res =>{
                    if(res.success==true){
                        this.collegeList = res.data.pageData;
                    }else{
                        this.$message.error("请求接口失败")
                    }
            })
            } else{
                this.$message.info("请先选择学校")
            }
        },
        loadingMajorList(school,college){
            if(school=='' || college=='') this.$message.info('请先选择学校和学院');
            getMajList({page:1,size:50,colCode:college}).then(res =>{
                if(res.success==true){
                    this.majorList = res.data.pageData;
                }else{
                    this.$message.error("请求接口失败")
                }
        })
        },
        search(){
           getStudentList({page:this.currentPage,size:this.pageSize,col:this.college,maj:this.major,sch:this.school}).then(res => {
              if(res.success== true){
                console.log(res)
                this.studentList = res.data.pageData;
                this.totalSize = res.data.totalSize
              }else{
                  this.$message.error("接口请求失败")
              }
           })
        },
        handleCurrentChange(currentPage){
            this.currentPage = currentPage
        },
        handleSizeChange(pageSize){
            this.pageSize = pageSize
        },
        editStudent(index,row){
            this.student = row
            this.editVisible = true;
        },
        handleUpdate(){
          this.$refs.editform.validate( valid =>{
              if(valid){
                updateStudent(this.student).then(res =>{
                console.log(res)
                if(res.success==true){
                    getStudentList({page:this.currentPage,size:this.pageSize,col:this.college,maj:this.major,sch:this.school}).then(res => {
                        if(res.success== true){
                            console.log(res)
                            this.studentList = res.data.pageData;
                            this.totalSize = res.data.totalSize
                        }else{
                            this.$message.error("接口请求失败")
                        }
                    })
                    this.$message.success("修改成功")
                    this.editVisible = false;
                }
            })}else{
                alert('表单验证失败')
                this.editVisible = false;
              }
          })
        }
    }
}
</script>

<style>
.red{
    color: red;
}
</style>