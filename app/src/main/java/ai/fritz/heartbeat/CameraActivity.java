package ai.fritz.heartbeat;

/* Copyright 2017 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.logging.Logger;

import ai.fritz.heartbeat.fragments.Camera2BasicFragment;
import ai.fritz.heartbeat.ml.ImageClassifierQuantizedMobileNet;

/**
 * Main {@code Activity} class for the Camera app.
 */
public class CameraActivity extends Activity {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static final String DOWNLOAD_URL = "https://s3.us-east-2.amazonaws.com/eric-test-staging/mobilenet_quant_v1_224.tflite";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        if (null == savedInstanceState) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.camera_container, Camera2BasicFragment.newInstance())
                    .commit();
        }
    }

    public class RetrieveModelTask extends AsyncTask<String, String, String> {
        private WeakReference<Activity> activity;

        public RetrieveModelTask(Activity activity) {
            this.activity = new WeakReference<Activity>(activity);
        }

        protected String doInBackground(String... url) {
            try {
                URL wallpaperURL = new URL(url[0]);
                wallpaperURL.openConnection();
                InputStream inputStream = new BufferedInputStream(wallpaperURL.openStream(), 8192);
                File appDir = this.activity.get().getFilesDir();
                File tfLiteFile = new File(appDir, ImageClassifierQuantizedMobileNet.modelName);
                FileOutputStream outputStream = new FileOutputStream(tfLiteFile);

                byte buffer[] = new byte[8192];
                int dataSize;
                int loadedSize = 0;
                while ((dataSize = inputStream.read(buffer)) != -1) {
                    loadedSize += dataSize;
                    logger.info("Downloaded: " + loadedSize);
                    outputStream.write(buffer, 0, dataSize);
                }

                outputStream.close();
                logger.info("DOWNLOADED:" + tfLiteFile.getAbsolutePath());
                return tfLiteFile.getAbsolutePath();

            } catch (Exception e) {
                logger.warning("WTF HAPPENED");
                logger.warning(e.toString());
            }

            return null;
        }

        protected void onPostExecute(String absolutePathForFile) {
            this.activity.get().getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.camera_container, Camera2BasicFragment.newInstance())
                    .commit();
        }
    }
}
