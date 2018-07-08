package lesigh.sharequick.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Posts.class}, version = 2)
public abstract class PostsDatabase extends RoomDatabase {


    public abstract PostsDAO postsDAO();

    private static PostsDatabase sInstance;

    public static PostsDatabase getInstance(Context context){
        if (sInstance == null){
            synchronized (PostsDatabase.class){
                if (sInstance == null){
                    sInstance = Room
                            .databaseBuilder(context.getApplicationContext(), PostsDatabase.class, "ex")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return sInstance;
    }

}
