package cl.example.repository;

import cl.example.model.Cliente;

import java.util.List;

public interface ClienteRepository {

    Integer crearCliente(Cliente cliente);
    List<Cliente> obtenerClientes();
    Cliente obtenerClientePorId(Integer idCliente);
    Cliente obtenerClientePorRut(String rutCliente);

}
