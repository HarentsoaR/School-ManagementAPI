package schoolManagement.mg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import schoolManagement.mg.entity.Teacher;
import schoolManagement.mg.repository.TeacherRepository;


@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository repo;

    public void addTeacher(Teacher e) {
        repo.save(e);
    }
    public Teacher getTeacherByName(String name) {
        return (Teacher) repo.findByName(name);
    }
}
