//引入mockjs
const Mock = require('mockjs');
const domain = 'http://mockjs.com/api';
Mock.XHR.prototype.withCredentials = true
var loginRes = Mock.mock({
    status:'success'
})

var itemsRes =  Mock.mock({
    status:'success',
    items:[
        {
            icon: 'iconfont icon-wode',
            index: 'index',
            title: '系统首页'
        },
        {
            icon: 'iconfont icon-all',
            index: 'student',
            title: '学生管理'
        },
    ]
})
// Mock.mock(`${domain}/login`, `get`, loginRes);
// Mock.mock(`${domain}/items`, `get`, itemsRes);