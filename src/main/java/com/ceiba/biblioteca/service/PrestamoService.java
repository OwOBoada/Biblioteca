package com.ceiba.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceiba.biblioteca.TipoUsuario;
import com.ceiba.biblioteca.model.Prestamo;
import com.ceiba.biblioteca.repository.PrestamoRepository;

@Service
public class PrestamoService {
    
    @Autowired
    PrestamoRepository prestamoRepo;


 
    public Prestamo crearPrestamo(Prestamo prestamo){
        return prestamoRepo.save(prestamo);
    }

    public void borrarPrestamo(Long id){
        prestamoRepo.deleteById(id);
    }

    public List<Prestamo> listarPrestamos(){
        return prestamoRepo.findAll();
    }

    public Prestamo buscarPrestamoPorId(Long id){
        return prestamoRepo.findById(id).orElse(null);
    }

    public Prestamo modificarPrestamo(Prestamo prestamo){
        return prestamoRepo.save(prestamo);
    }

    public boolean usuarioTienePrestamo(String identificacionUsuario) {
        // Consultar la base de datos para verificar si el usuario invitado ya tiene un préstamo
        // Si encuentra un préstamo con la identificación del usuario y el tipo de usuario invitado, retorna true
        // De lo contrario, retorna false
        return prestamoRepo.existsByIdentificacionUsuarioAndTipoUsuario(identificacionUsuario, TipoUsuario.USUARIO_INVITADO);
    }

}
