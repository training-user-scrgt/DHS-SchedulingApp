package gov.dhs.uscis.odos2.useradmin.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import gov.dhs.uscis.odos2.useradmin.model.JwtToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {


    @Value("jwt.issuer")
    private String issuer;

    @Value("jwt.secret")
    private String secret;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        return authentication;

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }


}
