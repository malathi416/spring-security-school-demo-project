package com.ramesh.springsecuritydemo.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Classes extends AbstractEntity {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 - 50 characters")
    private String className;

    @OneToMany(mappedBy = "classes")
    private final List<Teacher> teachers = new ArrayList<>();

    @OneToMany(mappedBy = "classes")
    private final List<Student> students = new ArrayList<>();

    public Classes() {

    }

    public Classes(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Student> getStudents() {
        return students;
    }


    @Override
    public String toString() {
        return className;
    }
}
