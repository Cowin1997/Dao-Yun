# 到云后端API


<a name="overview"></a>
## Overview
接口描述


### Version information
*Version* : 1.0.0-SNAPSHOT


### URI scheme
*Host* : localhost:6677  
*BasePath* : /


### Tags

* basic-error-controller : Basic Error Controller
* check-controller : Check Controller
* check-log-controller : Check Log Controller
* config-data-controller : Config Data Controller
* major-controller : Major Controller
* menu-controller : Menu Controller
* permission-controller : Permission Controller
* role-controller : Role Controller
* 学校列表接口 : School Controller
* 学生信息接口 : Student Controller
* 学院列表接口 : College Controller
* 教师信息接口 : Teacher Controller
* 班课信息接口 : Course Controller
* 用户账号接口 : User Controller




<a name="paths"></a>
## Resources

<a name="basic-error-controller_resource"></a>
### Basic-error-controller
Basic Error Controller


<a name="errorusingpost"></a>
#### error
```
POST /error
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="errorusingget"></a>
#### error
```
GET /error
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="errorusingput"></a>
#### error
```
PUT /error
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="errorusingdelete"></a>
#### error
```
DELETE /error
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


##### Produces

* `*/*`


<a name="errorusingpatch"></a>
#### error
```
PATCH /error
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="errorusinghead"></a>
#### error
```
HEAD /error
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="errorusingoptions"></a>
#### error
```
OPTIONS /error
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< string, object > map|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="check-controller_resource"></a>
### Check-controller
Check Controller


<a name="checktaskusingpost"></a>
#### CheckTask
```
POST /check
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**map**  <br>*required*|map|< string, object > map|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="createtaskusingpost"></a>
#### createTask
```
POST /check/task
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**task**  <br>*required*|task|[CheckTask](#checktask)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="gettaskusingget"></a>
#### getTask
```
GET /check/task
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**sid**  <br>*required*|sid|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="gettasklistusingget"></a>
#### getTaskList
```
GET /check/task-list
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**cid**  <br>*required*|cid|integer (int32)|
|**Query**|**sid**  <br>*required*|sid|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="check-log-controller_resource"></a>
### Check-log-controller
Check Log Controller


<a name="getchecklogsusingget"></a>
#### getCheckLogs
```
GET /check-log
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**cid**  <br>*optional*|cid|integer (int32)|
|**Query**|**sid**  <br>*optional*|sid|integer (int32)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="config-data-controller_resource"></a>
### Config-data-controller
Config Data Controller


<a name="updateconfigdatausingpost"></a>
#### updateConfigData
```
POST /data-config
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**data**  <br>*required*|data|[ConfigData](#configdata)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="getconfigdatasusingget"></a>
#### getConfigDatas
```
GET /data-config
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="addconfigdatausingput"></a>
#### addConfigData
```
PUT /data-config
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**data**  <br>*required*|data|[ConfigData](#configdata)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="delconfigdatausingdelete"></a>
#### delConfigData
```
DELETE /data-config/{id}
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**id**  <br>*required*|id|integer (int32)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


##### Produces

* `*/*`


<a name="major-controller_resource"></a>
### Major-controller
Major Controller


