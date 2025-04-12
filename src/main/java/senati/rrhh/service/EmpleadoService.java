package senati.rrhh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import senati.rrhh.model.Empleado;
import senati.rrhh.repository.EmpleadoRepository;

import java.util.List;

@Service
public class EmpleadoService implements IEmpleadoService{

    @Autowired
    private EmpleadoRepository repository;

    @Override
    public List<Empleado> listarEmpleados() {
        return repository.findAll();
    }

    @Override
    public Empleado buscarEmpleado_id(Integer empleado_id) {
        Empleado empleado = repository.findById(empleado_id).orElse(null);
        return empleado;
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return repository.save(empleado);
    }

    @Override
    public void eleminarEmpleado(Empleado empleado) {
        repository.delete(empleado);
    }
}
