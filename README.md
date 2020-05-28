# hua_wei_push_plugin

华为推送SDK Flutter插件  
华为Push版本: v4.0.3.301

## 配置
1. 前往华为开发者中心 \[项目-应用\] 下载 `agconnect-services.json`  
2. 将`agconnect-services.json`放在`android/app`目录  

## 使用要求
Flutter Version >= 1.12

## 使用
### 接口
|  接口   | 说明  | 参数  | 
|  ----  | ----  | ----  |
| getToken  | 获得Push Token | { appId: APP_ID }
| deleteToken  | 删除 Push Token | { appId: APP_ID }