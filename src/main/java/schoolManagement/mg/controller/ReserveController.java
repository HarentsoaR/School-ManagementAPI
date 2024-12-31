package schoolManagement.mg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import schoolManagement.mg.entity.Reserve;
import schoolManagement.mg.entity.Room;
import schoolManagement.mg.entity.Teacher;
import schoolManagement.mg.repository.ReserveRepository;
import schoolManagement.mg.repository.RoomRepository;
import schoolManagement.mg.repository.TeacherRepository;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/reserve")
public class ReserveController {

	@Autowired
    private final ReserveRepository reserveRepository;
	
	@Autowired
    private final TeacherRepository teacherRepository;
	
	@Autowired
    private final RoomRepository roomRepository;

    public ReserveController(ReserveRepository reserveRepository, TeacherRepository teacherRepository, RoomRepository roomRepository) {
        this.reserveRepository = reserveRepository;
        this.teacherRepository = teacherRepository;
        this.roomRepository = roomRepository;
    }
    @GetMapping("/list")
    public List<Reserve> getReservations() {
        return reserveRepository.getAllReservationsWithDetails();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        Reserve reserve = reserveRepository.findById(id).orElseThrow();
        reserveRepository.delete(reserve);
        return ResponseEntity.ok().body("Reservation deleted successfully with id " + id);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Reserve> updateReservation(@PathVariable Long id, @RequestBody Reserve reserveDetails) {
        Reserve reserve = reserveRepository.findById(id).orElseThrow();

        reserve.setTeacher(teacherRepository.findById(reserveDetails.getTeacher().getId()).orElseThrow());

        reserve.setRoom(roomRepository.findById(reserveDetails.getRoom().getId()).orElseThrow());

        // Update other fields as necessary
        reserve.setDate(reserveDetails.getDate());
        // ...

        Reserve updatedReserve = reserveRepository.save(reserve);
        return ResponseEntity.ok(updatedReserve);
    }


    @PostMapping("/save")
    public ResponseEntity<Reserve> createReservations(@RequestBody Reserve reserve) {
        Teacher teacher = teacherRepository.findById(reserve.getTeacher().getId()).orElseThrow();
        Room room = roomRepository.findById(reserve.getRoom().getId()).orElseThrow();

        reserve.setTeacher(teacher);
        reserve.setRoom(room);

        Reserve savedReserve = reserveRepository.save(reserve);
        return ResponseEntity.ok(savedReserve);
    }
    @GetMapping("/teacher/{name}")
    public ResponseEntity<List<Reserve>> getReservationsByTeacheName(@PathVariable String name) {
        List<Reserve> reservations = reserveRepository.findReservationsByTeacherName(name);
        if (!reservations.isEmpty()) {
            return ResponseEntity.ok(reservations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/room/{codesalle}")
    public ResponseEntity<List<Reserve>> getReservationsByRoom(@PathVariable String codesalle) {
        List<Reserve> reservations = reserveRepository.findReservationsByRoom(codesalle);
        if (!reservations.isEmpty()) {
            return ResponseEntity.ok(reservations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getTeacher")
    public List<String> getTeachers() {
        return reserveRepository.getTeacherName();
    }
    @GetMapping("/getClassroom")
    public List<String> getClassrooms() {
        return reserveRepository.getClassroom();
    }
}