<a name="getmajorbyschcodeandcolidusingget"></a>
#### getMajorBySchCodeAndCOLID
```
GET /major
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**col_id**  <br>*required*|col_id|integer (int32)|
|**Query**|**sch_code**  <br>*required*|sch_code|integer (int32)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="menu-controller_resource"></a>
### Menu-controller
Menu Controller


<a name="getmenuusingget"></a>
#### getMenu
```
GET /menu
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**type**  <br>*required*|type|integer (int32)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< object > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="updatemenupermissionusingput"></a>
#### updateMenuPermission
```
PUT /menu
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**map**  <br>*required*|map|< string, object > map|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="getmenusbyroleidusingget"></a>
#### getMenusByRoleId
```
GET /menu/role
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**roleid**  <br>*optional*|roleid|integer (int32)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< object > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="getmenusbyroleidusingget_1"></a>
#### getMenusByRoleId
```
GET /menu/role/{roleid}
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**roleid**  <br>*required*|roleid|integer (int32)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< object > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="permission-controller_resource"></a>
### Permission-controller
Permission Controller


<a name="setpermissionusingpost"></a>
#### setPermission
```
POST /permission
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**map**  <br>*required*|map|< string, object > map|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="getpermissionbyuriusingget"></a>
#### getPermissionByUri
```
GET /permission
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**uri**  <br>*optional*|uri|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="getpermissionbymenuidandroleidusingget"></a>
#### getPermissionByMenuIdAndRoleid
```
GET /permission/menu
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**menuid**  <br>*optional*|menuid|integer (int32)|
|**Query**|**roleid**  <br>*optional*|roleid|integer (int32)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="role-controller_resource"></a>
### Role-controller
Role Controller


