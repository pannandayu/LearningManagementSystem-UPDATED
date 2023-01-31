package com.example.demo.repositories;

import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.dto.FinishedCourseResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Employee;

import java.time.LocalDate;
import java.util.*;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    @Query(value = "select * from tb_tr_employee tte where email = ?1", nativeQuery = true)
    Employee findEmployeeByEmail(String email);

    @Query(value = "select * from tb_tr_employee tte where id = ?1", nativeQuery = true)
    Employee findEmployeeById(Integer id);

    @Modifying
    @Query(value =
            "UPDATE tb_tr_employeecourse e " +
            "SET e.status = ?1 " +
            "WHERE e.employee_id = ?2 AND e.course_id = ?3", nativeQuery = true)
    void updateCourseStudent(Boolean status, Integer empId, Integer courseId);

    @Modifying
    @Query(value =
            """
            insert into tb_tr_employeecourse (employee_id, course_id, status, startdate, enddate)
            values(?1, ?2, ?3, ?4, ?5)
            """, nativeQuery = true)
    void insertCourseStudent(Integer empId, Integer courseId, Boolean status, LocalDate startDate, LocalDate endDate);

    @Query(value = """
            select name
            from tb_tr_employeecourse tte
            join tb_tr_course ttc
            on tte.course_id = ttc.id
            where employee_id = ?1 and status = 1
            """, nativeQuery = true)
    List<String> getFinishedCourse(Integer id);

    @Query(value = """
            select id
            from tb_tr_course ttc
            where name = ?1
            """, nativeQuery = true)
    Integer getFinishedCourseIdByName(String courseName);

    @Query(value = """
            select distinct enddate from tb_tr_employeecourse tte
            where course_id = ?1
            """, nativeQuery = true)
    LocalDate getFinishedCourseDateById(Integer courseId);

    @Query(value = """
            select count(*) as module_count
            from tb_tr_course ttc
            join tb_tr_segment tts
            on ttc.id = tts.course_id
            join tb_tr_module ttm
            on tts.id = ttm.segment_id
            where course_id = ?1
            group by ttc.name
            """, nativeQuery = true)
    Integer getFinishedCourseModuleCountById(Integer courseId);

    @Query(value = """
            select name
            from tb_tr_employeecourse tte
            join tb_tr_course ttc
            on tte.course_id = ttc.id
            where employee_id = ?1 and status = 1
            """, nativeQuery = true)
    List<String> getObjectFinishedCourse(Integer id);

    @Query(value = """
            select distinct enddate
            from tb_tr_employeecourse tte
            join tb_tr_course ttc
            on tte.course_id = ttc.id
            join tb_tr_segment tts
            on ttc.id = tts.course_id
            where employee_id = ?1 and status = 1
            """, nativeQuery = true)
    List<Date> getFinishDateCourse(Integer id);

    @Query(value = """
            select count(ttm.name) as total_module
            from tb_tr_employeecourse tte
            join tb_tr_course ttc
            on tte.course_id = ttc.id
            join tb_tr_segment tts
            on ttc.id = tts.course_id
            join tb_tr_module ttm
            on tts.id = ttm.segment_id
            where employee_id = ?1 and status = 1
            group by tts.course_id
            """, nativeQuery = true)
    List<Integer> getTotalModuleFinishedCourse(Integer id);

    @Query(value = """
            select ttc.name
            from tb_tr_employeecourse tte
            join tb_tr_course ttc
            on tte.course_id = ttc.id
            where employee_id = ?1 and status = 0
            """, nativeQuery = true)
    String getOnGoingCourse(Integer id);

    @Query(value = """
            select tts.name
            from tb_tr_course ttc
            join tb_tr_segment tts
            on ttc.id = tts.course_id
            join tb_tr_module tmm
            on tts.id = tmm.segment_id
            where ttc.name = ?1
            group by tts.name
            """, nativeQuery = true)
    List<String> getSegmentPerOnGoingCourse(String courseName);

    @Query(value = """
            select count(*) as total_module
            from tb_tr_course ttc
            join tb_tr_segment tts
            on ttc.id = tts.course_id
            join tb_tr_module tmm
            on tts.id = tmm.segment_id
            where ttc.name = ?1
            """, nativeQuery = true)
    Integer getModulePerOnGoingCourse(String courseName);

    @Query(value = """
            select description from tb_tr_course ttc
            where ttc.name = ?1
            """, nativeQuery = true)
    String getOngoingCourseDescription(String courseName);

    @Query(value = """
            select sum(isfinished) as finishedModule
            from tb_tr_progress ttp
            where employee_id = ?1
            """, nativeQuery = true)
    Integer totalModuleFinished(Integer id);

    @Query(value = "SELECT e.* from tb_tr_employee e WHERE e.istrainer = 0", nativeQuery = true)
    List<Employee> getStudent();

    @Query(value = "SELECT e.* from tb_tr_employee e JOIN tb_tr_employeecourse ec ON ec.employee_id = e.id WHERE ec.status = 0 AND e.istrainer = 0 AND ec.course_id = ?", nativeQuery = true)
    List<Employee> getAssignedEmployee(Integer id);

    @Query(value = "SELECT id from tb_tr_employee WHERE fullname = ?", nativeQuery = true)
    Integer getIdByName(String name);
}
