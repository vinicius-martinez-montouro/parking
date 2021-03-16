package com.serasa.parking.service;

import com.serasa.parking.model.Client;
import com.serasa.parking.model.Vehicle;
import com.serasa.parking.repository.VehicleRepository;
import com.serasa.parking.service.exception.ObjectDuplicateException;
import com.serasa.parking.service.exception.ObjectNotFoundException;
import com.serasa.parking.service.impl.VehicleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class VehicleServiceImplTest {

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private ClientService clientService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldFindAllVehiclesWhenExistVehiclesRegister() {
        when(vehicleRepository.findAll()).thenReturn(asList(new Vehicle()));
        List<Vehicle> vehicles = vehicleService.findAll();
        assertNotNull(vehicles);
        assertFalse(vehicles.isEmpty());
    }

    @Test
    public void shouldFindVehiclesByColorWhenExisteColorVehicle() {
        when(vehicleRepository.findByColor(anyString())).thenReturn(asList(new Vehicle()));
        List<Vehicle> vehicles = vehicleService.findByColor("blue");
        assertNotNull(vehicles);
        assertFalse(vehicles.isEmpty());
    }

    @Test
    public void shouldFindVehicleByYearFabricationWhenExistVehicleWithYearFabrication() {
        when(vehicleRepository.findByYearFabrication(anyInt())).thenReturn(asList(new Vehicle()));
        List<Vehicle> vehicles = vehicleService.findByYearFabrication(12);
        assertNotNull(vehicles);
        assertFalse(vehicles.isEmpty());
    }

    @Test
    public void shouldFindVehicleByBrandWhenExistVehicleWithBrand() {
        when(vehicleRepository.findByBrand(anyString())).thenReturn(asList(new Vehicle()));
        List<Vehicle> vehicles = vehicleService.findByBrand("teste");
        assertNotNull(vehicles);
        assertFalse(vehicles.isEmpty());
    }

    @Test
    public void shouldFindVehicleByModelWhenExistVehicleModel() {
        when(vehicleRepository.findByModel(anyString())).thenReturn(asList(new Vehicle()));
        List<Vehicle> vehicles = vehicleService.findByModel("teste");
        assertNotNull(vehicles);
        assertFalse(vehicles.isEmpty());
    }

    @Test
    public void shouldFindVehicleByIdWhenExistVehicle() {
        when(vehicleRepository.findById(anyString())).thenReturn(Optional.of(new Vehicle()));
        Vehicle vehicle = vehicleService.findById("teste");
        assertNotNull(vehicle);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldThrowObjectNotFoundExceptionWhenFindVehicleByIdThenNotFoundVehicle() {
        when(vehicleRepository.findById(anyString())).thenThrow(new ObjectNotFoundException("Object not found"));
        vehicleService.findById("teste");
    }

    @Test
    public void shouldSaveVehicle() {
        when(clientService.findById(anyString())).thenReturn(new Client());
        when(vehicleRepository.insert(any(Vehicle.class))).thenReturn(new Vehicle());
        vehicleService.saveVehicle(buildVehicleTest());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldThrowObjectNotFoundExceptionSaveVehicleWhenNotExistClient() {
        when(clientService.findById(anyString())).thenThrow(new ObjectNotFoundException("Object not found"));
        vehicleService.saveVehicle(buildVehicleTest());
    }

    @Test(expected = ObjectDuplicateException.class)
    public void shouldThrowObjectDuplicateExceptionWhenSaveVehicleAndReadyExistInDataBase() {
        when(clientService.findById(anyString())).thenReturn(new Client());
        when(vehicleRepository.insert(any(Vehicle.class)))
                .thenThrow(new ObjectDuplicateException("Object exist in database"));
        vehicleService.saveVehicle(buildVehicleTest());
    }
    
    @Test
    public void deleteVehicleById() {
        doNothing().when(vehicleRepository).deleteById(anyString());
        vehicleService.deleteVehicleById("12");
    }


    private Vehicle buildVehicleTest(){
        return Vehicle.builder()
                .client(Client.builder().cpf("123").build())
                .build();
    }
}
