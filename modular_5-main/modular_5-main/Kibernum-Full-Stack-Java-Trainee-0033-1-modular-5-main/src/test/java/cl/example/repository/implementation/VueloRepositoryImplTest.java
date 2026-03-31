package cl.example.repository.implementation;

import cl.example.model.Vuelo;
import cl.example.repository.VueloRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VueloRepositoryImplTest {

    private final VueloRepository vueloRepository = new VueloRepositoryImpl();

    @Test
    void obtenerVuelos() {
        List<Vuelo> vuelos = vueloRepository.obtenerVuelos();
        for (Vuelo vuelo : vuelos) {
            System.out.println("vuelo: " + vuelo);
        }

        assertNotNull(vuelos);
    }

    @Test
    void obtenerVueloPorId() {
        Vuelo vuelo = vueloRepository.obtenerVueloPorId(1);
        System.out.println("vuelo: " + vuelo);

        assertNotNull(vuelo);
    }
}