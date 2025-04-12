package senati.rrhh.service;

import senati.rrhh.model.Cliente;


import java.util.List;

public interface IClienteService {

    public List<Cliente> listarClientes();
    public Cliente buscarClientes_id(Integer cliente_id);
    public Cliente guardarClientes(Cliente cliente);
    public void eleminarCliente(Cliente cliente);
}
