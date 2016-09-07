package challenge.model;

import javax.persistence.*;
import java.sql.Blob;

@Entity(name = "oauth_access_token")
public class OAuthAccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Column(name = "token_id", length = 256)
    private String tokenId;

    @Column(name = "token")
    private Blob token;

    @Column(name = "authentication_id", length = 256)
    private String authenticationId;

    @Column(name = "user_name", length = 256)
    private String userName;

    @Column(name = "client_id", length = 256)
    private String clientId;

    @Column(name = "authentication")
    private Blob authentication;

    @Column(name = "refresh_token", length = 256)
    private String refreshToken;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Blob getToken() {
        return token;
    }

    public void setToken(Blob token) {
        this.token = token;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Blob getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Blob authentication) {
        this.authentication = authentication;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
