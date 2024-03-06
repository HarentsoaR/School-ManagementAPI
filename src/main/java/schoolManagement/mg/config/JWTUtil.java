package schoolManagement.mg.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import schoolManagement.mg.entity.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JWTUtil {

    @Value("${jwt.secret}")
    private  String SECRET_KEY;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        // Extract roles from UserDetails
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", roles);
        return createToken(claims, userDetails.getUsername());
    }



    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
        		.setExpiration(new Date(System.currentTimeMillis() +  30 *  60 *  1000)) //  30 minutes
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    public List<String> extractRoles(String token) {
        return extractClaim(token, claims -> (List<String>) claims.get("roles"));
    }
    
    public boolean hasRole(String role) {
        // Assuming the current authentication context is accessible and contains the JWT token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Extract the token from the authentication context
        String token = (String) authentication.getCredentials();
        // Extract roles from the token
        List<String> roles = extractRoles(token);
        // Check if the role is present in the roles list
        return roles.contains(role);
    }

    public  List<String> getClaim(String token, String claimKey) {
        try {
            Jws<Claims> jws = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token);

            Claims claims = jws.getBody();
            Object claimValue = claims.get(claimKey);
            if (claimValue instanceof List) {
                return (List<String>) claimValue;
            } else if (claimValue instanceof String) {
                List<String> result = new ArrayList<>();
                result.add((String) claimValue);
                return result;
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., token expired, invalid signature)
            e.printStackTrace();
        }
        return null; // Return null or handle the case when the claim is not found
    }


}
