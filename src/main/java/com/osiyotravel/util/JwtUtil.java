package com.osiyotravel.util;


import com.osiyotravel.dto.deatil.JwtDTO;
import com.osiyotravel.enums.ProfileRole;
import com.osiyotravel.exception.AppBadRequestException;
import com.osiyotravel.exception.TokenNotValidException;
import io.jsonwebtoken.*;


import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private final static String secretKey = "perfetto-bigman";

    private static final int tokenLikeTime = 1000 * 60 * 60 * 24; // 1-day

    public static String encode(JwtDTO dto) {
        return encode(dto, 1);
    }

    public static String encode(JwtDTO dto, long days) {

        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("role", dto.getProfileRole());
        claimMap.put("id", dto.getId());
        claimMap.put("username", dto.getUsername());
        claimMap.put("phone", dto.getPhone());

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);

        jwtBuilder.setClaims(claimMap);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (days * tokenLikeTime)));
        jwtBuilder.setIssuer("Perfetto");

        return jwtBuilder.compact();
    }


    public static JwtDTO decode(String jwt) {
        try {
            JwtParser jwtParser = Jwts.parser();
            jwtParser.setSigningKey(secretKey);

            Jws<Claims> jws = jwtParser.parseClaimsJws(jwt);

            Claims claims = jws.getBody();

            Date expiration = claims.getExpiration();
            Date from = Date.from(Instant.now());

            if (from.after(expiration)) {
                throw new TokenNotValidException("Jwt Expired");
            }

            ProfileRole role = ProfileRole.valueOf((String) claims.get("role"));
            String username = (String) claims.get("username");
            String id = (String) claims.get("id");
            String phone = (String) claims.get("phone");


            return JwtDTO.builder()
                    .profileRole(role)
                    .username(username)
                    .id(id)
                    .phone(phone)
                    .build();

        } catch (JwtException e) {
            throw new AppBadRequestException("JWT invalid!");
        }
    }
}
