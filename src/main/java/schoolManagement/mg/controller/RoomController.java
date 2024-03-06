package schoolManagement.mg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import schoolManagement.mg.entity.Room;
import schoolManagement.mg.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/list")
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room savedRoom = roomRepository.save(room);
        return ResponseEntity.ok(savedRoom);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room roomDetails) {
        Optional<Room> room = roomRepository.findById(id);
        if (!room.isPresent())
            return ResponseEntity.notFound().build();

        room.get().setDesignation(roomDetails.getDesignation());
        room.get().setCodesalle(roomDetails.getCodesalle());

        Room updatedRoom = roomRepository.save(room.get());
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            return ResponseEntity.ok(room);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getId/{codesalle}")
    public ResponseEntity<Long> getRoomId(@PathVariable String codesalle) {
        List<Long> ids = roomRepository.getRoomId(codesalle);
        if (!ids.isEmpty()) {
            return new ResponseEntity<>(ids.get(0), HttpStatus.OK); // Assuming there's only one teacher with the given name
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
