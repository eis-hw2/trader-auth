package com.example.traderauth.Config.Auth.Filter;

import com.example.traderauth.Domain.Factory.ResponseWrapperFactory;
import com.example.traderauth.Domain.Wrapper.ResponseWrapper;
import com.example.traderauth.Service.RedisService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private RedisService redisService;

    public JWTLoginFilter(AuthenticationManager am, RedisService redisService){
        this.authenticationManager = am;
        this.redisService = redisService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        long expirationSeconds = 365 * 24 * 60 * 60;

        String username = authResult.getName();
        logger.info("[JWTLoginFilter.success] Login Success:" + username);
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
                .compact();

        String actualToken = "Bearer " + token;
        redisService.set(username, actualToken, expirationSeconds);

        PrintWriter out = response.getWriter();
        out.write(ResponseWrapperFactory.createResponseString(ResponseWrapper.SUCCESS, actualToken));
        out.flush();
        out.close();
    }
}
