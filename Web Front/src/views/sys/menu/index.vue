<template>
  <div style="height:100%;width:100%;">
      <el-table row-key="id" :data="menuList">
            <el-table-column :show-overflow-tooltip="true" label="菜单标题" width="125px" prop="title" />
            <el-table-column :show-overflow-tooltip="true" label="图标" width="125px" prop="title" >
               <template slot-scope="scope">
                   <i :class="scope.row.icon ? scope.row.icon : ''"></i>
               </template>
            </el-table-column>
            <el-table-column :show-overflow-tooltip="true" align="center" label="链接路径"  prop="path"  >
            </el-table-column>
            <el-table-column prop="menu_sort" align="center" label="排序">
            </el-table-column>
            <el-table-column label="操作" width="130px" align="center" fixed="right">
                <template slot-scope="scope">
                      <el-button type="text" :disabled="!hasPerm('menu:edit')" icon="el-icon-edit" @click="editMenu(scope.$index,scope.row)" >编辑</el-button>
                </template>
            </el-table-column>
      </el-table>





      <el-dialog title="菜单管理" :visible.sync="editVisible" width="30%">
        <el-form :model="menu" ref="form">
          <el-form-item  label="菜单标题" clearable prop="name">
              <el-input v-model="menu.title" clearable></el-input>
          </el-form-item>
          <el-form-item  label="菜单图标" clearable prop="name">
              <icon-picker v-model="menu.icon"></icon-picker>
          </el-form-item>
          <el-form-item label="链接路径" clearable prop="path">
              <el-input v-model="menu.path" clearable></el-input>
          </el-form-item>
          <el-form-item label="菜单排序" >
            <template>
                <el-input-number v-model="menu.menu_sort" @change="handleChange" :min="1" :max="50" ></el-input-number>
            </template>
          </el-form-item>
        </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button  type="primary" @click="handleUpdate()">确 定</el-button>
          
            </span>
      </el-dialog>
















  </div>
</template>

<script>
import {updateMenu} from '@/api/sys/menu'
export default {
    data(){
        return {
            menuList: JSON.parse(localStorage.getItem("user"))['menus'],
            editVisible:false,
            menu:{}

        }
      
    
    },
    methods:{
        handleUpdate(){
            console.log('....handleUpdate')
            console.log(this.menu)
            updateMenu(this.menu).then(res=>{
                console.log(res)
                if(res.success==true){
                    
                    this.editVisible = false
                    this.$message.success("修改成功,下次登录生效")
                }else{
                     this.editVisible = false
                    this.$message.error("修改失败");
                }
            })
            
        },
        editMenu(index,row){
            console.log(index,row)
            this.menu = row
            this.editVisible = true
        },
        handleChange(val){
            this.menu.menu_sort = val
        }
    },
    created(){
        console.log(this.menuList)
    }
}
</script>

<style>

</style>