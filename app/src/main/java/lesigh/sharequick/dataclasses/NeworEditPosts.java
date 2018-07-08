package lesigh.sharequick.dataclasses;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeworEditPosts {


    private int uid;


    private String title;

    private String body;

    private String imageUri;

    private String itemId;

    public static final List<NeworEditPosts> ITEMS = new ArrayList<>();

    public static final Map<String, NeworEditPosts> ITEM_MAP = new HashMap<>();

    public NeworEditPosts() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    private static void addItem(NeworEditPosts item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.itemId, item);
    }
}
