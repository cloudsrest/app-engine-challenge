package challenge.model;


import javax.persistence.*;
import java.sql.Blob;

@Entity(name = "oauth_refresh_token")
public class OAuthRefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Column(name = "token_id", length = 256)
    private String tokenId;

    @Column(name = "token")
    private String token;

    @Column(name = "authentication")
    private Blob authentication;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Blob getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Blob authentication) {
        this.authentication = authentication;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
