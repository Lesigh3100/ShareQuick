package lesigh.sharequick.utility;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import lesigh.sharequick.database.Posts;

public class PostConstructor {
    public static Posts constructPost(@NonNull String title, @Nullable String body, @Nullable String imageUriString){
        Posts post = new Posts();
            post.setTitle(title);
        if (body != null){
            post.setBody(body);
        }
        if (imageUriString != null){
            post.setImageUri(imageUriString);
        }
        return post;
    }

    public static Posts editPost(@Nullable Posts post, @Nullable String title, @Nullable String body, @Nullable String imageUriString){
        if (title != null){
            post.setTitle(title);
        }
        if (body != null){
            post.setBody(body);
        }
        if (imageUriString != null){
            post.setImageUri(imageUriString);
        }
        return post;
    }

}
