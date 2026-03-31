package cl.example.model;

public class Cliente {

    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String rut;

    public Cliente() {
    }

    public Cliente(Integer idCliente, String nombre, String apellido, String rut) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
    }

    public Integer getidCliente() {
        return idCliente;
    }

    public void setidCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    @Override
    public String toString() {
        return "Cliente{" +
            "idCliente=" + idCliente +
            ", nombre='" + nombre + '\'' +
            ", apellido='" + apellido + '\'' +
            ", rut='" + rut + '\'' +
            '}';
    }
}
