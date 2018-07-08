package lesigh.sharequick.savedcode;

public class SavedEditFragment {
    /*

        private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "body";
    private static final String ARG_PARAM3 = "image";
    private static final String ARG_PARAM4 = "fragmentName";

    public static EditPostFragment newInstance(@Nullable String fragmentName, @Nullable String titleText, @Nullable String bodyText, @Nullable String imageUriString) {
        EditPostFragment fragment = new EditPostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, titleText);
        args.putString(ARG_PARAM2, bodyText);
        args.putString(ARG_PARAM3, imageUriString);
        args.putString(ARG_PARAM4, fragmentName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (savedInstanceState != null){
                if (savedInstanceState.get(ARG_PARAM1) != null){
                    edittextTitleString = (String) savedInstanceState.get(ARG_PARAM1);
                    editTextTitle.setText(edittextTitleString);
                }
                if (savedInstanceState.get(ARG_PARAM2) != null){
                    edittextBodyString = (String) savedInstanceState.get(ARG_PARAM2);
                }
                if (savedInstanceState.get(ARG_PARAM3) != null){
                    editImageUri = savedInstanceState.getString(ARG_PARAM3);
                    imageStringToUri(editImageUri);
                }
                if (savedInstanceState.get(ARG_PARAM4) != null){
                    postId = savedInstanceState.getString(ARG_PARAM4);
                }
            }
        }
        MainViewModel model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        model.getSelected().observe(this, posts -> {

        });
    }
*/
}
