# Manual Documentation for SchoolDemoV1 Project
## **Overview**
The _SchoolDemoV1_ is a Spring Boot web application designed for managing data related to a school diary. It provides RESTful APIs for managing **Teachers**, **Students**, **Lessons**, and **Grade Records** through CRUD operations.
The application is built using modern Java frameworks and tools, such as:
- **Spring Boot** for rapid application development.
- **Spring Data JPA** for database interactions.
- **Hibernate** as the ORM implementation.
- **MySQL** as the database.
- **Lombok** for reducing boilerplate code.
- **Thymeleaf** template engine for potential web views (though currently focused on REST API).

## **Project Components**
### 1. **Controllers**
The controllers manage incoming HTTP requests and provide relevant responses by interacting with their corresponding services. The application uses well-defined RESTful endpoints.
#### **TeacherController**
- **Path:** `/api/teachers`
- **Endpoints:**
    - **POST /api/teachers** - Create a new teacher.
    - **PUT /api/teachers/{id}** - Update an existing teacher by ID.
    - **DELETE /api/teachers/{id}** - Delete a teacher by ID.
    - **GET /api/teachers/{id}** - Get teacher details by ID.
    - **GET /api/teachers** - Get all teachers in the system.

#### **StudentController**
- **Path:** `/api/students`
- **Endpoints:**
    - **POST /api/students** - Create a new student.
    - **PUT /api/students/{id}** - Update an existing student by ID.
    - **DELETE /api/students/{id}** - Delete a student by ID.
    - **GET /api/students/{id}** - Get student details by ID.
    - **GET /api/students** - Get all students in the system.

#### **LessonController**
- **Path:** `/api/lessons`
- **Endpoints:**
    - **POST /api/lessons** - Create a new lesson.
    - **PUT /api/lessons/{id}** - Update lesson details by ID.
    - **DELETE /api/lessons/{id}** - Delete a lesson by ID.
    - **GET /api/lessons/{id}** - Get lesson details by ID.
    - **GET /api/lessons** - Get all lessons.

#### **GradeRecordController**
- **Path:** `/api/grade-records`
- **Endpoints:**
    - **POST /api/grade-records** - Create a new grade record.
    - **PUT /api/grade-records/{id}** - Update an existing grade record.
    - **DELETE /api/grade-records/{id}** - Delete a grade record by ID.
    - **GET /api/grade-records/{id}** - Get grade record details by ID.
    - **GET /api/grade-records** - Get all grade records.

### 2. **Entities**
These are representations of database tables in the application.
#### **Teacher**
- Table: `teachers`
- Fields:
    - `id`: Primary Key.
    - `firstName`: Teacher's first name.
    - `lastName`: Teacher's last name.
    - `email`: Teacher's unique email.
    - `createdAt`: Record creation timestamp.
    - `updatedAt`: Record update timestamp.

#### **Student**
- Table: `students`
- Fields:
    - `id`: Primary Key.
    - `firstName`: Student's first name.
    - `lastName`: Student's last name.
    - `email`: Student's unique email.
    - `createdAt`: Record creation timestamp.
    - `updatedAt`: Record update timestamp.
    - `gradeRecords`: List of grade records associated with the student.

#### **Lesson**
- Table: `lessons`
- Fields:
    - `id`: Primary Key.
    - `name`: Lesson name.
    - `teacher`: Reference to the teacher handling the lesson.
    - `gradeRecords`: List of grade records associated with this lesson.

#### **GradeRecord**
- Table: `grade_records`
- Fields:
    - `id`: Primary Key.
    - `grade`: Numeric grade.
    - `teacherComments`: Comments from the teacher.
    - `date`: Date of the grade record.
    - `student`: Reference to the associated student.
    - `teacher`: Reference to the teacher who provided the grade.
    - `lesson`: Reference to the lesson the grade is associated with.
    - `createdAt`: Record creation timestamp.
    - `updatedAt`: Record update timestamp.

