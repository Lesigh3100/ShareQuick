package lesigh.sharequick.ui.main.uiinterfaces;


import android.support.annotation.Nullable;

public interface OnEditPostListener {

    void onSavePost(@Nullable String postId, @Nullable String title, @Nullable String body, @Nullable String imageUriString);
    void onDeletePost();
}
