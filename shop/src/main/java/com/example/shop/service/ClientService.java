package com.example.shop.service;

import com.example.shop.domain.dto.ClientDTO;
import com.example.shop.domain.model.Client;

import java.util.List;

public interface ClientService {
	List<ClientDTO> getAllClients();
	Client getClientById(long clientId);
	void updateClientDiscounts(long clientId, Integer firstDiscount, Integer secondDiscount);
}
