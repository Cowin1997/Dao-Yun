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
        </el-form>
        </div>
    <el-table :data="classList" height="80%" border style="width: 100%;font-size:20px;">
        <el-table-column prop="cl_classid" label="课程号" align="center"></el-table-column>
        <el-table-column prop="cl_classname" label="课程名" align="center"></el-table-column>
        <el-table-column prop="teacher.te_teachname" label="任课教师" align="center"></el-table-column>
        <el-table-column prop="teacher.te_phone" label="任课教师电话" align="center"></el-table-column>
        <el-table-column prop="cl_classloc" label="上课地点" align="center"></el-table-column>
        <el-table-column prop="info" label="上课时间" align="center"></el-table-column>
        <el-table-column label="操作" style="width: 40%;" align="center" >
            <template slot-scope="scope">
                <el-button type="text" icon="el-icon-edit" @click="editClass(scope.$index,scope.row)">编辑</el-button>
                <el-button type="text" icon="el-icon-delete" class="red" @click="deleteClass(scope.$index,scope.row)">删除</el-button>
            </template>
        </el-table-column>
   </el-table>


    <!-- 编辑弹出框 -->
    <el-dialog title="编辑" :visible.sync="editVisible" width="40%">
        <el-form  :model="eClass" label-width="100px">
            <el-form-item label="课程号">
                <el-input v-model="eClass.cl_classid" :disabled="true"></el-input>
            </el-form-item>

            <el-form-item label="课程名">
                <el-input v-model="eClass.cl_classname"></el-input>
            </el-form-item>

            <el-form-item label="任课教师">
                <el-input v-model="eClass.teacher.te_teachname"></el-input>
            </el-form-item>

            <el-form-item label="任课教师电话" >
                <el-input v-model="eClass.teacher.te_phone"></el-input>
            </el-form-item>

            <el-form-item label="上课地点">
                <el-input v-model="eClass.cl_classloc"></el-input>
            </el-form-item>

       
            <el-form-item label="上课时间"  width="40%">
                <el-input v-model="eClass.info"></el-input>
            </el-form-item>

        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="editVisible = false">取 消</el-button>
            <el-button type="primary" @click="doEditClass">确 定</el-button>
        </span>
    </el-dialog>
    </div>
   
</template>

<script>
export default {
data() {
      return {
          school:null,
          schoolList:[],
          college:null,
          collegeList:[],
          major:null,
          majorList:[],
          class_:null,
          classList:[],
          user:JSON.parse(localStorage.getItem("user")),
          editVisible:false,
          eClass:{
              teacher:{}

          }
       
      }
    },
    beforeCreate(){
        // console.log("beforeCreate")
        // this.user= JSON.parse(localStorage.getItem("user"));
        // console.log(this.user)
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
            this.$http.get("/course",{params:{"sch_code":this.school,"col_id":this.college,"maj_id":this.major}}).then(res => {
                if(res.data.status=== 0 ){
                    this.classList = res.data.datas;
                 
                }});
        },
        editClass(index,row){
            this.eClass = row;
            this.editVisible=!this.editVisible;
        },
        doEditClass(){
                this.$http.post("/course",this.eClass).then(res =>{
                    if(res.status==200&&res.data.status==0) {
                        this.$message.success("修改成功!")
                        this.editVisible=!this.editVisible
                    }
                })
        },
        deleteClass(index,row){
            this.$confirm('确定要删除吗？', '提示', {
                type: 'warning'
            }).then(() => {
                this.$http.delete("/course/"+index).then(res => {
                    if(res.status==200 && res.data.status==0){
                        this.$message.success("删除成功!");
                        this.query();
                    }
                })
            }).catch((e) => {})
        }

    }
}
    
</script>

<style>

</style>