import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/pages/Login.vue'
import Header from '../components/common/Header.vue'
import Sidebar from '../components/common/Sidebar.vue'
import Home from '../components/common/Home.vue'
import Tags from '../components/common/Tags.vue'
import Student from '../components/pages/Student.vue'
import Index from '../components/pages/Index.vue'
import Admin from '../components/pages/Admin.vue'
import Class from '../components/pages/Class.vue'
import Teacher from '../components/pages/Teacher.vue'
import Data from '../components/pages/Data.vue'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/',
      redirect:'/index'
    },
    {
      path:'/login',
      component:Login,
      meta: { title: '登录' }
    },
    {
      path:'/',
      component:Home,
      children:[
        {
          path:'/index',
          component:Index,
          meta: { title: '系统首页' }
        },
        {
          path:'/student',
          component:Student,
          meta: { title: '学生管理' }
        },
        {
          path:'/admin',
          component:Admin,
           meta: { title: '管理员设置' }
        },
        {
          path:'/class',
          component:Class,
          meta: { title: '班级管理' }
        },
        {
          path:'/teacher',
          component:Teacher,
          meta: { title: '教师管理' }
        },
        {
          path:'/data',
          component:Data,
          meta: { title: '数据字典' }
        }
      ]
    },
   
  ]
})
