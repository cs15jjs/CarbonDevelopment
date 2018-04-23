package carbon.zeroevents.HelpPage;

public class HelpList {
    private String topic, answer;


    public HelpList(String topic, String answer) {
        this.topic = topic;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public String getTopic() {
        return topic;
    }
}