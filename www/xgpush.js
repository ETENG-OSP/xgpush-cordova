/*
 * xpush cordova plugin
 * @author gg
 * @date 2014-03-21 
 */
var exec = require('cordova/exec');

var SERVICE='XGPush';

var ACTION_ENABLE_DEBUG = 'enable_debug';
var ACTION_REGISTER_PUSH = 'register_push';
var ACTION_REGISTER_ACCOUNT = 'register_action';
var ACTION_UNREGISTER_PUSH = 'unregister_push';
var ACTION_SET_TAG = 'set_tag';
var ACTION_DELETE_TAG = 'delete_tag';
var ACTION_CLEAR_CACHE = 'clear_cache';
var ACTION_SET_ID_AND_KEY = 'set_id_and_key';
var ACTION_READY = 'ready';

var EMPTY_FN = function () {};
var ready = function () {
  exec(EMPTY_FN, EMPTY_FN, SERVICE, ACTION_READY, []);
};

function XGPush() {
}

document.addEventListener('deviceready', ready, false);
document.addEventListener('resume', ready, false);

XGPush.prototype.success = EMPTY_FN;
XGPush.prototype.error = EMPTY_FN;

XGPush.prototype.setIdAndKey = function (id, key, success, error) {
  exec(success || this.success, error || this.error, SERVICE, ACTION_SET_ID_AND_KEY, [id, key]);
}

XGPush.prototype.enableDebug = function (flag, success, error) {
  exec(success || this.success, error || this.error, SERVICE, ACTION_ENABLE_DEBUG, [flag]);
}

XGPush.prototype.registerPush = function (success, error) {
  exec(success || this.success, error || this.error, SERVICE, ACTION_REGISTER_PUSH, []);
}

XGPush.prototype.unregisterPush = function (success, error){
  exec(success || this.success, error || this.error, SERVICE, ACTION_UNREGISTER_PUSH, []);
}

XGPush.prototype.setTag = function (tag, success, error){
  exec(success || this.success, error || this.error, SERVICE, ACTION_ET_TAG, [tag]);
}

XGPush.prototype.deleteTag = function (tag, success, error){
  exec(success || this.success, error || this.error, SERVICE, ACTION_DELETE_TAG, [tag]);
}

XGPush.prototype.clearCache = function (success, error){
  exec(success || this.success, error || this.error, SERVICE, ACTION_CLEAR_CACHE, []);
}

XGPush.prototype.registerAccount = function (account, success, error){
  exec(success || this.success, error || this.error, SERVICE, ACTION_REGISTER_ACCOUNT, [account]);
}

module.exports = new XGPush();