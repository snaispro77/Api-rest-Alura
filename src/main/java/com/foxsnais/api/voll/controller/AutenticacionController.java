package com.foxsnais.api.voll.controller;


import com.foxsnais.api.voll.domain.users.DatosAutenticacionUsuario;
import com.foxsnais.api.voll.domain.users.Usuario;
import com.foxsnais.api.voll.infra.security.DatosTokenJWT;
import com.foxsnais.api.voll.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class AutenticacionController {
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosTokenJWT> autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario dau){
        Authentication autenticacionToken = new UsernamePasswordAuthenticationToken(
                dau.usuario(),dau.password()
        );
        var usuarioAutenticado = authenticationManager.authenticate(autenticacionToken);

        String JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        /* obten todas las fuentes intaladas en el sistema operativo */
        return ResponseEntity.ok(new DatosTokenJWT(JWTtoken));
    }
}
