package challenge.dto;

import challenge.model.Recognition;

public class RecognitionDTO {

    private Long fromUserId;
    private Long toUserId;
    private String type;
    private String comment;
    private Long timestamp;

    public RecognitionDTO() {
    }

    public RecognitionDTO(Recognition recognition) {
        this.fromUserId = recognition.getFromUser().getId();
        this.toUserId = recognition.getToUser().getId();
        this.type = recognition.getRecognitionType() == null ? null : recognition.getRecognitionType().toString();
        this.comment = recognition.getComment();
        this.timestamp = recognition.getTimestamp().getTime();
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
