const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: false,
  devServer: {
    proxy: 'http://localhost:5000'
  },
})
