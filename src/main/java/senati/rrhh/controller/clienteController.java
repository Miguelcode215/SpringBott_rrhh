package senati.rrhh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senati.rrhh.model.Cliente;
import senati.rrhh.model.Empleado;
import senati.rrhh.service.ClienteService;
import senati.rrhh.service.EmpleadoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(value = "http://localhost:3000/")
public class clienteController {

    private static final Logger logger =
            LoggerFactory.getLogger(clienteController.class);

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> lister(){
        var cliente = clienteService.listarClientes();
        cliente.forEach(cliente1 -> logger.info(cliente.toString()));
        return cliente;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Cliente cliente){
        Cliente nuevoCliente = clienteService.guardarClientes(cliente);
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje","Cliente guardado correctamente.");
        respuesta.put("Cliente", nuevoCliente);
        return ResponseEntity.ok(respuesta);
    }
}
