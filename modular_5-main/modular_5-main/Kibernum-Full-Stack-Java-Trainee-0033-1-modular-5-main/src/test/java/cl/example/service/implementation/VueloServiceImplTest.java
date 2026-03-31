package cl.example.service.implementation;

import cl.example.model.Vuelo;
import cl.example.service.VueloService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VueloServiceImplTest {

    private final VueloService vueloService = new VueloServiceImpl();

    @Test
    void testObtenerVuelos() {
        // Act
        List<Vuelo> vuelos = vueloService.obtenerVuelos();
        for (Vuelo vuelo : vuelos) {
            System.out.println(vuelo);
        }

        // Assert
        assertNotNull(vuelos, "La lista de vuelos no debería ser nula");
        assertFalse(vuelos.isEmpty(), "La lista de vuelos no debería estar vacía");
    }

}