package com.example.security_ex.model;

public class Student {
    private int id;
    private String name;
    private int masks;

    public Student() {
    }

    public Student(int masks, String name, int id) {
        this.masks = masks;
        this.name = name;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMasks(int masks) {
        this.masks = masks;
    }

    public int getMasks() {
        return masks;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", masks=" + masks +
                '}';
    }
}
