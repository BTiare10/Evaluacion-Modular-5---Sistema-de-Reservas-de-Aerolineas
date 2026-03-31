package cl.example.model;

public enum EstadoVuelo {

    A_TIEMPO("A TIEMPO"),
    ATRASADO("ATRASADO"),
    CANCELADO("CANCELADO");

    private final String valor;

    EstadoVuelo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static EstadoVuelo fromValor(String valor) {
        for (EstadoVuelo estado : EstadoVuelo.values()) {
            if (estado.getValor().equalsIgnoreCase(valor)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Valor de estado de vuelo no válido: " + valor);
    }
}