### 3. **Services**
Services encapsulate the business logic for processing data and interacting with repositories.
#### **TeacherService**
Manages operations for the `Teacher` entity:
- `createTeacher(Teacher teacher)`: Saves a new teacher to the database.
- `updateTeacher(Teacher teacher)`: Updates an existing teacher.
- `deleteTeacher(Long id)`: Deletes a teacher by ID.
- `getTeacherById(Long id)`: Fetch a teacher by ID.
- `getAllTeachers()`: Get all teachers.

#### **StudentService**
Manages operations for the `Student` entity:
- Similar to `TeacherService` but for `Student`.

#### **LessonService**
Manages operations for the `Lesson` entity:
- Similar methods as `TeacherService`.

#### **GradeRecordService**
Manages operations for the `GradeRecord` entity:
- Handles relationships between teachers, lessons, students, and grade records.
- Methods include:
    - `createGradeRecord(GradeRecordRequest)`: Create a grade record associated with `Teacher`, `Student`, `Lesson`.
    - `updateGradeRecord(GradeRecordRequest)`: Updates an existing grade record with fields like grade, teacher comments, etc.
    - `getGradeRecordById(Long id)`: Fetch a grade record by ID.
    - `getAllGradeRecords()`: Fetch all grade records.
    - `deleteGradeRecord(Long id)`: Delete a grade record.

### 4. **Repositories**
Repositories are interfaces extending Spring Data JPA’s `JpaRepository` to abstract database interactions.
- **TeacherRepository**
    - Extends `JpaRepository<Teacher, Long>`: Handles CRUD operations for `Teacher`.

- **StudentRepository**
    - Extends `JpaRepository<Student, Long>`: Handles CRUD operations for `Student`.

- **LessonRepository**
    - Extends `JpaRepository<Lesson, Long>`: Handles CRUD operations for `Lesson`.

- **GradeRecordRepository**
    - Extends `JpaRepository<GradeRecord, Long>`: Handles CRUD operations for `GradeRecord`.

## **Technologies Used**
1. **Spring Boot (3.4.2)**
    - Framework for building REST APIs.
    - Dependency injection with services and repositories.

2. **Spring Data JPA**
    - Provides integration with Hibernate for seamless database interactions.

3. **Hibernate**
    - Object Relational Mapping (ORM) implementation.

4. **MySQL**
    - Relational database used for persistent storage of application data.

5. **Lombok**
    - Annotations for reducing boilerplate Java code.

6. **Jackson Databind**
    - JSON serialization and deserialization.

7. **Maven**
    - Dependency and project build management.

## **Database Configuration**
Defined in `application.properties`:

spring.datasource.url=jdbc:mysql://localhost:3306/school_diary
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

- **Database Schema:** `school_diary`
- **DDL Auto:** The schema will auto-update on application launch.

## **Development Setup**
### **Requirements**
1. **Java 21**: Ensure Java SDK 21 is installed.
2. **MySQL**: A running database instance.
3. **Maven**: Installed to manage dependencies and build the project.

### **Steps**
1. Clone the repository.
2. Configure database details in `application.properties`.
3. Build the project:    mvn clean install
4. Run the application:  mvn spring-boot:run
5. The application will be available at `http://localhost:8080`.

## **API Testing**
Use tools like **Postman** or **cURL** to test the REST API.
**Example Requests:**
- Get a Teacher by ID:    GET http://localhost:8080/api/teachers/1
- Create a Grade Record:    POST http://localhost:8080/api/grade-records
   Content-Type: application/json
   Body:
   {
       "teacherId": 1,
       "studentId": 1,
       "lessonId": 2,
       "grade": 90,
       "teacherComments": "Excellent work!"
   }

## **Features**
1. **Entity Relationships:**
    - Teachers, Students, Lessons are interconnected using `@ManyToOne` and `@OneToMany` mappings.

2. **Timestamps:**
    - Hibernate's `@CreationTimestamp` and `@UpdateTimestamp` automatically manage `createdAt` and `updatedAt` fields.

3. **Error Handling:**
    - Returns HTTP _404 (Not Found)_ when a resource is missing.
    - Validates inputs (e.g., null checks).

4. **CORS Support:**
    - Configured to allow requests from origins like `localhost:3000`.
