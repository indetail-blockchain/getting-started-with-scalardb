const express = require('express');
const path = require('path');
const app = express();

app.disable('x-powered-by');
app.use(express.static(path.join(__dirname, 'dist')));

app.all('/*', function(req, res) {
  res.sendFile('index.html', { root: path.join(__dirname, 'dist') });
});

app.listen(8080, "127.0.0.1");
