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
class ReleaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllReleasesTest() throws Exception {
        this.mockMvc.perform(get("/releases"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getReleaseByIdSuccessTest() throws Exception {
        this.mockMvc.perform(get("/releases/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.version").value("1.0 Beta"))
                .andExpect(jsonPath("$.description").value("Бета версия проекта"))
                .andExpect(jsonPath("$.startDate").value("2021-11-02"))
                .andExpect(jsonPath("$.endDate").value("2021-10-25"));
    }

    @Test
    void getReleaseByIdFailTest() throws Exception {
        this.mockMvc.perform(get("/releases/3"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Release with ID 3 not found"));
    }

    @Test
    void createReleaseSuccessTest() throws Exception {
        this.mockMvc.perform(post("/releases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/release-create-success.json").toPath())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.version").value("2.0 Beta"))
                .andExpect(jsonPath("$.description").value("Бета версия проекта"))
                .andExpect(jsonPath("$.startDate").value("2021-12-02"))
                .andExpect(jsonPath("$.endDate").value("2021-10-25"));
    }

    @Test
    void createReleaseFailTest() throws Exception {
        this.mockMvc.perform(post("/releases")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/release-create-fail.json").toPath())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void updateReleaseSuccessTest() throws Exception {
        this.mockMvc.perform(put("/releases/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/release-update-success.json").toPath())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.version").value("2.1 Alpha"))
                .andExpect(jsonPath("$.description").value("Альфа версия проекта"))
                .andExpect(jsonPath("$.startDate").value(nullValue()))
                .andExpect(jsonPath("$.endDate").value(nullValue()));
    }

    @Test
    void updateReleaseFailTest() throws Exception {
        this.mockMvc.perform(put("/releases/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/release-update-fail.json").toPath())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void deleteReleaseSuccessTest() throws Exception {
        this.mockMvc.perform(delete("/releases/3"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteReleaseFailTest() throws Exception {
        this.mockMvc.perform(delete("/releases/4"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Release with ID 4 not found"));
    }

    @Test
    void countUnderdoneTasksTest() throws Exception {
        this.mockMvc.perform(get("/releases/1/countUnderdoneTasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(3));
    }

}