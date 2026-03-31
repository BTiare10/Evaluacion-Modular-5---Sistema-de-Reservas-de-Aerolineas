package cl.example.repository;

import cl.example.model.Vuelo;

import java.util.List;

public interface VueloRepository {
    List<Vuelo> obtenerVuelos();
    Vuelo obtenerVueloPorId(Integer idVuelo);
}
