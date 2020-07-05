# hua_wei_push_plugin

华为推送SDK Flutter插件  
华为Push版本: v4.0.3.301

## 配置
0. 前往华为开发者中心 \[项目-应用\]下载 `agconnect-services.json`  
0. 将`agconnect-services.json`放在`android/app`目录  

## 使用要求
Flutter Version >= 1.12  
手机上安装 `华为移动服务（HMS Core）`，否则会报:`Failed to find HMS apk`错误，如果华为移动服务版本过低，会报:`client api invalid`

## 使用
### 接口
|  接口   | 说明  | 参数  | 
|  ----  | ----  | ----  |
| getToken  | 获得Push Token | { appId: APP_ID }
| deleteToken  | 删除 Push Token | { appId: APP_ID }