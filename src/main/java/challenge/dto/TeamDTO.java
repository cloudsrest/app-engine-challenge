package challenge.dto;

import challenge.model.Team;

public class TeamDTO {

    private Long id;
    private String name;

    public TeamDTO() {
    }

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
