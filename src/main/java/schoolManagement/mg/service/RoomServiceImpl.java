package schoolManagement.mg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import schoolManagement.mg.entity.Room;
import schoolManagement.mg.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository repo;

    public void addRoom(Room e) {
        repo.save(e);
    }
}
