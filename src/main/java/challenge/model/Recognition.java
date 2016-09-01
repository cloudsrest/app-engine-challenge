package challenge.model;

import challenge.dto.RecognitionTypeEnum;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
public class Recognition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User fromUser;

    @ManyToOne
    private User toUser;

    @Enumerated(EnumType.STRING)
    private RecognitionTypeEnum recognitionType;

    @Type(type="text")
    private String comment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public RecognitionTypeEnum getRecognitionType() {
        return recognitionType;
    }

    public void setRecognitionType(RecognitionTypeEnum recognitionType) {
        this.recognitionType = recognitionType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
