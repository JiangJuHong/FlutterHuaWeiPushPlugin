import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/services.dart';

class HuaWeiPushPlugin {
  static const MethodChannel _channel = const MethodChannel('hua_wei_push_plugin');

  /// 获得推送Token
  /// [appId] 官网应用的appId
  static Future<String> getToken({
    @required String appId,
  }) async {
    return await _channel.invokeMethod('getToken', {
      "appId": appId,
    });
  }

  /// 删除推送Token
  /// [appId] 官网应用的appId
  static Future<String> deleteToken({
    @required String appId,
  }) async {
    return await _channel.invokeMethod('deleteToken', {
      "appId": appId,
    });
  }
}
