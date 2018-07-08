package lesigh.sharequick.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import lesigh.sharequick.R;
import lesigh.sharequick.database.Posts;

import static lesigh.sharequick.utility.Constants.FILEPATH;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnViewFragmentListenerInteraction} interface
 * to handle interaction events.
 * Use the {@link ViewPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewPostFragment extends Fragment implements View.OnClickListener {


    @BindView(R.id.view_post_title)
    TextView postTitleView;
    @BindView(R.id.view_post_body)
    TextView postBodyView;
    @BindView(R.id.view_post_image)
    ImageView postImageView;
    @BindView(R.id.view_post_edit_button)
    Button editButton;
    @BindView(R.id.view_post_delete_button)
    Button deleteButton;

    private OnViewFragmentListenerInteraction mListener;

    public ViewPostFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ViewPost.
     */
    public static ViewPostFragment newInstance() {
        ViewPostFragment fragment = new ViewPostFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_post, container, false);
        ButterKnife.bind(this, view);
        MainViewModel model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        model.getSelected().observe(this, posts -> {
            populatePost(posts);
        });
        editButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnViewFragmentListenerInteraction) {
            mListener = (OnViewFragmentListenerInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnViewFragmentListenerInteraction {
        void onEditButtonClicked();
        void onDeleteButtonClicked();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.view_post_edit_button:
                mListener.onEditButtonClicked();
                break;
            case R.id.view_post_delete_button:
                deleteAlertDialogue();
                break;
                default:
                    break;

        }
    }

    private void populatePost(Posts post){
        if (post.getTitle() != null){
            postTitleView.setText(post.getTitle());
        }
        if (post.getBody() != null){
            postBodyView.setText(post.getBody());
        }
        if (post.getImageUri() != null){
            imageStringToUri(post.getImageUri());
        }

    }

    private void updateImage(@NonNull Uri uri) {
        Glide.with(getContext()).load(uri).into(postImageView);
    }

    private void imageStringToUri(String imageUriString) {
        updateImage(Uri.parse(FILEPATH + imageUriString));
    }

    private void deleteAlertDialogue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setMessage(getResources().getString(R.string.delete_check));
        builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    mListener.onDeleteButtonClicked();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
