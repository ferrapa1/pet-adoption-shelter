package ch.zhaw.ssdd.pas.adapters.inbound.rest;

import ch.zhaw.ssdd.pas.adapters.inbound.rest.dto.PetDTO;
import ch.zhaw.ssdd.pas.adapters.outbound.jpa.*;
import ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.entity.ShelterEntity;
import ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.repository.UserEntityRepository;
import ch.zhaw.ssdd.pas.domain.pet.model.Breed;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ch.zhaw.ssdd.pas.adapters.inbound.rest.PetController.BASE_PATH;
import static ch.zhaw.ssdd.pas.domain.pet.model.PetAdoptionStatus.AVAILABLE;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PetController.class)
@ContextConfiguration(classes = {
        PetController.class,
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

    @MockitoBean
    private UserEntityRepository userEntityRepository;

    @MockitoBean
    private CommentEntityRepository commentEntityRepository;

    @MockitoBean
    private PictureEntityRepository pictureEntityRepository;

    @Test
    void testSimpleSearch() throws Exception {
        ShelterEntity shelterEntity = new ShelterEntity(UUID.randomUUID());

        PetEntity petEntity = new PetEntity(UUID.randomUUID());
        petEntity.setName("Mock");
        petEntity.setDateOfBirth(LocalDate.now());
        petEntity.setSpecies(new Species("Dog"));
        petEntity.setBreed(new Breed("Husky"));
        petEntity.setAdoptionStatus(AVAILABLE);
        petEntity.setShelterId(shelterEntity.getId());

        PetDTO expectedPet = new PetDTO(
                "Mock",
                "Dog",
                "Husky",
                AVAILABLE
        );

        Mockito.when(userEntityRepository.findById(any())).thenReturn(Optional.of(shelterEntity));
        Mockito.when(petEntityRepository.search("")).thenReturn(List.of(petEntity));

        MvcResult mvcResult = mockMvc.perform(get(BASE_PATH)
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

        MvcResult mvcResult = mockMvc.perform(get(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        PetDTO[] petDTOResponse = mapper.readValue(actualResponseBody, PetDTO[].class);
        assertArrayEquals(new PetDTO[]{}, petDTOResponse);
    }
}
