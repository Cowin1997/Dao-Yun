import axios from 'axios'
import { Notification } from 'element-ui'

// 创建axios实例
const service = axios.create({
  baseURL:"http://127.0.0.1:8888",
  timeout: 5000 // 请求超时时间 5s
})
axios.defaults.withCredentials = true; // 允许前端访问后端时携带cookies信息
// request拦截器
service.interceptors.request.use(
  config => {
    config.headers['Content-Type'] = 'application/json'
    if(localStorage.getItem("token")!=null)
      //console.log(JSON.parse(localStorage.getItem("token")))
     config.headers['token'] = JSON.parse(localStorage.getItem("token"))
    return config
  },
  error => {
    Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    return response.data
  },
  error => {
    Notification.error({
      title: error.response.data.msg,
      duration: 5000
    })
    return Promise.reject(error)
  }
)
export default service
