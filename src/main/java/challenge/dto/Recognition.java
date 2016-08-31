package challenge.dto;

public class Recognition {

    private String fromUserKey;
    private String toUserKey;
    private String type;
    private String comment;
    private String activityDescription;

    public Recognition() {
    }

    public Recognition(String fromUserKey, String toUserKey, String type, String comment, String activityDescription) {
        this.fromUserKey = fromUserKey;
        this.toUserKey = toUserKey;
        this.type = type;
        this.comment = comment;
        this.activityDescription = activityDescription;
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

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recognition that = (Recognition) o;

        if (activityDescription != null ? !activityDescription.equals(that.activityDescription) : that.activityDescription != null)
            return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (fromUserKey != null ? !fromUserKey.equals(that.fromUserKey) : that.fromUserKey != null) return false;
        if (toUserKey != null ? !toUserKey.equals(that.toUserKey) : that.toUserKey != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fromUserKey != null ? fromUserKey.hashCode() : 0;
        result = 31 * result + (toUserKey != null ? toUserKey.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (activityDescription != null ? activityDescription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Recognition{" +
                "fromUserKey='" + fromUserKey + '\'' +
                ", toUserKey='" + toUserKey + '\'' +
                ", type='" + type + '\'' +
                ", comment='" + comment + '\'' +
                ", activityDescription='" + activityDescription + '\'' +
                '}';
    }
}
