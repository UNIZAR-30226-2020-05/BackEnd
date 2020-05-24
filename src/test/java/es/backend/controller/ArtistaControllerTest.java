/*
package es.backend.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import es.backend.model.Artista;
import es.backend.model.dto.ArtistaDto;
import es.backend.services.ArtistaService;
import es.backend.services.ImagenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc
class ArtistaControllerTest {
    @Autowired
    private ArtistaController artistaController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArtistaService artistService;

    @MockBean
    private ImagenService imagenService;

    @Autowired
    private ObjectMapper objectMapper;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    void addNewArtista() throws Exception {
        Artista a = new Artista();
        a.setNombre("sd");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(a );

        mockMvc.perform(post("/artist/add")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson)
                .secure( true ))
                .andExpect(status().isCreated());
    }

    @Test
    void get() throws Exception{
        Artista a = new Artista();
        a.setNombre("sd");
        Optional<Artista> optionalArtista = artistService.create(a);
        if(optionalArtista.isPresent()){
            mockMvc.perform(get("/artist/getByName")
                    .param("name", "Artista1")
                    .secure( true ))
                    .andExpect(status().isOk());
        }
    }

    @Test
    void getByName() throws Exception{
        Artista a = new Artista();
        a.setNombre("sd");
        artistService.create(a);
        MvcResult mvcResult = mockMvc.perform(get("/artist/getByName")
                .param("name", "Artista1")
                .secure( true ))
                .andExpect(status().isOk())
                .andReturn();
        Assert.isTrue(mvcResult.getResponse().getContentAsString().contains("Artista1"), mvcResult.getResponse().getContentAsString());
    }

    @Test
    void deleteArtista() throws Exception{
            Artista a = new Artista();
            a.setNombre("sd");
            Optional<Artista> optionalArtista = artistService.create(a);
            if(optionalArtista.isPresent()){
                mockMvc.perform(delete("/artist/delete/{id}", optionalArtista.get().getId())
                        .secure( true ))
                        .andExpect(status().isNoContent());
            }
    }

}
*/