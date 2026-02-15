//package ru.courses;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//public class StudentTests {
//    @Test
//    public void marksEncapsulationAdd(){
//        List<Integer> grades = List.of(88,5,1);
//        Student stud = new Student("Lexie");
//        stud.getGrades().addAll(grades);
//        int grade = 99;
//        stud.getGrades().add(grade);
//        Assertions.assertFalse(stud.getGrades().contains(grade));
//        Assertions.assertFalse(stud.getGrades().containsAll(grades));
//    }
//
//    @Test
//    public void marksEncapsulationClear(){
//        Student stud = new Student("Lexie");
//        stud.addGrade(5);
//        stud.getGrades().clear();
//        Assertions.assertFalse(stud.getGrades().isEmpty());
//    }
//
//    @Test
//    public void gradesInRange(){
//        List <Integer> rightGrades = List.of(2,3,4,5);
//        Student stud = new Student("Lexie");
//        for (Integer rightGrade : rightGrades) {
//            stud.addGrade(rightGrade);
//        }
//        Assertions.assertEquals(stud.getGrades(), rightGrades);
//    }
//
//    @Test
//    public void gradeTooLow(){
//        List<Integer> wrongGrades = List.of(0, 1, 6, 7);
//        Student stud = new Student("Lexie");
//        for (int grade : wrongGrades) {
//            Assertions.assertThrows(IllegalArgumentException.class, () -> stud.addGrade(grade));
//        }
//    }
//
//    @Test
//    public void equalsSameObject() {
//        Student stud = new Student("Lexie");
//        Assertions.assertEquals(stud, stud);
//    }
//
//    @Test
//    public void equalsNull() {
//        Student stud = new Student("Lexie");
//        Assertions.assertFalse(stud.equals(null));
//    }
//
//    @Test
//    public void equalsDiffClass() {
//        Student stud = new Student("Lexie");
//        String str = "82375";
//        Assertions.assertFalse(stud.equals(str));
//    }
//
//    @Test
//    public void equalsSameData() {
//        Student stud = new Student("Lexie");
//        stud.addGrade(5);
//        Student other = new Student("Lexie");
//        other.addGrade(5);
//        Assertions.assertEquals(stud, other);
//    }
//
//    @Test
//    public void equalsDiffName() {
//        Student stud1 = new Student("Lexie");
//        Student stud2 = new Student("Jordyn");
//        Assertions.assertNotEquals(stud1, stud2);
//    }
//
//    @Test
//    public void equalsDifferentGrades() {
//        Student stud1 = new Student("Lexie");
//        stud1.addGrade(5);
//        Student stud2 = new Student("Lexie");
//        stud2.addGrade(2);
//        Assertions.assertNotEquals(stud1, stud2);
//    }
//
//    @Test
//    public void getNameTest(){
//        Student stud = new Student("Lexie");
//        Assertions.assertEquals("Lexie", stud.getName());
//    }
//
//    @Test
//    public void setNameTest(){
//        Student stud = new Student("Lexie");
//        stud.setName("Mary");
//        Assertions.assertEquals("Mary", stud.getName());
//    }
//
//    @Test
//    void toStringTest() {
//        Student stud = new Student("Lexie");
//        stud.addGrade(5);
//        String expected = "Student{name=Lexie, marks=[5]}";
//        Assertions.assertEquals(expected, stud.toString());
//    }
//
//    @Test
//    void hashCodeTest() {
//        Student stud1 = new Student("Lexie");
//        stud1.addGrade(5);
//        Student stud2 = new Student("Lexie");
//        stud2.addGrade(5);
//        Assertions.assertEquals(stud1.hashCode(), stud2.hashCode());
//    }
//}
//
////Обеспечить не менее чем 85% покрытия тестами класса Student