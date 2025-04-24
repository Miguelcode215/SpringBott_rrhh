package senati.rrhh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senati.rrhh.model.Cliente;
import senati.rrhh.model.Empleado;
import senati.rrhh.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService implements IClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarClientes_id(Integer cliente_id) {
        Cliente cliente = clienteRepository.findById(cliente_id).orElse(null);
        return cliente;
    }

    @Override
    public Cliente guardarClientes(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void eleminarCliente(Cliente cliente) {
        clienteRepository.delete(cliente);
    }
}
