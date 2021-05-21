import request from '@/utils/request'


export function getCourseList(params) {
    return request({
      url: '/api/course/list',
      method: 'get',
      params: params
    })
  }

export function updateCourseStatus(data) {
return request({
    url: '/api/course/status',
    method: 'post',
    data: data
})
}


export function updateCourse(data) {
    return request({
        url: '/api/course',
        method: 'put',
        data: data
    })
    }


