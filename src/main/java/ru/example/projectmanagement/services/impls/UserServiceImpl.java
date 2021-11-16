package ru.example.projectmanagement.services.impls;

import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.projectmanagement.entities.User;
import ru.example.projectmanagement.exceptions.BadRequestException;
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

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("User with ID %s not found", id)));
    }

    @Override
    public User add(User user) {
        try {
            return userRepository.save(user);
        } catch (NestedRuntimeException e) {
            throw new BadRequestException("Bad request");
        }
    }

    @Override
    public User update(User user) {
        try {
            return userRepository.save(user);
        } catch (NestedRuntimeException e) {
            throw new BadRequestException("Bad request");
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
