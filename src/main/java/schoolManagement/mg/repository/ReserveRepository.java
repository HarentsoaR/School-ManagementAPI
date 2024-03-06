package schoolManagement.mg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import schoolManagement.mg.entity.Reserve;



public interface ReserveRepository extends JpaRepository<Reserve, Long> {
	@Query("SELECT r FROM Reserve r JOIN FETCH r.teacher t JOIN FETCH r.room rt ORDER by r.date DESC")
	List<Reserve> getAllReservationsWithDetails();
	
	@Query("SELECT r FROM Reserve r JOIN FETCH r.teacher t JOIN FETCH r.room rt WHERE t.name = :name")
    List<Reserve> findReservationsByTeacherName(@Param("name") String name);
	
	@Query("SELECT r FROM Reserve r JOIN FETCH r.teacher t JOIN FETCH r.room rt WHERE rt.codesalle = :codesalle")
	List<Reserve> findReservationsByRoom(@Param("codesalle") String codesalle);
	
	@Query("SELECT t.name FROM Teacher t ORDER BY t.name ASC")
	List<String> getTeacherName();
	
	@Query("SELECT r.codesalle FROM Room r ORDER BY r.codesalle ASC")
	List<String> getClassroom();
	
}
