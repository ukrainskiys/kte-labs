package com.example.shop.service;

import com.example.shop.model.entity.Client;
import com.example.shop.repository.ClientRepository;
import com.example.shop.service.errors.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
	private final ClientRepository clientRepository;

	@Override
	public List<Client> getAllClients() {
		return clientRepository.findAll();
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
