package challenge.dto;

import challenge.dao.RecognitionSummary;

import java.util.List;

public class Summary {

    private long userCount;
    private long recognitionCount;
    private List<RecognitionSummary> summaries;

    public Summary() {
    }

    public Summary(long userCount, long recognitionCount, List<RecognitionSummary> summaries) {
        this.userCount = userCount;
        this.recognitionCount = recognitionCount;
        this.summaries = summaries;
    }

    public long getUserCount() {
        return userCount;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    public long getRecognitionCount() {
        return recognitionCount;
    }

    public void setRecognitionCount(long recognitionCount) {
        this.recognitionCount = recognitionCount;
    }

    public List<RecognitionSummary> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<RecognitionSummary> summaries) {
        this.summaries = summaries;
    }
}
