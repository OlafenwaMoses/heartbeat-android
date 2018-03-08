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
        List<DemoItem> demoItems = Lists.newArrayList(
                new DemoItem(
                        getString(R.string.mobile_net_title),
                        getString(R.string.mobile_net_description),
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                logger.info("GO TO MOBILE NET");
                                Navigation.goToMobileNet(v.getContext());
                            }
                        })
        );
        return demoItems;
    }
}
