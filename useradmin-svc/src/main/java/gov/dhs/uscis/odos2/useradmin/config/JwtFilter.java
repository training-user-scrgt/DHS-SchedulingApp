package gov.dhs.uscis.odos2.useradmin.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import gov.dhs.uscis.odos2.useradmin.model.JwtToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class JwtFilter extends GenericFilterBean {

    private static final String AUTHORIZATION = "Authorization";
    private final String secret;
    private final String issuer;

    private AuthenticationManager authenticationManager;

    public JwtFilter(String secret, String issuer, AuthenticationManager authenticationManager) {
        this.secret = secret;
        this.issuer = issuer;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String authHeader = req.getHeader(AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("Not a valid authentication authHeader");
        }

        // and retrieve the token
        String token = authHeader.substring(7);

        try {

            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer(issuer)
                    .build();

            DecodedJWT jwt = verifier.verify(token);

            Authentication auth = authenticationManager.authenticate(new JwtToken(jwt));
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (UnsupportedEncodingException exception) {
            throw new ServletException("Unsupported Encoding");
        } catch (JWTVerificationException exception) {
            throw new ServletException("JWT is malformed");
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
