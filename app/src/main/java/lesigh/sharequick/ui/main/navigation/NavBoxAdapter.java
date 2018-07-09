package lesigh.sharequick.ui.main.navigation;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import lesigh.sharequick.R;
import lesigh.sharequick.ui.main.navigation.NavBoxFragment.OnNavigationItemSelected;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link NavChoiceItems} and makes a call to the
 * specified {@link OnNavigationItemSelected}.
 * TODO: Replace the implementation with code for your data type.
 */
public class NavBoxAdapter extends RecyclerView.Adapter<NavBoxAdapter.NavViewHolder> {
    private final String TAG = "NavBoxAdapter";
    private List<NavChoiceItems> mValues;
    private final OnNavigationItemSelected mListener;

    public NavBoxAdapter(OnNavigationItemSelected listener, List<NavChoiceItems> items) {
        mListener = listener;
        mValues = items;
    }

    @Override
    public NavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_navbox, parent, false);

        return new NavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NavViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.fragmentChoiceText.setText(mValues.get(position).getFragmentChoiceName());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mValues != null){
            return mValues.size();
        }
        return 0;
    }

    public class NavViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView fragmentChoiceText;
        public NavChoiceItems mItem;

        public NavViewHolder(View view) {
            super(view);
            mView = view;
            fragmentChoiceText = mView.findViewById(R.id.navbox_list_items_text);
            ButterKnife.bind(view);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    void setNavChoices(List<NavChoiceItems> items){
        mValues = items;
        notifyDataSetChanged();
    }
}
