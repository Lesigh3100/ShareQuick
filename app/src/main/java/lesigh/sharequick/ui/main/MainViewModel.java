package lesigh.sharequick.ui.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;


import java.util.List;

import lesigh.sharequick.database.Posts;
import lesigh.sharequick.repository.QuickRepository;

public class MainViewModel extends AndroidViewModel {

    private QuickRepository quickRepository;
    private LiveData<List<Posts>> allPosts;


    public MainViewModel(@NonNull Application application) {
        super(application);
        quickRepository = new QuickRepository(application);
        allPosts = quickRepository.getAllPosts();
    }

    private final MutableLiveData<Posts> selected = new MutableLiveData<>();

    public LiveData<List<Posts>> getAllPosts() {
        return allPosts;
    }

    public void setSelected(Posts posts){
    selected.setValue(posts);
    }

    public LiveData<Posts> getSelected (){
    return selected;
    }

public void insertPost(Posts posts){
        quickRepository.insert(posts);
}

public void deletePost(){
        quickRepository.delete(getSelected().getValue());
}

    // this is where viewmodel ends - called right after onDestroy
    @Override
    protected void onCleared() {
        super.onCleared();
    }



}
