package ai.fritz.app;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ai.fritz.app.fragments.Camera2BasicFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * An activity for the camera
 */
public class LiveVideoActivity extends AppCompatActivity {

    @BindView(R.id.app_toolbar)
    Toolbar appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        setTitle(R.string.fritz_vision_title);
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
