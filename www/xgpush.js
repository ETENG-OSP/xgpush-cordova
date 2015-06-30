/*
 * xpush cordova plugin
 * @author gg
 * @date 2014-03-21
 */
var exec = require('cordova/exec');

var SERVICE = 'XGPush';

var ACTION_REGISTER_PUSH = 'register_push';
var ACTION_REGISTER_ACCOUNT = 'register_account';
var ACTION_UNREGISTER_PUSH = 'unregister_push';

var ACTION_SET_TAG = 'set_tag';
var ACTION_DELETE_TAG = 'delete_tag';
var ACTION_CLEAR_CACHE = 'clear_cache';
var ACTION_READY = 'ready';

var EMPTY_FN = function() {};

function ready() {
  exec(EMPTY_FN, EMPTY_FN, SERVICE, ACTION_READY, []);
}

function XGPush() {
}

document.addEventListener('deviceready', ready, false);
document.addEventListener('resume', ready, false);

XGPush.prototype.success = EMPTY_FN;
XGPush.prototype.error = EMPTY_FN;

XGPush.prototype.registerPush = function(success, error) {
  if (arguments.length === 0) {
    return registerPush(null, null);
  } else if (typeof arguments[0] === 'string') {
    return registerAccount(arguments[1], arguments[2], arguments[0]);
  }

  registerPush(success, error);
};

function registerPush(success, error) {
  exec(success, error, SERVICE, ACTION_REGISTER_PUSH, []);
}

function registerAccount(alias, success, error) {
  exec(success, error, SERVICE, ACTION_REGISTER_ACCOUNT, [alias]);
}

XGPush.prototype.unregisterPush = function(success, error) {
  exec(null, null, SERVICE, ACTION_UNREGISTER_PUSH, []);
};

XGPush.prototype.setTag = function(tag, success, error) {
  exec(success, error, SERVICE, ACTION_ET_TAG, [tag]);
};

XGPush.prototype.deleteTag = function(tag, success, error) {
  exec(success, error, SERVICE, ACTION_DELETE_TAG, [tag]);
};

XGPush.prototype.clearCache = function(success, error) {
  exec(success, error, SERVICE, ACTION_CLEAR_CACHE, []);
};

module.exports = new XGPush();
