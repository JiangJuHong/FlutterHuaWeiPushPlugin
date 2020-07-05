import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:hua_wei_push_plugin/hua_wei_push_plugin.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  TextEditingController controller = TextEditingController();

  Map<String, Function> methods = {};

  @override
  void initState() {
    super.initState();
    this.methods = {
      "getToken": () async => controller.text = await HuaWeiPushPlugin.getToken(),
      "deleteToken": () async {
        await HuaWeiPushPlugin.deleteToken();
        controller.text = "删除成功!";
      },
      "getId": () async => controller.text = await HuaWeiPushPlugin.getId(),
      "getAAID": () async => controller.text = await HuaWeiPushPlugin.getAAID(),
      "deleteAAID": () async {
        await HuaWeiPushPlugin.deleteAAID();
        controller.text = "删除成功!";
      },
      "getAppId": () async => controller.text = await HuaWeiPushPlugin.getAppId(),
      "getCreationTime": () async => controller.text = "${await HuaWeiPushPlugin.getCreationTime()}",
      "getValue(client/package_name)": () async => controller.text = "${await HuaWeiPushPlugin.getValue("client/package_name")}",
      "turnOnPush": () async => controller.text = "${await HuaWeiPushPlugin.turnOnPush()}",
      "turnOffPush": () async => controller.text = "${await HuaWeiPushPlugin.turnOffPush()}",
      "subscribe(123)": () async => controller.text = "${await HuaWeiPushPlugin.subscribe("123")}",
      "unsubscribe(123)": () async => controller.text = "${await HuaWeiPushPlugin.unsubscribe("123")}",
      "setAutoInitEnabled(true)": () async {
        await HuaWeiPushPlugin.setAutoInitEnabled(true);
        controller.text = "启用成功";
      },
      "setAutoInitEnabled(false)": () async {
        await HuaWeiPushPlugin.setAutoInitEnabled(false);
        controller.text = "关闭成功";
      },
      "isAutoInitEnabled": () async => controller.text = "${await HuaWeiPushPlugin.isAutoInitEnabled()}",
    };
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            children: <Widget>[
              TextField(
                controller: controller,
                maxLines: 10,
              ),
              Expanded(
                child: Wrap(
                  runSpacing: 10,
                  spacing: 10,
                  children: methods.keys
                      .map(
                        (key) => RaisedButton(
                          onPressed: methods[key],
                          child: Text(key),
                        ),
                      )
                      .toList(),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
