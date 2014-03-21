var exec = require('cordova/exec');

function XGPush() {
}

XGPush.prototype.setIdAndKey = function (id, key, success, error) {
	exec(success, error, 'XGPush', 'set_id_and_key', [id, key]);
}

module.exports = new XGPush();