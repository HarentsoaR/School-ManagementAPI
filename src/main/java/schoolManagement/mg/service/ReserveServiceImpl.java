package schoolManagement.mg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import schoolManagement.mg.entity.Reserve;
import schoolManagement.mg.repository.ReserveRepository;


@Service
public class ReserveServiceImpl implements ReserveService {
	@Autowired
    private ReserveRepository repo;

    public Reserve addReserve(Reserve e) {
        return repo.save(e);
    }
}
