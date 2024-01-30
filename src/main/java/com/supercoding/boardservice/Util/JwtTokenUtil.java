package com.supercoding.boardservice.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtil {

    // JWT 토큰 생성
    public static String createToken(String email, String key, long expiredTimeMs){

        Claims claims = Jwts.claims();
        claims.put("email", email);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ expiredTimeMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    // Claims애서 LoginID 추출
    public static String getLoginId(String token, String secretKey){
        return extractClaims(token, secretKey).get("email").toString();
    }

    public static boolean isExpired(String token, String secretKey){
        Date expiration = extractClaims(token, secretKey).getExpiration();
        return expiration.before(new Date());
    }

    private static Claims extractClaims(String token, String secretKey){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
