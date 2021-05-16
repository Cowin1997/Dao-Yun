import request from '@/utils/request'

export function getSysDict(data){
    return request({
        url: '/api/sys/dicts',
        method: 'get',
        params:data
      })
}

export function addSysDict(data){
    return request({
        url: '/api/sys/dict',
        method: 'post',
        data:data
      })
}
export function updateSysDict(data){
    return request({
        url: '/api/sys/dict',
        method: 'put',
        data:data
      })
}
export function updateSubSysDict(data){
    return request({
        url: '/api/sys/sub-dict',
        method: 'put',
        data:data
      })
}
export function addSubSysDict(data){
    return request({
        url: '/api/sys/sub-dict',
        method: 'post',
        data:data
      })
}

export function deleteSysDict(data){
    return request({
        url: '/api/sys/dict/'+data,
        method: 'delete',
      })
}

export function deleteSubSysDict(data){
    return request({
        url: '/api/sys/sub-dict/'+data,
        method: 'delete',
      })
}