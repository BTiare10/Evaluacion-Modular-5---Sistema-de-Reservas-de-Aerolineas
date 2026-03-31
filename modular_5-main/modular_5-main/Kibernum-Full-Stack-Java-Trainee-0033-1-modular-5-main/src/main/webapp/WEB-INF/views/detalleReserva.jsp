<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cl.example.dtos.DetalleReservaDTO" %>
<%
    DetalleReservaDTO detalle = (DetalleReservaDTO) request.getAttribute("detalle");
    String errorDetalle = (String) request.getAttribute("errorDetalle");
    String estadoVuelo = detalle != null ? detalle.getEstadoVuelo() : "";
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle de Reserva</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f0f4f8; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }

        .navbar-brand span { font-size: 1.4rem; font-weight: 700; }

        .card-detalle {
            border: none;
            border-radius: 12px;
            box-shadow: 0 2px 12px rgba(0,0,0,0.08);
            max-width: 860px;
            margin: 0 auto;
        }

        .card-detalle .card-header {
            background: linear-gradient(135deg, #1a2e4a 0%, #2d5282 100%);
            color: white;
            border-radius: 12px 12px 0 0 !important;
            padding: 1rem 1.5rem;
        }

        .seccion-titulo {
            font-size: 0.78rem;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 0.8px;
            color: #718096;
            margin-bottom: 0.75rem;
            padding-bottom: 0.4rem;
            border-bottom: 2px solid #e2e8f0;
        }

        .dato-label {
            font-size: 0.78rem;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 0.4px;
            color: #a0aec0;
            margin-bottom: 2px;
        }

        .dato-valor {
            font-size: 1rem;
            font-weight: 500;
            color: #2d3748;
        }

        .ruta-grande {
            display: flex;
            align-items: center;
            gap: 12px;
            font-size: 1.3rem;
            font-weight: 700;
        }

        .badge-estado {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            padding: 6px 16px;
            border-radius: 20px;
            font-size: 0.85rem;
            font-weight: 600;
        }

        .flag-dot {
            width: 10px; height: 10px;
            border-radius: 50%;
            display: inline-block;
        }

        .estado-a-tiempo  { background-color: #dcfce7; color: #15803d; border: 1px solid #86efac; }
        .estado-atrasado  { background-color: #fff7ed; color: #c2410c; border: 1px solid #fed7aa; }
        .estado-cancelado { background-color: #fee2e2; color: #b91c1c; border: 1px solid #fca5a5; }
        .dot-a-tiempo  { background-color: #22c55e; }
        .dot-atrasado  { background-color: #f97316; }
        .dot-cancelado { background-color: #ef4444; }
    </style>
</head>
<body>

    <nav class="navbar navbar-dark" style="background: linear-gradient(135deg, #1a2e4a 0%, #2d5282 100%);">
        <div class="container">
            <a class="navbar-brand" href="<%= request.getContextPath() %>/reservas">
                <span>&#9992; Sistema de Reservas</span>
            </a>
        </div>
    </nav>

    <div class="container my-4">

        <!-- BOTÓN VOLVER -->
        <a href="<%= request.getContextPath() %>/reservas" class="btn btn-outline-secondary mb-4">
            &#8592; Volver al listado
        </a>

        <% if (errorDetalle != null) { %>
        <div class="alert alert-danger"><strong>&#9888; Error:</strong> <%= errorDetalle %></div>
        <% } else if (detalle == null) { %>
        <div class="alert alert-warning">No se encontró información para esta reserva.</div>
        <% } else { %>

        <div class="card card-detalle">
            <div class="card-header d-flex justify-content-between align-items-center">
                <span class="fw-semibold fs-5">&#128196; Detalle de Reserva #<%= detalle.getIdReserva() %></span>
                <%
                    String badgeClass = "estado-a-tiempo";
                    String dotClass   = "dot-a-tiempo";
                    if ("ATRASADO".equals(estadoVuelo))  { badgeClass = "estado-atrasado";  dotClass = "dot-atrasado"; }
                    if ("CANCELADO".equals(estadoVuelo)) { badgeClass = "estado-cancelado"; dotClass = "dot-cancelado"; }
                %>
                <span class="badge-estado <%= badgeClass %>">
                    <span class="flag-dot <%= dotClass %>"></span>
                    <%= estadoVuelo %>
                </span>
            </div>
            <div class="card-body p-4">

                <!-- RUTA -->
                <div class="mb-4">
                    <p class="seccion-titulo">&#9992; Vuelo</p>
                    <div class="ruta-grande mb-3">
                        <span class="text-primary"><%= detalle.getCiudadDespegue() %></span>
                        <span class="text-muted">&#8594;</span>
                        <span class="text-dark"><%= detalle.getCiudadAterrizaje() %></span>
                    </div>
                    <div class="row g-3">
                        <div class="col-sm-3">
                            <div class="dato-label">Fecha Salida</div>
                            <div class="dato-valor"><%= detalle.getFechaSalida() %></div>
                        </div>
                        <div class="col-sm-3">
                            <div class="dato-label">Fecha Llegada</div>
                            <div class="dato-valor"><%= detalle.getFechaLlegada() %></div>
                        </div>
                        <div class="col-sm-2">
                            <div class="dato-label">Hora Abordaje</div>
                            <div class="dato-valor"><%= detalle.getHoraAbordaje() %></div>
                        </div>
                        <div class="col-sm-2">
                            <div class="dato-label">Hora Salida</div>
                            <div class="dato-valor"><%= detalle.getHoraSalida() %></div>
                        </div>
                        <div class="col-sm-2">
                            <div class="dato-label">Hora Llegada</div>
                            <div class="dato-valor"><%= detalle.getHoraLlegada() %></div>
                        </div>
                    </div>
                </div>

                <hr class="my-3">

                <!-- PASAJERO -->
                <div class="mb-4">
                    <p class="seccion-titulo">&#128100; Pasajero</p>
                    <div class="row g-3">
                        <div class="col-sm-4">
                            <div class="dato-label">Nombre</div>
                            <div class="dato-valor"><%= detalle.getNombre() %> <%= detalle.getApellido() %></div>
                        </div>
                        <div class="col-sm-4">
                            <div class="dato-label">RUT</div>
                            <div class="dato-valor"><%= detalle.getRut() %></div>
                        </div>
                    </div>
                </div>

                <hr class="my-3">

                <!-- RESERVA -->
                <div>
                    <p class="seccion-titulo">&#127912; Reserva</p>
                    <div class="row g-3">
                        <div class="col-sm-3">
                            <div class="dato-label">N&deg; Asiento</div>
                            <div class="dato-valor"><%= detalle.getNumeroAsiento() %></div>
                        </div>
                        <div class="col-sm-3">
                            <div class="dato-label">Secci&oacute;n</div>
                            <div class="dato-valor"><%= detalle.getSeccionCliente() %></div>
                        </div>
                        <div class="col-sm-4">
                            <div class="dato-label">Estado del Vuelo</div>
                            <div class="dato-valor">
                                <span class="badge-estado <%= badgeClass %>">
                                    <span class="flag-dot <%= dotClass %>"></span>
                                    <%= estadoVuelo %>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <% } %>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/3.2.1/anime.min.js"></script>
    <script>
        window.addEventListener('DOMContentLoaded', function () {

            // Navbar cae desde arriba
            anime({
                targets:    '.navbar',
                translateY: [-70, 0],
                opacity:    [0, 1],
                duration:   600,
                easing:     'easeOutExpo'
            });

            // Botón volver entra desde la izquierda
            anime({
                targets:    'a.btn-outline-secondary',
                translateX: [-30, 0],
                opacity:    [0, 1],
                duration:   500,
                delay:      200,
                easing:     'easeOutExpo'
            });

            // Card principal sube con rebote
            anime({
                targets:    '.card-detalle',
                translateY: [60, 0],
                opacity:    [0, 1],
                duration:   800,
                delay:      300,
                easing:     'easeOutExpo'
            });

            // Header de la card: título y badge entran opuestos
            anime({
                targets:    '.card-detalle .card-header span:first-child',
                translateX: [-30, 0],
                opacity:    [0, 1],
                duration:   600,
                delay:      600,
                easing:     'easeOutExpo'
            });
            anime({
                targets:    '.card-detalle .card-header .badge-estado',
                translateX: [30, 0],
                opacity:    [0, 1],
                scale:      [0.6, 1],
                duration:   600,
                delay:      600,
                easing:     'easeOutBack'
            });

            // Ruta: ciudad origen desde izquierda, destino desde derecha
            anime({
                targets:    '.ruta-grande span:first-child',
                translateX: [-60, 0],
                opacity:    [0, 1],
                duration:   700,
                delay:      800,
                easing:     'easeOutExpo'
            });
            anime({
                targets:    '.ruta-grande span:last-child',
                translateX: [60, 0],
                opacity:    [0, 1],
                duration:   700,
                delay:      800,
                easing:     'easeOutExpo'
            });
            anime({
                targets:    '.ruta-grande span:nth-child(2)',
                opacity:    [0, 1],
                scale:      [0.3, 1],
                duration:   400,
                delay:      1100,
                easing:     'easeOutBack'
            });

            // Secciones de datos aparecen escalonadas
            anime({
                targets:    '.seccion-titulo',
                translateX: [-20, 0],
                opacity:    [0, 1],
                duration:   500,
                delay:      anime.stagger(150, { start: 900 }),
                easing:     'easeOutExpo'
            });

            // Todos los datos aparecen con stagger
            anime({
                targets:    '.dato-label, .dato-valor',
                translateY: [15, 0],
                opacity:    [0, 1],
                duration:   400,
                delay:      anime.stagger(30, { start: 1000 }),
                easing:     'easeOutExpo'
            });

            // Separadores hr se expanden desde el centro
            anime({
                targets:    'hr',
                scaleX:     [0, 1],
                opacity:    [0, 1],
                duration:   600,
                delay:      anime.stagger(200, { start: 900 }),
                easing:     'easeOutExpo'
            });

            // Badge de estado en la sección reserva: pulso final
            anime({
                targets:    '.card-body .badge-estado',
                scale:      [0.7, 1],
                opacity:    [0, 1],
                duration:   500,
                delay:      1400,
                easing:     'easeOutBack'
            });

            // Pulso continuo en el flag dot del header
            anime({
                targets:    '.card-header .flag-dot',
                scale:      [1, 1.6, 1],
                opacity:    [1, 0.6, 1],
                duration:   1800,
                loop:       true,
                easing:     'easeInOutSine'
            });
        });
    </script>
</body>
</html>
