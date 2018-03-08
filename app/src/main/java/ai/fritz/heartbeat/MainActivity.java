package ai.fritz.heartbeat;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.logging.Logger;

import ai.fritz.heartbeat.adapters.DemoAdapter;
import ai.fritz.heartbeat.adapters.DemoItem;
import ai.fritz.heartbeat.ui.SeparatorDecoration;
import ai.fritz.heartbeat.utils.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @BindView(R.id.demo_list_view)
    RecyclerView recyclerView;

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(myToolbar);

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager rvLinearLayoutMgr = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLinearLayoutMgr);

        SeparatorDecoration decoration = new SeparatorDecoration(this, Color.GRAY, 1);
        recyclerView.addItemDecoration(decoration);

        DemoAdapter adapter = new DemoAdapter(getDemoItems());
        recyclerView.setAdapter(adapter);
        recyclerView.setClickable(true);
    }

    private List<DemoItem> getDemoItems() {
        List<DemoItem> demoItems = Lists.newArrayList(
                new DemoItem(
                        "INCEPTION",
                        "Detect objects in real-time using the camera. A nice upgrade from Not-Hotdog",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                logger.info("GO TO INCEPTION");
                                Navigation.goToInception(v.getContext());
                            }
                        })
        );
        return demoItems;
    }
}
