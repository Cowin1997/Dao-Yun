import request from '@/utils/request'

export function updateMenu(data){
    return request({
        url: '/api/menu',
        method: 'put',
        data:data
      })
}