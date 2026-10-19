package entities;

import java.io.Serializable;

public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    // First: Attributes
    private Long id;
    private String nombre;
    private String docente;
    private int creditos;
    private double precio;
    private EstadoCurso estado;

    // Second: Constructors
    public Curso() {
        super();
    }

    public Curso(Long id, String nombre, String docente, int creditos, double precio, EstadoCurso estado) {
        this.id = id;
        this.nombre = nombre;
        this.docente = docente;
        this.creditos = creditos;
        this.precio = precio;
        this.estado = estado;
    }

    // Third: Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public EstadoCurso getEstado() {
        return estado;
    }

    public void setEstado(EstadoCurso estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", docente='" + docente + '\'' +
                ", creditos=" + creditos +
                ", precio=" + precio +
                ", estado=" + estado +
                '}';
    }
}
