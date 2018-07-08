package lesigh.sharequick.ui.main.navigation;

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

import butterknife.ButterKnife;
import lesigh.sharequick.R;
import lesigh.sharequick.ui.main.MainViewModel;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnNavigationItemSelected}
 * interface.
 */
public class NavBoxFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 2;
    private OnNavigationItemSelected mListener;
    private NavBoxAdapter adapter;
    private final String TAG = "NavBoxFragment";
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NavBoxFragment() {
    }

    public static NavBoxFragment newInstance(int columnCount) {
        NavBoxFragment fragment = new NavBoxFragment();
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
        adapter = new NavBoxAdapter(mListener, NavListChanger.getNavOptions(getContext(), model.getSelected().getValue() != null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navbox_list, container, false);
        ButterKnife.bind(this, view);
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            Log.d(TAG, "columncount = " + Integer.toString(mColumnCount));
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
        if (context instanceof OnNavigationItemSelected) {
            mListener = (OnNavigationItemSelected) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNavigationItemSelected");
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
    public interface OnNavigationItemSelected {
        // TODO: Update argument type and name
        void onListFragmentInteraction(NavChoiceItems item);
    }

    /*
    @Override
    public void onNavChoicesUpdated(List<NavChoiceItems> items) {
        if (adapter == null) {
            Log.d(TAG, "adapter == NULL");
            adapter = new NavBoxAdapter(mListener);
        }
        adapter.setNavChoices(items);
    } */

}
