package challenge.dto;

public class UserDTO {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean isAdmin;
    private Long team;
    private String password;
    private boolean isActive = true;

    private boolean canGiveTo = true;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String firstName, String lastName, boolean isAdmin, Long team, String password) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
        this.team = team;
        this.password = password;
    }

    public UserDTO(challenge.model.User model) {
        this.id = model.getId();
        this.username = model.getUsername();
        this.firstName = model.getFirstName();
        this.lastName = model.getLastName();
        this.team = model.getTeam() == null ? null : model.getTeam().getId();
        this.isActive = model.isActivated();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isCanGiveTo() {
        return canGiveTo;
    }

    public void setCanGiveTo(boolean canGiveTo) {
        this.canGiveTo = canGiveTo;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
