import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  static const platform = MethodChannel('com.example.kiosk_mode_app/locktask');

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Kiosk Mode App'),
        ),
        body: Center(
          child: ElevatedButton(
            onPressed: () async {
              try {
                await platform.invokeMethod('startLockTask');
              } on PlatformException catch (e) {
                print("Failed to start lock task: '${e.message}'.");
              }
            },
            child: Text('Start Kiosk Mode'),
          ),
        ),
      ),
    );
  }
}
