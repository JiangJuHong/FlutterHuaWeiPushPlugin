# hua_wei_push_plugin

华为推送SDK Flutter插件  
华为Push版本: v4.0.3.301

## 使用要求
Flutter Version >= 1.12  
如果非华为手机测试，则必须在 手机上安装 `华为移动服务（HMS Core）`，否则会报:`Failed to find HMS apk`错误，如果华为移动服务版本过低或权限不足(建议授予所有权限)，会报:`client api invalid`

## 配置
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