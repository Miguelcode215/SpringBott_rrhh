package senati.rrhh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
}
