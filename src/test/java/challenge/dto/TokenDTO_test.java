package challenge.dto;

import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TokenDTO_test {

    public void test() {
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setAccess_token("acc_toke");
        tokenDTO.setExpires_in(1);
        tokenDTO.setRefresh_token("refresh");
        tokenDTO.setScope("scope");
        tokenDTO.setToken_type("type");

        assertEquals("acc_toke", tokenDTO.getToken_type());
        assertEquals(1, tokenDTO.getExpires_in());
        assertEquals("refresh", tokenDTO.getRefresh_token());
        assertEquals("scope", tokenDTO.getScope());
        assertEquals("type", tokenDTO.getToken_type());
        assertNotNull(tokenDTO.toString());
    }

}
