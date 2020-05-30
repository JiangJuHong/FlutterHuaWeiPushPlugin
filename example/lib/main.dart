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
      "deleteToken": () async => controller.text = await HuaWeiPushPlugin.getToken(),
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
