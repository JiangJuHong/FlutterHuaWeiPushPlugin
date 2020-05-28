import 'dart:async';

import 'package:flutter/services.dart';

class HuaWeiPushPlugin {
  static const MethodChannel _channel =
      const MethodChannel('hua_wei_push_plugin');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
