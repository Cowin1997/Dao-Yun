<template>
    <div class="sidebar">
        <el-menu
            class="sidebar-el-menu"
            :default-active="onRoutes"
            :collapse="collapse"
            background-color="#324157"
            text-color="#bfcbd9"
            active-text-color="#20a0ff"
            unique-opened
            router
        >
            <template v-for="item in menus">
                <template v-if="item.children">
                    <el-submenu :index="item.path" :key="item.path">
                        <template slot="title">
                            <i :class="item.icon"></i>
                            <span slot="title">{{ item.title }}</span>
                        </template>
                        <template v-for="subItem in item.children">
                            <el-submenu
                                v-if="subItem.children"
                                :index="subItem.path"
                                :key="subItem.path"
                            >
                            <template slot="title">
                                <i :class="subItem.icon"></i>
                                <span slot="title">{{ subItem.title }}</span>
                            </template>
                                <el-menu-item
                                    v-for="(threeItem,i) in subItem.children"
                                    :key="i"
                                    :index="threeItem.path"
                                >{{ threeItem.title }}</el-menu-item>
                            </el-submenu>
                            <el-menu-item
                                v-else
                                :index="subItem.path"
                                :key="subItem.path"
                                
                            > <template slot="title">
                            <i :class="subItem.icon"></i>
                            <span slot="title">{{ subItem.title }}</span>
                        </template></el-menu-item>
                        </template>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.path" :key="item.path">
                        <i :class="item.icon"></i>
                        <span slot="title">{{ item.title }}</span>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>
<script>
// 导入bus,接收来自Header组件的通信
import bus from './bus';
// import Vuex from 'vuex'
export default {
    
    data(){
        return {
            collapse: false,
            menus: [
                {
                    path: '/dashboard',
                    title: '系统首页'
                },
                // {
                //     path: '/sys',
                //     title: '系统管理',
                //     children:[
                //         {
                //             path: '/sys/role',
                //             title: '角色管理'
                //         },
                //            {
                //             path: '/sys/user',
                //             title: '用户管理'
                //         }
                //     ]
                // }
            ]
        }
    },
    created() {
        // 通过 Event Bus 进行组件间通信，来折叠侧边栏
        bus.$on('collapse', msg => {
            this.collapse = msg;
            bus.$emit('collapse-content', msg);
        });
       this.menus = this.menus.concat(this.$store.state.sidebarRouters)
       console.log("MENUS")
       console.log(this.menus)
      

    },
    computed:{
        onRoutes(){
          return this.$route.path;
        }

    }
}
</script>

<style>
.sidebar {
    display: block;
    position: absolute;
    left: 0;
    top: 70px;
    bottom: 0;
    overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
    width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
    width: 250px;
}
.sidebar > ul {
    height: 100%;
}
</style>