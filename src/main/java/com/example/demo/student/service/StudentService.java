package com.example.demo.student.service;

import com.example.demo.student.model.entity.StudentModel;
import com.example.demo.student.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final IStudentRepository studentRepository;
    @Autowired
    public StudentService(IStudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    public List<StudentModel> getStudents()
    {
      return studentRepository.findAll();
    }

    public void addNewStudent(StudentModel  student)
    {
        Optional<StudentModel> studentOptional = studentRepository.findByEmail(student.getEmail());
        if(studentOptional.isPresent())
        {
            throw new IllegalStateException("Email taken");
        }

        studentRepository.saveAndFlush(student);
    }

    public void deleteStudent(Long studentId)
    {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists)
        {
            throw new IllegalStateException("student Id" + studentId + "Does not exist");
        }
        studentRepository.deleteById(studentId);
    }


}
