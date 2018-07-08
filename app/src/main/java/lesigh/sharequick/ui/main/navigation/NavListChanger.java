package lesigh.sharequick.ui.main.navigation;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import lesigh.sharequick.R;

import static lesigh.sharequick.utility.Constants.*;

public class NavListChanger {

    public static List<NavChoiceItems> getStartingList(Context context){
        List<NavChoiceItems> navAvailable = new ArrayList<>();
        NavChoiceItems newPost = new NavChoiceItems("newPost", context.getString(R.string.start_new_post_text), CODE_CHOOSE_NEW_POST_TYPE_FRAGMENT);
        navAvailable.add(newPost);
        return navAvailable;
    }

    public static List<NavChoiceItems> getNavOptions(Context context, boolean postSelected){
        List<NavChoiceItems> navAvailable = new ArrayList<>();
        NavChoiceItems newPost = new NavChoiceItems("newPost", context.getString(R.string.start_new_post_text), CODE_CHOOSE_NEW_POST_TYPE_FRAGMENT);;
        NavChoiceItems editPost = new NavChoiceItems("editPost", context.getString(R.string.edit_last_post), CODE_EDIT_POST_FRAGMENT);
        NavChoiceItems viewPost = new NavChoiceItems("viewPost", context.getString(R.string.view_last_post), CODE_VIEW_SINGLE_POST_FRAGMENT);
        NavChoiceItems viewSavedPosts = new NavChoiceItems("savedPosts", context.getString(R.string.view_saved_posts), CODE_VIEW_SAVED_POSTS_RECYCLER_FRAGMENT);

        navAvailable.add(newPost);
        navAvailable.add(viewSavedPosts);
        if (postSelected){
            navAvailable.add(editPost);
            navAvailable.add(viewPost);
        }
        return navAvailable;
    }
}
