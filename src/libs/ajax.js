//引入axios
import axios from 'axios'
import Cookies from "js-cookie";
import {router} from '../router/index.js'

axios.defaults.baseURL = ajaxUrl;
//超时请求
axios.defaults.timeout = 1000000;
//响应拦截器即异常处理
axios.interceptors.response.use(response => {
  return response
}, err => {
  if (err && err.response) {
    app.error(err.response.status);
  } else {
    err.message = "连接到服务器失败"
  }
  return Promise.reject(err.response);
});

const requests = {

  //get请求
  get(url, param) {
    const user = JSON.parse(localStorage.getItem("user"));
    const mdToken = user ? user.token : "";
    return new Promise((resolve, reject) => {
      axios({
        method: 'get',
        url,
        params: param,
        headers: {
          "md-token": mdToken
        }
      }).then(res => {
        resolve(res)
      })
    })
  },
  //post请求
  post(url, param) {
    const user = JSON.parse(localStorage.getItem("user"));
    const mdToken = user ? user.token : "";
    return new Promise((resolve, reject) => {
      axios({
        method: 'post',
        url,
        data: param,
        headers: {
          "md-token": mdToken
        }
      }).then(res => {
        resolve(res);
      }).catch((res) => {
        // this.error(res.data.status);
        //console.log(res)
      })
    })
  },

  // 同步请求，promise不能返回值
 asynGet(url, param, success, error){
    $.ajax({
      url: ajaxUrl + url,
      type: "get",
      async: false,
      data: param,
      success: function (res) {
        if(success){
          success(res);
        }
      },
      error: function () {
        if(error){
          error();
        }
      }
    });
  },
  getFiles(url, params, fileName) {
    const user = JSON.parse(localStorage.getItem("user"));
    const mdToken = user ? user.token : "";
    fileName = fileName || "";
    return new Promise((resolve, reject) => {
      axios.get(url, {
        headers: {
          "md-token": mdToken
        },
        params: params,
        responseType: 'blob',
      }).then(res => {
        let url = window.URL.createObjectURL(res.data);
        let link = document.createElement('a');
        link.style.display = 'none';
        link.href = url;
        link.setAttribute('download', fileName);
        document.body.appendChild(link);
        link.click();
        resolve(res);
      }).catch(err => {
        reject(err)
      })
    })
  },

  error(status) {
    switch (status) {
      //请求没有权限状态码
      case 2:
        router.replace({
          path: '/error-403'
        });
        break;
      //请求未登录状态码
      case 3:

        Cookies.remove('user');
        router.replace({
          path: '/login'
        });
        break;
      //请求未找到
      case 404:
        router.replace({
          path: '/error-404'
        });
        break;

    }

  }


}
export default requests;
