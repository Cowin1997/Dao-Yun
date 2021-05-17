import request from '@/utils/request'


export function getRoleList(data){
    return request({
        url: 'api/role',
        method: 'get',
        params: data
      })
}

export function addRole(data){
  return request({
      url: 'api/role',
      method: 'post',
      data: data
    })
}

export function updateRole(data){
  return request({
      url: 'api/role',
      method: 'put',
      data: data
    })
}

export function deleteRole(data){
  return request({
      url: 'api/role/'+data,
      method: 'delete'
    })
}

export function getPermissionTree(param){
  return request({
      url: 'api/menu',
      method: 'get',
      params: param
    })
}


export function setPermission(data){
  return request({
      url: 'api/menu/set',
      method: 'post',
      data: data
    })
}