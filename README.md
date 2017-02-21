# XGPush 腾讯信鸽推送 for Cordova

- [170109] 升级 SDK
- [160119] 升级 SDK
- [151229] iOS SDK 版本更新至 2.4.5，重新规划 SDK 存储路径
- [151015] 庆祝 20 Stars，SDK 升级

SDK     | version
------- | --------------------------------
android | Xg-Push-SDK-Android-2.47
ios     | Xg-Push-SDK-iOS-2.5.0

腾讯信鸽推送服务：http://xg.qq.com/

Cordova 版本：3.x / 4.x / 5.x

这个插件支持通知与消息，同时针对混合应用的生命周期进行了处理，保证消息的抵达。

## 安装方法

打开控制台，进入 Cordova 项目目录，输入：

```bash
cordova plugin add https://github.com/ETENG-OSP/xgpush-cordova --save \
  --variable ACCESS_ID="Your Access ID" \
  --variable ACCESS_KEY="Your Access Key"
```

## 使用方法

安装完成即可接收推送通知。这种用法适合于仅需要偶尔向全部用户发信息的情况。

如果需要精确控制，参考以下 API 和事件。

### API

* __xgpush.registerPush([alias])__: 注册设备

  这个方法可以手动注册设备。如果需要为接收推送的设备取别名以便有针对性的通知，需要在 `deviceready` 后注册别名：

  ```js
  // 这里的别名是可选的，不传代表没用别名
  xgpush.registerPush('tom');
  ```

  如果需要结果，可以用 Promise 的形式获取：

  ```js
  xgpush.registerPush('tom').then(function(results) {
    // results 里有 flag 和 data
    alert('设备的 token 是: ' + results.data);
  });
  ```

  可以重复注册，下一个别名会替换上一个别名。

* __xgpush.unregisterPush()__: 注销设备

  如果不想接收推送，使用 `xgpush.unregisterPush`：

  ```js
  xgpush.unregisterPush()
  ```

  这个方法也返回 Promise。

### 事件

* __textmessage__: 文本消息

如果需要接受消息，直接在代码里处理，可以监听 `textmessage` 事件：

```js
xgpush.on('textmessage', function(e) {
  // 事件处理方法
  alert(JSON.stringify(arguments, null, 2));
});
```

事件采用了与 Node.js 事件兼容的 eventemitter3。具体方法参考：https://nodejs.org/api/events.html

## 用例

如果需要推送一条信息，用户在点击该信息后进入应用，同时触发程序逻辑。可以发送一个带有自定义 URL scheme 的通知。

参考这个插件：https://github.com/EddyVerbruggen/Custom-URL-scheme


## 注意事项

### armv7s 与 xcode 6

如果执行 `cordova build --device --release` 不成功，请去掉 `build.js` 里的 armv7s。

参考：
https://issues.apache.org/jira/browse/CB-8788

### 不同平台 so 文件

这个插件内置了 armeabi 的库文件。如果目标平台不是 armeabi，请到 http://xg.qq.com/xg/help/ctr_help/download 下载对应版本的 SDK。

以下引用 SDK 中的说明：

> 1. 信鸽的 .so 支持所有的 android 平台，但考虑到平时接入一般只需要 armeabi 平台，因此 libs 目录只提供该平台的 .so，其它平台可在上层目录的 All-Platform-SO 找到。
>
> 2. 嵌入 .so 可能存在的问题：
>   1. so 文件与 jar 包不匹配。
>
>     解决办法：在更新 jar 时同时更新对应的 so 文件；
>
>   2. 当前工程已有某些平台的 so，如只有 armeabi 平台，却添加信鸽所有平台导致打包时异常。
>
>     解决办法：只添加当前工程已有的平台的信鸽 so 文件。具体可参考网上或以下示例：
>
>     ```
>     armeabi	   ！此平台既有当前存在so又有信鸽，正常！
>       --libCurrent.so			当前工程已有so
>       --libtpnsSecurity.so	信鸽
>       --libtpnsWatchdog.so	信鸽
>
>     armeabi-v7a	！此平台既有当前存在so又有信鸽，正常！
>       --libCurrent.so			当前工程已有so
>       --libtpnsSecurity.so	信鸽
>       --libtpnsWatchdog.so	信鸽
>
>     mips	！！！错误，由于此平台只有信鸽，必须删掉mips目录！！！
>       --libtpnsSecurity.so	信鸽
>       --libtpnsWatchdog.so	信鸽
>
>     x86		！！！错误，由于此平台只有信鸽，必须删掉x86目录！！！
>       --libtpnsSecurity.so	信鸽
>       --libtpnsWatchdog.so	信鸽
>     ```
>
>   3. 若当前工程不存在 so 文件。
>
>     解决办法：可复制所有信鸽平台或只复制 armeabi 平台。
