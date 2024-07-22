package com.example.kiosk_mode_app;

import android.os.Bundle;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {
  private static final String CHANNEL = "com.example.kiosk_mode_app/locktask";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    new MethodChannel(getFlutterEngine().getDartExecutor().getBinaryMessenger(), CHANNEL)
      .setMethodCallHandler(
        (call, result) -> {
          if (call.method.equals("startLockTask")) {
            startLockTaskMode();
            result.success(null);
          } else {
            result.notImplemented();
          }
        }
      );
  }

  private void startLockTaskMode() {
    DevicePolicyManager devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
    ComponentName adminComponent = new ComponentName(this, MyDeviceAdminReceiver.class);

    if (devicePolicyManager.isDeviceOwnerApp(getPackageName())) {
      String[] packages = {getPackageName()};
      devicePolicyManager.setLockTaskPackages(adminComponent, packages);
    }

    startLockTask();
  }
}
