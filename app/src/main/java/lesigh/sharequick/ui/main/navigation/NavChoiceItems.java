package lesigh.sharequick.ui.main.navigation;


public class NavChoiceItems {

    public String fragmentName;
    public String fragmentChoiceName;
    public final int FRAGMENT_CODE;

        public NavChoiceItems(String id, String content, int FRAGMENT_CODE) {
            this.fragmentName = id;
            this.fragmentChoiceName = content;
            this.FRAGMENT_CODE = FRAGMENT_CODE;
        }


    public String getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName = fragmentName;
    }

    public String getFragmentChoiceName() {
        return fragmentChoiceName;
    }

    public void setFragmentChoiceName(String fragmentChoiceName) {
        this.fragmentChoiceName = fragmentChoiceName;
    }

    public int getFRAGMENT_CODE() {
        return FRAGMENT_CODE;
    }
}
