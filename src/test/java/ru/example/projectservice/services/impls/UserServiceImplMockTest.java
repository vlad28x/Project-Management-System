package ru.example.projectservice.services.impls;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.example.projectservice.dto.UserRequestDto;
import ru.example.projectservice.dto.UserResponseDto;
import ru.example.projectservice.entities.Project;
import ru.example.projectservice.entities.User;
import ru.example.projectservice.entities.enums.Role;
import ru.example.projectservice.exceptions.BadRequestException;
import ru.example.projectservice.exceptions.NotFoundException;
import ru.example.projectservice.repositories.UserRepository;
import ru.example.projectservice.utils.mappers.UserMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserServiceImplMockTest {

    private static final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void getAllUsersTest() {
        List<User> list = new ArrayList<>(Arrays.asList(new User(), new User()));
        Mockito.when(userRepository.findAll()).thenReturn(list);
        List<UserResponseDto> excepted = userService.getAll();
        assertEquals(excepted.size(), list.size());
    }

    @Test
    void getUserByIdSuccessTest() {
        Project project = new Project();
        project.setId(1L);
        User user = new User();
        user.setId(1L);
        user.setUsername("vlad28x");
        user.setPassword("vlad2921");
        user.setFirstName("Vladislav");
        user.setSecondName("Skibin");
        user.setEmail("vlad@mail.ru");
        user.setRole(Role.ADMIN);
        user.setProject(project);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDto actual = userService.getById(1L);
        UserResponseDto expected = userMapper.userToUserResponseDto(user);

        assertEqualsUserResponseDto(expected, actual);
    }

    @Test
    void getUserByIdFailTest() {
        Mockito.when(userRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getById(1L));
    }

    @Test
    void addUserSuccessTest() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setId(1L);
        userRequestDto.setUsername("vlad28x");
        userRequestDto.setPassword("vlad2921");
        userRequestDto.setFirstName("Vladislav");
        userRequestDto.setSecondName("Skibin");
        userRequestDto.setEmail("vlad@mail.ru");
        userRequestDto.setRole(Role.ADMIN);
        userRequestDto.setProjectId(1L);

        User user = userMapper.userRequestDtoToUser(userRequestDto);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserResponseDto actual = userService.add(userRequestDto);
        UserResponseDto expected = userMapper.userToUserResponseDto(user);

        assertEqualsUserResponseDto(expected, actual);
    }

    @Test
    void addUserFailTest() {
        UserRequestDto userRequestDto = new UserRequestDto();
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenThrow(BadRequestException.class);
        assertThrows(BadRequestException.class, () -> userService.add(userRequestDto));
    }

    @Test
    void updateUserSuccessTest() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setId(1L);
        userRequestDto.setUsername("vlad28x");
        userRequestDto.setPassword("vlad2921");
        userRequestDto.setFirstName("Vladislav");
        userRequestDto.setSecondName("Skibin");
        userRequestDto.setEmail("vlad@mail.ru");
        userRequestDto.setRole(Role.ADMIN);
        userRequestDto.setProjectId(1L);

        User user = userMapper.userRequestDtoToUser(userRequestDto);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDto actual = userService.update(userRequestDto);
        UserResponseDto expected = userMapper.userToUserResponseDto(user);

        assertEqualsUserResponseDto(expected, actual);
    }

    @Test
    void updateUserFailTest() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setId(1L);
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.update(userRequestDto));
    }

    @Test
    void deleteUserSuccessTest() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(new User()));
        userService.delete(1L);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(Mockito.any(Long.class));
    }

    @Test
    void deleteUserFailTest() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.delete(1L));
        Mockito.verify(userRepository, Mockito.times(0)).deleteById(Mockito.any(Long.class));
    }

    @Test
    void findByUsernameSuccessTest() {
        String username = "vlad28x";
        Project project = new Project();
        User expected = new User();
        expected.setId(1L);
        expected.setUsername(username);
        expected.setPassword("vlad2921");
        expected.setFirstName("Vladislav");
        expected.setSecondName("Skibin");
        expected.setEmail("vlad@mail.ru");
        expected.setRole(Role.ADMIN);
        expected.setProject(project);

        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(expected));

        User actual = userService.findByUsername(username);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getSecondName(), actual.getSecondName());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getRole(), actual.getRole());
        assertEquals(expected.getProject().getId(), actual.getProject().getId());
    }

    @Test
    void findByUsernameFailTest() {
        String username = "vlad28x";
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.findByUsername(username));
    }

    private void assertEqualsUserResponseDto(UserResponseDto expected, UserResponseDto actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getSecondName(), actual.getSecondName());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getRole(), actual.getRole());
        assertEquals(expected.getProjectId(), actual.getProjectId());
    }

}