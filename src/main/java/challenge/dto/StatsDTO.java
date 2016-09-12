package challenge.dto;

import java.util.List;

public class StatsDTO {

    private long recognitionCount;
    private long userCount;
    private List<Summary> summaries;

    public StatsDTO() {
    }

    public StatsDTO(long recognitionCount, long userCount, List<Summary> summaries) {
        this.recognitionCount = recognitionCount;
        this.userCount = userCount;
        this.summaries = summaries;
    }

    public long getRecognitionCount() {
        return recognitionCount;
    }

    public void setRecognitionCount(long recognitionCount) {
        this.recognitionCount = recognitionCount;
    }

    public long getUserCount() {
        return userCount;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    public List<Summary> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<Summary> summaries) {
        this.summaries = summaries;
    }
}
