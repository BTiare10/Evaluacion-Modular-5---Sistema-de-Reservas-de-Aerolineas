package cl.example.repository.implementation;

import cl.example.model.*;
import cl.example.repository.ClienteRepository;
import cl.example.repository.ReservaRepository;
import cl.example.repository.VueloRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservaRepositoryImplTest {

    private final ReservaRepository reservaRepository = new ReservaRepositoryImpl();
    private final ClienteRepository clienteRepository = new ClienteRepositoryImpl();
    private final VueloRepository vueloRepository = new VueloRepositoryImpl();

    @Test
    void crearReserva() {
        Cliente cliente = new Cliente(
            null,
            "Juan",
            "Pérez",
            "12345678-9"
        );

        List<Vuelo> vuelos = vueloRepository.obtenerVuelos();

        Integer clienteId = clienteRepository.crearCliente(cliente);
        Vuelo primerVuelo = vuelos.get(0);

        EstadoVuelo estadoVuelo = EstadoVuelo.A_TIEMPO;
        SeccionCliente seccionCliente = SeccionCliente.VIP;

        Reserva reserva = new Reserva(
            null,
            clienteId,
            primerVuelo.getIdVuelo(),
            12,
            estadoVuelo,
            seccionCliente
        );

        Integer reservaId = reservaRepository.crearReserva(reserva);

        assertNotNull(reservaId);
    }

    @Test
    void obtenerReservas() {
        List<Reserva> reservas = reservaRepository.obtenerReservas();
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }

        assertNotNull(reservas);
        assertFalse(reservas.isEmpty());
    }

    @Test
    void obtenerReservaPorId() {
        Reserva reserva = reservaRepository.obtenerReservaPorId(1);
        System.out.println(reserva);

        assertNotNull(reserva);
    }

}