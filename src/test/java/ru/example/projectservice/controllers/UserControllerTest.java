package ru.example.projectservice.controllers;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails(value = "admin")
@TestPropertySource(value = "/application-test.properties")
@Sql(value = "/sql-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllUsersTest() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(9)));
    }

    @Test
    void getUserByIdSuccessTest() throws Exception {
        this.mockMvc.perform(get("/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.username").value("manager1"))
                .andExpect(jsonPath("$.firstName").value("Виталий"))
                .andExpect(jsonPath("$.secondName").value("Кудрявцев"))
                .andExpect(jsonPath("$.email").value("vital45@gmail.com"))
                .andExpect(jsonPath("$.role").value("MANAGER"))
                .andExpect(jsonPath("$.projectId").value(1));
    }

    @Test
    void getUserByIdFailTest() throws Exception {
        this.mockMvc.perform(get("/users/10"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Пользователь с ID 10 не найден"));
    }

    @Test
    void createUserSuccessTest() throws Exception {
        this.mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/user-create-success.json").toPath())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.username").value("topprogrammer"))
                .andExpect(jsonPath("$.firstName").value("Владислав"))
                .andExpect(jsonPath("$.secondName").value("Скибин"))
                .andExpect(jsonPath("$.email").value("vlad.skibin1@mail.ru"))
                .andExpect(jsonPath("$.role").value("PROGRAMMER"))
                .andExpect(jsonPath("$.projectId").value(nullValue()));
    }

    @Test
    void createUserFailTest() throws Exception {
        this.mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/user-create-fail.json").toPath())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void updateUserSuccessTest() throws Exception {
        this.mockMvc.perform(put("/users/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/user-update-success.json").toPath())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.username").value("manager2"))
                .andExpect(jsonPath("$.firstName").value("Александра"))
                .andExpect(jsonPath("$.secondName").value("Ястребова"))
                .andExpect(jsonPath("$.email").value("alexass@gmail.com"))
                .andExpect(jsonPath("$.role").value("MANAGER"))
                .andExpect(jsonPath("$.projectId").value(nullValue()));
    }

    @Test
    void updateUserFailTest() throws Exception {
        this.mockMvc.perform(put("/users/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/user-update-fail.json").toPath())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void deleteUserSuccessTest() throws Exception {
        this.mockMvc.perform(delete("/users/5"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserFailTest() throws Exception {
        this.mockMvc.perform(delete("/users/10"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Пользователь с ID 10 не найден"));
    }

}