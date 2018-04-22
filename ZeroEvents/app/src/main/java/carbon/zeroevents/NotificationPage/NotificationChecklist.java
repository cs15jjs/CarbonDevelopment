package carbon.zeroevents.NotificationPage;

public class NotificationChecklist {

    boolean isSelected;
    String Option;

    //now create constructor and getter setter method using shortcut like command+n for mac & Alt+Insert for window.


    public NotificationChecklist(boolean isSelected, String Option) {
        this.isSelected = isSelected;
        this.Option = Option;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getOption() {
        return Option;
    }

    public void setUserName(String Option) {
        this.Option = Option;
    }
}
