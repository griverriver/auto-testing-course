package ru.courses;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.courses.main.StudentRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudentMockTest {
    @Mock
    StudentRepository studentRepository;
    Student student;

    @BeforeEach
    void setUp() {
        student = new Student("Lexie", studentRepository);
    }

    @Test
    public void testAddGrades() {
        Mockito.when(studentRepository.checkGrade(5)).thenReturn(true);
        Mockito.when(studentRepository.checkGrade(100)).thenReturn(false);

        student.addGrade(5);
        student.addGrade(100);

        assertEquals(1, student.getGrades().size());
        assertTrue(student.getGrades().contains(5));
        assertFalse(student.getGrades().contains(100));

        verify(studentRepository).checkGrade(5);
        verify(studentRepository).checkGrade(100);
    }

    @Test
    public void testRating() {
        Mockito.when(studentRepository.checkGrade(5)).thenReturn(true);
        Mockito.when(studentRepository.checkGrade(4)).thenReturn(true);
        Mockito.when(studentRepository.rating(9)).thenReturn(90);

        student.addGrade(5);
        student.addGrade(4);

        int actualRating = student.rating();

        assertEquals(90, actualRating);
        verify(studentRepository).rating(9);
    }
}
