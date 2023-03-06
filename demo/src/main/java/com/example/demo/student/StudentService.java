package com.example.demo.student;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository){
		this.studentRepository = studentRepository;
	}
    
    public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
		if(studentByEmail.isPresent()){
			throw new IllegalStateException("email Taken");
		}

		studentRepository.save(student);

		System.out.println(student);
	}



	public void deleteStudent(Long studentId) {
		Boolean exists = studentRepository.existsById(studentId);
		if(!exists){
			throw new IllegalStateException("Student with id " + studentId + " doesn't exist");
		}
		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
				"Student with id " + studentId + " doesn't exist"
		));
		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
			student.setName(name);
		}
		if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
			Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
			if (studentByEmail.isPresent()){
				throw new IllegalStateException("This email is already taken");
			}
			student.setEmail(email);
		}
	}
}
