package ai.fritz.heartbeat;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ai.fritz.heartbeat.fragments.Camera2BasicFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity for the camera
 */
public class CameraActivity extends AppCompatActivity {

    @BindView(R.id.app_toolbar)
    Toolbar appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        setTitle(R.string.mobile_net_title);
        ButterKnife.bind(this);

        setSupportActionBar(appBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (null == savedInstanceState) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.camera_container, Camera2BasicFragment.newInstance())
                    .commit();
        }
    }
}
