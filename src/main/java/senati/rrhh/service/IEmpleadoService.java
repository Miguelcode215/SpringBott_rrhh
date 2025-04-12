package senati.rrhh.service;

import senati.rrhh.model.Empleado;

import java.util.List;

public interface IEmpleadoService {

    public List<Empleado> listarEmpleados();
    public Empleado buscarEmpleado_id(Integer empleado_id);
    public Empleado guardarEmpleado(Empleado empleado);
    public void eleminarEmpleado( Empleado empleado);
}
