package lesigh.sharequick.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface PostsDAO {

    @Query("SELECT * FROM Posts")
    LiveData<List<Posts>> getAllPosts();

    /*
    @Query("SELECT * FROM Posts WHERE uid)
    Posts getPost(); */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPost(Posts posts);

    @Delete
    void deletePost(Posts posts);


}
