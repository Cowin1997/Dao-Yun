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
import "font-awesome/css/font-awesome.css";
import iconPicker from 'vue-fontawesome-elementui-icon-picker';

Vue.use(iconPicker);
// axios.defaults.baseURL = 'http://127.0.0.1:6677' // 设置默认请求的url

Vue.prototype.$http = axios
Vue.config.productionTip = false
Vue.use(ElementUI, {
  size: 'small'
})


function hasPerm(permission){
  let btnPerms = JSON.parse(localStorage.getItem("perm"));
  var i = btnPerms.indexOf(permission);
  if(i>=0) return true;
  return false;
}

Vue.prototype.hasPerm = hasPerm;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
