package lt.ca.javau11.schooldemov1.controllers;

import lt.ca.javau11.schooldemov1.entities.Teacher;
import lt.ca.javau11.schooldemov1.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher){
        Teacher createdTeacher = teacherService.createTeacher(teacher);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher){
        Optional<Teacher> optionalTeacher = Optional.ofNullable(teacherService.getTeacherById(id));
        if(optionalTeacher.isPresent()){
            teacher.setId(id);
            Teacher updatedTeacher = teacherService.updateTeacher(teacher);
            return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id){
        Optional<Teacher> optionalTeacher = Optional.ofNullable(teacherService.getTeacherById(id));
        if(optionalTeacher.isPresent()){
            teacherService.deleteTeacher(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id){
        Optional<Teacher> teacher = Optional.ofNullable(teacherService.getTeacherById(id));
        return teacher.map(record -> new ResponseEntity<>(record, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers(){
        List<Teacher> allTeachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(allTeachers, HttpStatus.OK);
    }
}