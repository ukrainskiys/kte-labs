package com.example.shop.api.soap;

import com.example.shop.service.ClientService;
import com.example.shop.service.ProductService;
import com.example.shop.service.SaleFactService;
import com.example.shop.service.StatisticService;
import jakarta.xml.ws.Endpoint;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SoapServicesConfiguration {
    private final Bus bus;
    private final ClientService clientService;
    private final ProductService productService;
    private final SaleFactService saleFactService;
    private final StatisticService statisticService;

    @Bean
    public Endpoint clientSoapService() {
        EndpointImpl endpoint = new EndpointImpl(bus, new ClientSoapService(clientService));
        endpoint.publish("/client");
        return endpoint;
    }

    @Bean
    public Endpoint productSoapService() {
        EndpointImpl endpoint = new EndpointImpl(bus, new ProductSoapService(productService));
        endpoint.publish("/product");
        return endpoint;
    }

    @Bean
    public Endpoint saleSoapService() {
        EndpointImpl endpoint = new EndpointImpl(bus, new SaleSoapService(saleFactService, productService, statisticService));
        endpoint.publish("/sale");
        return endpoint;
    }
}
