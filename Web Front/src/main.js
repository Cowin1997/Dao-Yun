// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'; // 默认主题
import axios from 'axios' // 导入axios http请求库
import VueCookies from 'vue-cookies'


axios.defaults.baseURL = 'http://localhost:6677/' // 设置默认请求的url
axios.defaults.withCredentials = true; // 允许前端访问后端时携带cookies信息
Vue.prototype.$http = axios
Vue.config.productionTip = false
Vue.use(ElementUI, {
  size: 'small'
})
Vue.use(VueCookies)

//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
  document.title = `${to.meta.title}`;
  const user = localStorage.getItem("user");
  if (!user && to.path !== '/login') {
    next('/login');
  }else{
    next();
  }


  
})



/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
