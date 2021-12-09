package ru.example.projectservice.controllers;

import org.hamcrest.core.IsNull;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails(value = "admin")
@TestPropertySource(value = "/application-test.properties")
@Sql(value = "/sql-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/sql-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllTasksTest() throws Exception {
        this.mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    void getTaskByIdSuccessTest() throws Exception {
        this.mockMvc.perform(get("/tasks/6"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.name").value("TASK-1: Создать БД"))
                .andExpect(jsonPath("$.description").value("Создать базу данных в СУБД PostgreSQL"))
                .andExpect(jsonPath("$.createdAt").value("2021-12-08T16:32:34.451569"))
                .andExpect(jsonPath("$.updatedAt").value("2021-12-08T16:32:34.451569"))
                .andExpect(jsonPath("$.status").value("BACKLOG"))
                .andExpect(jsonPath("$.type").value("FEATURE"))
                .andExpect(jsonPath("$.startDate").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.endDate").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.assignerId").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.ownerId").value(3))
                .andExpect(jsonPath("$.projectId").value(2))
                .andExpect(jsonPath("$.releaseId").value(IsNull.nullValue()));
    }

    @Test
    void getTaskByIdFailTest() throws Exception {
        this.mockMvc.perform(get("/tasks/11"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Task with ID 11 not found"));
    }

    @Test
    void createTaskSuccessTest() throws Exception {
        this.mockMvc.perform(post("/tasks/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/task-create-success.json").toPath())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(11))
                .andExpect(jsonPath("$.name").value("TASK-6: Пофиксить баг"))
                .andExpect(jsonPath("$.description").value("Пофиксить баг в сервисном слое"))
                //.andExpect(jsonPath("$.createdAt").value("2021-12-08T16:32:34.451569"))
                //.andExpect(jsonPath("$.updatedAt").value("2021-12-08T16:32:34.451569"))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"))
                .andExpect(jsonPath("$.type").value("BUG"))
                .andExpect(jsonPath("$.startDate").value("2021-10-26"))
                .andExpect(jsonPath("$.endDate").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.assignerId").value(6))
                .andExpect(jsonPath("$.ownerId").value(3))
                .andExpect(jsonPath("$.projectId").value(2))
                .andExpect(jsonPath("$.releaseId").value(2));
    }

    @Test
    void createTaskFailTest() throws Exception {
        this.mockMvc.perform(post("/tasks/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/task-create-fail.json").toPath())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void updateTaskSuccessTest() throws Exception {
        this.mockMvc.perform(put("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/task-update-success.json").toPath())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("TASK-1: Создать БД"))
                .andExpect(jsonPath("$.description").value("Создать базу данных в СУБД Microsoft SQL Server"))
                .andExpect(jsonPath("$.createdAt").value("2021-12-08T16:32:34.451569"))
                //.andExpect(jsonPath("$.updatedAt").value("2021-12-08T16:32:34.451569"))
                .andExpect(jsonPath("$.status").value("BACKLOG"))
                .andExpect(jsonPath("$.type").value("FEATURE"))
                .andExpect(jsonPath("$.startDate").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.endDate").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.assignerId").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.ownerId").value(2))
                .andExpect(jsonPath("$.projectId").value(1))
                .andExpect(jsonPath("$.releaseId").value(IsNull.nullValue()));
    }

    @Test
    void updateTaskFailTest() throws Exception {
        this.mockMvc.perform(put("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/task-update-fail.json").toPath())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void deleteTaskSuccessTest() throws Exception {
        this.mockMvc.perform(delete("/tasks/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteTaskFailTest() throws Exception {
        this.mockMvc.perform(delete("/tasks/11"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Task with ID 11 not found"));
    }

    @Test
    void assignUserSuccessTest() throws Exception {
        this.mockMvc.perform(put("/tasks/1/assign/user/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.assignerId").value(4));
    }

    @Test
    void assignUserFailTest() throws Exception {
        this.mockMvc.perform(put("/tasks/1/assign/user/10"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Referential integrity violated for user with ID 10"));
    }

    @Test
    void assignReleaseSuccessTest() throws Exception {
        this.mockMvc.perform(put("/tasks/4/assign/release/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.releaseId").value(1));
    }

    @Test
    void assignReleaseFailTest() throws Exception {
        this.mockMvc.perform(put("/tasks/4/assign/release/3"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Referential integrity violated for release with ID 3"));
    }

    @Test
    void completeTaskSuccessTest() throws Exception {
        this.mockMvc.perform(put("/tasks/1/complete"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.status").value("DONE"));
    }

    @Test
    void completeTaskFailTest() throws Exception {
        this.mockMvc.perform(put("/tasks/11/complete"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Task with ID 11 not found"));
    }

    @Test
    void filterTasks() throws Exception {
        this.mockMvc.perform(get("/tasks/filter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/task-filter.json").toPath())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(2)));
    }
}