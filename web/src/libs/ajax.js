//引入axios
import axios from 'axios'
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
    return new Promise((resolve, reject) => {
      axios({
        method: 'get',
        url,
        params: param,
        headers: {
          "md-token": localStorage.getItem("mdToken")
        }
      }).then(res => {
        resolve(res)
      })
    })
  },
  //post请求
  post(url, param) {
    return new Promise((resolve, reject) => {
      axios({
        method: 'post',
        url,
        data: param,
        headers: {
          "md-token": localStorage.getItem("mdToken")
        }
      }).then(res => {
        resolve(res);
      }).catch((res) => {
        // this.error(res.data.status);
        //console.log(res)
      })
    })
  },

  //post请求
  upload(url, param) {
    return new Promise((resolve, reject) => {
      axios({
        method: 'post',
        url,
        data: param,
        headers: {
          "md-token": localStorage.getItem("mdToken"),
          "content-type": "multipart/form-data"
        }
      }).then(res => {
        resolve(res);
      }).catch((res) => {
        // this.error(res.data.status);
        //console.log(res)
      })
    })
  },

  getFiles(url, params, fileName) {
    fileName = fileName || "";
    return new Promise((resolve, reject) => {
      axios.get(url, {
        headers: {
          "md-token": localStorage.getItem("mdToken")
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
