import request from '@/utils/request'

export function getSysParam(data){
    return request({
        url: '/api/sys/params',
        method: 'get',
        params:data
      })
}
export function addSysParam(data){
  return request({
      url: '/api/sys/param',
      method: 'post',
      data: data
    })
}

export function updateSysParam(data){
  return request({
      url: '/api/sys/param',
      method: 'put',
      data: data
    })
}
export function deleteSysParam(data){
  return request({
      url: '/api/sys/param/'+data,
      method: 'delete',
    })
}




