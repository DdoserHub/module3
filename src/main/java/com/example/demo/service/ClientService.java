package com.example.demo.service;

import com.example.demo.dto.ClientDTO.ClientPartialUpdateDTO;
import com.example.demo.dto.ClientDTO.ClientRequestDTO;
import com.example.demo.dto.ClientDTO.ClientResponseDTO;
import com.example.demo.dto.ClientDTO.ClientWithOrdersResponseDTO;
import com.example.demo.entity.Client;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.ClientMapper;
import com.example.demo.repository.ClientRepository;
import com.example.demo.specification.ClientSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientResponseDTO addClient(ClientRequestDTO clientRequestDTO) {
        Client client = clientMapper.toClient(clientRequestDTO);
        clientRepository.save(client);
        return clientMapper.toResponseDTO(client);
    }

    public ClientResponseDTO partialUpdateClient(Long id, ClientPartialUpdateDTO clientPartialUpdateDTO) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        Client client = null;

        if (clientOptional.isPresent()) {
            client = clientOptional.get();
            clientMapper.partialUpdateClient(clientPartialUpdateDTO, client);
            clientRepository.save(client);
        } else {
            throw new NotFoundException("Клиент с id: " + id + " не найден");
        }
        return clientMapper.toResponseDTO(client);
    }

    public boolean deleteClient(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<ClientResponseDTO> getClient(String name, String directional,
                                             String sortBy, String surname,
                                             String email, String number,
                                             int page, int size) {

        String sortField = sortBy != null && sortBy.equals("name") ? sortBy : "id";

        Sort.Direction sortDirection = directional.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));

        Specification<Client> specification = ClientSpecification.filterBy(name, surname, email, number);


        return clientRepository.findAll(specification, pageable).map(clientMapper::toResponseDTO);
    }

    public ClientWithOrdersResponseDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Клиент с id: " + id + " не найден"));
        return clientMapper.toResponseWithOrderDTO(client);
    }
}
