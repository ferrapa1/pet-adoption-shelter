package ch.zhaw.ssdd.pas.adapters.inbound.rest;

import ch.zhaw.ssdd.pas.adapters.inbound.rest.dto.PetDTO;
import ch.zhaw.ssdd.pas.adapters.outbound.jpa.PetEntity;
import ch.zhaw.ssdd.pas.adapters.outbound.jpa.PetEntityRepository;
import ch.zhaw.ssdd.pas.adapters.outbound.jpa.PetPersistenceAdapter;
import ch.zhaw.ssdd.pas.domain.pet.model.Breed;
import ch.zhaw.ssdd.pas.domain.pet.model.PetAdoptionStatus;
import ch.zhaw.ssdd.pas.domain.pet.model.Species;
import ch.zhaw.ssdd.pas.domain.service.PetManagementService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PetRestController.class)
@ContextConfiguration(classes = {
        PetRestController.class,
        PetManagementService.class,
        PetPersistenceAdapter.class
})
class PetRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private PetEntityRepository petEntityRepository;

    @Test
    void testSimpleSearch() throws Exception {
        PetEntity entity = new PetEntity();
        entity.setId(UUID.randomUUID());
        entity.setName("Mock");
        entity.setSpecies(new Species("Dog"));
        entity.setBreed(new Breed("Husky"));
        entity.setAdoptionStatus(PetAdoptionStatus.AVAILABLE);
        entity.setShelterId(UUID.randomUUID());

        PetDTO expectedPet = new PetDTO(
                "Mock",
                "Dog",
                "Husky",
                "AVAILABLE"
        );

        Mockito.when(petEntityRepository.search("")).thenReturn(List.of(entity));

        MvcResult mvcResult = mockMvc.perform(get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        PetDTO[] petDTOResponse = mapper.readValue(actualResponseBody, PetDTO[].class);
        assertArrayEquals(new PetDTO[]{expectedPet}, petDTOResponse);
    }

    @Test
    void testNothingFound() throws Exception {
        Mockito.when(petEntityRepository.search("")).thenReturn(new ArrayList<>());

        MvcResult mvcResult = mockMvc.perform(get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        PetDTO[] petDTOResponse = mapper.readValue(actualResponseBody, PetDTO[].class);
        assertArrayEquals(new PetDTO[]{}, petDTOResponse);
    }
}
