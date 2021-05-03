package jpa.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;


public class StudentServiceTests
{
	private static  StudentService studentService;
	

	@BeforeAll
	static void setup() {
		studentService = new StudentService();
	}
	
	@Test
	void testValidateStudentForNullValues() {
		assertFalse(studentService.validateStudent(null, null));
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings= {"","  ","\n"})
	void testGetStudentByEmailIllegalArgumentException(String text) {
		assertThrows(IllegalArgumentException.class,()->{
			studentService.getStudentByEmail(text);
		});
	}
}
