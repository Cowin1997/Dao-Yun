import request from '@/utils/request'

export function getTeacherList(params) {
    return request({
      url: '/api/teacher/list',
      method: 'get',
      params: params
    })
}

export function updateTeacher(data) {
  return request({
    url: '/api/teacher',
    method: 'put',
    data: data
  })
}



export function searchTeacher(params) {
  return request({
    url: '/api/teacher/search',
    method: 'get',
    params: params
  })
}

export function deleteTeacher(params) {
  return request({
    url: '/api/teacher/'+params,
    method: 'delete'
  })
}