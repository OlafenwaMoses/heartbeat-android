package ai.fritz.app.utils;

import android.content.Context;
import android.content.Intent;

import ai.fritz.app.DetectorActivity;
import ai.fritz.app.LiveVideoActivity;
import ai.fritz.app.CustomTFLiteActivity;
import ai.fritz.app.VisionActivity;
import ai.fritz.app.CustomTFMobileActivity;

/**
 * Navigation is a helper class for common links throughout the app.
 */
public class Navigation {


    public static void goToFritzVision(Context context) {
        Intent fritzVision = new Intent(context, VisionActivity.class);
        context.startActivity(fritzVision);
    }

    public static void goToTFMobile(Context context) {
        Intent tfMobile = new Intent(context, CustomTFMobileActivity.class);
        context.startActivity(tfMobile);
    }

    public static void goToTFLite(Context context) {
        Intent tflite = new Intent(context, CustomTFLiteActivity.class);
        context.startActivity(tflite);
    }

    public static void goToLiveVideoFritzLabel(Context context) {
        Intent liveVideoActivity = new Intent(context, LiveVideoActivity.class);
        context.startActivity(liveVideoActivity);
    }

    public static void goToObjectDetection(Context context) {
        Intent objectDetection = new Intent(context, DetectorActivity.class);
        context.startActivity(objectDetection);
    }
}
