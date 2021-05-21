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
      </el-form>



        <el-table :data="courseList" height="80%" border style="width: 100%;font-size:15px;">
            <el-table-column prop="cid" label="班课号" align="center"  style="width: 8%;"></el-table-column>
            <el-table-column prop="coursename" label="班课名" align="center" style="width: 8%;"></el-table-column>
            <el-table-column prop="detailinfo" label="班课信息" align="center" style="width: 8%;"></el-table-column>
            <el-table-column prop="gmt_create" label="创建时间" align="center" style="width: 8%;"></el-table-column>
            <el-table-column prop="teacher_tid" label="任课教师工号" align="center" style="width: 8%;"></el-table-column>
            <el-table-column prop="teacher.name" label="任课教师" align="center" style="width: 8%;"></el-table-column>
            <el-table-column prop="enabled" label="当前班课是否可加入" align="center">
              <template slot-scope="scope">
                  <el-switch
                    v-model="scope.row.enabled"
                    active-color="#409EFF"
                    inactive-color="#F56C6C"
                    @change="changeEnabled(scope.row, scope.row.enabled)"
                    :disabled="!hasPerm('course:edit')"
                  />
              </template>
            </el-table-column>
            <el-table-column label="操作" style="width: 20%;" align="center" >
                <template slot-scope="scope">
                    <el-button type="text" icon="el-icon-edit" :disabled="!hasPerm('course:edit')" @click="handleEdit(scope.$index,scope.row)">编辑</el-button>
                    <el-popconfirm title="删除该学生将同时删除该学生的所有相关信息, 确定删除吗？" @confirm="handleDelete(true,scope.$index,scope.row)"  @cancel="handleDelete(false,scope.$index,scope.row)">
                        <el-button icon="el-icon-delete" :disabled="!hasPerm('course:del')" slot="reference" style="margin-left: 10px"  type="danger" size="small"   >删除</el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>



    <!-- 编辑弹出框 -->
    <el-dialog title="班课编辑" :visible.sync="editVisible" width="40%">
        <el-form :model="course" label-width="150px" :rules="editRules" ref="editform">
            <el-form-item label="班课号" prop="cid">
                <el-input v-model="course.cid" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="班课名" prop="coursename">
                <el-input v-model="course.coursename"></el-input>
            </el-form-item>
            <el-form-item label="班课信息" prop="detailinfo">
                <el-input v-model="course.detailinfo"></el-input>
            </el-form-item>
            <el-form-item label="任课教师工号" prop="teacher_tid">
                <el-input v-model="course.teacher_tid"></el-input>
            </el-form-item>
            <el-form-item label="当前班课是否可加入"  >
                  <el-switch
                    v-model="course.enabled"
                    active-color="#409EFF"
                    inactive-color="#F56C6C"
                  />
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="editVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleUpdate()" >确 定</el-button>
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
import { getCourseList, updateCourseStatus, updateCourse} from '@/api/course/list'




export default {
  data(){
    return{
      schoolList:[],
      collegeList:[],
      majorList:[],
      school:'',
      college:'',
      major:'',
      searchInput:'',
      courseList:[],
      currentPage:1,
      totalSize:0,
      pageSize:10,
      currentIndex:0,
      editVisible:false,
      course:{},
      editRules:{

      }







    }
  },
  methods:{
    handleDelete(bol,index,row){
      this.currentIndex = index;
      if(bol){

      }
    },
    handleUpdate(){
      updateCourse(this.course).then( res => {
        console.log(res)
        if(res.success===true){
          this.$message.success("更新成功")
          this.editVisible = false
        }else{
          this.$message.error(res.error)
          this.editVisible = false
        }
      } )
    },
    handleEdit(index,row){
      this.currentIndex = index
      this.course = row
      this.editVisible = true
    },
    changeEnabled(row,enabled){
      console.log(row,enabled)
      updateCourseStatus({cid:row.cid,enabled:enabled}).then(res => {
        if(res.success){
          this.$message.success("更新成功")
        }else{
          this.$message.error("数据请求失败")
        }
      })
    },
    search(){
      getCourseList({page:this.currentPage,size:this.pageSize,col:this.college,maj:this.major,sch:this.school}).then(res => {
        console.log(res)
        if(res.success==true){
          this.courseList = res.data.pageData;
          this.totalSize = res.data.totalSize;
        }else{
          this.$message.error("请求数据失败")
        }
      })
    },
    handleCurrentChange(currentPage){
            this.currentPage = currentPage
    },
    handleSizeChange(pageSize){
            this.pageSize = pageSize
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
  }
}
</script>

<style>

</style>