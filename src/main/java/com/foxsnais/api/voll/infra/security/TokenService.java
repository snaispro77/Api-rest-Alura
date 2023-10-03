package com.foxsnais.api.voll.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.foxsnais.api.voll.domain.users.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secretjwt}")
    private String secretJwt;
    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretJwt);
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject(usuario.getUsername())
                    .withClaim("Id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("Hubo un error en la creacion del token");
        }
    }
    public String getSubject(String tokenJwt){
        if (tokenJwt == null){
            throw new RuntimeException("No se recibio el token para obtener Subject");
        }
        DecodedJWT decodedJWT;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretJwt); //validando la firma del token
            JWTVerifier  verifier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build();

            decodedJWT = verifier.verify(tokenJwt);
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Hubo un problema al validar la firma del token");
        }
        return decodedJWT.getSubject();
    }
    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2L).toInstant(
                ZoneOffset.of("-05:00")
        );
    }
}
