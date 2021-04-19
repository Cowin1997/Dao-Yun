package cn.edu.fzu.utils;

import cn.edu.fzu.entity.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class TokenUtils {
    /**
     * 密钥
     */
    private static final String SECRET = "secretKey";
    /**
     * 过期时间（单位：秒）
     **/
    private static final long EXPIRATION = 3600L;

    public static String getToken(User user){
        String token = "";
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        token = JWT.create()
                // 添加头部
                .withHeader(map)
                // 放入用户的id
                .withAudience(String.valueOf(user.getId()))
                // 可以将基本信息放到claims中
                .withClaim("username",user.getUs_username())
                .withClaim("type",String.valueOf(user.getUs_roleid()))
                // 超时设置,设置过期的日期
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION * 100000))
                // 签发时间
                .withIssuedAt(new Date())
                // SECRET加密
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    public static Boolean verify(String token){
        if(token == null) throw new RuntimeException("无token,请重新登录");
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        try {
            DecodedJWT jwt = jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("token验证失败");
        }
        return true;
    }



    @Test
    void test(){
        User user = new User();
        user.setId(1);
        user.setUs_username("username");
        user.setUs_roleid(1);
        String token = TokenUtils.getToken(user);
        log.info(token);
        Boolean aBoolean  = TokenUtils.verify(token);
        log.info(String.valueOf(aBoolean));
    }
}
