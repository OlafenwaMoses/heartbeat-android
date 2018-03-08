package ai.fritz.heartbeat.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import ai.fritz.heartbeat.CameraActivity;

/**
 * Created by erichsiao on 3/7/18.
 */

public class Navigation {

    public static void goToInception(Context context) {
        Intent cameraIntent = new Intent(context, CameraActivity.class);
        context.startActivity(cameraIntent);
    }
}
