package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.StudentDao;
import mthree.com.fullstackschool.model.Course;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE

    StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        return studentDao.getAllStudents();

        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE

        try {
            return studentDao.findStudentById(id);
        } catch (DataAccessException e) {
            Student errorStudent = new Student();
            errorStudent.setStudentFirstName("Student Not Found");
            errorStudent.setStudentLastName("Student Not Found");
            return errorStudent;
        }

        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE

        boolean malformed = false;
        if (student.getStudentFirstName().isBlank()) {
            student.setStudentFirstName("First Name blank, student NOT added");
            malformed = true;
        }
        if (student.getStudentLastName().isBlank()) {
            student.setStudentLastName("Last Name blank, student NOT added");
            malformed = true;
        }
        if (malformed) {
            return student;
        }

        return studentDao.createNewStudent(student);

        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE

        if (id != student.getStudentId()) {
            student.setStudentFirstName("IDs do not match, student not updated");
            student.setStudentLastName("IDs do not match, student not updated");
            return student;
        }

        studentDao.updateStudent(student);

        return student;

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE

        studentDao.deleteStudent(id);

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        Student student = getStudentById(studentId);
        if (student.getStudentLastName().equals("Student Not Found")) {
            System.out.println("Student Not Found");
            return;
        }

        studentDao.deleteStudentFromCourse(studentId,courseId);
        System.out.println("Student: " + studentId + " deleted from course: " + courseId);

        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        Student student = getStudentById(studentId);
        if (student.getStudentLastName().equals("Student Not Found")) {
            System.out.println("Student Not Found");
            return;
        }

        try {
            studentDao.addStudentToCourse(studentId,courseId);
        } catch (DataAccessException e) {
            System.out.println("Student: "+studentId+" already enrolled in course: "+courseId);
        }

        System.out.println("Student: " + studentId + " added to course: " + courseId);

        //YOUR CODE ENDS HERE
    }
}
