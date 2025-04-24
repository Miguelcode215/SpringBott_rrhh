package senati.rrhh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senati.rrhh.model.Cliente;
import senati.rrhh.model.Empleado;
import senati.rrhh.service.EmpleadoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/empleados")
@CrossOrigin(value = "http://localhost:3000")
public class empleadoController {
    private static final Logger logger =
            LoggerFactory.getLogger(empleadoController.class);

    @Autowired
    private EmpleadoService service;

    @GetMapping
    public List<Empleado> listar(){
        var empleado = service.listarEmpleados();
        empleado.forEach(empleado1 -> logger.info(empleado.toString()));
        return empleado;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Empleado empleado){
        Empleado nuevoEmpleado = service.guardarEmpleado(empleado);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje","Empleado guardado correctamente.");
        respuesta.put("Empleado", nuevoEmpleado);
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarEmpleado(@PathVariable Integer id){
        Empleado empleado =  service.buscarEmpleado_id(id);
        Map<String, Object> respuesta = new HashMap<>();
        if(empleado != null) {
            respuesta.put("mensaje", "Empleado encontardo");
            respuesta.put("Empleado", empleado);
            return ResponseEntity.ok(respuesta);
        }else{
            respuesta.put("mensaje", "Empleado no encontrado");
            return ResponseEntity.ok(respuesta);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarCliente(@PathVariable Integer id, @RequestBody Empleado empleado){
        Empleado existeEmpleado = service.buscarEmpleado_id(id);
        Map<String, Object> respuesta = new HashMap<>();

        if(existeEmpleado != null){

            if(empleado.getApellidos() != null){
              existeEmpleado.setApellidos(empleado.getApellidos());
            }
            if (empleado.getNombre() != null){
                existeEmpleado.setNombre(empleado.getNombre());
            }
            if (empleado.getEmail() != null){
                existeEmpleado.setEmail(empleado.getEmail());
            }
            if (empleado.getTelefono() != null){
                existeEmpleado.setTelefono(empleado.getTelefono());
            }
            if (empleado.getDireccion() != null){
                existeEmpleado.setDireccion(empleado.getDireccion());
            }
            if (empleado.getArea() != null){
                existeEmpleado.setArea(empleado.getArea());
            }
            if (empleado.getSueldo() != null){
                existeEmpleado.setSueldo(empleado.getSueldo());
            }

            Empleado guardado = service.guardarEmpleado(existeEmpleado);

            respuesta.put("mensaje", "Cliente acualizado correctamete");
            respuesta.put("Cliente", guardado);
            return ResponseEntity.ok(respuesta);
        }else {
            respuesta.put("mensaje", "Cliente no encontrado");
            return ResponseEntity.ok(respuesta);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmpleado(@PathVariable Integer id){
        Empleado empleado = service.buscarEmpleado_id(id);
        if (empleado != null){
            service.eleminarEmpleado(empleado);
            return ResponseEntity.noContent().build(); //204
        }else {
            return ResponseEntity.notFound().build(); //404
        }
    }
}
