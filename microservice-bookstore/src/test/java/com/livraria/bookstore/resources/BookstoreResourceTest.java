/*package com.livraria.bookstore.resources;

import com.livraria.bookstore.entities.Bookstore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static com.livraria.bookstore.utils.BookstoreUtils.asJsonString;
import static com.livraria.bookstore.utils.BookstoreUtils.createFakeBookstore;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BookstoreResourceTest {

    private MockMvc mockMvc;

    @Autowired
    private BookstoreResource resource;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(resource).build();
    }

    // add new
    @Test
    public void createBookstoreTestAndReturnStatusCode201() throws Exception {
        Bookstore bookstore = createFakeBookstore();

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/bookstore/")
                .content(asJsonString(bookstore))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testBadRequestCreateBookstore() throws Exception{
        String json = "{\"id\":\"14\"," +
                "\"trade\":\"Bookstore Fantasy\"," +
                "\"company\":\"Bookstore LTDA\"," +
                "\"cnpj\":\"123456789\"," +
                "\"cep\":\"02934000\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/bookstore/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testBadRequestCreateBookstoreWithInvalidCEP() throws Exception{
        String json = "{\"id\":\" \"," +
                "\"trade\":\"Bookstore Fantasy\"," +
                "\"company\":\"Bookstore LTDA\"," +
                "\"cnpj\":\"123456789\"," +
                "\"cep\":\"00000000\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/bookstore/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    //findById
    @Test
    public void searchTestByBookstoreIdStatus200() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/bookstore/{id}",16)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(16));
    }

    @Test
    public void searchTestByBookstoreIdNotFoundBadRequest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/bookstore/{id}", 100)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void updateStore() throws Exception{
        String json = "{\"id\":\"11\"," +
                "\"trade\":\"Bookstore Fantasy\"," +
                "\"company\":\"Bookstore LTDA\"," +
                "\"cnpj\":\"123456789\"," +
                "\"cep\":\"00000000\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/bookstore/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void searchAndDeleteBookstoreById() throws Exception {
        Long idTest = 3L;

        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/bookstore/")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(String.valueOf(idTest)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}*/