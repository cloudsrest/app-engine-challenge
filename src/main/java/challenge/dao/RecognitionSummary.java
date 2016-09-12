package challenge.dao;

public class RecognitionSummary {

    public long count;
    public String userName;

    public RecognitionSummary() {
    }

    public RecognitionSummary(String userName, long count) {
        this.count = count;
        this.userName = userName;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
