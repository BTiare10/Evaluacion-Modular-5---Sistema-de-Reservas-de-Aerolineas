package cl.example.service.implementation;

import cl.example.model.Vuelo;
import cl.example.repository.VueloRepository;
import cl.example.repository.implementation.VueloRepositoryImpl;
import cl.example.service.VueloService;

import java.util.List;

public class VueloServiceImpl implements VueloService {

    private final VueloRepository vueloRepository = new VueloRepositoryImpl();

    @Override
    public List<Vuelo> obtenerVuelos() {
        return vueloRepository.obtenerVuelos();
    }
}
