package com.inyta.assciation.common.Jwt;

import com.inyta.assciation.entity.dto.UserLoginDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT工具类
 */
@Slf4j
@Data
@Component
public class JwtUtils {

    private static final String SECRET = "inyta0321";
    private static final Long EXPIRE = 2678400L;
    private static final String HEADER = "Authorization";

    /**
     * 生成jwt token
     */
    public String generateToken(UserLoginDTO userLoginDTO) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + EXPIRE * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userLoginDTO.getUserId() + "")
                .setPayload(userLoginDTO.getUserName())
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * token是否过期
     *
     * @return true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
