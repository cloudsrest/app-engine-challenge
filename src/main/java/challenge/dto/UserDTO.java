package challenge.dto;

public class UserDTO {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private Long team;

    public UserDTO() {
    }

    public UserDTO(challenge.model.User model) {
        this.id = model.getId();
        this.username = model.getUsername();
        this.firstName = model.getFirstName();
        this.lastName = model.getLastName();
        this.isAdmin = model.isAdmin();
        this.team = model.getTeam() == null ? null : model.getTeam().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Long getTeam() {
        return team;
    }

    public void setTeam(Long team) {
        this.team = team;
    }
}
