package com.example.demo.repositories;

import com.example.demo.dto.LearningCourseResponseDTO;
import com.example.demo.dto.SegmentResponseDTO;
import com.example.demo.models.EmployeeCourse;
import com.example.demo.models.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeCourseRepository extends JpaRepository<EmployeeCourse, Integer> {

    @Query(value = "SELECT NEW com.example.lms.dto.LearningCourse(c.name, c.description) FROM EmployeeCourse e JOIN e.course c", nativeQuery = true)
    public List<LearningCourseResponseDTO> findCourse();

    @Query(value = "SELECT e.*, c.* FROM tb_tr_employeecourse e JOIN tb_tr_course c ON e.course_id = c.id WHERE e.employee_id = ?1", nativeQuery = true)
    public List<LearningCourseResponseDTO> findCourseByEmployeeIdDTO(Integer id);

    @Query(value = "SELECT e.*, c.* FROM tb_tr_employeecourse e JOIN tb_tr_course c ON e.course_id = c.id WHERE e.employee_id = ?1", nativeQuery = true)
    public EmployeeCourse findCourseByEmployeeId(Integer id);

    @Query(value = "SELECT e.*, c.* FROM tb_tr_employeecourse e JOIN tb_tr_course c ON e.course_id = c.id WHERE e.employee_id = ?1", nativeQuery = true)
    public List<EmployeeCourse> findCourseByEmployeeIdCheck(Integer id);

    @Query(value = "SELECT s.id, s.name,s.description, s.course_id FROM tb_tr_segment s JOIN tb_tr_course c ON c.id = s.course_id WHERE c.id = ?1", nativeQuery = true)
    public List<Segment> findSegmentByCourseIdCheck(Integer id);

    @Query(value = "SELECT s.id, s.name,s.description, s.course_id FROM tb_tr_segment s JOIN tb_tr_course c ON c.id = s.course_id WHERE c.id = ?1", nativeQuery = true)
    public List<SegmentResponseDTO> findSegmentByCourseId(Integer id);

    @Query(value = "SELECT m.* FROM tb_tr_module m WHERE m.segement_id = ?1", nativeQuery = true)
    public List<Module> findModuleBySegmentId(Integer id);

    @Query(value = "select course_id from tb_tr_employeecourse tte \n" +
            "where employee_id = ?1 and status = 0", nativeQuery = true)
    public Integer getCourseIdByEmployeeId(Integer empId);

    @Modifying
    @Query(value = """
            update tb_tr_employeecourse
            set status = 1
            where employee_id = ?1 and course_id = ?2
            """, nativeQuery = true)
    public void updateCourseFinished(Integer empId, Integer courseId);
}
