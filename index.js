var util = require('util');

/**
 * Node.js 的事件类
 *
 * @external EventEmitter
 * @see {@link https://nodejs.org/api/events.html}
 */
var EventEmitter = require('events').EventEmitter;

/**
 * 信鸽 Cordova 服务类，用于注册。
 *
 * @class
 * @extends external:EventEmitter
 * @param {function} exec - cordova 插件接口
 */
function XGPush(exec) {
  EventEmitter.call(this);
  this.exec = exec;
}

util.inherits(XGPush, EventEmitter);

XGPush.SERVICE = 'XGPush';
XGPush.ACTION_REGISTER_PUSH = 'registerpush';
XGPush.ACTION_UNREGISTER_PUSH = 'unregisterpush';
XGPush.ACTION_ADD_LISTENER = 'addlistener';
XGPush.ACTION_REMOVE_LISTENER = 'removelistener';

/**
 * 与信鸽服务注册。注册的时候可以填别名，按照别名发送时，只有经过注册的别名才会收到推送。
 *
 * 可以重复调用这个方法。如果变更别名，则替换原有注册的结果。
 *
 * @param  {string} [alias] - 设备的别名
 * @return {external:Promise} 判断是否成功的 Promise
 * @example
 * // 直接注册
 * xgpush.registerPush().then(function() {
 *   console.log('success');
 *
 * }).catch(function(errCode) {
 *   console.log('oh no: ' + errCode);
 * });
 *
 * // 带设备别名注册
 * xgpush.registerPush('foo').then(function() {
 *   console.log('success');
 *
 * }).catch(function(errCode) {
 *   console.log('oh no: ' + errCode);
 * });
 */
XGPush.prototype.registerPush = function(alias) {
  var exec = this.exec;
  return new Promise(function(resolve, reject) {
    exec(resolve, reject, XGPush.SERVICE, XGPush.ACTION_REGISTER_PUSH, [alias]);
  });
};

/**
 * 取消注册
 *
 * @return {external:Promise} 完成后的回调
 */
XGPush.prototype.unregisterPush = function() {
  var exec = this.exec;
  return new Promise(function(resolve, reject) {
    exec(resolve, reject, XGPush.SERVICE, XGPush.ACTION_UNREGISTER_PUSH, []);
  });
};

/**
 * 添加事件监听。其他方法请参照 Node.js 的文档。
 *
 * 如果监听之前没有注册，则自动与信鸽注册。
 *
 * @method XGPush#on
 * @param {string} eventName - 事件名称
 * @param {XGPush#EventListener} listener - 事件处理方法
 * @listens XGPush#textmessage
 * @example
 * xgpush.on('textmessage', function(e) {
 *   console.log(e.content);
 * });
 */

/**
 * 事件的处理回调
 *
 * @callback XGPush#EventListener
 * @param {event} e - 事件内容
 */

/**
 * 收到信鸽消息时的事件
 *
 * @event XGPush#textmessage
 * @property {string} content - 消息正文内容
 * @property {Object} customContent - 消息自定义 key-value
 * @property {string} title - 消息标题
 */

module.exports = XGPush;
window._XGPush = XGPush;
