package cl.example.servlet;

import cl.example.dtos.CrearReservaDTO;
import cl.example.dtos.DetalleReservaDTO;
import cl.example.dtos.ListarReservaDTO;
import cl.example.model.Vuelo;
import cl.example.service.ReservaService;
import cl.example.service.VueloService;
import cl.example.service.implementation.ReservaServiceImpl;
import cl.example.service.implementation.VueloServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ReservaServlet", urlPatterns = {"/reservas"})
public class ReservaServlet extends HttpServlet {

    private final ReservaService reservaService = new ReservaServiceImpl();
    private final VueloService vueloService = new VueloServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recuperar mensaje flash de la sesión (patrón PRG)
        HttpSession session = request.getSession(false);
        if (session != null) {
            String mensajeAlerta = (String) session.getAttribute("mensajeAlerta");
            String tipoAlerta    = (String) session.getAttribute("tipoAlerta");
            if (mensajeAlerta != null) {
                request.setAttribute("mensajeAlerta", mensajeAlerta);
                request.setAttribute("tipoAlerta", tipoAlerta);
                session.removeAttribute("mensajeAlerta");
                session.removeAttribute("tipoAlerta");
            }
        }

        String idParam = request.getParameter("id");

        // Si viene el parámetro id → vista de detalle
        if (idParam != null && !idParam.isEmpty()) {
            try {
                Integer id = Integer.parseInt(idParam);
                DetalleReservaDTO detalle = reservaService.obtenerReservaPorId(id);
                request.setAttribute("detalle", detalle);
            } catch (Exception e) {
                System.err.println("[ReservaServlet] Error al obtener detalle: " + e.getMessage());
                request.setAttribute("errorDetalle", "No se encontró la reserva con ID " + idParam);
            }
            request.getRequestDispatcher("/WEB-INF/views/detalleReserva.jsp").forward(request, response);
            return;
        }

        // Sin parámetro id → listado general
        List<ListarReservaDTO> reservas = new ArrayList<>();
        List<Vuelo> vuelos = new ArrayList<>();

        try {
            reservas = reservaService.obtenerReservas();
            if (reservas == null) reservas = new ArrayList<>();
        } catch (Exception e) {
            System.err.println("[ReservaServlet] Error al obtener reservas: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            vuelos = vueloService.obtenerVuelos();
            if (vuelos == null) vuelos = new ArrayList<>();
        } catch (Exception e) {
            System.err.println("[ReservaServlet] Error al obtener vuelos: " + e.getMessage());
            e.printStackTrace();
        }

        request.setAttribute("reservas", reservas);
        request.setAttribute("vuelos", vuelos);
        request.getRequestDispatcher("/WEB-INF/views/reservas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {
            CrearReservaDTO dto = new CrearReservaDTO();
            dto.setNombreCliente(request.getParameter("nombreCliente"));
            dto.setApellidoCliente(request.getParameter("apellidoCliente"));
            dto.setRutCliente(request.getParameter("rutCliente"));

            String idVueloParam = request.getParameter("idVuelo");
            if (idVueloParam != null && !idVueloParam.isEmpty()) {
                dto.setIdVuelo(Integer.parseInt(idVueloParam));
            }

            String asientoParam = request.getParameter("numeroAsiento");
            if (asientoParam != null && !asientoParam.isEmpty()) {
                dto.setNumeroAsiento(Integer.parseInt(asientoParam));
            }

            dto.setEstadoVuelo(request.getParameter("estadoVuelo"));
            dto.setSeccionCliente(request.getParameter("seccionCliente"));

            Integer idGenerado = reservaService.crearReserva(dto);
            session.setAttribute("mensajeAlerta", "Reserva creada exitosamente con ID #" + idGenerado + ".");
            session.setAttribute("tipoAlerta", "success");

        } catch (Exception e) {
            session.setAttribute("mensajeAlerta", "Error al crear la reserva: " + e.getMessage());
            session.setAttribute("tipoAlerta", "danger");
        }

        response.sendRedirect(request.getContextPath() + "/reservas");
    }
}
