package challenge.dto;

public class Kudos {

    private String fromUserKey;
    private String toUserKey;
    private String type;
    private String comment;

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

    public String getFromUserKey() {
        return fromUserKey;
    }

    public void setFromUserKey(String fromUserKey) {
        this.fromUserKey = fromUserKey;
    }

    public String getToUserKey() {
        return toUserKey;
    }

    public void setToUserKey(String toUserKey) {
        this.toUserKey = toUserKey;
    }

}
