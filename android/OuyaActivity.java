package com.tealeaf.plugin.plugins;

import com.tealeaf.EventQueue;
import com.tealeaf.event.PluginEvent;
import com.tealeaf.logger;
import tv.ouya.console.api.OuyaController;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class OuyaActivity extends FragmentActivity {

    public class OuyaKeyEvent extends com.tealeaf.event.PluginEvent {
        int player, code, action;

        public OuyaKeyEvent(int player, int code, int action) {
            super("ouyakey");
            this.player = player;
            this.code = code;
            this.action = action;
        }
    }

    public class OuyaMotionEvent extends com.tealeaf.event.PluginEvent {
        int player;
        float lsx, lsy, rsx, rsy, l2, r2;

        public OuyaMotionEvent(int player, float lsx, float lsy, float rsx, float rsy, float l2, float r2) {
            super("ouyamotion");
            this.player = player;
            this.lsx = lsx;
            this.lsy = lsy;
            this.rsx = rsx;
            this.rsy = rsy;
            this.l2 = l2;
            this.r2 = r2;
        }
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		logger.log("MAR ACTIVITY ON CREATE");
		super.onCreate(savedInstanceState);
		OuyaController.init(this);
	};

	@Override
    public boolean onKeyDown(final int keyCode, KeyEvent event) {
        logger.log("MAR KEY DOWN");
        int player = OuyaController.getPlayerNumByDeviceId(event.getDeviceId());
        EventQueue.pushEvent(new OuyaKeyEvent(player, keyCode, 1));
        return true;
    }

	@Override
    public boolean onKeyUp(final int keyCode, KeyEvent event) {
        logger.log("MAR KEY UP");
        int player = OuyaController.getPlayerNumByDeviceId(event.getDeviceId());
        EventQueue.pushEvent(new OuyaKeyEvent(player, keyCode, 2));
        return true;
    }

	@Override
    public boolean onGenericMotionEvent(final MotionEvent event) {
        logger.log("MAR MOTION");
        EventQueue.pushEvent(new OuyaMotionEvent(
            OuyaController.getPlayerNumByDeviceId(event.getDeviceId()),
            event.getAxisValue(OuyaController.AXIS_LS_X),
            event.getAxisValue(OuyaController.AXIS_LS_Y),
            event.getAxisValue(OuyaController.AXIS_RS_X),
            event.getAxisValue(OuyaController.AXIS_RS_Y),
            event.getAxisValue(OuyaController.AXIS_L2),
            event.getAxisValue(OuyaController.AXIS_R2)
        ));
        return true;
    }

}