package com.ceiba.biblioteca.controller;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.ceiba.biblioteca.TipoUsuario;
import com.ceiba.biblioteca.model.Prestamo;
import com.ceiba.biblioteca.model.Views;
import com.ceiba.biblioteca.service.PrestamoService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class PrestamoControlador {
    
    @Autowired
    PrestamoService prestamoService;
 
    @GetMapping("/prestamo")
    @ResponseBody
    public List<Prestamo> listarPrestamo(){
        return prestamoService.listarPrestamos();
    }

    @PostMapping("/prestamo")
    @ResponseBody
    @JsonView(Views.Response.class) // Usa la vista personalizada
    public Prestamo crearPrestamo(@RequestBody Prestamo prestamo){
                // Verificar si el tipoUsuario es válido
        if (prestamo.getTipoUsuario() != TipoUsuario.USUARIO_AFILIADO &&
            prestamo.getTipoUsuario() != TipoUsuario.USUARIO_EMPLEADO &&
            prestamo.getTipoUsuario() != TipoUsuario.USUARIO_INVITADO) {
            // Tipo de usuario no permitido, debes manejar el error adecuadamente
            throw new TipoUsuarioInvalidoException("Tipo de usuario no permitido en la biblioteca");
        }

            // Verificar si el usuario invitado ya tiene un préstamo
        if (prestamo.getTipoUsuario() == TipoUsuario.USUARIO_INVITADO &&
            prestamoService.usuarioTienePrestamo(prestamo.getIdentificacionUsuario())) {
            // El usuario invitado ya tiene un libro prestado, lanza una excepción 400
            throw new TipoUsuarioInvalidoException("El usuario con identificación " + prestamo.getIdentificacionUsuario() + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
}
            // Realizar el cálculo de la fecha de devolución según el tipo de usuario
            calcularFechaDevolucion(prestamo);
            
            // Guardar el préstamo en la base de datos
           Prestamo nuevoPrestamo = prestamoService.crearPrestamo(prestamo);

        // Retornar el resultado adecuado
        return nuevoPrestamo;
       
    }

    private void calcularFechaDevolucion(Prestamo prestamo) {
        // Obtiene la fecha actual
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
    
        // Cálculo de días adicionales basado en el tipo de usuario
        int diasAdicionales = 0;
    
        switch (prestamo.getTipoUsuario()) {
            case USUARIO_AFILIADO:
                diasAdicionales = 10;
                break;
            case USUARIO_EMPLEADO:
                diasAdicionales = 8;
                break;
            case USUARIO_INVITADO:
                diasAdicionales = 7;
                break;
            default:
                break;
        }
    
        // Realiza el cálculo de la fecha de devolución
        for (int i = 0; i < diasAdicionales; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
    
            // Verifica si el día calculado es sábado o domingo y agrega un día adicional si es el caso
            int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
            if (diaSemana == Calendar.SATURDAY) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            } else if (diaSemana == Calendar.SUNDAY) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        }
    
        // Establece la fecha calculada en el objeto prestamo
        prestamo.setFechaDevolucion(calendar.getTime());
    }
    
    @DeleteMapping("/prestamo/{id}")
    public void borrarPrestamo(@PathVariable Long id){
        prestamoService.borrarPrestamo(id);
    }

    @GetMapping("/prestamo/{id}")
    @ResponseBody
    public Prestamo buscarPrestamo(@PathVariable Long id){
        return prestamoService.buscarPrestamoPorId(id);
    }

    @PutMapping("/prestamo")
    @ResponseBody
    public Prestamo modificarPrestamo(@RequestBody Prestamo prestamo){
        return prestamoService.modificarPrestamo(prestamo);
    }

}

