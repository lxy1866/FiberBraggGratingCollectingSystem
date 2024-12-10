const { ElementPlusResolver, VantResolver } = require('unplugin-vue-components/resolvers')
const ComponentsPlugin = require('unplugin-vue-components/webpack').default

module.exports = {
    configureWebpack: {
        plugins: [
            ComponentsPlugin({
                resolvers: [ElementPlusResolver(), VantResolver()],
            }),
        ],
    },
};