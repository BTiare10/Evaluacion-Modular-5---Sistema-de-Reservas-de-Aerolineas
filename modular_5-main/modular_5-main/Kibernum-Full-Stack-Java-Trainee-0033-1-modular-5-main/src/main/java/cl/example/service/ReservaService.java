package cl.example.service;

import cl.example.dtos.CrearReservaDTO;
import cl.example.dtos.DetalleReservaDTO;
import cl.example.dtos.ListarReservaDTO;

import java.util.List;

public interface ReservaService {

    Integer crearReserva(CrearReservaDTO crearReservaDTO);
    List<ListarReservaDTO> obtenerReservas();
    DetalleReservaDTO obtenerReservaPorId(Integer idReserva);

}
