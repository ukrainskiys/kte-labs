package com.example.shop.service;

import com.example.shop.model.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
	List<Client> getAllClients();
	Client getClientById(long clientId);
	void updateClientDiscounts(long clientId, int firstDiscount, int secondDiscount);
}
