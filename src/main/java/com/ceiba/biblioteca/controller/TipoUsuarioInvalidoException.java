package com.ceiba.biblioteca.controller;

public class TipoUsuarioInvalidoException extends RuntimeException {
    public TipoUsuarioInvalidoException(String message) {
        super(message);
    }
}