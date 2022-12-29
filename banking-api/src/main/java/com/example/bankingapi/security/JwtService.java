package com.example.banking.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Service
@Slf4j
public class JwtService {

    public static final String USERNAME_KEY = "username";
    public static final String COOKIE_KEY = "cookie";
    public static final String API_PATH = "/api";
    private static final int EXPIRED_TIME = 1000 * 60 * 30; // 30 minutes

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

    @PostConstruct
    private void initKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();
        privateKey = (RSAPrivateKey) keyPair.getPrivate();
        publicKey = (RSAPublicKey) keyPair.getPublic();
    }

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, COOKIE_KEY);
        return cookie != null ? cookie.getValue() : null;
    }

    public String getUsernameFromJwtToken(String token) {
        String payload = validateToken(token);
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> payloadMap = parser.parseMap(payload);

        return (String) payloadMap.get(USERNAME_KEY);
    }

    public String generateTokenFromUsername(String username) {
        return JWT.create()
                .withClaim(USERNAME_KEY, username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRED_TIME))
                .sign(Algorithm.RSA256(publicKey, privateKey));
    }

    public String validateToken(String token) throws JWTVerificationException {
        String encodedPayload = JWT.require(Algorithm.RSA256(publicKey, privateKey))
                .build()
                .verify(token)
                .getPayload();

        return new String(Base64.getDecoder().decode(encodedPayload));
    }

    public static Cookie generateEmptyCookie() {
        Cookie cookie = new Cookie(COOKIE_KEY, null);
        cookie.setMaxAge(0);
        cookie.setValue(null);
        cookie.setPath(API_PATH);
        cookie.setHttpOnly(true);

        return cookie;
    }
}