<a name="getallroleusingget"></a>
#### getAllRole
```
GET /role
```


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[ResponseEntity](#responseentity)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="0d93c07a5e7a4700a8b2107d896d5a34"></a>
### 学校列表接口
School Controller


<a name="getschoollistusingget"></a>
#### getSchoolList
```
GET /school
```


##### Description
获取学校列表


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«List«School»»](#d8085fc9b32cda7d000afd3f25f3ada1)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="getschoolbycodeusingget"></a>
#### getSchoolByCode
```
GET /school/{code}
```


##### Description
根据学校编码,获取学校信息


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**code**  <br>*required*|code|integer (int32)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«School»](#ec2660d3fb3156e44e3a9e3be30aae26)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="06ee24910b4d3f9eecc95d7fcbd560ee"></a>
### 学生信息接口
Student Controller


<a name="addstudentusingpost"></a>
#### addStudent
```
POST /student
```


##### Description
添加学生


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**student**  <br>*required*|student|[Student](#student)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«boolean»](#7ae860c5ec6796033e558e63ae210b4e)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="getstudentbyclassidusingget"></a>
#### getStudentByClassId
```
GET /student
```


##### Description
通过班级id获取学生列表


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**cid**  <br>*optional*|cid|integer (int32)|
|**Query**|**page**  <br>*optional*|page|integer (int32)|
|**Query**|**size**  <br>*optional*|size|integer (int32)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«StudentsRes»](#1334aca9a1785b6916ffa6956ed911f6)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="updatestudentusingput"></a>
#### updateStudent
```
PUT /student
```


##### Description
更新学生信息


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**student**  <br>*required*|student|[Student](#student)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«boolean»](#7ae860c5ec6796033e558e63ae210b4e)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="delstudentbysidusingdelete"></a>
#### delStudentBySid
```
DELETE /student/{sid}
```


##### Description
通过学号,删除学生


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**sid**  <br>*required*|sid|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«boolean»](#7ae860c5ec6796033e558e63ae210b4e)|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


##### Produces

* `*/*`


<a name="getstudentusingget"></a>
#### getStudent
```
GET /student/{st_id}
```


##### Description
通过学生学号查找学生


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**st_id**  <br>*required*|st_id|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«Student»](#0f539417dcfba6a7cd7edb127bc2efd2)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="15e13ec97ffc4f26e82b3ecf5c95bf1b"></a>
### 学院列表接口
College Controller


<a name="getcollegebyschoolcodeusingget"></a>
#### getCollegeBySchoolCode
```
GET /college
```


##### Description
通过学校编码,获取学院列表


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**sch_code**  <br>*required*|学校编码|integer (int32)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«List«College»»](#94b676674dcb8395590937b4e2b178fc)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="getcollegebycollegecodeusingget"></a>
#### getCollegeByCollegeCode
```
GET /college/{code}
```


##### Description
通过学院编码获取学院信息


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**code**  <br>*required*|code|integer (int32)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«College»](#b103c91e6b4ecec676a19c8663e9e020)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="08f819b1d35d7c79735ab09814daeb5c"></a>
### 教师信息接口
Teacher Controller


<a name="addteacherusingpost"></a>
#### addTeacher
```
POST /teacher
```


##### Description
添加教师信息


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**teacher**  <br>*required*|teacher|[Teacher](#teacher)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«boolean»](#7ae860c5ec6796033e558e63ae210b4e)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="updateteacherusingput"></a>
#### updateTeacher
```
PUT /teacher
```


##### Description
更新教师信息


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**teacher**  <br>*required*|teacher|[Teacher](#teacher)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«boolean»](#7ae860c5ec6796033e558e63ae210b4e)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="getteacherbytidusingget"></a>
#### getTeacherByTid
```
GET /teacher/{tid}
```


##### Description
通过教工号获取教师信息


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**tid**  <br>*required*|tid|integer (int32)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«Teacher»](#cda696be43d84c2590158d41187cf637)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="deleteteacherbytidusingdelete"></a>
#### deleteTeacherByTid
```
DELETE /teacher/{tid}
```


##### Description
删除教师信息


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**tid**  <br>*required*|tid|integer (int32)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«boolean»](#7ae860c5ec6796033e558e63ae210b4e)|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


##### Produces

* `*/*`


<a name="1ce878cdd5eec2810ebc07e1dcbbe3a5"></a>
### 班课信息接口
Course Controller


<a name="addcourseusingpost"></a>
#### addCourse
```
POST /course
```


##### Description
添加班课


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**course**  <br>*required*|course|[CourseReq](#coursereq)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«boolean»](#7ae860c5ec6796033e558e63ae210b4e)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="getcoursebytidusingget"></a>
#### getCourseByTid
```
GET /course
```


##### Description
根据教师工号,获取该教师的所有班课列表


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**tid**  <br>*required*|tid|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«List«Course»»](#af39d2fe43b3ee7b954cf40ede620e8c)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="updatecourseusingput"></a>
#### updateCourse
```
PUT /course
```


##### Description
添加班课,只允许修改班课名,上课地点,上课时间,是否禁止加入班课,班课说明。更新按照班课编号。


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**course**  <br>*required*|course|[CourseReq](#coursereq)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«boolean»](#7ae860c5ec6796033e558e63ae210b4e)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="getcoursebysch_col_majusingget"></a>
#### getCourseBySch_Col_Maj
```
GET /course/list
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Query**|**col**  <br>*required*|col|integer (int32)|
|**Query**|**maj**  <br>*required*|maj|integer (int32)|
|**Query**|**sch**  <br>*required*|sch|integer (int32)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«List«Course»»](#af39d2fe43b3ee7b954cf40ede620e8c)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Produces

* `*/*`


<a name="deletecourseusingdelete"></a>
#### deleteCourse
```
DELETE /course/{cid}
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Path**|**cid**  <br>*required*|cid|string|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«boolean»](#7ae860c5ec6796033e558e63ae210b4e)|
|**204**|No Content|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|


##### Produces

* `*/*`


<a name="82e7416047b887a72af65bda6d5e369f"></a>
### 用户账号接口
User Controller


<a name="userloginusingpost"></a>
#### userLogin
```
POST /user/login
```


##### Description
普通登录接口,若用户名不为空,则使用用户名密码登录。若电话号码不为空,则使用短信验证


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**loginReq**  <br>*required*|loginReq|[LoginParam](#loginparam)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«LoginRes»](#9e4d1aa16efb21b85b2549486b0339be)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="passwordresetusingpost"></a>
#### passwordReset
```
POST /user/pwd-reset
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**req**  <br>*required*|req|[PassRestReq](#passrestreq)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«string»](#9bd25fa0a902d3b63e5d24414c09e717)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`


<a name="registusingpost"></a>
#### regist
```
POST /user/regist
```


##### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Body**|**req**  <br>*required*|req|[RegistReq](#registreq)|


##### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[基础返回类«RegistRes»](#ff90930d192859d02c2af2ca785b87e2)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


##### Consumes

* `application/json`


##### Produces

* `*/*`




<a name="definitions"></a>
## Definitions

<a name="checklog"></a>
### CheckLog

|Name|Schema|
|---|---|
|**ch_checkcount**  <br>*optional*|integer (int32)|
|**ch_checkcouseid**  <br>*optional*|integer (int32)|
|**ch_checkloc**  <br>*optional*|string|
|**ch_checkscore**  <br>*optional*|integer (int32)|
|**ch_checksid**  <br>*optional*|integer (int32)|
|**ch_checksname**  <br>*optional*|string|
|**ch_checktime**  <br>*optional*|string (date-time)|
|**ch_currentscore**  <br>*optional*|integer (int32)|
|**id**  <br>*optional*|integer (int32)|


<a name="checktask"></a>
### CheckTask

|Name|Schema|
|---|---|
|**check**  <br>*optional*|boolean|
|**class_id**  <br>*optional*|integer (int32)|
|**course**  <br>*optional*|[Course](#course)|
|**create_time**  <br>*optional*|string (date-time)|
|**id**  <br>*optional*|integer (int32)|
|**info**  <br>*optional*|string|
|**log**  <br>*optional*|[CheckLog](#checklog)|
|**password**  <br>*optional*|string|
|**status**  <br>*optional*|boolean|
|**teacher**  <br>*optional*|[Teacher](#teacher)|
|**teacher_id**  <br>*optional*|integer (int32)|
|**time_limit**  <br>*optional*|integer (int32)|
|**type**  <br>*optional*|integer (int32)|


<a name="college"></a>
### College

|Name|Schema|
|---|---|
|**co_code**  <br>*optional*|integer (int32)|
|**co_info**  <br>*optional*|string|
|**co_name**  <br>*optional*|string|
|**co_phone**  <br>*optional*|string|
|**id**  <br>*optional*|integer (int32)|


<a name="configdata"></a>
### ConfigData

|Name|Schema|
|---|---|
|**code**  <br>*optional*|integer (int32)|
|**id**  <br>*optional*|integer (int32)|
|**info**  <br>*optional*|string|
|**key**  <br>*optional*|string|
|**value**  <br>*optional*|string|


<a name="course"></a>
### Course

|Name|Description|Schema|
|---|---|---|
|**cs_college**  <br>*optional*||integer (int32)|
|**cs_id**  <br>*optional*|班课号|string|
|**cs_loc**  <br>*optional*|上课地点|string|
|**cs_major**  <br>*optional*||integer (int32)|
|**cs_name**  <br>*optional*|班课名字|string|
|**cs_school**  <br>*optional*||integer (int32)|
|**cs_status**  <br>*optional*|是否还可以加入班课  <br>**Example** : `true`|boolean|
|**cs_time**  <br>*optional*|上课时间  <br>**Example** : `"5-8节"`|string|
|**teacher**  <br>*optional*|任课教师详细信息|[Teacher](#teacher)|


<a name="coursereq"></a>
### CourseReq

|Name|Description|Schema|
|---|---|---|
|**cs_id**  <br>*optional*|班课号|string|
|**cs_info**  <br>*optional*|班课说明|string|
|**cs_loc**  <br>*optional*|上课地点|string|
|**cs_name**  <br>*optional*|班课名字|string|
|**cs_status**  <br>*optional*|是否还可以加入班课  <br>**Example** : `true`|boolean|
|**cs_time**  <br>*optional*|上课时间  <br>**Example** : `"5-8节"`|string|


<a name="loginparam"></a>
### LoginParam
登录参数类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*optional*|验证码  <br>**Example** : `"code"`|string|
|**password**  <br>*required*|密码  <br>**Example** : `"password"`|string|
|**phone**  <br>*optional*|电话号码  <br>**Example** : `"phone"`|string|
|**type**  <br>*optional*|用户类型:{ 1:超级管理员,2:普通管理员,3:教师,4:学生}  <br>**Example** : `1`|integer (int32)|
|**uid**  <br>*optional*|工号  <br>**Example** : `"phone"`|string|
|**username**  <br>*optional*|用户名  <br>**Example** : `"username"`|string|


<a name="loginres"></a>
### LoginRes

|Name|Description|Schema|
|---|---|---|
|**token**  <br>*optional*|登录成功返回token  <br>**Example** : `"a.b.c"`|string|
|**type**  <br>*optional*|登录成功返回,身份类型{1:超级管理员,2:普通管理员,3:教师,4:学生|integer (int32)|
|**uid**  <br>*optional*|登录成功返回工号|string|


<a name="modelandview"></a>
### ModelAndView

|Name|Schema|
|---|---|
|**empty**  <br>*optional*|boolean|
|**model**  <br>*optional*|object|
|**modelMap**  <br>*optional*|< string, object > map|
|**reference**  <br>*optional*|boolean|
|**status**  <br>*optional*|enum (100 CONTINUE, 101 SWITCHING_PROTOCOLS, 102 PROCESSING, 103 CHECKPOINT, 200 OK, 201 CREATED, 202 ACCEPTED, 203 NON_AUTHORITATIVE_INFORMATION, 204 NO_CONTENT, 205 RESET_CONTENT, 206 PARTIAL_CONTENT, 207 MULTI_STATUS, 208 ALREADY_REPORTED, 226 IM_USED, 300 MULTIPLE_CHOICES, 301 MOVED_PERMANENTLY, 302 FOUND, 302 MOVED_TEMPORARILY, 303 SEE_OTHER, 304 NOT_MODIFIED, 305 USE_PROXY, 307 TEMPORARY_REDIRECT, 308 PERMANENT_REDIRECT, 400 BAD_REQUEST, 401 UNAUTHORIZED, 402 PAYMENT_REQUIRED, 403 FORBIDDEN, 404 NOT_FOUND, 405 METHOD_NOT_ALLOWED, 406 NOT_ACCEPTABLE, 407 PROXY_AUTHENTICATION_REQUIRED, 408 REQUEST_TIMEOUT, 409 CONFLICT, 410 GONE, 411 LENGTH_REQUIRED, 412 PRECONDITION_FAILED, 413 PAYLOAD_TOO_LARGE, 413 REQUEST_ENTITY_TOO_LARGE, 414 URI_TOO_LONG, 414 REQUEST_URI_TOO_LONG, 415 UNSUPPORTED_MEDIA_TYPE, 416 REQUESTED_RANGE_NOT_SATISFIABLE, 417 EXPECTATION_FAILED, 418 I_AM_A_TEAPOT, 419 INSUFFICIENT_SPACE_ON_RESOURCE, 420 METHOD_FAILURE, 421 DESTINATION_LOCKED, 422 UNPROCESSABLE_ENTITY, 423 LOCKED, 424 FAILED_DEPENDENCY, 425 TOO_EARLY, 426 UPGRADE_REQUIRED, 428 PRECONDITION_REQUIRED, 429 TOO_MANY_REQUESTS, 431 REQUEST_HEADER_FIELDS_TOO_LARGE, 451 UNAVAILABLE_FOR_LEGAL_REASONS, 500 INTERNAL_SERVER_ERROR, 501 NOT_IMPLEMENTED, 502 BAD_GATEWAY, 503 SERVICE_UNAVAILABLE, 504 GATEWAY_TIMEOUT, 505 HTTP_VERSION_NOT_SUPPORTED, 506 VARIANT_ALSO_NEGOTIATES, 507 INSUFFICIENT_STORAGE, 508 LOOP_DETECTED, 509 BANDWIDTH_LIMIT_EXCEEDED, 510 NOT_EXTENDED, 511 NETWORK_AUTHENTICATION_REQUIRED)|
|**view**  <br>*optional*|[View](#view)|
|**viewName**  <br>*optional*|string|


<a name="passrestreq"></a>
### PassRestReq

|Name|Description|Schema|
|---|---|---|
|**id**  <br>*optional*|账户id  <br>**Example** : `1`|integer (int32)|
|**newPass**  <br>*optional*|新密码  <br>**Example** : `"newPass"`|string|
|**oldPass**  <br>*optional*|旧密码  <br>**Example** : `"oldPass"`|string|


<a name="registreq"></a>
### RegistReq

|Name|Description|Schema|
|---|---|---|
|**email**  <br>*optional*|邮箱  <br>**Example** : `"123456@qq.com"`|string|
|**identity**  <br>*required*|教工号/学号  <br>**Example** : `"123456"`|string|
|**msgcode**  <br>*required*|验证码  <br>**Example** : `"4856"`|string|
|**password**  <br>*required*|密码  <br>**Example** : `"123456"`|string|
|**phone**  <br>*required*|手机号码  <br>**Example** : `"130xxx456"`|string|
|**type**  <br>*required*|身份类型{3:老师,4:学生}  <br>**Example** : `1`|integer (int32)|
|**username**  <br>*optional*|用户名  <br>**Example** : `"123456"`|string|


<a name="registres"></a>
### RegistRes

|Name|Description|Schema|
|---|---|---|
|**indentity**  <br>*optional*|教工号/学号  <br>**Example** : `"123456"`|string|


<a name="responseentity"></a>
### ResponseEntity

|Name|Schema|
|---|---|
|**body**  <br>*optional*|object|
|**statusCode**  <br>*optional*|enum (100 CONTINUE, 101 SWITCHING_PROTOCOLS, 102 PROCESSING, 103 CHECKPOINT, 200 OK, 201 CREATED, 202 ACCEPTED, 203 NON_AUTHORITATIVE_INFORMATION, 204 NO_CONTENT, 205 RESET_CONTENT, 206 PARTIAL_CONTENT, 207 MULTI_STATUS, 208 ALREADY_REPORTED, 226 IM_USED, 300 MULTIPLE_CHOICES, 301 MOVED_PERMANENTLY, 302 FOUND, 302 MOVED_TEMPORARILY, 303 SEE_OTHER, 304 NOT_MODIFIED, 305 USE_PROXY, 307 TEMPORARY_REDIRECT, 308 PERMANENT_REDIRECT, 400 BAD_REQUEST, 401 UNAUTHORIZED, 402 PAYMENT_REQUIRED, 403 FORBIDDEN, 404 NOT_FOUND, 405 METHOD_NOT_ALLOWED, 406 NOT_ACCEPTABLE, 407 PROXY_AUTHENTICATION_REQUIRED, 408 REQUEST_TIMEOUT, 409 CONFLICT, 410 GONE, 411 LENGTH_REQUIRED, 412 PRECONDITION_FAILED, 413 PAYLOAD_TOO_LARGE, 413 REQUEST_ENTITY_TOO_LARGE, 414 URI_TOO_LONG, 414 REQUEST_URI_TOO_LONG, 415 UNSUPPORTED_MEDIA_TYPE, 416 REQUESTED_RANGE_NOT_SATISFIABLE, 417 EXPECTATION_FAILED, 418 I_AM_A_TEAPOT, 419 INSUFFICIENT_SPACE_ON_RESOURCE, 420 METHOD_FAILURE, 421 DESTINATION_LOCKED, 422 UNPROCESSABLE_ENTITY, 423 LOCKED, 424 FAILED_DEPENDENCY, 425 TOO_EARLY, 426 UPGRADE_REQUIRED, 428 PRECONDITION_REQUIRED, 429 TOO_MANY_REQUESTS, 431 REQUEST_HEADER_FIELDS_TOO_LARGE, 451 UNAVAILABLE_FOR_LEGAL_REASONS, 500 INTERNAL_SERVER_ERROR, 501 NOT_IMPLEMENTED, 502 BAD_GATEWAY, 503 SERVICE_UNAVAILABLE, 504 GATEWAY_TIMEOUT, 505 HTTP_VERSION_NOT_SUPPORTED, 506 VARIANT_ALSO_NEGOTIATES, 507 INSUFFICIENT_STORAGE, 508 LOOP_DETECTED, 509 BANDWIDTH_LIMIT_EXCEEDED, 510 NOT_EXTENDED, 511 NETWORK_AUTHENTICATION_REQUIRED)|
|**statusCodeValue**  <br>*optional*|integer (int32)|


<a name="school"></a>
### School

|Name|Schema|
|---|---|
|**id**  <br>*optional*|integer (int32)|
|**sch_addr**  <br>*optional*|string|
|**sch_code**  <br>*optional*|integer (int32)|
|**sch_info**  <br>*optional*|string|
|**sch_name**  <br>*optional*|string|
|**sch_phone**  <br>*optional*|string|


<a name="student"></a>
### Student
Student


|Name|Schema|
|---|---|
|**id**  <br>*optional*|integer (int32)|
|**st_college**  <br>*optional*|integer (int32)|
|**st_email**  <br>*optional*|string|
|**st_grade**  <br>*optional*|integer (int32)|
|**st_id**  <br>*optional*|string|
|**st_major**  <br>*optional*|integer (int32)|
|**st_name**  <br>*optional*|string|
|**st_phone**  <br>*optional*|string|
|**st_school**  <br>*optional*|integer (int32)|
|**st_sex**  <br>*optional*|string|


<a name="studentsres"></a>
### StudentsRes

|Name|Schema|
|---|---|
|**page**  <br>*optional*|integer (int32)|
|**size**  <br>*optional*|integer (int32)|
|**studentList**  <br>*optional*|< [Student](#student) > array|
|**total**  <br>*optional*|integer (int32)|


<a name="teacher"></a>
### Teacher

|Name|Schema|
|---|---|
|**te_college**  <br>*optional*|integer (int32)|
|**te_edu**  <br>*optional*|string|
|**te_email**  <br>*optional*|string|
|**te_id**  <br>*optional*|integer (int32)|
|**te_info**  <br>*optional*|string|
|**te_major**  <br>*optional*|integer (int32)|
|**te_name**  <br>*optional*|string|
|**te_phone**  <br>*optional*|string|
|**te_rank**  <br>*optional*|string|
|**te_school**  <br>*optional*|integer (int32)|
|**te_sex**  <br>*optional*|string|


<a name="view"></a>
### View

|Name|Schema|
|---|---|
|**contentType**  <br>*optional*|string|


<a name="b103c91e6b4ecec676a19c8663e9e020"></a>
### 基础返回类«College»
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|响应状态码{0:正常,-1:异常}  <br>**Example** : `0`|integer (int32)|
|**data**  <br>*optional*|响应数据|[College](#college)|
|**msg**  <br>*optional*|响应消息  <br>**Example** : `"msg"`|string|


<a name="94b676674dcb8395590937b4e2b178fc"></a>
### 基础返回类«List«College»»
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|响应状态码{0:正常,-1:异常}  <br>**Example** : `0`|integer (int32)|
|**data**  <br>*optional*|响应数据  <br>**Example** : `"data"`|< [College](#college) > array|
|**msg**  <br>*optional*|响应消息  <br>**Example** : `"msg"`|string|


<a name="af39d2fe43b3ee7b954cf40ede620e8c"></a>
### 基础返回类«List«Course»»
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|响应状态码{0:正常,-1:异常}  <br>**Example** : `0`|integer (int32)|
|**data**  <br>*optional*|响应数据  <br>**Example** : `"data"`|< [Course](#course) > array|
|**msg**  <br>*optional*|响应消息  <br>**Example** : `"msg"`|string|


<a name="d8085fc9b32cda7d000afd3f25f3ada1"></a>
### 基础返回类«List«School»»
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|响应状态码{0:正常,-1:异常}  <br>**Example** : `0`|integer (int32)|
|**data**  <br>*optional*|响应数据  <br>**Example** : `"data"`|< [School](#school) > array|
|**msg**  <br>*optional*|响应消息  <br>**Example** : `"msg"`|string|


<a name="9e4d1aa16efb21b85b2549486b0339be"></a>
### 基础返回类«LoginRes»
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|响应状态码{0:正常,-1:异常}  <br>**Example** : `0`|integer (int32)|
|**data**  <br>*optional*|响应数据|[LoginRes](#loginres)|
|**msg**  <br>*optional*|响应消息  <br>**Example** : `"msg"`|string|


<a name="ff90930d192859d02c2af2ca785b87e2"></a>
### 基础返回类«RegistRes»
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|响应状态码{0:正常,-1:异常}  <br>**Example** : `0`|integer (int32)|
|**data**  <br>*optional*|响应数据|[RegistRes](#registres)|
|**msg**  <br>*optional*|响应消息  <br>**Example** : `"msg"`|string|


<a name="ec2660d3fb3156e44e3a9e3be30aae26"></a>
### 基础返回类«School»
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|响应状态码{0:正常,-1:异常}  <br>**Example** : `0`|integer (int32)|
|**data**  <br>*optional*|响应数据|[School](#school)|
|**msg**  <br>*optional*|响应消息  <br>**Example** : `"msg"`|string|


<a name="1334aca9a1785b6916ffa6956ed911f6"></a>
### 基础返回类«StudentsRes»
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|响应状态码{0:正常,-1:异常}  <br>**Example** : `0`|integer (int32)|
|**data**  <br>*optional*|响应数据|[StudentsRes](#studentsres)|
|**msg**  <br>*optional*|响应消息  <br>**Example** : `"msg"`|string|


<a name="0f539417dcfba6a7cd7edb127bc2efd2"></a>
### 基础返回类«Student»
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|响应状态码{0:正常,-1:异常}  <br>**Example** : `0`|integer (int32)|
|**data**  <br>*optional*|响应数据|[Student](#student)|
|**msg**  <br>*optional*|响应消息  <br>**Example** : `"msg"`|string|


<a name="cda696be43d84c2590158d41187cf637"></a>
### 基础返回类«Teacher»
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|响应状态码{0:正常,-1:异常}  <br>**Example** : `0`|integer (int32)|
|**data**  <br>*optional*|响应数据|[Teacher](#teacher)|
|**msg**  <br>*optional*|响应消息  <br>**Example** : `"msg"`|string|


<a name="7ae860c5ec6796033e558e63ae210b4e"></a>
### 基础返回类«boolean»
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|响应状态码{0:正常,-1:异常}  <br>**Example** : `0`|integer (int32)|
|**data**  <br>*optional*|响应数据  <br>**Example** : `false`|boolean|
|**msg**  <br>*optional*|响应消息  <br>**Example** : `"msg"`|string|


<a name="9bd25fa0a902d3b63e5d24414c09e717"></a>
### 基础返回类«string»
基础返回类


|Name|Description|Schema|
|---|---|---|
|**code**  <br>*required*|响应状态码{0:正常,-1:异常}  <br>**Example** : `0`|integer (int32)|
|**data**  <br>*optional*|响应数据  <br>**Example** : `"data"`|string|
|**msg**  <br>*optional*|响应消息  <br>**Example** : `"msg"`|string|





