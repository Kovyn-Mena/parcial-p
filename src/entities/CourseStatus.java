package entities;

public enum CourseStatus {
    OPEN("Abierto"),
    IN_PROGRESS("En curso"),
    FINISHED("Finalizado"),
    CANCELLED("Cancelado");

    private final String label;

    CourseStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
