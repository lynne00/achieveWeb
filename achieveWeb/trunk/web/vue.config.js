const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 9000,
    open: true,
    //跨域安全问题
    proxy: {
      // match /apixxxx url
      '/api': {
        // proxy and send request to target
        target: 'http://localhost:8080',
        changeOrigin: true,
        secure: false,
        // rewrite request target url
        // http://localhost:9000/api/captcha/xxxxxx  => http://locahost:8080/captcha/xxxxxx
        pathRewrite: {
          '^/api': '/'
        }
      }
    }
  }
})
