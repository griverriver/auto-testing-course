package ru.courses.main;

import ru.courses.Student;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("Mary", null);
        student.addGrade(4);
        int grade = 99;
        student.getGrades().add(grade);
        System.out.println(student.getGrades());
    }
}
