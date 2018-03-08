package ai.fritz.heartbeat.utils;

import android.content.Context;
import android.content.Intent;

import ai.fritz.heartbeat.CameraActivity;


public class Navigation {

    /**
     * Goes to the Mobile Next Activity.
     * @param context
     */
    public static void goToMobileNet(Context context) {
        Intent cameraIntent = new Intent(context, CameraActivity.class);
        context.startActivity(cameraIntent);
    }
}
