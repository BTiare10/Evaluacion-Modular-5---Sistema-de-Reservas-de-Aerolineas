package cl.example.model;

public enum SeccionCliente {

    ECONOMICA("ECONOMICA"),
    EJECUTIVA("EJECUTIVO"),
    VIP("VIP");

    private final String valor;

    SeccionCliente(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static SeccionCliente fromValor(String valor) {
        for (SeccionCliente estado : SeccionCliente.values()) {
            if (estado.getValor().equalsIgnoreCase(valor)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Valor de estado de la secciónCliente no válido: " + valor);
    }

}

