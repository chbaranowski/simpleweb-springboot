package io.boot.swagger.auth;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {

	final String secret;
	
	public JwtFilter(String secret) {
		this.secret = secret;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String authHeader = request.getHeader("Authorization");
		if (authHeader == null) {
			response.sendError(403);
		} else {
			final String token = authHeader;
			try {
				final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
				request.setAttribute("claims", claims);
				chain.doFilter(req, res);
			} catch (final SignatureException e) {
				response.sendError(401);
			}
		}
	}
	
	public static void main(String[] args) {
		String token = Jwts.builder()
			.setSubject(args[0])
			.setIssuedAt(new Date())
			.signWith(SignatureAlgorithm.HS256, args[1])
		.compact();
		System.out.println(token);
	}

}
