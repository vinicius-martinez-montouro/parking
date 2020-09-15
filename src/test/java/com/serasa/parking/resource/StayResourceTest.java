package com.serasa.parking.resource;

import com.serasa.parking.model.Stay;
import com.serasa.parking.model.Vehicle;
import com.serasa.parking.service.StayService;
import com.serasa.parking.service.VehicleService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
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
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author vinicius.montouro
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StayResourceTest {

    @LocalServerPort
    private int port;

    @MockBean
    private StayService stayService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listSouldReturnStatus200() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        List<Stay> stayList = asList(Stay.builder()
                .dateInput(new Date())
                .dateOutput(new Date())
                .outStay(true)
                .id(0L)
                .vehicle(Vehicle.builder()
                        .board("123")
                        .brand("Volkswagen")
                        .color("black")
                        .model("gol")
                        .yearFabrication(2013)
                        .build())
                .build(),
                Stay.builder()
                        .dateInput(new Date())
                        .dateOutput(new Date())
                        .outStay(true)
                        .id(0L)
                        .vehicle(Vehicle.builder()
                                .board("1245")
                                .brand("Volkswagen")
                                .color("black")
                                .model("gol")
                                .yearFabrication(2013)
                                .build())
                        .build()
        );
        final String baseUrl = "http://localhost:" + port + "/parking/v1/stay";
        URI uri = new URI(baseUrl);
        when(stayService.findAll()).thenReturn(stayList);
        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void findByIdSouldReturnStatus200() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        Stay stay = Stay.builder()
                .dateInput(new Date())
                .dateOutput(new Date())
                .outStay(true)
                .id(0L)
                .vehicle(Vehicle.builder()
                        .board("1245")
                        .brand("Volkswagen")
                        .color("black")
                        .model("gol")
                        .yearFabrication(2013)
                        .build())
                .build();
        final String baseUrl = "http://localhost:" + port + "/parking/v1/stay/0";
        URI uri = new URI(baseUrl);
        when(stayService.findById(stay.getId()))
                .thenReturn(stay);
        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void createSouldReturnStatus201() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        Stay stay = Stay.builder()
                .dateInput(new Date())
                .dateOutput(new Date())
                .outStay(true)
                .id(0L)
                .vehicle(Vehicle.builder()
                        .board("1245")
                        .brand("Volkswagen")
                        .color("black")
                        .model("gol")
                        .yearFabrication(2013)
                        .build())
                .build();

        final String baseUrl = "http://localhost:" + port + "/parking/v1/stay";
        URI uri = new URI(baseUrl);
        Assert.assertEquals(stay.getId(),stay.getId());
        ResponseEntity<String> response = restTemplate.postForEntity(uri, stay, String.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(CREATED);
    }
}
