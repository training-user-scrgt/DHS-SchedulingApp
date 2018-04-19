package gov.dhs.uscis.odos2.useradmin.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import gov.dhs.uscis.odos2.useradmin.model.Users;
import gov.dhs.uscis.odos2.useradmin.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UsersRepository usersRepository;

    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.typ}")
    private String type;

    @GetMapping
    @ResponseBody
    public ResponseEntity getJWTToken(Authentication authentication) {

        try {

            SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
            String username = credential.getNameID().getValue();

            Users user = usersRepository.findByUsername(username);

            if (user == null) {

                return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);

            } else {

                Calendar c = Calendar.getInstance();
                Date now = c.getTime();
                c.add(Calendar.SECOND, 15);
                Date expirationDate = c.getTime();

                Map<String, Object> headerClaims = new HashMap<String, Object>(1);
                headerClaims.put("typ", type);

                String token = JWT.create()
                        .withIssuer(issuer)
                        .withExpiresAt(expirationDate)
                        .withIssuedAt(now)
                        .withNotBefore(now)
                        .withClaim("username", user.getUserName())
                        .withClaim("uuid", user.getId().toString())
                        .withClaim("first", user.getFirstName())
                        .withClaim("last", user.getLastName())
                        .withHeader(headerClaims)
                        .sign(Algorithm.HMAC256(secret));

                return ResponseEntity.ok(token);
            }

        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseEntity.noContent().build();
        }



    }
}
