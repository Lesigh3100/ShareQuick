package lesigh.sharequick.ui.main;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fxn.pix.Pix;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import lesigh.sharequick.R;
import lesigh.sharequick.database.Posts;
import lesigh.sharequick.ui.main.uiinterfaces.OnUpdateImageListener;
import lesigh.sharequick.utility.PermissionChecker;

import static lesigh.sharequick.utility.Constants.*;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnEditPostFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditPostFragment extends Fragment implements View.OnClickListener, OnUpdateImageListener {
    private final String TAG = "EditPostFragment:";

    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "body";
    private static final String ARG_PARAM3 = "image";
    private static final String ARG_PARAM4 = "newOrEditFragment";


    private boolean makingNewPost;

    PermissionChecker permissionChecker;

    @BindView(R.id.edit_post_save_button)
    Button saveButton;
    @BindView(R.id.edit_post_delete_button)
    Button deleteButton;
    @BindView(R.id.edit_post_add_image_button)
    Button addImageButton;
    @BindView(R.id.edit_post_title)
    EditText editTextTitle;
    @BindView(R.id.edit_post_body)
    EditText editTextBody;
    @BindView(R.id.edit_post_image)
    ImageView addImageView;

    private String edittextTitleString;
    private String edittextBodyString;
    private String editImageStringUri;



    private OnEditPostFragmentInteractionListener mListener;


    public EditPostFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param newOrEditPost Parameter 1.
     * @return A new instance of fragment EditPostFragment.
     */
    public static EditPostFragment newInstance(int newOrEditPost) {
        EditPostFragment fragment = new EditPostFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_PARAM4, newOrEditPost);
            fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Log.d(TAG, "INCOMING ARG_PARAM4 = " + getArguments().getInt(ARG_PARAM4));
            if (getArguments().getInt(ARG_PARAM4) == CODE_EDIT_POST_FRAGMENT){
                MainViewModel model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
                model.getSelected().observe(this, posts -> {
                editSavedPost(posts);
                });
                makingNewPost = false;
            } else if (getArguments().getInt(ARG_PARAM4) == CODE_MAKE_NEW_POST_FRAGMENT){
                makingNewPost = true;
            }
        } else {
            Log.d(TAG, "getArguments coming up null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.edit_post_layout, container, false);


        ButterKnife.bind(this, view);
        saveButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        addImageButton.setOnClickListener(this);
        if (savedInstanceState != null && getArguments() == null){
            restoreView(savedInstanceState);
        }
        permissionChecker = new PermissionChecker(getActivity());
        permissionChecker.checkAllPermissions();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditPostFragmentInteractionListener) {
            mListener = (OnEditPostFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " OnEditPostFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (!TextUtils.isEmpty(editTextTitle.getText())){
            outState.putString(ARG_PARAM1, edittextTitleString);
        }
        if (!TextUtils.isEmpty(editTextBody.getText())){
            outState.putString(ARG_PARAM2, edittextBodyString);
        }
        if (editImageStringUri != null){
            if (!editImageStringUri.equals("")){
                outState.putString(ARG_PARAM3, editImageStringUri);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_post_save_button:
                onSaveButtonPressed();
                break;
            case R.id.edit_post_delete_button:
                onDeleteButtonPressed();
                break;
            case R.id.edit_post_add_image_button:
                if (permissionCheckerInstantiated()) {
                    if (permissionChecker.allPermissionsGranted()) {
                        imageSelection();
                    } else {
                        permissionChecker.checkAllPermissions();
                    }
                }
                break;
            default:
                break;
        }
    }

    public void onSaveButtonPressed() {
        if (mListener != null) {

            if (!TextUtils.isEmpty(editTextTitle.getText())) {
                edittextTitleString = editTextTitle.getText().toString();
                if (!TextUtils.isEmpty(editTextBody.getText())){
                    edittextBodyString = editTextBody.getText().toString();
                }

            } else {
                Toast.makeText(getContext(), R.string.blank_field_toast , Toast.LENGTH_SHORT).show();
                return;
            }

            if (makingNewPost) {
                mListener.onSaveNewPost(edittextTitleString, edittextBodyString, editImageStringUri);
            } else {
                mListener.onSavePostBeingEdited(edittextTitleString, edittextBodyString, editImageStringUri);
            }

        }
    }


    public void onDeleteButtonPressed(){
        deleteAlertDialogue();
    }

    private void imageSelection() {
        Pix.start(this,
                CODE_PICK_IMAGE);
    }


    // updates the image view
    private void updateImage(@NonNull Uri uri) {
        Glide.with(getContext()).load(uri).into(addImageView);
    }

    private void imageStringToUri(String imageUriString) {
        updateImage(Uri.parse(FILEPATH + imageUriString));
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
    public interface OnEditPostFragmentInteractionListener {
        void onSaveNewPost(@Nullable String title, @Nullable String body, @Nullable String imageUriString);
        void onSavePostBeingEdited(@Nullable String title, @Nullable String body, @Nullable String imageUriString );
        void onDeleteButtonPressed();
    }

    @Override
    public void onUpdateImage(String imageUriString) {
        imageStringToUri(imageUriString);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == CODE_PICK_IMAGE) {
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            editImageStringUri = returnValue.get(0);
            Log.d(TAG, "editImageStringUri result = " + editImageStringUri);
            imageStringToUri(editImageStringUri);
        }
    }

    private boolean permissionCheckerInstantiated() {
        if (permissionChecker != null) {
            return true;
        } else {
            permissionChecker = new PermissionChecker(getActivity());
            return false;
        }
    }

    private void deleteAlertDialogue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setMessage(getResources().getString(R.string.delete_check));
        builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!makingNewPost){
                    mListener.onDeleteButtonPressed();
                } else {
                    edittextTitleString = "";
                    edittextBodyString = "";
                    editImageStringUri = "";
                    editTextTitle.setText(edittextTitleString);
                    editTextBody.setText(edittextBodyString);
                    Glide.with(getContext()).load(android.R.color.transparent).into(addImageView);
                }

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

    public void restoreView(Bundle savedInstanceState){
        if (getArguments() != null) {
            if (savedInstanceState != null){
                if (savedInstanceState.get(ARG_PARAM1) != null){
                    edittextTitleString = (String) savedInstanceState.get(ARG_PARAM1);
                    editTextTitle.setText(edittextTitleString);
                }
                if (savedInstanceState.get(ARG_PARAM2) != null){
                    edittextBodyString = (String) savedInstanceState.get(ARG_PARAM2);
                }
                if (savedInstanceState.get(ARG_PARAM3) != null){
                    editImageStringUri = savedInstanceState.getString(ARG_PARAM3);
                    imageStringToUri(editImageStringUri);
                }
            }
        }
    }
    public void editSavedPost(Posts posts){
        if (posts.getTitle() != null){
            edittextTitleString = posts.getTitle();
            editTextTitle.setText(edittextTitleString);
        }
        if (posts.getBody() != null){
            edittextBodyString = posts.getBody();
            editTextBody.setText(posts.getBody());
        }
        if (posts.getImageUri() != null){
            editImageStringUri = posts.getImageUri();
            imageStringToUri(editImageStringUri);
        }
    }


}
