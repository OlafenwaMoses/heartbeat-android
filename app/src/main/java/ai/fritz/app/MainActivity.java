package ai.fritz.app;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import ai.fritz.app.adapters.DemoAdapter;
import ai.fritz.app.adapters.DemoItem;
import ai.fritz.app.ui.SeparatorDecoration;
import ai.fritz.app.utils.Navigation;
import ai.fritz.core.Fritz;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The primary activity that shows the different model demos.
 */
public class MainActivity extends AppCompatActivity {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @BindView(R.id.demo_list_view)
    RecyclerView recyclerView;

    @BindView(R.id.app_toolbar)
    Toolbar appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.demo_title);
        ButterKnife.bind(this);

        // customize the action bar
        setSupportActionBar(appBar);

        // Initialize Fritz
        Fritz.configure(this);

        // Setup the recycler view
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager rvLinearLayoutMgr = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLinearLayoutMgr);

        // Add a divider
        SeparatorDecoration decoration = new SeparatorDecoration(this, Color.GRAY, 1);
        recyclerView.addItemDecoration(decoration);

        // Add the adapter
        DemoAdapter adapter = new DemoAdapter(getDemoItems());
        recyclerView.setAdapter(adapter);
        recyclerView.setClickable(true);
    }

    private List<DemoItem> getDemoItems() {
        // Add different demo items here
        List<DemoItem> demoItems = new ArrayList<>();

        demoItems.add(new DemoItem(
                getString(R.string.fritz_vision_title),
                getString(R.string.fritz_vision_description_live_video),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        logger.info("FRITZ VISION LABEL MODEL");
                        Navigation.goToLiveVideoFritzLabel(v.getContext());
                    }
                }));
        demoItems.add(new DemoItem(
                getString(R.string.fritz_object_detection_title),
                getString(R.string.fritz_object_detection_description),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        logger.info("FRITZ OBJECT DETECTION");
                        Navigation.goToObjectDetection(v.getContext());
                    }
                }));
        demoItems.add(new DemoItem(
                getString(R.string.fritz_customtfmobile_title),
                getString(R.string.fritz_customtfmobile_description),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        logger.info("FRITZ CUSTOM TFMOBILE");
                        Navigation.goToTFMobile(v.getContext());
                    }
                }));
        demoItems.add(new DemoItem(
                getString(R.string.fritz_customtflite_title),
                getString(R.string.fritz_customtflite_description),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        logger.info("FRITZ CUSTOM TFLITE");
                        Navigation.goToTFLite(v.getContext());
                    }
                }));
        return demoItems;
    }
}
