package lt.ca.javau11.schooldemov1.controllers.DTO;

import lt.ca.javau11.schooldemov1.entities.Lesson;

public class LessonDTO {
    private Long id;
    private String name;
    private Long teacherId;
    private String teacherFirstName;
    private String teacherLastName;


    public LessonDTO(Lesson lesson) {
        this.id = lesson.getId();
        this.name = lesson.getName();
        if (lesson.getTeacher() != null) {
            this.teacherId = (long) lesson.getTeacher().getId();
            this.teacherFirstName = lesson.getTeacher().getFirstName();
            this.teacherLastName = lesson.getTeacher().getLastName();
        }
    }

    public LessonDTO() {}

    public LessonDTO(Long id, String name, Long teacherId, String teacherFirstName, String teacherLastName) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    // Getters and setters
}
