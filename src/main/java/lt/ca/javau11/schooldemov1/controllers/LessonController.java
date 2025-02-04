package lt.ca.javau11.schooldemov1.controllers;

import lt.ca.javau11.schooldemov1.entities.Lesson;
import lt.ca.javau11.schooldemov1.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    private final LessonService lessonService;
    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson){
        Lesson createdLesson = lessonService.createLesson(lesson);
        return new ResponseEntity<>(createdLesson, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson lesson){
        Optional<Lesson> optionalLesson = Optional.ofNullable(lessonService.getLessonById(id));
        if(optionalLesson.isPresent()){
            lesson.setId(id); //setId(Math.toIntExact(id)) ??
            Lesson updatedLesson = lessonService.updateLesson(lesson);
            return new ResponseEntity<>(updatedLesson, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id){
        Optional<Lesson> optionalLesson = Optional.ofNullable(lessonService.getLessonById(id));
        if(optionalLesson.isPresent()){
            lessonService.deleteLesson(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id){
        Optional<Lesson> lesson = Optional.ofNullable(lessonService.getLessonById(id));
        return lesson.map(record -> new ResponseEntity<>(record, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons(){
        List<Lesson> allLessons = lessonService.getAllLessons();
        return new ResponseEntity<>(allLessons, HttpStatus.OK);
    }
}
