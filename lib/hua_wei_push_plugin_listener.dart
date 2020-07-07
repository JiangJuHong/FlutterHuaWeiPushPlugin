import 'dart:convert';

import 'package:flutter/services.dart';
import 'package:hua_wei_push_plugin/entity/exception_entity.dart';

import 'entity/message_exception_entity.dart';

/// 监听器对象
class HuaWeiPushPluginListener {
  /// 监听器列表
  static Set<ListenerValue> listeners = Set();

  HuaWeiPushPluginListener(MethodChannel channel) {
    // 绑定监听器
    channel.setMethodCallHandler((methodCall) async {
      // 解析参数
      Map<dynamic, dynamic> arguments = methodCall.arguments;

      switch (methodCall.method) {
        case 'onListener':
          // 获得原始类型和参数
          String typeStr = arguments['type'].toString();
          var params = arguments['params'] != null ? arguments['params'] : null;

          // 封装回调类型和参数
          HuaWeiPushListenerTypeEnum type;

          // 初始化类型
          for (var item in HuaWeiPushListenerTypeEnum.values) {
            var es = item.toString().split(".");
            if (es[es.length - 1] == typeStr) {
              type = item;
              break;
            }
          }

          // 没有找到类型就返回
          if (type == null) {
            throw MissingPluginException();
          }

          // 回调触发
          switch (type) {
            case HuaWeiPushListenerTypeEnum.MessageReceived:
              params = jsonDecode(params);
              break;
            case HuaWeiPushListenerTypeEnum.MessageSent:
              break;
            case HuaWeiPushListenerTypeEnum.MessageDelivered:
              params = MessageExceptionEntity.fromJson(jsonDecode(params));
              break;
            case HuaWeiPushListenerTypeEnum.SendError:
              params = MessageExceptionEntity.fromJson(jsonDecode(params));
              break;
            case HuaWeiPushListenerTypeEnum.NewToken:
              // 由于 NewToken 是在程序启动时就进行获取，可能监听器还没有加载进来，故在此等待 1 秒
              // TODO 本方法并不推荐，但是目前没有找到更好的解决办法，如果您在浏览源码的时候发现有更好的解决办法，欢迎 PR 或 Issues
              if (listeners.length == 0) {
                await Future.delayed(Duration(seconds: 1));
              }
              break;
            case HuaWeiPushListenerTypeEnum.TokenError:
              params = ExceptionEntity.fromJson(jsonDecode(params));
              break;
          }

          for (var item in listeners) {
            item(type, params);
          }

          break;
        default:
          throw MissingPluginException();
      }
    });
  }

  /// 添加消息监听
  void addListener(ListenerValue func) {
    listeners.add(func);
  }

  /// 移除消息监听
  void removeListener(ListenerValue func) {
    listeners.remove(func);
  }
}

/// 监听器值模型
typedef ListenerValue<P> = void Function(
    HuaWeiPushListenerTypeEnum type, P params);

/// 监听器类型枚举
enum HuaWeiPushListenerTypeEnum {
  MessageReceived,
  MessageSent,
  MessageDelivered,
  SendError,
  NewToken,
  TokenError,
}
