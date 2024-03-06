package schoolManagement.mg.repository;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import schoolManagement.mg.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM Teacher t WHERE t.name = :name")
    List<Teacher> findByName(@Param("name") String name);
    
    @Query("SELECT t.id FROM Teacher t WHERE t.name = :name")
    List<Long> getTeacherIdByName(@Param("name") String name);
}
