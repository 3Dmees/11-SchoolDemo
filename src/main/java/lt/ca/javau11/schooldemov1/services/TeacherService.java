package lt.ca.javau11.schooldemov1.services;

import lt.ca.javau11.schooldemov1.entities.Teacher;
import lt.ca.javau11.schooldemov1.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher createTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }
    public Teacher updateTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
    }
    public void deleteTeacher(Long id){
        teacherRepository.deleteById(id);
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(()-> new RuntimeException("Teacher not found"));
    }
    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }
}
