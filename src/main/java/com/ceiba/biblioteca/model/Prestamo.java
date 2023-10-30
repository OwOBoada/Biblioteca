package com.ceiba.biblioteca.model;

import java.util.Date;



import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonView;


import com.ceiba.biblioteca.TipoUsuario;




@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Response.class)
    private long id; 

    @NotNull
    @JsonView(Views.Response.class)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date fechaDevolucion;

    @NotEmpty
    private String identificacionUsuario; //Numero de la identificacion del usuario

    @NotEmpty
    private String isbn;                  //Identificador unico de libro

    
    @Enumerated(EnumType.ORDINAL)
    private TipoUsuario tipoUsuario;      //Determina la relacion de usuario con biblioteca


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }
    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }
    public void setIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }


    @Override
    public String toString() {
        return "PrestamoModel [id=" + id + ", fechaDevolucion=" + fechaDevolucion + ", identificacionUsuario="
                + identificacionUsuario + ", isbn=" + isbn + ", tipoUsuario=" + tipoUsuario + "]";
    }
      
    
   
}
