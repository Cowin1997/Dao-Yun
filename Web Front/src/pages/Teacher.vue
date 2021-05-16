<template>
   <div style="height:100%;width:100%;"> 
      <div v-if="user.us_roleid==1||user.us_roleid==2">
        <el-form :inline="true"  class="demo-form-inline" style="border-radius: 30px;">
            <el-form-item label="学校">
                <el-select v-model="school" placeholder="请选择学校" @focus="loadingSchool">
                    <el-option v-for="item in schoolList" :key="item.id" :label="item.sch_name" :value="item.sch_code"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="学院">
                <el-select v-model="college" placeholder="请选择学院" @focus="loadingCollege" >
                    <el-option v-for="item in collegeList" :key="item.id" :label="item.co_collegename" :value="item.co_collegeid" ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="专业">
                <el-select v-model="major" placeholder="请选择专业" @focus="loadingMajor">
                    <el-option v-for="item in majorList" :key="item.value" :label="item.ma_majorname" :value="item.ma_majorid" ></el-option>
                </el-select>
            </el-form-item>
             <el-form-item><el-button type="primary" @click="query">查询</el-button></el-form-item>
              <el-form-item><el-button type="primary" @click="addTeacher">+添加教师</el-button></el-form-item>
        </el-form>
        </div>
       <el-table :data="teacherList" height="80%" border style="width: 100%;font-size:20px;">
            <el-table-column prop="te_teachid" label="教工号" align="center"></el-table-column>
            <el-table-column prop="te_teachname" label="姓名" align="center"></el-table-column>
             <el-table-column  prop="te_phone" label="电话" align="center"></el-table-column>
            <el-table-column  prop="te_email" label="邮箱" align="center"></el-table-column>
            <el-table-column  prop="te_sex"  label="性别" align="center"></el-table-column>
            <el-table-column label="操作" style="width: 40%;" align="center" >
                <template slot-scope="scope">
                    <el-button type="text" icon="el-icon-edit" @click="editTeacher(scope.$index,scope.row)">编辑</el-button>
                    <el-button type="text" icon="el-icon-delete" class="red" @click="deleteTeacher(scope.$index,scope.row)">删除</el-button>
                </template>
            </el-table-column>
       </el-table>
 <!-- 编辑弹出框 -->
    <el-dialog title="编辑" :visible.sync="addVisible" width="40%">
        <el-form :model="teacher" label-width="70px">
        <el-form-item label="教工号">
                <el-input v-model="teacher.te_teachid" ></el-input>
        </el-form-item>
        <el-form-item label="姓名">
                <el-input v-model="teacher.te_teachname" ></el-input>
        </el-form-item>
        <el-form-item label="电话">
                <el-input v-model="teacher.te_phone" ></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
                <el-input v-model="teacher.te_email" ></el-input>
        </el-form-item>
        <el-form-item label="性别">
             <template>
                    <el-radio v-model="teacher.te_sex" label='男'>男</el-radio>
                    <el-radio v-model="teacher.te_sex" label='女'>女</el-radio>
                </template>
        </el-form-item>
        <el-form-item label="学校">
                <el-select v-model="school" placeholder="请选择学校" @focus="loadingSchool">
                    <el-option v-for="item in schoolList" :key="item.id" :label="item.sch_name" :value="item.sch_code"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="学院">
                <el-select v-model="college" placeholder="请选择学院" @focus="loadingCollege" >
                    <el-option v-for="item in collegeList" :key="item.id" :label="item.co_collegename" :value="item.co_collegeid" ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="专业">
                <el-select v-model="major" placeholder="请选择专业" @focus="loadingMajor">
                    <el-option v-for="item in majorList" :key="item.value" :label="item.ma_majorname" :value="item.ma_majorid" ></el-option>
                </el-select>
            </el-form-item>
      </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="addVisible = false">取 消</el-button>
            <el-button type="primary" @click="doAddTeacher" >确 定</el-button>
        </span>
    </el-dialog>



 <!-- 添加弹出框 -->
    <el-dialog title="添加" :visible.sync="editVisible" width="40%">
        <el-form :model="teacher" label-width="70px">
        <el-form-item label="教工号">
                <el-input v-model="teacher.te_teachid" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
                <el-input v-model="teacher.te_teachname" ></el-input>
        </el-form-item>
        <el-form-item label="电话">
                <el-input v-model="teacher.te_phone" ></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
                <el-input v-model="teacher.te_email" ></el-input>
        </el-form-item>
        <el-form-item label="性别">
             <template>
                    <el-radio v-model="teacher.te_sex" label='男'>男</el-radio>
                    <el-radio v-model="teacher.te_sex" label='女'>女</el-radio>
                </template>
        </el-form-item>






      </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="editVisible = false">取 消</el-button>
            <el-button type="primary" @click="doEditTeacher" >确 定</el-button>
        </span>
    </el-dialog>
   </div>
</template>

<script>
// import { Message } from 'element-ui'
// Vue.use(Message)
// Vue.prototype.$message = Message
export default {
    data() {
     return {
         teacher:{},
         teacherList:[],
         user:JSON.parse(localStorage.getItem("user")),
         school:null,
         schoolList:[],
         college:null,
         collegeList:[],
         major:null,
         majorList:[],
         editVisible:false,
         addVisible:false,
     }
    },
    methods:{
        loadingSchool(){
            this.$http.get("/school").then(res => {
            if(res.data.status === 0 ){
                this.schoolList = res.data.datas;
            }
           });
        },
        loadingCollege(){
            this.$http.get("/college",{params:{"sch_code":this.school}}).then(res => {
                if(res.data.status === 0 ){this.collegeList = res.data.datas;}
           });
        },
        loadingMajor(){
         this.$http.get("/major",{params:{sch_code:this.school,col_id:this.college}}).then(res => {
                if(res.data.status === 0 ){this.majorList = res.data.datas;}});
        },
        query(){
             this.$http.get("/teacher",{params:{school:this.school,college:this.college,major:this.major}}).then(res => {
                if(res.data.status === 0 ){this.teacherList = res.data.datas;console.log(this.teacherList)}});
        },
        editTeacher(index,row){
            this.teacher = row;
            this.editVisible=!this.editVisible;
        },
        doEditTeacher(){
            this.$http.post("/teacher",this.teacher).then(res => {
                  if(res.status==200 && res.data.status==0){
                       this.$message.success("修改成功!")
                      this.editVisible=!this.editVisible;
                  }else{
                     this.$message.error("修改失败!")
                  }
              })
        },
        deleteTeacher(index,row){
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            }).then(() => {
                console.log(row)
              this.$http.delete("/teacher/"+row.id).then(res => {
                  console.log(res)
                  if(res.status==200 && res.data.status==0){
                      this.$message.success("删除成功!")
                  }else {
                        this.$message.error("删除失败!")
                  }
              }).catch((e) =>{
                   this.$message.error("删除失败!")
              })
            }).catch((e) => {
                 this.$message.error("删除失败!")
            })
        },
        addTeacher(){
            this.addVisible = !this.addVisible
        },
        doAddTeacher(){
            this.teacher.te_schoolcode = this.school;
            this.teacher.te_collegeid = this.college;
            this.teacher.te_majorid = this.major;
            this.$http.put('/teacher',this.teacher).then(res =>{
                if(res.status==200 &&  res.data.status==0){
                    this.$message.success("添加成功!");
                    this.addVisible=!this.addVisible;
                }else{
                    this.$message.error("添加失败!");
                    this.addVisible=!this.addVisible;
                }
            })
             
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