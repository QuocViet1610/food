package com.example.osahaneat.util;

import com.example.osahaneat.service.imp.LoginServiceImp;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;

@Component
public class JwtUtilHelper {

    @Value("${jwt.privateKey}")
    private String privateKey;


        public String generateToken(int UserDetails) {

            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey)); // lay key va giai ma
            // tao doi tuong jws voi subject la data cung voi chuoi ma hoa
            String jws = Jwts.builder().subject(UserDetails+"").signWith(key).compact();
            return jws;

        }

    // Lấy thông tin user từ jwt


    public int getUserIdFromJWT(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey)); // lay key va giai ma
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).build().parseClaimsJws(token); // Giải mã token và lấy Jws<Claims>
        Claims claims = claimsJws.getBody(); // Lấy claims từ Jws
        return Integer.parseInt(claims.getSubject());
    }

        public boolean verifyToken(String Token ){
            try{
                SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
                Jwts.parser()
                        .setSigningKey(key)
                        .build()
                        .parse(Token);
                return true;
            }catch(Exception e){
                return false;
            }
        }


}