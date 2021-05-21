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
        <el-form-item>
             <el-button type="primary" :disabled="!hasPerm('student:add')" plain @click="student={gender:'男',enabled:true};addVisible=true;">+ 新增</el-button>
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
                    <el-button type="text" icon="el-icon-edit" :disabled="!hasPerm('student:edit')" @click="editStudent(scope.$index,scope.row)" >编辑</el-button>
                    <el-popconfirm title="删除该学生将同时删除该学生的所有相关信息, 确定删除吗？" @confirm="handleDelete(true,scope.$index,scope.row)"  @cancel="handleDelete(false,scope.$index,scope.row)">
                        <el-button icon="el-icon-delete" :disabled="!hasPerm('student:del')" slot="reference" style="margin-left: 10px"  type="danger" size="small"   >删除</el-button>
                    </el-popconfirm>
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

 <!-- 添加弹出框 -->
    <el-dialog title="学生添加" :visible.sync="addVisible" width="40%">
        <el-form :model="student" label-width="100px" :rules="addRules" ref="addform">
            <el-form-item label="用户名" prop="accountName">
                <el-input v-model="student.accountName"></el-input>
            </el-form-item>
             <el-form-item label="电话" prop="phone">
                <el-input v-model="student.phone"></el-input>
            </el-form-item>
            <el-form-item label="学号" prop="sid">
                <el-input v-model="student.sid"></el-input>
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
            <el-form-item label="角色类型" prop="role">
                <el-select v-model="student.role" placeholder="请选择角色类型" @focus="loadingRoleList">
                  <el-option :disabled="item.name!='学生'" v-for="item in roleList" :key="item.id" :label="item.name" :value="item.id" ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="学校" >
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
            <el-select v-model="student.major_code" filterable placeholder="请选择专业"  @focus="loadingMajorList(student.school_code,student.college_code)">
                <el-option v-for="item in majorList" :key="item.value" :label="item.majName" :value="item.majCode" ></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="enabled">
              <template >
                <el-radio v-model="student.enabled" :label="true">启用</el-radio>
                <el-radio v-model="student.enabled" :label="false">禁用</el-radio>
                </template>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="addVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleCreate()">确 定</el-button>
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
import { getStudentList, updateStudent,searchStudent,deleteStudent, addStudent}  from '@/api/student/list'
import { getRoleList }  from '@/api/sys/role'
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
            searchInput:'',
            addVisible:false,
            addRules:{
               sid: [{required: true, message: '请输入学生学号', trigger: 'blur' }],
               name:[{required: true, message: '请输入学生姓名', trigger: 'blur' }],
               phone:[{required: true, message: '请输入学生手机号', trigger: 'blur'},
                {pattern: '1[0-9]{10}$', message: '请输入手机号', trigger: 'blur'}],
                role:[{required: true, message: '请选择学生学号', trigger: 'blur' }],
                accountName:[{required: true, message: '请输入账户名', trigger: 'blur'}],
                enabled:[{required: true, message: '请选择账号状态', trigger: 'blur'}]
            },
            roleList:[],

        }
    },
    methods:{
        handleCreate(){
            this.$refs.addform.validate( valid => {
                if(valid){
                    this.$confirm('此操作将同时以电话号码为登录账户, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then( () => {
                        console.log(this.student)
                        //确定
                        addStudent(this.student).then(res => {
                            console.log(res)
                            if(res.success==true){
                                this.$message.success("添加成功,默认密码为123456")
                                this.addVisible = false
                            }else{
                                this.$message.error(res.error)
                                this.addVisible = false
                            }
                        })
                    }).catch( () => {
                        //取消
                    } )
                }else{
                    alert("表单验证失败")
                }
            })
        },


        handleDelete(bol,index,row){
            if(bol==true){
                deleteStudent(row.sid).then(res => {
                    if(res.success===true){
                        this.$message.success("删除成功")
                        this.studentList.splice(index, 1)
                    }else{
                        this.$message.error();("删除失败")
                    }
                })
            }
        },
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
         loadingRoleList(){ //加载角色列表
        getRoleList({page:1,size:50}).then(res => {
          this.roleList = res.data.pageData
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