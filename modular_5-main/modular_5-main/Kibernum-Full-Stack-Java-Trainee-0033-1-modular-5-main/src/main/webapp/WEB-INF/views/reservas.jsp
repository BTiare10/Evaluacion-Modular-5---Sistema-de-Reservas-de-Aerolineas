<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cl.example.dtos.ListarReservaDTO" %>
<%@ page import="cl.example.model.Vuelo" %>
<%@ page import="java.util.List" %>
<%
    List<ListarReservaDTO> reservas = (List<ListarReservaDTO>) request.getAttribute("reservas");
    List<Vuelo> vuelos = (List<Vuelo>) request.getAttribute("vuelos");
    String mensajeAlerta = (String) request.getAttribute("mensajeAlerta");
    String tipoAlerta    = (String) request.getAttribute("tipoAlerta");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Reservas de Vuelos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f0f4f8;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .navbar-brand span {
            font-size: 1.4rem;
            font-weight: 700;
            letter-spacing: 0.5px;
        }

        .page-title {
            font-size: 1.6rem;
            font-weight: 600;
            color: #1a2e4a;
        }

        .card-reservas {
            border: none;
            border-radius: 12px;
            box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
        }

        .card-reservas .card-header {
            background: linear-gradient(135deg, #1a2e4a 0%, #2d5282 100%);
            color: white;
            border-radius: 12px 12px 0 0 !important;
            padding: 1rem 1.5rem;
        }

        #formulario-reserva {
            display: none;
            border: none;
            border-radius: 12px;
            box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
        }

        #formulario-reserva .card-header {
            background: linear-gradient(135deg, #065f46 0%, #059669 100%);
            color: white;
            border-radius: 12px 12px 0 0 !important;
            padding: 1rem 1.5rem;
        }

        .table thead th {
            background-color: #edf2f7;
            color: #2d3748;
            font-weight: 600;
            font-size: 0.85rem;
            text-transform: uppercase;
            letter-spacing: 0.5px;
            border-bottom: 2px solid #cbd5e0;
        }

        .table tbody tr:hover {
            background-color: #f7fafc;
        }

        .badge-id {
            background-color: #e2e8f0;
            color: #2d3748;
            font-weight: 600;
            padding: 0.35em 0.75em;
            border-radius: 6px;
            font-size: 0.85rem;
        }

        .ruta-vuelo {
            display: flex;
            align-items: center;
            gap: 8px;
            font-weight: 500;
        }

        .ruta-vuelo .flecha { color: #718096; }

        .flag-estado {
            display: inline-block;
            width: 12px;
            height: 12px;
            border-radius: 50%;
            flex-shrink: 0;
        }

        .flag-a-tiempo  { background-color: #22c55e; box-shadow: 0 0 0 3px rgba(34,197,94,0.25); }
        .flag-atrasado  { background-color: #f97316; box-shadow: 0 0 0 3px rgba(249,115,22,0.25); }
        .flag-cancelado { background-color: #ef4444; box-shadow: 0 0 0 3px rgba(239,68,68,0.25); }

        #estado-indicador {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            padding: 6px 14px;
            border-radius: 20px;
            font-size: 0.85rem;
            font-weight: 600;
            min-width: 130px;
            transition: all 0.2s ease;
        }

        #estado-indicador.oculto { display: none !important; }

        .estado-verde   { background-color: #dcfce7; color: #15803d; border: 1px solid #86efac; }
        .estado-naranja { background-color: #fff7ed; color: #c2410c; border: 1px solid #fed7aa; }
        .estado-rojo    { background-color: #fee2e2; color: #b91c1c; border: 1px solid #fca5a5; }

        #aviso-cancelado { display: none; }

        .seccion-titulo {
            font-size: 0.8rem;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 0.8px;
            color: #718096;
            margin-bottom: 0.75rem;
            padding-bottom: 0.5rem;
            border-bottom: 2px solid #e2e8f0;
        }

        .empty-state {
            text-align: center;
            padding: 3rem 1rem;
            color: #a0aec0;
        }

        .empty-state .empty-icon { font-size: 3rem; margin-bottom: 1rem; }

        #btn-submit:disabled { cursor: not-allowed; opacity: 0.6; }
    </style>
</head>
<body>

    <!-- NAVBAR -->
    <nav class="navbar navbar-dark" style="background: linear-gradient(135deg, #1a2e4a 0%, #2d5282 100%);">
        <div class="container">
            <a class="navbar-brand" href="<%= request.getContextPath() %>/reservas">
                <span>&#9992; Sistema de Reservas</span>
            </a>
        </div>
    </nav>

    <div class="container my-4">

        <!-- ALERTA FLASH -->
        <% if (mensajeAlerta != null) { %>
        <div class="alert alert-<%= tipoAlerta %> alert-dismissible fade show" role="alert">
            <strong>
                <% if ("success".equals(tipoAlerta)) { %>&#10003; &Eacute;xito:<% } else { %>&#9888; Atenci&oacute;n:<% } %>
            </strong>
            <%= mensajeAlerta %>
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <% } %>

        <!-- CABECERA + BOTÓN -->
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1 class="page-title mb-0">&#128196; Listado de Reservas</h1>
            <button id="btn-nueva-reserva" class="btn btn-success px-4 py-2 fw-semibold" onclick="toggleFormulario()">
                + Nueva Reserva
            </button>
        </div>

        <!-- FORMULARIO NUEVA RESERVA (oculto por defecto) -->
        <div id="formulario-reserva" class="card mb-4">
            <div class="card-header d-flex justify-content-between align-items-center">
                <span class="fw-semibold fs-5">&#128221; Nueva Reserva</span>
                <button type="button" class="btn-close btn-close-white" onclick="toggleFormulario()"></button>
            </div>
            <div class="card-body p-4">
                <form method="POST" action="<%= request.getContextPath() %>/reservas" id="form-reserva"
                      onsubmit="return validarFormulario()">

                    <!-- DATOS DEL PASAJERO -->
                    <p class="seccion-titulo">&#128100; Datos del Pasajero</p>
                    <div class="row g-3 mb-4">
                        <div class="col-md-4">
                            <label for="nombreCliente" class="form-label fw-semibold">
                                Nombre <span class="text-danger">*</span>
                            </label>
                            <input type="text" class="form-control" id="nombreCliente"
                                   name="nombreCliente" placeholder="Ej: Juan" required>
                        </div>
                        <div class="col-md-4">
                            <label for="apellidoCliente" class="form-label fw-semibold">
                                Apellido <span class="text-danger">*</span>
                            </label>
                            <input type="text" class="form-control" id="apellidoCliente"
                                   name="apellidoCliente" placeholder="Ej: P&eacute;rez" required>
                        </div>
                        <div class="col-md-4">
                            <label for="rutCliente" class="form-label fw-semibold">
                                RUT <span class="text-danger">*</span>
                            </label>
                            <input type="text" class="form-control" id="rutCliente"
                                   name="rutCliente" placeholder="Ej: 12345678-9"
                                   maxlength="10" pattern="^\d{7,8}-[\dkK]$"
                                   title="Formato: sin puntos, con guión (ej: 12345678-9)"
                                   required>
                        </div>
                    </div>

                    <!-- DATOS DEL VUELO -->
                    <p class="seccion-titulo">&#9992; Datos del Vuelo</p>
                    <div class="row g-3 mb-4">
                        <div class="col-md-6">
                            <label for="idVuelo" class="form-label fw-semibold">
                                Vuelo disponible <span class="text-danger">*</span>
                            </label>
                            <select class="form-select" id="idVuelo" name="idVuelo" required>
                                <option value="">-- Seleccione un vuelo --</option>
                                <% if (vuelos != null) { for (Vuelo v : vuelos) { %>
                                <option value="<%= v.getIdVuelo() %>">
                                    Vuelo #<%= v.getIdVuelo() %>
                                    &nbsp;&mdash;&nbsp;
                                    <%= v.getCiudadDespegue() %> &rarr; <%= v.getCiudadAterrizaje() %>
                                    <% if (v.getFechaSalida() != null) { %>
                                        &nbsp;(<%= v.getFechaSalida() %>)
                                    <% } %>
                                </option>
                                <% } } %>
                                <% if (vuelos == null || vuelos.isEmpty()) { %>
                                <option disabled>No hay vuelos disponibles</option>
                                <% } %>
                            </select>
                        </div>

                        <div class="col-md-6">
                            <label for="estadoVuelo" class="form-label fw-semibold">
                                Estado del Vuelo <span class="text-danger">*</span>
                            </label>
                            <div class="d-flex align-items-center gap-3 flex-wrap">
                                <select class="form-select" id="estadoVuelo" name="estadoVuelo"
                                        onchange="actualizarEstado(this.value)" required style="max-width: 220px;">
                                    <option value="">-- Estado --</option>
                                    <option value="A TIEMPO">&#11044; A TIEMPO</option>
                                    <option value="ATRASADO">&#11044; ATRASADO</option>
                                    <option value="CANCELADO">&#11044; CANCELADO</option>
                                </select>
                                <span id="estado-indicador" class="oculto">
                                    <span id="estado-flag" class="flag-estado"></span>
                                    <span id="estado-texto"></span>
                                </span>
                            </div>
                            <div id="aviso-cancelado"
                                 class="mt-2 d-flex align-items-center gap-2 text-danger small fw-semibold">
                                <span>&#128683;</span>
                                <span>No se pueden crear reservas para vuelos CANCELADOS.</span>
                            </div>
                        </div>
                    </div>

                    <!-- DATOS DE LA RESERVA -->
                    <p class="seccion-titulo">&#127912; Datos de la Reserva</p>
                    <div class="row g-3 mb-4">
                        <div class="col-md-4">
                            <label for="numeroAsiento" class="form-label fw-semibold">
                                N&deg; de Asiento <span class="text-danger">*</span>
                            </label>
                            <input type="number" class="form-control" id="numeroAsiento"
                                   name="numeroAsiento" min="1" placeholder="Ej: 12" required>
                        </div>
                        <div class="col-md-4">
                            <label for="seccionCliente" class="form-label fw-semibold">
                                Secci&oacute;n <span class="text-danger">*</span>
                            </label>
                            <select class="form-select" id="seccionCliente" name="seccionCliente" required>
                                <option value="">-- Seleccione secci&oacute;n --</option>
                                <option value="ECONOMICA">Econ&oacute;mica</option>
                                <option value="EJECUTIVO">Ejecutiva</option>
                                <option value="VIP">VIP</option>
                            </select>
                        </div>
                    </div>

                    <!-- BOTONES -->
                    <div class="d-flex gap-3 justify-content-end pt-3 border-top">
                        <button type="button" class="btn btn-outline-secondary px-4"
                                onclick="toggleFormulario()">
                            Cancelar
                        </button>
                        <button type="submit" id="btn-submit" class="btn btn-success px-5 fw-semibold">
                            &#10003; Guardar Reserva
                        </button>
                    </div>

                </form>
            </div>
        </div>

        <!-- TABLA DE RESERVAS -->
        <div class="card card-reservas">
            <div class="card-header d-flex justify-content-between align-items-center">
                <span class="fw-semibold fs-5">Reservas registradas</span>
                <span class="badge bg-light text-dark fs-6">
                    Total: <%= reservas != null ? reservas.size() : 0 %>
                </span>
            </div>
            <div class="card-body p-0">
                <% if (reservas == null || reservas.isEmpty()) { %>
                <div class="empty-state">
                    <div class="empty-icon">&#9992;</div>
                    <p class="fs-5 fw-semibold text-secondary">No hay reservas registradas</p>
                    <p class="text-muted">Haz clic en <strong>Nueva Reserva</strong> para crear la primera.</p>
                </div>
                <% } else { %>
                <div class="table-responsive">
                    <table class="table table-hover align-middle mb-0">
                        <thead>
                            <tr>
                                <th class="ps-4" style="width: 100px;">ID</th>
                                <th>Ruta</th>
                                <th class="text-center" style="width: 180px;">Origen</th>
                                <th class="text-center" style="width: 180px;">Destino</th>
                                <th class="text-center pe-4" style="width: 120px;">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (ListarReservaDTO r : reservas) { %>
                            <tr>
                                <td class="ps-4">
                                    <span class="badge-id">#<%= r.getIdReserva() %></span>
                                </td>
                                <td>
                                    <div class="ruta-vuelo">
                                        <span class="text-primary fw-semibold"><%= r.getCiudadDespegue() %></span>
                                        <span class="flecha">&#8594;</span>
                                        <span class="text-dark"><%= r.getCiudadAterrizaje() %></span>
                                    </div>
                                </td>
                                <td class="text-center text-muted"><%= r.getCiudadDespegue() %></td>
                                <td class="text-center text-muted"><%= r.getCiudadAterrizaje() %></td>
                                <td class="text-center pe-4">
                                    <a href="<%= request.getContextPath() %>/reservas?id=<%= r.getIdReserva() %>"
                                       class="btn btn-sm btn-outline-primary">
                                        Ver detalle
                                    </a>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
                <% } %>
            </div>
        </div>

    </div><!-- /container -->

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/animejs/3.2.1/anime.min.js"></script>
    <script>
        // ── ANIMACIÓN DE ENTRADA ──────────────────────────────────────────
        window.addEventListener('DOMContentLoaded', function () {

            // Navbar desliza desde arriba
            anime({
                targets: '.navbar',
                translateY: [-60, 0],
                opacity:    [0, 1],
                duration:   600,
                easing:     'easeOutExpo'
            });

            // Título + botón entran desde lados opuestos
            anime({
                targets: '.page-title',
                translateX: [-50, 0],
                opacity:    [0, 1],
                duration:   700,
                delay:      200,
                easing:     'easeOutExpo'
            });
            anime({
                targets: '#btn-nueva-reserva',
                translateX: [50, 0],
                opacity:    [0, 1],
                duration:   700,
                delay:      200,
                easing:     'easeOutExpo'
            });

            // Alerta flash (si existe)
            anime({
                targets: '.alert',
                translateY: [-20, 0],
                opacity:    [0, 1],
                duration:   500,
                delay:      300,
                easing:     'easeOutBack'
            });

            // Card de reservas sube con rebote
            anime({
                targets: '.card-reservas',
                translateY: [50, 0],
                opacity:    [0, 1],
                duration:   800,
                delay:      350,
                easing:     'easeOutExpo'
            });

            // Filas de la tabla entran escalonadas
            anime({
                targets: 'tbody tr',
                translateX: [-30, 0],
                opacity:    [0, 1],
                duration:   500,
                delay:      anime.stagger(80, { start: 500 }),
                easing:     'easeOutExpo'
            });

            // Reabre el formulario si hubo error en el POST
            <% if ("danger".equals(tipoAlerta)) { %>
            toggleFormulario();
            <% } %>
        });

        // ── TOGGLE FORMULARIO CON ANIME.JS ───────────────────────────────
        let formAbierto = false;

        function toggleFormulario() {
            const form     = document.getElementById('formulario-reserva');
            const btnAbrir = document.getElementById('btn-nueva-reserva');

            if (!formAbierto) {
                form.style.display  = 'block';
                form.style.opacity  = '0';
                form.style.transform = 'translateY(-20px) scaleY(0.95)';
                formAbierto = true;
                btnAbrir.textContent = '\u2715 Cerrar Formulario';
                btnAbrir.classList.replace('btn-success', 'btn-secondary');

                anime({
                    targets:   form,
                    opacity:   [0, 1],
                    translateY: [-20, 0],
                    scaleY:    [0.95, 1],
                    duration:  500,
                    easing:    'easeOutExpo',
                    complete:  () => form.scrollIntoView({ behavior: 'smooth', block: 'start' })
                });

                // Entran los campos del formulario escalonados
                anime({
                    targets:  '#form-reserva .form-control, #form-reserva .form-select',
                    translateX: [-15, 0],
                    opacity:  [0, 1],
                    duration: 400,
                    delay:    anime.stagger(40, { start: 150 }),
                    easing:   'easeOutExpo'
                });

            } else {
                formAbierto = false;
                btnAbrir.textContent = '+ Nueva Reserva';
                btnAbrir.classList.replace('btn-secondary', 'btn-success');

                anime({
                    targets:   form,
                    opacity:   [1, 0],
                    translateY: [0, -15],
                    scaleY:    [1, 0.97],
                    duration:  350,
                    easing:    'easeInExpo',
                    complete:  () => {
                        form.style.display = 'none';
                        resetFormulario();
                    }
                });
            }
        }

        // ── ESTADO DEL VUELO CON ANIMACIÓN ───────────────────────────────
        function actualizarEstado(valor) {
            const indicador = document.getElementById('estado-indicador');
            const flag      = document.getElementById('estado-flag');
            const texto     = document.getElementById('estado-texto');
            const aviso     = document.getElementById('aviso-cancelado');
            const btnSubmit = document.getElementById('btn-submit');

            indicador.classList.remove('estado-verde', 'estado-naranja', 'estado-rojo');
            flag.classList.remove('flag-a-tiempo', 'flag-atrasado', 'flag-cancelado');

            if (!valor) {
                anime({ targets: indicador, opacity: [1, 0], scale: [1, 0.8], duration: 200, easing: 'easeInExpo',
                        complete: () => indicador.classList.add('oculto') });
                aviso.style.display = 'none';
                btnSubmit.disabled  = false;
                return;
            }

            indicador.classList.remove('oculto');
            texto.textContent = valor;

            if (valor === 'A TIEMPO') {
                flag.classList.add('flag-a-tiempo');
                indicador.classList.add('estado-verde');
                aviso.style.display = 'none';
                btnSubmit.disabled  = false;
            } else if (valor === 'ATRASADO') {
                flag.classList.add('flag-atrasado');
                indicador.classList.add('estado-naranja');
                aviso.style.display = 'none';
                btnSubmit.disabled  = false;
            } else if (valor === 'CANCELADO') {
                flag.classList.add('flag-cancelado');
                indicador.classList.add('estado-rojo');
                btnSubmit.disabled = true;

                // Aviso con shake
                aviso.style.display = 'flex';
                anime({ targets: aviso, translateX: [-8, 8, -6, 6, -3, 3, 0],
                        duration: 500, easing: 'easeInOutSine' });

                // Pulso de peligro en el botón submit
                anime({ targets: btnSubmit, backgroundColor: ['#dc3545', '#6c757d'],
                        duration: 400, easing: 'easeOutExpo' });
            }

            // Badge de estado aparece con rebote
            anime({ targets: indicador, scale: [0.5, 1], opacity: [0, 1],
                    duration: 400, easing: 'easeOutBack' });

            // Pulso del flag dot
            anime({ targets: flag, scale: [1, 1.8, 1], opacity: [0.5, 1],
                    duration: 600, easing: 'easeOutElastic(1, .5)' });
        }

        function validarFormulario() {
            const estado = document.getElementById('estadoVuelo').value;
            if (estado === 'CANCELADO') {
                // Shake en el select de estado
                anime({ targets: '#estadoVuelo', translateX: [-10, 10, -8, 8, -4, 4, 0],
                        duration: 500, easing: 'easeInOutSine' });
                return false;
            }
            return true;
        }

        function resetFormulario() {
            document.getElementById('form-reserva').reset();
            actualizarEstado('');
        }
    </script>
</body>
</html>