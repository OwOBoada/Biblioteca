package com.ceiba.biblioteca;

public enum TipoUsuario {
    USUARIO_AFILIADO(0),
    USUARIO_EMPLEADO(1),
    USUARIO_INVITADO(2),

    USUARIO_DESCONOCIDO(4);

    private final int codigo;


    TipoUsuario(int  codigo){
        this.codigo = codigo;
    }

    public int getCodigo(){
        return codigo;
    }

}
