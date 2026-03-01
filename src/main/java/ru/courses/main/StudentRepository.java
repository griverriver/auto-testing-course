package ru.courses.main;

public interface StudentRepository {
    boolean checkGrade(int grade);
    int rating(int gradesSum);
}
