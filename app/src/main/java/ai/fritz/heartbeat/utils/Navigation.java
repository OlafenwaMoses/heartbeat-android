package ai.fritz.heartbeat.utils;

import android.content.Context;
import android.content.Intent;

import ai.fritz.heartbeat.CameraActivity;
import ai.fritz.heartbeat.MnistActivity;

/**
 * Navigation is a helper class for common links throughout the app.
 */
public class Navigation {

    /**
     * Goes to the Mobile Net Activity.
     *
     * @param context
     */
    public static void goToMobileNet(Context context) {
        Intent cameraIntent = new Intent(context, CameraActivity.class);
        context.startActivity(cameraIntent);
    }

    /**
     * Goes to the MNIST activity
     * @param context
     */
    public static void goToMNIST(Context context) {
        Intent mnistIntent = new Intent(context, MnistActivity.class);
        context.startActivity(mnistIntent);
    }
}
