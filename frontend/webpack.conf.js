const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const { VueLoaderPlugin } = require('vue-loader');
const webpack = require('webpack');

module.exports = {
    mode: 'production',
    entry: [
        path.resolve(__dirname, 'src', 'app'),
    ],
    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: path.join('js', '[name].bundle.js'),
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: path.resolve(__dirname, 'index.html'),
        }),
        new VueLoaderPlugin(),
        new webpack.DefinePlugin({
            CONFIG: JSON.stringify(require("config")),
        }),
    ],
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: [
                    path.resolve(__dirname, 'node_modules/'),
                    path.resolve(__dirname, 'dist/'),
                ],
                loader: 'babel-loader',
                query: {
                    presets: [
                        'stage-3',
                    ],
                },
            },
            {
                test: /\.vue$/,
                loader: 'vue-loader',
            },
            {
                test: /\.css$/,
                loader: [
                    'vue-style-loader',
                    'css-loader',
                ],
            },
        ],
    },
    resolve: {
        alias: {
            'vue$': 'vue/dist/vue.min.js',
        },
        extensions: [
            '.js',
            '.vue',
        ],
    },
    optimization: {
        splitChunks: {
            chunks: 'all',
        },
    },
};
