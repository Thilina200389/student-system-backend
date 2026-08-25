package com.student.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "https://student-system-frontend-eta.vercel.app", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow();
        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());
        student.setGrade(studentDetails.getGrade());
        return studentRepository.save(student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "Student deleted successfully";
    }
}
