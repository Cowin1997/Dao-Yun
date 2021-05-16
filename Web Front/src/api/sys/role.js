import request from '@/utils/request'


export function getRoleList(){
    return request({
        url: 'api/role/all',
        method: 'get'
      })
}