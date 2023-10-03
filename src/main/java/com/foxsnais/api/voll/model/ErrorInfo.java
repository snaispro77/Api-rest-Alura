package com.foxsnais.api.voll.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
public class ErrorInfo {
    private  int codigo;
    private  String descripcion;
    private List<String> errores;
    public ErrorInfo(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }
}
