package com.tealeaf.plugin.plugins;

import tv.ouya.console.api.OuyaController;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.tealeaf.EventQueue;
import com.tealeaf.event.PluginEvent;
import com.tealeaf.logger;
import com.tealeaf.TeaLeaf;
import com.tealeaf.plugin.IPlugin;
import java.io.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.tealeaf.util.HTTP;
import java.net.URI;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.os.Bundle;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class OuyaPlugin implements IPlugin {

    public class OuyaEvent extends com.tealeaf.event.PluginEvent {
        int controller;
        int keyCode;
        int keyAction;
        double analogValue;

        public OuyaEvent(int controller, int keyCode, double analogValue) {
            super("ouya");
            this.controller = controller;
            this.keyCode = keyCode;
            this.keyAction = 0;
            this.analogValue = analogValue;
        }

        public OuyaEvent(int controller, int keyCode, int keyAction) {
            super("ouya");
            this.controller = controller;
            this.keyCode = keyCode;
            this.keyAction = keyAction;
            this.analogValue = 0;
        }
    }

    public OuyaPlugin() {

    }

    public void onCreateApplication(Context applicationContext) {

    }

    public void onCreate(Activity activity, Bundle savedInstanceState) {
        OuyaController.init(activity);
    }

    public void onResume() {
    }

    public void onStart() {
    }

    public void onPause() {
    }

    public void onStop() {
    }

    public void onDestroy() {
    }

    public void onNewIntent(Intent intent) {

    }

    public void setInstallReferrer(String referrer) {

    }

    public boolean onKeyDown(final int keyCode, KeyEvent event) {
        int player = OuyaController.getPlayerNumByDeviceId(event.getDeviceId());
        EventQueue.pushEvent(new OuyaEvent(player, keyCode, event.getAction())); 
        return true;
    }

    public boolean onGenericMotionEvent(final MotionEvent event) {
        int player = OuyaController.getPlayerNumByDeviceId(event.getDeviceId());
        float LS_X = event.getAxisValue(OuyaController.AXIS_LS_X);
        float LS_Y = event.getAxisValue(OuyaController.AXIS_LS_Y);
        float RS_X = event.getAxisValue(OuyaController.AXIS_RS_X);
        float RS_Y = event.getAxisValue(OuyaController.AXIS_RS_Y);
        float L2 = event.getAxisValue(OuyaController.AXIS_L2);
        float R2 = event.getAxisValue(OuyaController.AXIS_R2);
        EventQueue.pushEvent(new OuyaEvent(player, OuyaController.AXIS_LS_X, LS_X));
        EventQueue.pushEvent(new OuyaEvent(player, OuyaController.AXIS_LS_Y, LS_Y));
        EventQueue.pushEvent(new OuyaEvent(player, OuyaController.AXIS_RS_X, RS_X));
        EventQueue.pushEvent(new OuyaEvent(player, OuyaController.AXIS_RS_Y, RS_Y));
        EventQueue.pushEvent(new OuyaEvent(player, OuyaController.AXIS_L2, L2));
        EventQueue.pushEvent(new OuyaEvent(player, OuyaController.AXIS_R2, R2));
        return true;
    }

    public void onActivityResult(Integer request, Integer result, Intent data) {

    }

    public boolean consumeOnBackPressed() {
        return false;
    }

    public void onBackPressed() {
    }

}
