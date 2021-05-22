import request from '@/utils/request'



export function pwdRest(data) {
    return request({
      url: 'api/auth/pwd-reset',
      method: 'post',
      data:data
    })
}