package lt.ca.javau11.schooldemov1.services;

import lt.ca.javau11.schooldemov1.entities.Lesson;
import lt.ca.javau11.schooldemov1.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }
    public Lesson createLesson(Lesson lesson){
        return lessonRepository.save(lesson);
    }
    public Lesson updateLesson(Lesson lesson){
        return  lessonRepository.save(lesson);
    }
    public void deleteLesson(Long id){
        lessonRepository.deleteById(id);
    }

    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id).orElseThrow(()-> new RuntimeException("Lesson not found"));
    }
    public List<Lesson> getAllLessons(){
        return lessonRepository.findAll();
    }
}
