package web.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private int age;

    @Column
    private String sex;

    @Column
    private String location;

    @Column
    private short salary;

    @Column
    private float height;

    public User() {
    }

    public User(String name, String surname, int age, String sex, String location,  short salary, float height) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        this.location = location;
        this.salary = salary;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public short getSalary() {
        return salary;
    }

    public void setSalary(short salary) {
        this.salary = salary;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User { " +
               "id = " + id +
               ", name = '" + name + '\'' +
               ", surname = '" + surname + '\'' +
               ", age = " + age +
               ", sex = " + sex +
               ", location = '" + location + '\'' +
               ", salary = " + salary +
               ", height = " + height +
               " }";
    }
}
