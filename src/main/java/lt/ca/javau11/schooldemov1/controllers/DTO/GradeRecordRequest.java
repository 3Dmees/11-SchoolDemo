package lt.ca.javau11.schooldemov1.controllers.DTO;

import java.time.LocalDate;

public class GradeRecordRequest {
    private Long id;
    private Long teacherId;
    private Long lessonId;
    private int grade;
    private String teacherComments;
    private LocalDate date;
    private Long studentId;

    public GradeRecordRequest() {
    }

    public GradeRecordRequest(Long id, Long teacherId, Long lessonId, int grade, LocalDate date, Long studentId) {
        this.id = id;
        this.teacherId = teacherId;
        this.lessonId = lessonId;
        this.grade = grade;
        this.date = date;
        this.studentId = studentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getTeacherComments() {
        return teacherComments;
    }

    public void setTeacherComments(String teacherComments) {
        this.teacherComments = teacherComments;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getStudentId() {
        return Long.valueOf(studentId);
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
