package com.example.shop.api.dto.response;

import com.example.shop.model.ClientDTO;
import jakarta.xml.bind.annotation.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class GetAllClientsResponse {
	private List<ClientDTO> clients;
}
