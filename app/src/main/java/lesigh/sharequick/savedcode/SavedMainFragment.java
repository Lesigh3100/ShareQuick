package lesigh.sharequick.savedcode;

public class SavedMainFragment {
    /*
    public class MainFragment extends Fragment implements OnEditPostListener, PostRecycleFragment.OnPostSelectedFromRecyclerView, NewPostFragment.OnNewPostFragmentInteractionListener, ViewPostFragment.OnViewFragmentListenerInteraction {


    private MainViewModel mainViewModel;
    private FragmentManager fragmentManager;


    private PostRecycleFragment postRecycleFragment;
    private EditPostFragment editPostFragment;
    private NewPostFragment newPostFragment;


    @BindView(R.fragmentName.main_fragment_text)
    public TextView mainFragmentText;

    private final int mainFragmentWindow = R.fragmentName.main_navigation_window;

    public static MainFragment newInstance() {
        return new MainFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getAllPosts().observe(this, new Observer<List<Posts>>() {
            @Override
            public void onChanged(@Nullable List<Posts> posts) {
                if (postRecycleFragment != null) {
                    postRecycleFragment.onPostListUpdated(posts);
                }
            }
        });
        fragmentManager = getFragmentManager();
        switchToFragment(CODE_CHOOSE_NEW_POST_TYPE_FRAGMENT);

    }

    private void switchToFragment(int fragmentCode) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (fragmentCode) {
            case CODE_CHOOSE_NEW_POST_TYPE_FRAGMENT:
                fragmentTransaction.add(mainFragmentWindow, NewPostFragment.newInstance());
                break;
            case CODE_MAKE_NEW_POST_FRAGMENT:
                fragmentTransaction.add(mainFragmentWindow, EditPostFragment.newInstance(CODE_MAKE_NEW_POST_FRAGMENT));
                break;

            case CODE_EDIT_POST_FRAGMENT:
                fragmentTransaction.add(mainFragmentWindow, EditPostFragment.newInstance(CODE_EDIT_POST_FRAGMENT));
                break;

            case CODE_VIEW_SINGLE_POST_FRAGMENT:
                fragmentTransaction.add(mainFragmentWindow, ViewPostFragment.newInstance());
                break;
            case CODE_VIEW_SAVED_POSTS_RECYCLER_FRAGMENT:
                fragmentTransaction.add(mainFragmentWindow, PostRecycleFragment.newInstance(CODE_ONE_COLUMN));
                break;

            default:
                return;

        }
        fragmentTransaction.commit();
    }

    private void deleteSelectedPost() {
        mainViewModel.deletePost();
    }

    private void savePost(@NonNull Posts postToSave) {
        mainViewModel.insertPost(postToSave);
    }


    @Override
    public void onSavePost(@Nullable String postId, @Nullable String title, @Nullable String body, @Nullable String imageUriString) {
        savePost(PostConstructor.constructPost(postId, title, body, imageUriString));
        switchToFragment(CODE_VIEW_SAVED_POSTS_RECYCLER_FRAGMENT);
    }

    @Override
    public void onDeletePost() {
        deleteSelectedPost();
    }

    @Override
    public void onItemSelectedFromRecyclerView(Posts item) {
        mainViewModel.setSelected(item);
        switchToFragment(CODE_VIEW_SINGLE_POST_FRAGMENT);
    }

    @Override
    public void onNewPostButtonClicked() {
        switchToFragment(CODE_MAKE_NEW_POST_FRAGMENT);
    }

    @Override
    public void onEditButtonClicked() {
        switchToFragment(CODE_EDIT_POST_FRAGMENT);
    }

    @Override
    public void onDeleteButtonClicked() {
        deleteSelectedPost();
    }
}

    */
}
