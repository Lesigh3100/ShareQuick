package lesigh.sharequick.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "posts")
public class Posts {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo (name = "body")
    private String body;

    @ColumnInfo (name = "image")
    private String imageUri;

    @ColumnInfo (name = "link")
    private String linkUrl;


    public Posts() {
    }

    @Ignore
    public Posts(String title, String body, String imageUri) {
        this.title = title;
        this.body = body;
        this.imageUri = imageUri;
    }

    @Ignore
    public Posts(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
