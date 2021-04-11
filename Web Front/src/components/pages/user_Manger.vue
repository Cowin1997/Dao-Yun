<template>
  <div style="height:100%;width:100%;">
      <el-form :inline="true"  class="demo-form-inline" style="border-radius: 30px;">
        <el-form-item>
            <el-button type="primary"  @click="searchAllRole" >查询所有角色</el-button>
        </el-form-item>
      </el-form>
    <el-table :data="roleList" height="80%" border style="width: 100%;font-size:20px;">
        <el-table-column prop="id" label="ID" align="center" ></el-table-column>
        <el-table-column prop="role_name" label="角色名称" align="center" ></el-table-column>
        <el-table-column prop="role_describe" label="角色信息" align="center" ></el-table-column>
        <el-table-column prop="status" label="角色状态" align="center" width="180">
            <template slot-scope="scope">
                <el-tag :type="scope.row.status===true?'success':'danger'">{{scope.row.status===true?'正常':'禁用'}}</el-tag>
            </template>
        </el-table-column>
        <el-table-column  label="权限查询与设置" align="center" width="180">
            <template slot-scope="scope">
               <el-button @click="permissionSet(scope.row)">权限查询与设置</el-button>
            </template>
        </el-table-column>
    </el-table>
    <el-dialog :visible.sync="permissionSetVisiable" title="权限查询与设置" width="40%" @close="closeDialog">
        <el-form  width="70%">
            <el-form-item>
                <div class="block" style="width:100%;">
                <span class="demonstration">菜单权限：</span>
                <el-cascader
                    ref="cascader"
                    :options="menuOptions"
                    :props="menuProps"
                    v-model="menuPermissionList"
                    clearable
                    style="width:100%;"
                    ></el-cascader>
                </div>
            </el-form-item>
            <el-form-item>
                <div class="block" style="width:100%;">
                <span class="demonstration">功能权限：</span>
                <el-cascader
                    ref="cascader"
                    :options="funcOptions"
                    :props="funcProps"
                    v-model="funcPermissionList"
                    clearable
                    style="width:100%;"
                    ></el-cascader>
                </div>
            </el-form-item>

            <el-form-item class="center">
               <el-button type="primary" @click="onPermissionSet()">立即创建</el-button>
               <el-button @click="onPermissionSetCancel()">取消</el-button>
            </el-form-item>
        </el-form>
    </el-dialog>



  </div>
</template>

