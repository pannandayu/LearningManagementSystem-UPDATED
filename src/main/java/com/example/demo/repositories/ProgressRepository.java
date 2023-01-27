package com.example.demo.repositories;

import com.example.demo.models.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_tr_progress " +
            "p SET p.isfinished = :finished " +
            "WHERE p.module_id = :id AND p.employee_id = :empid", nativeQuery = true)
    public void finishedModule(@Param("finished") Boolean finished,
                               @Param("id") Integer id,
                               @Param("empid") Integer empid);

    @Query(value = """
            select ttp.* from tb_tr_progress ttp
            where employee_id = ?1 and module_id = ?2
            """, nativeQuery = true)
    public Progress checkFinishedModule(Integer empId, Integer moduleId);
}
