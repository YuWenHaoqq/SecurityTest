package priv.wenhao.base.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtils {
	public static final String TOKEN_HEADER="Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";

	private static final String SECRET = "jwtsecretdemo";
	private static final String ISS = "echisan";

//	过期时间3600秒,1小时
	private static final long EXPIRATION=3600L;

//	选择了记住我之后的过期时间为7天
	private static final long EXPIRATION_REMEMBER = 604800L;

//	创建token
	public static String createToken(String username,boolean isRememberMe){
		long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
		return Jwts.builder()
				.signWith(SignatureAlgorithm.HS512,SECRET)
				.setIssuer(ISS)
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+expiration*1000))
				.compact();
	}

//	从token中获取用户名
	public static String getUserName(String token){
		return getTokenBody(token).getSubject();
	}

//	是否已经过期
	public static boolean isExpiration(String token){
		return getTokenBody(token).getExpiration().before(new Date());
	}
	private static Claims getTokenBody(String token){
		return (Claims) Jwts.parser()
				.setSigningKey(SECRET)
				.parse(token)
				.getBody();
	}
}
