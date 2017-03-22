/*
* @Author: GaNeShKuMaRm
* @Date:   2017-03-22 19:31:51
* @Last Modified by:   GaNeShKuMaRm
* @Last Modified time: 2017-03-22 19:58:53
*/

'use strict';
var https = require('https');
var fs = require('fs');
var express = require('express');

var app = express();
var port = 1234;

app.get('/', function(req, res) {
    res.end('Hello');
});

var options = {
  key: fs.readFileSync(__dirname+'/private.key'),
  cert: fs.readFileSync(__dirname+'/certificate.pem')
};

var secureServer = https.createServer(options,app);

secureServer.listen(port, function() {
   console.log('Server listening on port ',port);
});