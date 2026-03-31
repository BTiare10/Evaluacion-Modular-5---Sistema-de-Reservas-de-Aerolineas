package cl.example.service.implementation;

import cl.example.dtos.CrearReservaDTO;
import cl.example.dtos.DetalleReservaDTO;
import cl.example.dtos.ListarReservaDTO;
import cl.example.service.ReservaService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservaServiceImplTest {

    private final ReservaService reservaService = new ReservaServiceImpl();

    @Test
    void testCrearReserva() {
        // Creamos el DTO de reserva
        CrearReservaDTO crearReservaDTO = new CrearReservaDTO();

        // Llenamos el DTO con datos de prueba
        crearReservaDTO.setNombreCliente("Abdon");
        crearReservaDTO.setApellidoCliente("Sandoval");
        crearReservaDTO.setRutCliente("12345478-9");
        crearReservaDTO.setIdVuelo(1);
        crearReservaDTO.setNumeroAsiento(15);
        crearReservaDTO.setEstadoVuelo("A TIEMPO");
        crearReservaDTO.setSeccionCliente("VIP");

        Integer idReservaGenerada = reservaService.crearReserva(crearReservaDTO);

        assertNotNull(idReservaGenerada);
        DetalleReservaDTO detalleReserva = reservaService.obtenerReservaPorId(idReservaGenerada);
        System.out.println(detalleReserva);
        assertNotNull(detalleReserva);
    }

    @Test
    void testObtenerReservas() {
        List<ListarReservaDTO> reservas = reservaService.obtenerReservas();

        for (ListarReservaDTO reserva : reservas) {
            System.out.println(reserva);
        }
        assertNotNull(reservas);
    }

    @Test
    void testObtenerReservaPorId() {
        DetalleReservaDTO detalleReserva = reservaService.obtenerReservaPorId(1);
        System.out.println(detalleReserva);

        assertNotNull(detalleReserva);
    }
}