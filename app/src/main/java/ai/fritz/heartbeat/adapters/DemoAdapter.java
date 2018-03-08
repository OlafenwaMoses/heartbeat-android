package ai.fritz.heartbeat.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.common.collect.Lists;

import java.util.List;

import ai.fritz.heartbeat.R;

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {
    private List<DemoItem> demoItems = Lists.newArrayList();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public LinearLayout linearLayout;
        public TextView titleView;
        public TextView descriptionView;
        public ViewHolder(LinearLayout v) {
            super(v);
            linearLayout = v;
            titleView = linearLayout.findViewById(R.id.title);
            descriptionView = linearLayout.findViewById(R.id.description);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DemoAdapter(List<DemoItem> demoItems) {
        this.demoItems = demoItems;
    }

    // Replace the contents of a view (invoked by the layout manager)
    public void onBindViewHolder(DemoAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        DemoItem demoItem = demoItems.get(position);
        holder.titleView.setText(demoItem.getTitle());
        holder.descriptionView.setText(demoItem.getDescription());
        holder.linearLayout.setOnClickListener(demoItem.getOnClickListener());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return demoItems.size();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DemoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_demo, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }


}