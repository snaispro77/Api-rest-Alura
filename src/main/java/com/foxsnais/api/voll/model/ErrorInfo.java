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
}
