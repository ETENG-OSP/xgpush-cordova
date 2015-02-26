# XGPush 腾讯信鸽推送 for Cordova

腾讯信鸽推送服务：http://xg.qq.com/

这个插件支持通知与消息，同时针对混合应用的生命周期进行了处理，保证消息的抵达。

## 安装方法

打开控制台，进入 Cordova 项目目录，输入：

```bash
cordova plugin add https://github.com/gengen1988/XGPush \
  --variable ACCESS_ID="Your Access ID" \
  --variable ACCESS_KEY="Your Access Key"
```

如果使用 Crosswalk，则使用 plugman 安装：

```bash
plugman install --platform android --project "Your Project Directory" \
  --plugin https://github.com/gengen1988/XGPush \
  --variable ACCESS_ID="Your Access ID" \
  --variable ACCESS_KEY="Your Access Key"
```


## 注意事项
### 不同平台SO文件
请到 http://xg.qq.com/xg/help/ctr_help/download 下载对应版本的 SDK。
以下引用SDK中的说明：

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
