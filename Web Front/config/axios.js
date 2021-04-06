import axios from 'axios'
axios.interceptors.request.use(function (config){
    console.log("interceptorsinterceptorsinterceptorsinterceptorsinterceptorsinterceptors")
    console.log(config)
    console.log("interceptorsinterceptorsinterceptorsinterceptorsinterceptorsinterceptors")
    if(window.localStorage.getItem('token')){
        config.headers.Authorization = window.localStorage.getItem('token');
        return config;
    }else{
        console.log('window.localStorage.getItem(token)')
        console.log(window.localStorage.getItem('token'))
        this.$router.push('/login');
        return Promise.reject(config)
    }
    
   
},function(e){
    return Promise.reject(e)
})