<script>
export default {
    data(){
        return {
            row:null,
            menuPermissionList:[],
            menuAllPermissionList:[],
            menuNoPermissionList:[],
            funcPermissionList:[],
            funcPermissionAllList:[],
            funcNoPermissionList:[],
            funcProps:{
                 multiple: true,
                 children: 'subs',
                 value:'id',
                 label:'name',
                emitPath:false,
                disabled:'disabled'
            },
            funcOptions:null,
            fix:true,
            menuProps: { 
                multiple: true,
                children: 'subs',
                value:'id',
                label:'title',
                emitPath:false,
                checkStrictly:true
            },
            roleList:[],
            permissionSetVisiable:false,
            menuOptions: null,

           
        }
    },
    methods:{
        closeDialog(){
            this.menuPermissionList=[]
            this.menuAllPermissionList=[]
            this.menuNoPermissionList=[]
            this.funcPermissionList=[]
            this.funcPermissionAllList=[]
            this.funcNoPermissionList=[]
            
        },
        onPermissionSet(){
            this.permissionSetVisiable = !this.permissionSetVisiable
            // console.log(this.menuPermissionList);
            // console.log(this.menuAllPermissionList);
            // console.log(this.row)
            for(var i=0; i<this.menuAllPermissionList.length;i++){
                if(!this.menuPermissionList.includes(this.menuAllPermissionList[i])){
                    this.menuNoPermissionList.push(this.menuAllPermissionList[i])
                }
            }

            this.$http.put("/menu",{
                id:this.row.id,
                permit:this.menuPermissionList,
                nopermit:this.menuNoPermissionList
            }).then(res => {
                 this.menuNoPermissionList = []
            });
            for(var i=0; i<this.funcPermissionAllList.length;i++){
                if(!this.funcPermissionList.includes(this.funcPermissionAllList[i])){
                    this.funcNoPermissionList.push(this.funcPermissionAllList[i])
                }
            }
            console.log("funcNoPermissionList")
            console.log(this.funcNoPermissionList)
             console.log("funcPermissionList")
            console.log(this.funcPermissionList)
            this.$http.post("/permission",{
                roleid:this.row.id,
                setList:this.funcPermissionList,
                unSetList:this.funcNoPermissionList
            }).then(res => {
                if(res.status==200 && res.data.status ==0){
                    console.log("修改权限成功!")
                    console.log(res)
                }

            })


            this.$http.get("/menu").then(res => {
                            if(res.status === 200){
                                var plist = []
                                for(var i=0;i<res.data.length;i++){
                                    var m = {
                                        "index":res.data[i]['index'],
                                        "title":res.data[i]['title']
                                    }
                                    plist.push(m)
                                    if("subs" in res.data[i]){
                                        for(var j=0;j<res.data[i]['subs'].length;j++){
                                            var m = {
                                                "index":res.data[i]['subs'][j]['uri'],
                                                "title":res.data[i]['subs'][j]['title']
                                            }
                                    plist.push(m)
                                        }
                                    }

                                }
                                        global.permitList = plist;
                                        localStorage.setItem("route",JSON.stringify(plist))
                                }})



        },
        onPermissionSetCancel(){
            this.menuPermissionList=[]
            this.menuAllPermissionList=[]
            this.menuNoPermissionList=[]
            this.funcPermissionList=[]
            this.funcPermissionAllList=[]
            this.funcNoPermissionList=[]
            this.permissionSetVisiable = !this.permissionSetVisiable
             
        },
        searchAllRole(){
            this.$http.get("/role").then(res =>{
                if(res.status==200&&res.data.status ===0){
                    this.roleList = res.data.datas;
  //                  console.log(this.roleList)
                }
            })
        },
        permissionSet(row){
            this.row = row;
            var mlist = []
            this.$http.get("/menu/role").then(res => {
               
                if(res.status==200){
                    this.menuOptions = res.data;
                    for(var i=0;i< res.data.length;i++){
                            this.menuAllPermissionList.push(res.data[i].id);
                            if('subs' in res.data[i]){
                                for(var j=0;j<res.data[i]['subs'].length;j++){
                                    this.menuAllPermissionList.push(res.data[i]['subs'][j]['id']); 
                                }
                            }
                        }
                    }
                     console.log( this.menuAllPermissionList)
            })
            this.$http.get("/menu/role/"+row.id).then(res => {
               // console.log(res)
                if(res.status==200){
                    var list = res.data;
                    for(var i=0;i<list.length;i++){
                        mlist.push(list[i].id);
                        if('subs' in list[i]){
                            for(var j=0;j<list[i]['subs'].length;j++){
                              //  this.$set(this.menuPermissionList, -1, list[i]['sub']['id'])
                                mlist.push(list[i]['subs'][j]['id']);
                               
                            }
                        }
                    }
                    this.menuPermissionList = mlist;
                    this.permissionSetVisiable = !this.permissionSetVisiable
                }
            })


           this.$http.get("/permission/menu").then(res => {        
                if(res.status==200 && res.data.status==0){
                    this.funcOptions = res.data.datas;
                }
            })
            this.$http.get("/permission").then(res => {        
                if(res.status==200 && res.data.status==0){
                    this.funcPermissionAllList = res.data.datas;
                }
            })

             this.$http.get("/permission/menu",{
                 params:{
                     "roleid":this.row.id
                 }
             }).then(res => {
                console.log("/permission/menudddd");
                console.log(res.data.datas)
                if(res.status==200 && res.data.status==0){
                    this.funcPermissionList = res.data.datas;

                }
            })
        }
    },


}
</script>

<style>

</style>