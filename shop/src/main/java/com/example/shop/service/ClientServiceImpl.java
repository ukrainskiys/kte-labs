package com.example.shop.service;

import com.example.shop.domain.dto.ClientDTO;
import com.example.shop.domain.model.Client;
import com.example.shop.repository.ClientRepository;
import com.example.shop.service.errors.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
	private final ClientRepository clientRepository;
	private final DozerBeanMapper mapper;

	@Override
	public List<ClientDTO> getAllClients() {
		return clientRepository.findAll().stream().map(client -> mapper.map(client, ClientDTO.class)).toList();
	}

	@Override
	public Client getClientById(long clientId) {
		return clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
	}

	@Override
	public void updateClientDiscounts(long clientId, Integer firstDiscount, Integer secondDiscount) {
		Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
		client.setIndividualDiscountFirst(firstDiscount);
		client.setIndividualDiscountSecond(secondDiscount);
		clientRepository.saveAndFlush(client);
	}
}
