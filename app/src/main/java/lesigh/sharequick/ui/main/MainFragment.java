package lesigh.sharequick.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lesigh.sharequick.R;
import lesigh.sharequick.ui.main.uiinterfaces.OnChangeMainFragment;

import static lesigh.sharequick.utility.Constants.CODE_POST_WHOLE_POST;
import static lesigh.sharequick.utility.Constants.CODE_POST_PICTURE;

public class MainFragment extends Fragment implements OnChangeMainFragment, View.OnClickListener {


    @BindView(R.id.main_fragment_text)
    TextView mainFragmentText;
    @BindView(R.id.picturePostButton)
    Button instagramPostButton;
    @BindView(R.id.wholePostButton)
    Button facebookPostButton;

    OnPostButtonClickedListener mListener;

    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        onIncludeButtons(false);
        instagramPostButton.setOnClickListener(this);
        facebookPostButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPostButtonClickedListener) {
            mListener = (OnPostButtonClickedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " OnPostButtonClickedListener");
        }
    }

    @Override
    public void onChangeText(String pageTitle) {
        if (mainFragmentText != null){
            mainFragmentText.setText(pageTitle);
        }
    }

    @Override
    public void onIncludeButtons(boolean show) {
    if (show){
        instagramPostButton.setVisibility(View.VISIBLE);
        facebookPostButton.setVisibility(View.VISIBLE);
    } else {
        instagramPostButton.setVisibility(View.INVISIBLE);
        facebookPostButton.setVisibility(View.INVISIBLE);
    }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.picturePostButton:
                mListener.onPostButtonClicked(CODE_POST_PICTURE);
                break;
            case R.id.wholePostButton:
                mListener.onPostButtonClicked(CODE_POST_WHOLE_POST);
                break;
            default:
                break;
        }
    }

    public interface OnPostButtonClickedListener {
       void onPostButtonClicked(int postTypeCode);
    }

}
