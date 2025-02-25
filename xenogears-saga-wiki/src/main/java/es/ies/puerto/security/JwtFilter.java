package es.ies.puerto.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import es.ies.puerto.model.db.dao.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;






@Component
public class JwtFilter extends OncePerRequestFilter {
	
	public static final String AUTH_HEADER ="Authorization";
	public static final String AUTH_HEADER_TOKEN_PREFIX ="Bearer ";

    private JwtService jwtTokenManager;

	@Autowired
	public void setJwtTokenManager(JwtService jwtTokenManager) {
		this.jwtTokenManager = jwtTokenManager;
	}


	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	
    	
    	String path = request.getRequestURI();
        
    	
    	//rutas permitidas sin estar autenticado
    	String[] permitted= {"/swagger-ui.html",
				"/swagger-ui/", "/v2/", "/v3/",
				"/configuration/","/swagger/",
				"/webjars/", "/api/v1/",
				"/api/auth/",
				"/websocket/", "/index.html",
				"/services/"};

    			
        
    	for (String ruta : permitted) {
            if (path.startsWith(ruta)) {
                // Permitir la solicitud sin autenticación 
                filterChain.doFilter(request, response);
                return;
            }
        }
    	
    	//el token viene en un header Authorization
        String header = request.getHeader(AUTH_HEADER);
        
        //típicamente se precede el token con bearer:  Bearer token
        if (header != null && header.startsWith(AUTH_HEADER_TOKEN_PREFIX)) {
        	
            String token = header.substring(AUTH_HEADER_TOKEN_PREFIX.length());
            try {
            	Map<String, String> mapInfoToken = jwtTokenManager.validateAndGetClaims(token);

            	final String nombreuser=mapInfoToken.get("username");
            
            	final String rol = mapInfoToken.get("role");

				CustomUserDetails userDetails = new CustomUserDetails(nombreuser, rol);

        		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        				userDetails,
        				null,
        				userDetails.getAuthorities()
        		);

        		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        		SecurityContextHolder.getContext().setAuthentication(authToken);
        		

        			
        		filterChain.doFilter(request, response);

            } catch (JWTVerificationException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
			
        }
    }
}
