import router from './routes'
import store from '@/store'
import Layout from '@/layout/Layout'
import NProgress from 'nprogress' // progress bar
import Role from '../views/sys/role/index'
import { constantRouterMap } from '../router/routes'
import Vue from 'vue'
const whiteList = ['/login']// no redirect whitelist

export const loadView = (view) => {
    return (resolve) => require([`@/views/${view}`], resolve)
  }

router.beforeEach((to, from, next) => {
    console.log(to)
    if (to.meta.title) {
        document.title = to.meta.title;
      }
    NProgress.start()
    if (localStorage.getItem("token")){ //当前用户已经登录
         // 已登录且要跳转的页面是登录页
        if (to.path === '/login') {
            console.log("当前用户已经登录且要跳转的页面是登录页")
            next({ path: '/' })
            NProgress.done()
        }else{
            console.log("当前用户已经登录且要跳转:"  + to.path)
        
            if(store.state.addRouters!=null && store.state.addRouters.length !=0){
                next()
            }else{
                
                const menus = JSON.parse(localStorage.getItem("user")).menus
                store.dispatch('SetSidebarRouters',JSON.parse(localStorage.getItem("user")).menus);
                build(menus)
                
                menus.push({ path: '*', redirect: '/404', hidden: true })
                store.dispatch('GenerateRoutes', menus).then(() => { // 存储路由
                     router.addRoutes(menus) // 动态添加可访问路由表
                    next({...to, replace:true})
                })
            }
        }
    }else{/* has no token*/
    if (whiteList.indexOf(to.path) !== -1) { // 在免登录白名单，直接进入
        next()
      } else {
        next(`/login?redirect=${to.fullPath}`) // 否则全部重定向到登录页
      }
    }
})





function build(menus){
    menus.filter( menu => {
        if (menu.children){
            build(menu.children)
        }

        if (menu.component){
            if (menu.component == 'layout') { // Layout组件特殊处理
                menu.component = Layout
        }else{
            menu.component = loadView(menu.component)
            menu['meta'] = {
                title:menu.title,
                icon:menu.icon
            }
        }
    }})
    return true
}


