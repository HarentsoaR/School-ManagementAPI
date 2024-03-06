package schoolManagement.mg.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import schoolManagement.mg.entity.Teacher;
import schoolManagement.mg.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/teachers")
public class TeacherController {
	@Autowired
    private final TeacherRepository teacherRepository;

    
    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // Create a new teacher
    @PostMapping("/save")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherRepository.save(teacher);
        return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
    }

    // Retrieve all teachers
    @GetMapping("/list")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    // Retrieve a single teacher by ID
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable Long id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isPresent()) {
            return new ResponseEntity<>(teacherOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update a teacher by ID
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacherDetails) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        if (teacherOptional.isPresent()) {
            Teacher teacher = teacherOptional.get();
            teacher.setName(teacherDetails.getName());
            teacher.setPrenom(teacherDetails.getPrenom());
            teacher.setGrade(teacherDetails.getGrade());
            teacherRepository.save(teacher);
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a teacher by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Search for teachers by name
    @GetMapping("/search/{name}")
    public ResponseEntity<List<Teacher>> findTeachersByName(@PathVariable String name) {
        List<Teacher> teachers = teacherRepository.findByName(name);
        if (!teachers.isEmpty()) {
            return new ResponseEntity<>(teachers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/getId/{name}")
    public ResponseEntity<Long> getTeacherIdByName(@PathVariable String name) {
        List<Long> ids = teacherRepository.getTeacherIdByName(name);
        if (!ids.isEmpty()) {
            return new ResponseEntity<>(ids.get(0), HttpStatus.OK); // Assuming there's only one teacher with the given name
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
