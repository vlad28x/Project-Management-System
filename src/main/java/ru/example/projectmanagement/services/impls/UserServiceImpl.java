package ru.example.projectmanagement.services.impls;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.projectmanagement.dto.UserRequestDto;
import ru.example.projectmanagement.dto.UserResponseDto;
import ru.example.projectmanagement.entities.User;
import ru.example.projectmanagement.exceptions.BadRequestException;
import ru.example.projectmanagement.exceptions.NotFoundException;
import ru.example.projectmanagement.repositories.UserRepository;
import ru.example.projectmanagement.services.UserService;
import ru.example.projectmanagement.utils.mappers.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponseDto getById(Long id) {
        return userMapper.userToUserResponseDto(userRepository.findById(id).orElseThrow(() -> {
            log.error(String.format("User with ID %s not found", id));
            return new NotFoundException(String.format("User with ID %s not found", id));
        }));
    }

    @Override
    public UserResponseDto add(UserRequestDto newUser) {
        try {
            return userMapper.userToUserResponseDto(userRepository.save(userMapper.userRequestDtoToUser(newUser)));
        } catch (NestedRuntimeException e) {
            log.error(e.getMessage(), e.getCause());
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public UserResponseDto update(UserRequestDto newUser) {
        try {
            return userMapper.userToUserResponseDto(userRepository.save(userMapper.userRequestDtoToUser(newUser)));
        } catch (NestedRuntimeException e) {
            log.error(e.getMessage(), e.getCause());
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> {
            log.error(String.format("User with username %s not found", username));
            return new NotFoundException(String.format("User with username %s not found", username));
        });
    }
}
