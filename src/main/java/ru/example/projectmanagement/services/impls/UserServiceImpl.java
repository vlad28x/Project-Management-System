package ru.example.projectmanagement.services.impls;

import org.springframework.stereotype.Service;
import ru.example.projectmanagement.entities.User;
import ru.example.projectmanagement.exceptions.NotFoundException;
import ru.example.projectmanagement.repositories.UserRepository;
import ru.example.projectmanagement.services.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Пользователь не найден", id));
    }

    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
