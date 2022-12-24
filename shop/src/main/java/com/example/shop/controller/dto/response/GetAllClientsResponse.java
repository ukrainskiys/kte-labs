package com.example.shop.controller.dto.response;

import com.example.shop.model.entity.Client;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetAllClientsResponse {
	private List<Client> clients;
}
