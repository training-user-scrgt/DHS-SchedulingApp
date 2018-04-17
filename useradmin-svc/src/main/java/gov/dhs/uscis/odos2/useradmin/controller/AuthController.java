package gov.dhs.uscis.odos2.useradmin.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping
    @ResponseBody
    public String getJWTToken(Authentication authentication) {

        SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
        return credential.getNameID().getValue();

    }
}
