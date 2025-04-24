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

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarCliente(@PathVariable Integer id){
        Cliente cliente = clienteService.buscarClientes_id(id);
        Map<String, Object> respuesta = new HashMap<>();
        if(cliente != null){
            respuesta.put("mensaje", "Cliente encontrado con exito");
            respuesta.put("Cliente", cliente);
            return ResponseEntity.ok(respuesta);
        }else {
            respuesta.put("mensaje", "Cliente no encontrado");
            return ResponseEntity.ok(respuesta);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> actualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {

        Cliente existeCliente = clienteService.buscarClientes_id(id);
        Map<String, Object> respuesta = new HashMap<>();

        if (existeCliente != null) {

            if (cliente.getNombre() != null)
                existeCliente.setNombre(cliente.getNombre());

            if (cliente.getApellidos() != null)
                existeCliente.setApellidos(cliente.getApellidos());

            if (cliente.getEmail() != null)
                existeCliente.setEmail(cliente.getEmail());

            if (cliente.getTelefono() != null)
                existeCliente.setTelefono(cliente.getTelefono());

            if (cliente.getDireccion() != null)
                existeCliente.setDireccion(cliente.getDireccion());

            Cliente guardado = clienteService.guardarClientes(existeCliente);

            respuesta.put("mensaje", "Cliente actualizado correctamente");
            respuesta.put("Cliente", guardado);
            return ResponseEntity.ok(respuesta);

        } else {
            respuesta.put("mensaje", "Cliente no encontrado");
            return ResponseEntity.ok(respuesta);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id){
        Cliente cliente = clienteService.buscarClientes_id(id);
        if (cliente != null){
            clienteService.eleminarCliente(cliente);
            return ResponseEntity.noContent().build(); //204
        }else {
            return ResponseEntity.notFound().build(); //404
        }
    }

}
