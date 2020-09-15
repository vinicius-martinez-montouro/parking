package com.serasa.parking.resource;

import com.serasa.parking.model.Client;
import com.serasa.parking.model.Vehicle;
import com.serasa.parking.service.ClientService;
import com.serasa.parking.service.VehicleService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.BDDMockito.when;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author vinicius.montouro
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClientResourceTest {

    @LocalServerPort
    private int port;

    @MockBean
    private ClientService clientService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listSouldReturnStatus200() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        List<Client> clientList = asList(Client.builder()
                .email("vincius!@teste.com")
                .tel("121212121")
                .birthDate(new Date())
                .cpf("123123123")
                .name("vinicius")
                .build(),
                Client.builder()
                        .email("joaquim!@teste.com")
                        .tel("121212132")
                        .birthDate(new Date())
                        .cpf("123123132")
                        .name("Joaquim")
                        .build()
        );
        final String baseUrl = "http://localhost:" + port + "/parking/v1/client";
        URI uri = new URI(baseUrl);
        when(clientService.findAll()).thenReturn(clientList);
        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(OK);
    }

}
