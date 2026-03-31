package cl.example.service.implementation;

import cl.example.dtos.CrearReservaDTO;
import cl.example.dtos.DetalleReservaDTO;
import cl.example.dtos.ListarReservaDTO;
import cl.example.model.*;
import cl.example.repository.ClienteRepository;
import cl.example.repository.ReservaRepository;
import cl.example.repository.VueloRepository;
import cl.example.repository.implementation.ClienteRepositoryImpl;
import cl.example.repository.implementation.ReservaRepositoryImpl;
import cl.example.repository.implementation.VueloRepositoryImpl;
import cl.example.service.ReservaService;

import java.util.ArrayList;
import java.util.List;

public class ReservaServiceImpl implements ReservaService {

    private final ClienteRepository clienteRepository = new ClienteRepositoryImpl();
    private final ReservaRepository reservaRepository = new ReservaRepositoryImpl();
    private final VueloRepository vueloRepository = new VueloRepositoryImpl();

    @Override
    public Integer crearReserva(CrearReservaDTO crearReservaDTO) {
        validarCrearReserva(crearReservaDTO);

        // Logica para cliente
        Integer clienteReservaId;

        Cliente clienteExistente = clienteRepository.obtenerClientePorRut(crearReservaDTO.getRutCliente());
        if (clienteExistente != null) {
            clienteReservaId = clienteExistente.getidCliente();
        } else {
            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setNombre(crearReservaDTO.getNombreCliente());
            nuevoCliente.setApellido(crearReservaDTO.getApellidoCliente());
            nuevoCliente.setRut(crearReservaDTO.getRutCliente());
            clienteReservaId = clienteRepository.crearCliente(nuevoCliente);
        }

        // Logica de reserva
        Reserva nuevaReserva = new Reserva(
            null,
            clienteReservaId,
            crearReservaDTO.getIdVuelo(),
            crearReservaDTO.getNumeroAsiento(),
            EstadoVuelo.fromValor(crearReservaDTO.getEstadoVuelo()),
            SeccionCliente.fromValor(crearReservaDTO.getSeccionCliente())
        );

        // Creamos la reserva y obtenemos el ID generado
        Integer idReservaGenerada = reservaRepository.crearReserva(nuevaReserva);

        return idReservaGenerada;
    }

    @Override
    public List<ListarReservaDTO> obtenerReservas() {

        List<Reserva> reservasExistentes = reservaRepository.obtenerReservas();
        List<ListarReservaDTO> reservasDTO = new ArrayList<>();

        for (Reserva reserva : reservasExistentes) {
            Vuelo vuelo = vueloRepository.obtenerVueloPorId(reserva.getIdVuelo());
            ListarReservaDTO reservaDTO = new ListarReservaDTO(
                reserva.getIdReserva(),
                vuelo.getCiudadDespegue(),
                vuelo.getCiudadAterrizaje()
            );

            reservasDTO.add(reservaDTO);
        }

        return reservasDTO;
    }

    @Override
    public DetalleReservaDTO obtenerReservaPorId(Integer idReserva) {

        if (idReserva == null) {
            throw new IllegalArgumentException("El ID de la reserva es obligatorio.");
        }

        Reserva reserva = reservaRepository.obtenerReservaPorId(idReserva);
        if (reserva == null) {
            throw new IllegalArgumentException("No se encontró una reserva con el ID proporcionado.");
        }

        // Logica para obtener detalles de la reserva
        DetalleReservaDTO reservaDTO = new DetalleReservaDTO();
        reservaDTO.setIdReserva(idReserva);

        // Obtener información del cliente
        Cliente cliente = clienteRepository.obtenerClientePorId(reserva.getIdCliente());
        reservaDTO.setNombre(cliente.getNombre());
        reservaDTO.setApellido(cliente.getApellido());
        reservaDTO.setRut(cliente.getRut());

        // Obtener información del vuelo
        Vuelo vuelo = vueloRepository.obtenerVueloPorId(reserva.getIdVuelo());
        reservaDTO.setCiudadDespegue(vuelo.getCiudadDespegue());
        reservaDTO.setCiudadAterrizaje(vuelo.getCiudadAterrizaje());
        reservaDTO.setFechaSalida(vuelo.getFechaSalida());
        reservaDTO.setFechaLlegada(vuelo.getFechaLlegada());
        reservaDTO.setHoraAbordaje(vuelo.getHoraAbordaje());
        reservaDTO.setHoraSalida(vuelo.getHoraSalida());
        reservaDTO.setHoraLlegada(vuelo.getHoraLlegada());
        reservaDTO.setNumeroAsiento(reserva.getNumeroAsiento());
        reservaDTO.setEstadoVuelo(reserva.getEstadoVuelo().getValor());
        reservaDTO.setSeccionCliente(reserva.getSeccionCliente().getValor());



        return reservaDTO;
    }

    private void validarCrearReserva(CrearReservaDTO reserva) {
        // INFORMACION DE CLIENTE
        if (reserva.getNombreCliente() == null || reserva.getNombreCliente().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio.");
        }
        if (reserva.getApellidoCliente() == null || reserva.getApellidoCliente().isEmpty()) {
            throw new IllegalArgumentException("El apellido del cliente es obligatorio.");
        }
        if (reserva.getRutCliente() == null || reserva.getRutCliente().isEmpty()) {
            throw new IllegalArgumentException("El RUT del cliente es obligatorio.");
        }

        // INFORMACION DE VUELO
        if (reserva.getIdVuelo() == null) {
            throw new IllegalArgumentException("Vuelo es obligatorio.");
        }
        if (reserva.getNumeroAsiento() <= 0) {
            throw new IllegalArgumentException("El número de asiento debe ser mayor a cero.");
        }
        if (reserva.getEstadoVuelo() == null || reserva.getEstadoVuelo().isEmpty()) {
            throw new IllegalArgumentException("El estado del vuelo es obligatorio.");
        }
        if (reserva.getEstadoVuelo().equalsIgnoreCase("CANCELADO")) {
            throw new IllegalArgumentException("No se pueden crear reservas para vuelos cancelados.");
        }

        // INFORMACION DE RESERVA
        if (reserva.getSeccionCliente() == null || reserva.getSeccionCliente().isEmpty()) {
            throw new IllegalArgumentException("La sección del cliente es obligatoria.");
        }

    }
}
