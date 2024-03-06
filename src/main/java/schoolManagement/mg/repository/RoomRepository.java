package schoolManagement.mg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import schoolManagement.mg.entity.Room;


public interface RoomRepository extends JpaRepository<Room, Long> {
	@Query("SELECT r.id FROM Room r WHERE r.codesalle = :codesalle")
    List<Long> getRoomId(@Param("codesalle") String codesalle);
}
