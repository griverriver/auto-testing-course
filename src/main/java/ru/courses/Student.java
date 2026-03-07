package ru.courses;

import lombok.*;
import ru.courses.main.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Student {


    private Integer id;

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private List<Integer> grades = new ArrayList<>();
    private StudentRepository studentRepository;

    public Student(String name, StudentRepository studentRepository) {
        this.name = name;
        this.studentRepository = studentRepository;
    }

    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Integer> getGrades() {
        return new ArrayList<>(grades);
    }

    public void addGrade(int grade) {
        if(studentRepository.checkGrade(grade)){
            grades.add(grade);
        }
    }

    public int rating() {
        int sum = grades.stream()
                .mapToInt(Integer::intValue)
                .sum();
        return studentRepository.rating(sum);
    }
}
//Предположим, что мы изменили класс Студента таким образом, что
//проверка корректности добавляемой оценки выполняется им не самостоятельно, а с
//помощью сервиса checkGrade,
//который получает параметр grade
//и возвращает true или false.
//
//Реализуйте заглушку сервиса и реализуйте тест на проверку того, что
//правильные оценки попадают в список оценок, а неправильные нет.
/*public class Student {
    private String name;
    private List<Integer> grades= new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getGrades() {
        return new ArrayList<>(grades);
    }

    public void addGrade(int grade) {
        if (grade < 2 || grade > 5) {
            throw new IllegalArgumentException(grade + " is wrong grade");
        }
        grades.add(grade);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.grades);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.grades, other.grades);
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", marks=" + grades + '}';
    }
}*/
