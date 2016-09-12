package challenge.dto;

import challenge.dao.RecognitionSummary;

import java.util.List;

public class Summary {

    private long userCount;
    private long recognitionCount;
    private List<RecognitionSummary> topRecognitionReceivers;
    private List<RecognitionSummary> topRecognitionSenders;

    public Summary() {
    }

    public Summary(long userCount, long recognitionCount, List<RecognitionSummary> topRecognitionReceivers, List<RecognitionSummary> topRecognitionSenders) {
        this.userCount = userCount;
        this.recognitionCount = recognitionCount;
        this.topRecognitionReceivers = topRecognitionReceivers;
        this.topRecognitionSenders = topRecognitionSenders;
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

    public List<RecognitionSummary> getTopRecognitionReceivers() {
        return topRecognitionReceivers;
    }

    public void setTopRecognitionReceivers(List<RecognitionSummary> topRecognitionReceivers) {
        this.topRecognitionReceivers = topRecognitionReceivers;
    }

    public List<RecognitionSummary> getTopRecognitionSenders() {
        return topRecognitionSenders;
    }

    public void setTopRecognitionSenders(List<RecognitionSummary> topRecognitionSenders) {
        this.topRecognitionSenders = topRecognitionSenders;
    }
}
