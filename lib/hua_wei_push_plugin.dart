import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';

class HuaWeiPushPlugin {
  static const MethodChannel _channel = const MethodChannel('hua_wei_push_plugin');

  /// 获得推送Token
  /// [appId] 官网应用的appId，如果不传，则默认走 agconnect-services.json 文件读取
  static Future<String> getToken({
    String appId,
  }) async {
    return await _channel.invokeMethod('getToken', {
      "appId": appId,
    });
  }

  /// 删除推送Token
  /// [appId] 官网应用的appId，如果不传，则默认走 agconnect-services.json 文件读取
  static Future<void> deleteToken({
    String appId,
  }) async {
    return await _channel.invokeMethod('deleteToken', {
      "appId": appId,
    });
  }

  /// 获得 Id
  static Future<String> getId() async {
    return await _channel.invokeMethod('getId');
  }

  /// 获得 AAID
  static Future<String> getAAID() async {
    return await _channel.invokeMethod('getAAID');
  }

  /// 删除 AAID
  static Future<void> deleteAAID() async {
    return await _channel.invokeMethod('deleteAAID');
  }

  /// 获得 AppId
  static Future<String> getAppId() async {
    return await _channel.invokeMethod('getAppId');
  }

  /// 获得 CreationTime
  static Future<int> getCreationTime() async {
    return await _channel.invokeMethod('getCreationTime');
  }

  /// 获得 agconnect-services.json 文件的值
  /// [key] agconnect-services.json 文件中的Key，多个用 / 区分
  static Future<dynamic> getValue(String key) async {
    return await _channel.invokeMethod('getValue', {
      "key": key,
    });
  }
}
