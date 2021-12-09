package ru.example.projectservice.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;
import ru.example.projectservice.dto.DebtResponseDto;
import ru.example.projectservice.proxy.PaymentClient;

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
class ProjectControllerTest {

    @MockBean
    private PaymentClient paymentClient;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllProjectsTest() throws Exception {
        this.mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getProjectByIdSuccessTest() throws Exception {
        this.mockMvc.perform(get("/projects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Система управления проектами"))
                .andExpect(jsonPath("$.description").value("Проект написан с использование Spring Boot"))
                .andExpect(jsonPath("$.startDate").value("2021-10-25"))
                .andExpect(jsonPath("$.endDate").value("2021-12-15"))
                .andExpect(jsonPath("$.debt").value(100000))
                .andExpect(jsonPath("$.status").value("PAYMENT"))
                .andExpect(jsonPath("$.ownerId").value(2))
                .andExpect(jsonPath("$.customerId").value(8));
    }

    @Test
    void getProjectByIdFailTest() throws Exception {
        this.mockMvc.perform(get("/projects/3"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Project with ID 3 not found"));
    }

    @Test
    void createProjectSuccessTest() throws Exception {
        this.mockMvc.perform(post("/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/project-create-success.json").toPath())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("Система управления проектами"))
                .andExpect(jsonPath("$.description").value("Проект написан с использование Spring Boot"))
                .andExpect(jsonPath("$.startDate").value("2021-10-25"))
                .andExpect(jsonPath("$.endDate").value("2021-12-15"))
                .andExpect(jsonPath("$.debt").value(100000))
                .andExpect(jsonPath("$.status").value("PAYMENT"))
                .andExpect(jsonPath("$.ownerId").value(2))
                .andExpect(jsonPath("$.customerId").value(8));
    }

    @Test
    void createProjectFailTest() throws Exception {
        this.mockMvc.perform(post("/projects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/project-create-fail.json").toPath())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void updateProjectSuccessTest() throws Exception {
        this.mockMvc.perform(put("/projects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/project-update-success.json").toPath())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Система управления проектами"))
                .andExpect(jsonPath("$.description").value("Проект написан с использование Spring Boot"))
                .andExpect(jsonPath("$.startDate").value("2021-10-25"))
                .andExpect(jsonPath("$.endDate").value("2021-12-14"))
                .andExpect(jsonPath("$.debt").value(0))
                .andExpect(jsonPath("$.status").value("DONE"))
                .andExpect(jsonPath("$.ownerId").value(2))
                .andExpect(jsonPath("$.customerId").value(8));
    }

    @Test
    void updateProjectFailTest() throws Exception {
        this.mockMvc.perform(put("/projects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile("classpath:data/project-update-fail.json").toPath())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", notNullValue()));
    }

    @Test
    void deleteProjectSuccessTest() throws Exception {
        this.mockMvc.perform(delete("/projects/3"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProjectFailTest() throws Exception {
        this.mockMvc.perform(delete("/projects/4"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Project with ID 4 not found"));
    }

    @Test
    void completeProjectSuccessTest() throws Exception {
        this.mockMvc.perform(put("/projects/3/complete"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.status").value("DONE"));
    }

    @Test
    void completeProjectFailTest() throws Exception {
        this.mockMvc.perform(put("/projects/1/complete"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message").value("Project with ID 1 has 4 underdone tasks"));
    }

    @Test
    void payDebt() throws Exception {
        DebtResponseDto debtResponseDto = new DebtResponseDto();
        debtResponseDto.setDebt(0);
        Mockito.when(paymentClient.payDebt(Mockito.any(String.class), Mockito.any())).thenReturn(ResponseEntity.ok(debtResponseDto));
        //eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjEiLCJyb2xlIjoiQ1VTVE9NRVIiLCJpYXQiOjE2MzkwNDk3MTgsImV4cCI6MTYzOTE3MDAwMH0.wXhUSw2W8Kxga0xvwVYA-lKTa4RAbyVPdzSMSj_jFnA
        this.mockMvc.perform(put("/projects/pay")
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjEiLCJyb2xlIjoiQ1VTVE9NRVIiLCJpYXQiOjE2MzkwNDk3MTgsImV4cCI6MTYzOTE3MDAwMH0.wXhUSw2W8Kxga0xvwVYA-lKTa4RAbyVPdzSMSj_jFnA"))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.debt").value(0))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

}