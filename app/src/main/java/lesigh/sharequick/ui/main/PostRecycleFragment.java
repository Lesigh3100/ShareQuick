package lesigh.sharequick.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import lesigh.sharequick.R;
import lesigh.sharequick.database.Posts;
import lesigh.sharequick.ui.main.uiinterfaces.OnUpdatePostListListener;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnPostSelectedFromRecyclerView}
 * interface.
 */
public class PostRecycleFragment extends Fragment implements OnUpdatePostListListener {


    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnPostSelectedFromRecyclerView mListener;
    private PostRecyclerAdapter adapter;
    private final String TAG = "PostRecycleFragment";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PostRecycleFragment() {
    }


    public static PostRecycleFragment newInstance(int columnCount) {
        PostRecycleFragment fragment = new PostRecycleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        MainViewModel model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        model.getAllPosts().observe(this, posts -> {
            onPostListUpdated(posts);
        });
        adapter = new PostRecyclerAdapter(mListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_list, container, false);
        ButterKnife.bind(this, view);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(adapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPostSelectedFromRecyclerView) {
            mListener = (OnPostSelectedFromRecyclerView) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnPostSelectedFromRecyclerView {
        void onItemSelectedFromRecyclerView(Posts item);
    }

    @Override
    public void onPostListUpdated(List<Posts> posts) {
        if (adapter == null){
            Log.d(TAG, "adapter == NULL");
            adapter = new PostRecyclerAdapter(mListener);
        }
    adapter.setPosts(posts);
    }
}
