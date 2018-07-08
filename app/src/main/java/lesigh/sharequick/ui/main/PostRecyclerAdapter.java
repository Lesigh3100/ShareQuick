package lesigh.sharequick.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import lesigh.sharequick.R;
import lesigh.sharequick.database.Posts;
import lesigh.sharequick.ui.main.PostRecycleFragment.OnPostSelectedFromRecyclerView;


import java.util.List;

import static lesigh.sharequick.utility.Constants.FILEPATH;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Posts} and makes a call to the
 * specified {@link OnPostSelectedFromRecyclerView}.
 */
public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    private List<Posts> mValues;
    private final OnPostSelectedFromRecyclerView mListener;


    public PostRecyclerAdapter(OnPostSelectedFromRecyclerView listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mPostTitle.setText(mValues.get(position).getTitle());
        holder.mPostBody.setText(mValues.get(position).getBody());

        Glide.with(holder.mView).load(FILEPATH + mValues.get(position).getImageUri()).into(holder.mPostImage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onItemSelectedFromRecyclerView(holder.mItem);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.posts_text_title)TextView mPostTitle;
        @BindView(R.id.posts_text_body)TextView mPostBody;
        @BindView(R.id.posts_image_view)ImageView mPostImage;
        public Posts mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mPostBody.getText() + "'";
        }
    }

    void setPosts(List<Posts> posts){
        mValues = posts;
        notifyDataSetChanged();
    }

}
