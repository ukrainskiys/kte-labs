package com.example.shop.service;

import com.example.shop.model.ClientDTO;
import com.example.shop.model.entity.Client;

import java.util.List;

public interface ClientService {
	List<ClientDTO> getAllClients();
	Client getClientById(long clientId);
	void updateClientDiscounts(long clientId, Integer firstDiscount, Integer secondDiscount);
}
