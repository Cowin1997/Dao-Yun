import request from '@/utils/request'

export function getUserList(data){
    return request({
        url: 'api/user',
        method: 'get',
        params:data
      })
}

export function lock(data){
  return request({
      url: 'api/user/lock',
      method: 'get',
      params:data
    })
}
export function unlock(data){
  return request({
      url: 'api/user/unlock',
      method: 'get',
      params:data
    })
}
export function editUserx(data){
  return request({
      url: 'api/user',
      method: 'put',
      data:data
    })
}



