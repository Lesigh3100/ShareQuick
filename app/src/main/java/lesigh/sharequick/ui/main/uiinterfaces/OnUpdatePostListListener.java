package lesigh.sharequick.ui.main.uiinterfaces;


import java.util.List;

import lesigh.sharequick.database.Posts;

public interface OnUpdatePostListListener {
    void onPostListUpdated(List<Posts> posts);
}
