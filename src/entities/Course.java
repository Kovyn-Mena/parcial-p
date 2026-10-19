package entities;

import java.io.Serializable;

public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    // First: Attributes
    private Long id;
    private String name;
    private String teacher;
    private int credits;
    private double price;
    private CourseStatus status;

    // Second: Constructors
    public Course() {
        super();
    }

    public Course(Long id, String name, String teacher, int credits, double price, CourseStatus status) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.credits = credits;
        this.price = price;
        this.status = status;
    }

    // Third: Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", credits=" + credits +
                ", price=" + price +
                ", status=" + status +
                '}';
    }
}
