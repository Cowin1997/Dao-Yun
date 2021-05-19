import request from '@/utils/request'

export function getSchoolList(params) {
    return request({
      url: 'api/org/school-list',
      method: 'get',
      params: params
    })
}


export function getCollegeList(params) {
    return request({
      url: '/api/org/college-list',
      method: 'get',
      params: params
    })
}



export function getMajList(params) {
    return request({
      url: '/api/org/major-list',
      method: 'get',
      params: params
    })
}
export function getStudentList(params) {
    return request({
      url: '/api/student/list',
      method: 'get',
      params: params
    })
}
export function updateStudent(data) {
    return request({
      url: '/api/student',
      method: 'put',
      data: data
    })
}

export function searchStudent(params) {
  return request({
    url: '/api/student/search',
    method: 'get',
    params: params
  })
}







