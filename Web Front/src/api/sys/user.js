import request from '@/utils/request'

export function getUserList(data){
    return request({
        url: 'api/user',
        method: 'get',
        params:data
      })
}