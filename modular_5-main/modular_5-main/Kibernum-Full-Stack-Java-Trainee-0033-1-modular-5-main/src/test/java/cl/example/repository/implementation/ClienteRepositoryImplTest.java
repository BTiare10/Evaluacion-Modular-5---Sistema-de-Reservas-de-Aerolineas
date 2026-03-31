package cl.example.repository.implementation;

import cl.example.model.Cliente;
import cl.example.repository.ClienteRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteRepositoryImplTest {

    private final ClienteRepository clienteRepository = new ClienteRepositoryImpl();

    @Test
    void crearCliente() {
        Cliente cliente = new Cliente(
            null,
            "Juan",
            "Pérez",
            "12345678-9"
        );

        Integer id_cliente = clienteRepository.crearCliente(cliente);
        assertNotEquals(0, id_cliente);
    }

    @Test
    void obtenerClientes() {
        List<Cliente> clientes = clienteRepository.obtenerClientes();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }

        assertNotNull(clientes);
    }

    @Test
    void obtenerClientesVacio() {
        Cliente cliente = new Cliente(
            null,
            "Test",
            "Test",
            "00000000-0"
        );
        Integer id_cliente = clienteRepository.crearCliente(cliente);

        Cliente clienteRecuperado = clienteRepository.obtenerClientePorId(id_cliente);
        System.out.println(clienteRecuperado);
        assertNotNull(clienteRecuperado);
    }
}