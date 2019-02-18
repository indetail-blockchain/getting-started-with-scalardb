development: node_modules
	npx webpack-dev-server --config webpack.conf.js

production: node_modules
	rm -rf dist/
	NODE_ENV=production npx webpack --config webpack.conf.js

node_modules: yarn.lock
	yarn install
	touch node_modules

local: node_modules
	rm -rf dist/
	npx webpack --config webpack.conf.js
