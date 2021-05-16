import request from '@/utils/request'



export function getCodeImg() {
    return request({
      url: 'api/code/captcha',
      method: 'get'
    })
}
export function login(loginQuery) {
    return request({
      url: 'api/auth/login',
      method: 'post',
      data: loginQuery
    })
  }
