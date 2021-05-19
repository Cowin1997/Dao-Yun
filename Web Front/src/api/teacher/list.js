import request from '@/utils/request'

export function getTeacherList(params) {
    return request({
      url: '/api/teacher/list',
      method: 'get',
      params: params
    })
}