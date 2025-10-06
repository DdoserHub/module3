package com.example.demo.IntegrationTest;

import com.example.demo.controller.ClientController;
import com.example.demo.dto.ClientDTO.ClientPartialUpdateDTO;
import com.example.demo.dto.ClientDTO.ClientRequestDTO;
import com.example.demo.dto.ClientDTO.ClientResponseDTO;
import com.example.demo.mapper.ClientMapper;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
@SpringBootTest
class ClientControllerIT {

    @Autowired
    private ClientController clientController;

    private ClientRequestDTO request;
    private ClientResponseDTO response;
    private ClientPartialUpdateDTO requestUpdate;

    @BeforeEach
    public void initFixtures() {
        this.request = new ClientRequestDTO(
                "Ivan", "Ivanov",
                "example@mail.ru", "79005553535");
        this.response = new ClientResponseDTO(
                1L, "Ivan", "Ivanov",
                "example@mail.ru", "79005553535");
        this.requestUpdate = new ClientPartialUpdateDTO(
                "Ivan", "Ivanov",
                "example@mail.ru", "79005553535");
    }

    @Test
    void addClient() {

    }

    @Test
    void partialUpdateClient() {
    }

    @Test
    void deleteClient() {
    }

    @Test
    void getClient() {
    }

    @Test
    void getClientById() {
    }
}