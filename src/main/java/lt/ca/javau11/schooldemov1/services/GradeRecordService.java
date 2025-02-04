package lt.ca.javau11.schooldemov1.services;

import lt.ca.javau11.schooldemov1.controllers.DTO.GradeRecordRequest;
import lt.ca.javau11.schooldemov1.entities.GradeRecord;
import lt.ca.javau11.schooldemov1.entities.Lesson;
import lt.ca.javau11.schooldemov1.entities.Student;
import lt.ca.javau11.schooldemov1.entities.Teacher;
import lt.ca.javau11.schooldemov1.repositories.GradeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GradeRecordService {
    private final GradeRecordRepository gradeRecordRepository;
    private final TeacherService teacherService;
    private final LessonService lessonService;
    private final StudentService studentService;

    @Autowired
    public GradeRecordService(GradeRecordRepository gradeRecordRepository,
                              TeacherService teacherService,
                              LessonService lessonService,
                              StudentService studentService) {
        this.gradeRecordRepository = gradeRecordRepository;
        this.teacherService = teacherService;
        this.lessonService = lessonService;
        this.studentService = studentService;
    }

    public GradeRecord createGradeRecord(GradeRecordRequest gradeRecordRequest){
        Teacher teacher = teacherService.getTeacherById(gradeRecordRequest.getTeacherId());
        Lesson lesson = lessonService.getLessonById(gradeRecordRequest.getLessonId());
        Student student = studentService.getStudentById(gradeRecordRequest.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        GradeRecord gradeRecord = new GradeRecord();
        gradeRecord.setTeacher(teacher);
        gradeRecord.setLesson(lesson);
        gradeRecord.setStudent(student);
        if(gradeRecordRequest.getDate() ==null){
            gradeRecord.setDate(LocalDate.now());
        } else {
            gradeRecord.setDate(gradeRecordRequest.getDate());
        }
        gradeRecord.setGrade(gradeRecordRequest.getGrade());
        gradeRecord.setTeacherComments(gradeRecordRequest.getTeacherComments());
        return gradeRecordRepository.save(gradeRecord);
    }

    public GradeRecord updateGradeRecord(GradeRecordRequest gradeRecordRequest) {
        // Ensure the ID from the request is valid
        if (gradeRecordRequest.getId() == null) {
            throw new IllegalArgumentException("GradeRecord ID cannot be null when updating.");
        }

        // Retrieve the existing record
        GradeRecord gradeRecord = getGradeRecordById(gradeRecordRequest.getId())
                .orElseThrow(() -> new RuntimeException("Grade record not found"));

        // Update other fields
        Teacher teacher = teacherService.getTeacherById(gradeRecordRequest.getTeacherId());
        Lesson lesson = lessonService.getLessonById(gradeRecordRequest.getLessonId());
        Student student = studentService.getStudentById(gradeRecordRequest.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        gradeRecord.setTeacher(teacher);
        gradeRecord.setLesson(lesson);
        gradeRecord.setStudent(student);
        if (gradeRecordRequest.getDate() != null) {
            gradeRecord.setDate(gradeRecordRequest.getDate());
        }
        gradeRecord.setGrade(gradeRecordRequest.getGrade());
        return gradeRecordRepository.save(gradeRecord);
    }

    public void deleteGradeRecord(Long id){
        gradeRecordRepository.deleteById(id);
    }
    public Optional<GradeRecord> getGradeRecordById(Long id){
        return gradeRecordRepository.findById(id);
    }
    public List<GradeRecord> getAllGradeRecords(){
        return gradeRecordRepository.findAll();
    }
}
