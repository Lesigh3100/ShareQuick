package lesigh.sharequick;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import lesigh.sharequick.database.Posts;
import lesigh.sharequick.ui.main.EditPostFragment;
import lesigh.sharequick.ui.main.MainFragment;
import lesigh.sharequick.ui.main.MainViewModel;
import lesigh.sharequick.ui.main.NewPostFragment;
import lesigh.sharequick.ui.main.PostRecycleFragment;
import lesigh.sharequick.ui.main.ViewPostFragment;
import lesigh.sharequick.ui.main.navigation.NavBoxFragment;
import lesigh.sharequick.ui.main.navigation.NavChoiceItems;
import lesigh.sharequick.utility.PostConstructor;

import static lesigh.sharequick.utility.Constants.CODE_CHOOSE_NEW_POST_TYPE_FRAGMENT;
import static lesigh.sharequick.utility.Constants.CODE_EDIT_POST_FRAGMENT;
import static lesigh.sharequick.utility.Constants.CODE_MAKE_NEW_POST_FRAGMENT;
import static lesigh.sharequick.utility.Constants.CODE_NAVIGATION_FRAGMENT;
import static lesigh.sharequick.utility.Constants.CODE_ONE_COLUMN;
import static lesigh.sharequick.utility.Constants.CODE_TWO_COLUMNS;
import static lesigh.sharequick.utility.Constants.CODE_VIEW_SAVED_POSTS_RECYCLER_FRAGMENT;
import static lesigh.sharequick.utility.Constants.CODE_VIEW_SINGLE_POST_FRAGMENT;


public class MainActivity extends AppCompatActivity implements EditPostFragment.OnEditPostFragmentInteractionListener, PostRecycleFragment.OnPostSelectedFromRecyclerView, NewPostFragment.OnNewPostFragmentInteractionListener, ViewPostFragment.OnViewFragmentListenerInteraction, NavBoxFragment.OnNavigationItemSelected {
    private final String TAG = "MainActivity";
    private MainViewModel mainViewModel;
    private FragmentManager fragmentManager;
    private final int mainFragmentWindow = R.id.main_navigation_window;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(mainFragmentWindow, NavBoxFragment.newInstance(CODE_TWO_COLUMNS));
        fragmentTransaction.commit();
    }



    private void switchToFragment(int fragmentCode) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (fragmentCode) {
            case CODE_NAVIGATION_FRAGMENT:
                fragmentTransaction.replace(mainFragmentWindow, NavBoxFragment.newInstance(CODE_TWO_COLUMNS));
                break;
            case CODE_CHOOSE_NEW_POST_TYPE_FRAGMENT:
                fragmentTransaction.replace(mainFragmentWindow, NewPostFragment.newInstance());
                break;
            case CODE_MAKE_NEW_POST_FRAGMENT:
                fragmentTransaction.replace(mainFragmentWindow, EditPostFragment.newInstance(CODE_MAKE_NEW_POST_FRAGMENT));
                break;
            case CODE_EDIT_POST_FRAGMENT:
                fragmentTransaction.replace(mainFragmentWindow, EditPostFragment.newInstance(CODE_EDIT_POST_FRAGMENT));
                break;
            case CODE_VIEW_SINGLE_POST_FRAGMENT:
                fragmentTransaction.replace(mainFragmentWindow, ViewPostFragment.newInstance());
                break;
            case CODE_VIEW_SAVED_POSTS_RECYCLER_FRAGMENT:
                Log.d(TAG, "switch to recycler fragment");
                fragmentTransaction.replace(mainFragmentWindow, PostRecycleFragment.newInstance(CODE_ONE_COLUMN));
                break;
            default:
                return;

        }
        fragmentTransaction.commit();

    }

    private void deleteSelectedPost() {
        mainViewModel.deletePost();
        switchToFragment(CODE_NAVIGATION_FRAGMENT);
    }

    private void savePost(@NonNull Posts postToSave) {
        mainViewModel.insertPost(postToSave);
    }


    @Override
    public void onSaveNewPost(@Nullable String title, @Nullable String body, @Nullable String imageUriString) {
        savePost(PostConstructor.constructPost(title, body, imageUriString));
        switchToFragment(CODE_VIEW_SAVED_POSTS_RECYCLER_FRAGMENT);
    }

    @Override
    public void onSavePostBeingEdited(@Nullable String title, @Nullable String body, @Nullable String imageUriString) {
    savePost(PostConstructor.editPost(mainViewModel.getSelected().getValue(), title, body, imageUriString ));
    }

    @Override
    public void onDeleteButtonPressed() {
        deleteSelectedPost();
    }



    @Override
    public void onItemSelectedFromRecyclerView(Posts item) {
        mainViewModel.setSelected(item);
        switchToFragment(CODE_VIEW_SINGLE_POST_FRAGMENT);
    }

    @Override
    public void onNewPostButtonClicked() {
        switchToFragment(CODE_MAKE_NEW_POST_FRAGMENT);
    }

    @Override
    public void onEditButtonClicked() {
        switchToFragment(CODE_EDIT_POST_FRAGMENT);
    }

    @Override
    public void onDeleteButtonClicked() {
        deleteSelectedPost();
    }

    @Override
    public void onListFragmentInteraction(NavChoiceItems item) {
        switchToFragment(item.FRAGMENT_CODE);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        switchToFragment(CODE_NAVIGATION_FRAGMENT);
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                switchToFragment(CODE_NAVIGATION_FRAGMENT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
