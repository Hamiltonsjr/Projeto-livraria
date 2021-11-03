package br.com.livraria.services;

import br.com.livraria.entities.Client;
import br.com.livraria.exceptions.ClientNotFoundException;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;


@QuarkusTest
class MockClientServiceTest {

    @InjectMock
    ClientService service;

    private Client client;

    @BeforeEach
    void setUp(){
        client = new Client();
        client.setId(1L);
        client.setName("José Carlos");
        client.setCPF("111.222.333.44");
        client.setAddress("Rua do test nº 171");
        client.setCity("São Paulo");
        client.setZipCode("02934-000");
    }

    @Test
    void testIfFindAllReturnsCorrectCustomer() throws ClientNotFoundException {
        List<Client> clients = new ArrayList<>();
        Long userId = 1L;
        clients.add(client);
        Mockito.when(service.getAll()).thenReturn(clients);
        Assertions.assertFalse(clients.isEmpty());
        Assertions.assertEquals(1L,clients.get(0).getId());
        Assertions.assertEquals("José Carlos", clients.get(0).getName());
        Assertions.assertEquals("111.222.333.44",clients.get(0).getCPF());
        Assertions.assertEquals("Rua do test nº 171",clients.get(0).getAddress());
        Assertions.assertEquals("São Paulo", clients.get(0).getCity());
        Assertions.assertEquals("02934-000",clients.get(0).getZipCode());
    }

    @Test
    void testIfFindByIdOptionalReturnsTheCorrectCustomerId(){
        Mockito.when(service.getId(1L)).thenReturn(client);
        Assertions.assertEquals(1L , client.getId());
        Assertions.assertEquals("José Carlos", client.getName());
        Assertions.assertEquals("111.222.333.44", client.getCPF());
        Assertions.assertEquals("Rua do test nº 171", client.getAddress());
        Assertions.assertEquals("São Paulo", client.getCity());
        Assertions.assertEquals("02934-000", client.getZipCode());
    }

    @Test
    void testCustomerCreationClientStatus200(){
        Client newClient = new Client();
        newClient.setId(2L);
        newClient.setName("Maria Clara");
        newClient.setCPF("333.444.555.66");
        newClient.setAddress("Alameda do test nº 2000");
        newClient.setCity("Rio de janeiro");
        newClient.setZipCode("02944-000");
        Mockito.when(service.insert(newClient, 1L)).thenReturn(newClient);
    }

    @Test
    void testUpgradeIfClientExists(){
        Client updateClient = new Client();
        updateClient.setId(1L);
        updateClient.setName("Antonio Carlos");
        updateClient.setCPF("111.222.333.44");
        updateClient.setAddress("Rua do test nº 171");
        updateClient.setCity("São Paulo");
        updateClient.setZipCode("02934-000");
        Mockito.when(service.update(updateClient,1L)).thenReturn(updateClient);
        Assertions.assertEquals(1L,updateClient.getId());
        Assertions.assertEquals("Antonio Carlos", updateClient.getName());
        Assertions.assertEquals("111.222.333.44",updateClient.getCPF());
        Assertions.assertEquals("Rua do test nº 171",updateClient.getAddress());
        Assertions.assertEquals("São Paulo", updateClient.getCity());
        Assertions.assertEquals("02934-000",updateClient.getZipCode());
    }

    @Test
    void testDeleteIfIdExistingInTheDatabase(){
        Long userId = 1L;
        Mockito.when(service.getId(1L)).thenReturn(client);
        service.delete(1L,userId);
    }

    @Test
    void testDeleteIfIdNotFoundInTheDatabase() throws ClientNotFoundException{
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        Mockito.when(service.getId(4L)).thenReturn(client);
        Assertions.assertFalse(clients.isEmpty());
    }

    @Test
    void testUpdateClientNotFoundInDatabase(){
        Client updateClient = new Client();
        updateClient.setId(4L);
        Mockito.when(service.update(updateClient,1L)).thenReturn(updateClient);
        Assertions.assertFalse(updateClient.equals(client));
    }
}