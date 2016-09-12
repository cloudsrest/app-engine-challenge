package challenge.dto;

import java.util.List;

public class SummaryDTO {

    private int kudosCount;
    private int userCount;
    private List<Summary> kudosSummaries;

    public int getKudosCount() {
        return kudosCount;
    }

    public void setKudosCount(int kudosCount) {
        this.kudosCount = kudosCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public List<Summary> getKudosSummaries() {
        return kudosSummaries;
    }

    public void setKudosSummaries(List<Summary> kudosSummaries) {
        this.kudosSummaries = kudosSummaries;
    }
}
