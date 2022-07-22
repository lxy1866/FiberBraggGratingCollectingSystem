const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;
const CompressionWebpackPlugin = require('compression-webpack-plugin')
module.exports = {
    plugins: [
        new BundleAnalyzerPlugin(),
        new CompressionWebpackPlugin()
    ]
}


