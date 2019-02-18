development: node_modules
	node_modules/.bin/npx webpack-dev-server --config webpack.conf.js

production: node_modules
	rm -rf dist/
	NODE_ENV=production node_modules/.bin/npx webpack --config webpack.conf.js

node_modules: yarn.lock
	yarn install
	touch node_modules

local: node_modules
	rm -rf dist/
	node_modules/.bin/npx webpack --config webpack.conf.js
