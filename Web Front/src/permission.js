import axios from 'axios'
const _import = require('./router/_import_' + process.env.NODE_ENV) //获取组件的方法
import Home from './components/common/Home.vue' //Layout 是架构组件，不在后台返回，在文件里单独引入
var getRouter = null;

axios.defaults.baseURL = 'http://localhost:6677/' // 设置默认请求的url
axios.defaults.withCredentials = true; // 允许前端访问后端时携带cookies信息
axios.get("/menu").then(res => {
    console.log(res)
})
router.beforeEach((to, from, next) => {
    console.log(getRouter)
    if (!getRouter) {//不加这个判断，路由会陷入死循环
       
        if (!getObjArr('router')) {
            this.$http.get("/menu").then(res => {
                console.log(res)
            getRouter = res.data//后台拿到路由
            console.log(getRouter)
            saveObjArr('router', getRouter) //存储路由到localStorage
    
            routerGo(to, next)//执行路由跳转方法
          })
        } else {//从localStorage拿到了路由
          getRouter = getObjArr('router')//拿到路由
          routerGo(to, next)
        }
      } else {
        next()
      }
    })



function routerGo(to, next) {
    getRouter = filterAsyncRouter(getRouter) //过滤路由
    router.options.routes = getRouter;//必须在addroutes前，使用router.options.routes=XXXXX的方法手动添加
    router.addRoutes(getRouter) //动态添加路由
    global.antRouter = getRouter //将路由数据传递给全局变量，做侧边栏菜单渲染工作
    next({ ...to, replace: true })
}

function saveObjArr(name, data) { //localStorage 存储数组对象的方法
        localStorage.setItem(name, JSON.stringify(data))
}

function getObjArr(name) { //localStorage 获取数组对象的方法
        return JSON.parse(window.localStorage.getItem(name));

}

function filterAsyncRouter(asyncRouterMap) { //遍历后台传来的路由字符串，转换为组件对象
const accessedRouters = asyncRouterMap.filter(route => {
    if (route.component) {
        if (route.component === 'Home') {//Layout组件特殊处理
            route.component = Home
        } else {
            route.component = _import(route.component)
        }
        }
        if (route.children && route.children.length) {
        route.children = filterAsyncRouter(route.children)
        }
        return true
})
        return accessedRouters
}
