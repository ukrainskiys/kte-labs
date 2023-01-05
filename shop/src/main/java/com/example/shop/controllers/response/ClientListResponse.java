package com.example.shop.controllers.response;

import com.example.shop.domain.dto.ClientDTO;
import javax.xml.bind.annotation.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class ClientListResponse {
	private List<ClientDTO> clients;
}
