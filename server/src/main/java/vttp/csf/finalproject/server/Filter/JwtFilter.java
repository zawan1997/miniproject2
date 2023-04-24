package vttp.csf.finalproject.server.Filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {
    @Value("${UI.BASE.URL}")
    String UI_BASE_URL;

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain)
    throws IOException, ServletException, MalformedJwtException {
        System.out.println("filter started");
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(request.getHeader("content-type")!=null||request.getHeader("GET")!=null){
        
        final String headersPresent = request.getHeader("HeadersPresent");
        final String authHeader = request.getHeader("Authorization");
        System.out.println("original authheader: "+request.getHeader("Authorization"));
        
            try {       
                System.out.println("authHeader: "+authHeader);
                if(authHeader!=null && authHeader.startsWith("Bearer ")){
                    final String token = authHeader.substring(7);
                    System.out.println("filter started 1");

                    Claims claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();
                    System.out.println("filter started 2");

                    request.setAttribute("claims", claims);
                    System.out.println("filter started 3");

                }else {
                    throw new ServletException("JWT Filter Exception");
                }
    
            }catch(MalformedJwtException e) {
                System.out.println("filter catch 1");

                    response.sendError(401, "JWT token is wrong");
            }catch(ServletException e) {
                System.out.println("filter catch 2");
                System.out.println(e.getMessage());

                    response.sendError(401, "JWT token is wrong");
            }

            System.out.println(response);
    }
        filterChain.doFilter(request, response);        
    }

}
