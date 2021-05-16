// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router/routes'
import './router/index' // permission control
import ElementUI, {Form} from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'; // 默认主题
import axios from 'axios' // 导入axios http请求库
import Cookies from 'js-cookie'
import store from './store'



// axios.defaults.baseURL = 'http://127.0.0.1:6677' // 设置默认请求的url

Vue.prototype.$http = axios
Vue.config.productionTip = false
Vue.use(ElementUI, {
  size: 'small'
})



// //使用钩子函数对路由进行权限跳转
// router.beforeEach((to, from, next) => {
//   next();
// })



/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
