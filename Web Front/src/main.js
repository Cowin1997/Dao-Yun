// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI, {Form} from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'; // 默认主题
import axios from 'axios' // 导入axios http请求库
import VueCookies from 'vue-cookies'


// axios.defaults.baseURL = 'http://127.0.0.1:6677' // 设置默认请求的url
axios.defaults.withCredentials = true; // 允许前端访问后端时携带cookies信息
Vue.prototype.$http = axios
Vue.config.productionTip = false
Vue.use(ElementUI, {
  size: 'small'
})
Vue.use(VueCookies)


// 添加请求拦截器
axios.interceptors.request.use(function (config) {
  // 在发送请求之前做些什么
  if (localStorage.getItem('token')) {
    config.headers.token = localStorage.getItem('token');
  }
  return config
}, function (error) {
  // 对请求错误做些什么
  return Promise.reject(error)
});


//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
  const user = localStorage.getItem("user");
  if (to.path == '/login') next();
  if (!user && to.path !== '/login') {
    next('/login');
  } else {
    var title = to.meta.title; //hasPermission(to.path);
    if (title != null) {
      document.title = title;
      next();
    } else {
      next('/login');
    }
  }
})

function hasPermission(to) {
  console.log(to)
  if (localStorage.getItem("route")) global.permitList = JSON.parse(localStorage.getItem("route"));
  console.log(global.permitList)
  for (var i = 0; i < global.permitList.length; i++) {
    if ("subs" in global.permitList[i]) {
      for (var j = 0; j < global.permitList[i]['subs'].length; j++) {
        if (to == global.permitList[i]['subs'][j]['uri']) return global.permitList[i]['subs'][j]['title']
      }
    }
    if (to == global.permitList[i]['index']) return global.permitList[i]['title']
  }
  return null
}


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
