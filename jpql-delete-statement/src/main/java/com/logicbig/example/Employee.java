package com.logicbig.example;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private double salary;

    @ManyToOne
    private Department dept;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }


    public long getId() {
        return id;
    }

    public static Employee create(String name, double salary, Department dept) {
        Employee e = new Employee();
        e.setName(name);
        e.setSalary(salary);
        e.setDept(dept);
        return e;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", dept='" + dept + '\'' +
                '}';
    }
}