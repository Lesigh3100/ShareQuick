package lesigh.sharequick.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import lesigh.sharequick.database.PostsDAO;
import lesigh.sharequick.database.PostsDatabase;
import lesigh.sharequick.database.Posts;

public class QuickRepository {


    private PostsDAO postsDAO;
    private LiveData<List<Posts>> getAllPostsFromDB;

    public QuickRepository(Application application){
        PostsDatabase database = PostsDatabase.getInstance(application);
        postsDAO = database.postsDAO();
        getAllPostsFromDB = postsDAO.getAllPosts();

    }

    public LiveData<List<Posts>> getAllPosts(){
        return getAllPostsFromDB;
    }

    public void insert(Posts posts){
        new insertAsyncTask(postsDAO).execute(posts);
    }

    public void delete(Posts posts){
        new deleteAsyncTask(postsDAO).execute(posts);
    }

    private static class insertAsyncTask extends AsyncTask<Posts, Void, Void> {

        private PostsDAO mAsyncTaskDao;

        insertAsyncTask(PostsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Posts... post) {
            mAsyncTaskDao.insertPost(post[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Posts, Void, Void>{

        private PostsDAO mAsyncTaskDao;

        deleteAsyncTask(PostsDAO dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Posts... postId) {
            mAsyncTaskDao.deletePost(postId[0]);
            return null;
        }
    }

}
