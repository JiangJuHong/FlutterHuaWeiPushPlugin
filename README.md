# hua_wei_push_plugin

华为推送SDK Flutter插件  
华为Push版本: v5.0.0.300

## 使用要求
Flutter Version >= 1.12  
如果非华为手机测试，则必须在 手机上安装 `华为移动服务（HMS Core）`，否则会报:`Failed to find HMS apk`错误，如果华为移动服务版本过低或权限不足(建议授予所有权限)，会报:`client api invalid`

## 配置
### Flutter
````
hua_wei_push_plugin: ^[最新版本号]
````

### Android
0. 生成证书 [证书生成教程](https://developer.huawei.com/consumer/cn/doc/development/HMS-Guides/game-preparation-v4#certificate)，并前往 华为开发者中心 \[项目-应用\] 中配置 `SHA256证书指纹`
0. 修改 `android\app\build.gradle` 文件  
   在末尾加入
   ````diff
   + apply plugin: 'com.huawei.agconnect'
   ````
   增加证书信息，例:
   ````diff
   android {
   
   +   signingConfigs {
   +       config {
   +           storeFile file('android.keystore')
   +           storePassword '123456'
   +           keyAlias 'android_test_key'
   +           keyPassword '123456'
   +           v1SigningEnabled true
   +           v2SigningEnabled true
   +       }
   +   }
   
       buildTypes {
   +       debug {
   +           signingConfig signingConfigs.config
   +       }
   +       release {
   +           // Signing with the debug keys for now, so `flutter run --release` works.
   +           signingConfig signingConfigs.config
   +       }
       }
   }
   ````

0. 修改 `android\build.gradle`
   ````diff
   buildscript {
       repositories {
           google()
           jcenter()
   +       maven {url 'https://developer.huawei.com/repo/'}
       }
   
       dependencies {
   +       classpath 'com.huawei.agconnect:agcp:1.2.1.301'
       }
   }
   ````

0. 前往华为开发者中心 \[项目-应用\]下载 `agconnect-services.json`  
0. 将`agconnect-services.json`放在`android/app`目录  


## 使用
### 接口
|  接口   | 说明  | 参数  | 
|  ----  | ----  | ----  |
| getToken  | 获得Push Token | { appId: APP_ID }
| deleteToken  | 删除 Push Token | { appId: APP_ID }
| getId  | 获得ID | -
| getAAID  | 获得AAID | -
| deleteAAID  | 删除AAID | -
| getAppId  | 获得AppId | -
| getCreationTime  | 获得创建时间 | -
| getValue  | 获得`agconnect-services.json`文件的值,不同层级以 / 分开，例如 : client/package_name | String key
| turnOnPush  | 启用推送 | -
| turnOffPush  | 关闭推送 | -
| subscribe  | 订阅 | String topic
| unsubscribe  | 取消订阅 | String topic
| setAutoInitEnabled  | 设置自动初始化 | bool enabled
| isAutoInitEnabled  | 是否启用自动初始化 | -

### 监听器
添加监听器:`HuaWeiPushPlugin.addListener`，移除监听器:`HuaWeiPushPlugin.removeListener`  
监听器方法原形: `typedef ListenerValue<P> = void Function(HuaWeiPushListenerTypeEnum type, P params);`

|  类型   | 说明  | 参数格式  | 
|  ----  | ----  | ----  |
| MessageReceived  | 接收透传消息 | Map
| MessageSent  | 发送上行消息成功回调方法 | String
| MessageDelivered  | 发送上行消息时如果使用了消息回执能力，消息到达App服务器后，App服务器的应答消息通过本方法回调给应用。 | MessageExceptionEntity
| SendError  | 发送上行消息失败回调方法 | MessageExceptionEntity
| NewToken  | 服务端更新token回调方法。 | String
| TokenError  | 申请token失败回调方法。 | ExceptionEntity


## 其它插件
````
我同时维护的还有以下插件，如果您感兴趣与我一起进行维护，请通过Github联系我，欢迎 issues 和 PR。
````
| 平台 | 插件  |  描述  |  版本  | - |
| ---- | ----  | ---- |  ---- | ---- |
| Flutter | [FlutterTencentImPlugin](https://github.com/JiangJuHong/FlutterTencentImPlugin)  | 腾讯云IM插件 | [![pub package](https://img.shields.io/pub/v/tencent_im_plugin.svg)](https://pub.dartlang.org/packages/tencent_im_plugin) | ![](https://img.shields.io/github/stars/JiangJuHong/FlutterTencentImPlugin?style=social) |
| Flutter | [FlutterTencentRtcPlugin](https://github.com/JiangJuHong/FlutterTencentRtcPlugin)  | 腾讯云Rtc插件 | [![pub package](https://img.shields.io/pub/v/tencent_rtc_plugin.svg)](https://pub.dartlang.org/packages/tencent_rtc_plugin) | ![](https://img.shields.io/github/stars/JiangJuHong/FlutterTencentRtcPlugin?style=social) |
| Flutter | [FlutterXiaoMiPushPlugin](https://github.com/JiangJuHong/FlutterXiaoMiPushPlugin)  | 小米推送SDK插件 | [![pub package](https://img.shields.io/pub/v/xiao_mi_push_plugin.svg)](https://pub.dartlang.org/packages/xiao_mi_push_plugin) | ![](https://img.shields.io/github/stars/JiangJuHong/FlutterXiaoMiPushPlugin?style=social) |
| Flutter | [FlutterHuaWeiPushPlugin](https://github.com/JiangJuHong/FlutterHuaWeiPushPlugin)  | 华为推送(HMS Push)插件 | [![pub package](https://img.shields.io/pub/v/hua_wei_push_plugin.svg)](https://pub.dartlang.org/packages/hua_wei_push_plugin) | ![](https://img.shields.io/github/stars/JiangJuHong/FlutterHuaWeiPushPlugin?style=social) |
| Flutter | [FlutterTextSpanField](https://github.com/JiangJuHong/FlutterTextSpanField)  | 自定义文本样式输入框 | [![pub package](https://img.shields.io/pub/v/text_span_field.svg)](https://pub.dartlang.org/packages/text_span_field) | ![](https://img.shields.io/github/stars/JiangJuHong/FlutterTextSpanField?style=social) |
| Flutter | [FlutterQiniucloudLivePlugin](https://github.com/JiangJuHong/FlutterQiniucloudLivePlugin)  | Flutter 七牛云直播云插件 | 暂未发布，通过 git 集成 | ![](https://img.shields.io/github/stars/JiangJuHong/FlutterQiniucloudLivePlugin?style=social) |
