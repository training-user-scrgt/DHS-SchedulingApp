package gov.dhs.uscis.odos2.useradmin.model;


import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;


@SuppressWarnings("serial")
public class JwtToken implements Authentication {

    private final DecodedJWT jwt;
    private Map<String, Claim> claims;
    boolean authenticated;

    public JwtToken(DecodedJWT jwt) {
        this.jwt = jwt;
        this.authenticated = true;

        this.claims = (this.jwt.getClaims() == null) ? new HashMap<String, Claim>(0) : this.jwt.getClaims();

    }

    public void clearClaims() {
        this.claims = new HashMap<String, Claim>();
    }

    public DecodedJWT getJwt() {
        return this.jwt;
    }

    @Override
    public String getName() {
        return this.claims.get("subject") != null ? this.claims.get("subject").asString() : "";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String claimsString = this.claims.get("roles").asString();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (StringUtils.isNotEmpty(claimsString)) {

            String[] roles = claimsString.split(",");

            for (String role : roles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role));
            }
        }

        return Collections.unmodifiableList(grantedAuthorities);
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getDetails() {
        return claims.toString();
    }

    @Override
    public Object getPrincipal() {
        return claims.get("subject");
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

}
