import Vue from 'vue'
//import Router from 'vue-router'
import Layout from '../layout/Layout'
import Home from '../views/home'
import Role from '../views/sys/role/index'

import VueRouter from 'vue-router'

Vue.use(VueRouter)
//Vue.use(Router)


// const originalPush = Router.prototype.push
// const originalReplace = Router.prototype.replace
// // push
// Router.prototype.push = function push (location, onResolve, onReject) {
//   if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
//   return originalPush.call(this, location).catch(err => err)
// }
// // replace
// Router.prototype.replace = function push (location, onResolve, onReject) {
//   if (onResolve || onReject) return originalReplace.call(this, location, onResolve, onReject)
//   return originalReplace.call(this, location).catch(err => err)
// }

export const constantRouterMap = [
  // { 
  //   path: '/sys',
  //   title: '系统管理',
  //   component: Layout,
  //   children:[{
  //     path: '/sys/role',
  //     component: Role,
  //     title: '角色管理',
  //   }]
  // },
    {
      path: '/',
      redirect: '/dashboard',
      component: Layout,
      children:[{
        path: '/dashboard',
        component: Home,
        meta:{ title:"首页", icon:'el-icon-s-custom'}
      }
    ]
    },
      {
        path: '/404',
        component: () => import(/* webpackChunkName: "404" */ '../views/404.vue'),
        meta: { title: '404' }
    },
    {
        path: '/403',
        component: () => import(/* webpackChunkName: "403" */ '../views/403.vue'),
        meta: { title: '403' }
    },
    { 
      path: '/login',
      meta: { title: '登录'},
      component: (resolve) => require(['@/views/login'], resolve)
    }

  ]


export default new VueRouter({
  // mode: 'hash',
  mode: 'history',
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
