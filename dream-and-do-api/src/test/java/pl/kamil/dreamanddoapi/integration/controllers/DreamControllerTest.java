package pl.kamil.dreamanddoapi.integration.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.kamil.dreamanddoapi.application.DreamController;
import pl.kamil.dreamanddoapi.application.DreamDTO;
import pl.kamil.dreamanddoapi.application.DreamMapper;
import pl.kamil.dreamanddoapi.application.DreamMapperImpl;
import pl.kamil.dreamanddoapi.configuration.Config;
import pl.kamil.dreamanddoapi.domain.DreamsFacade;
import pl.kamil.dreamanddoapi.domain.exceptions.MissingDreamException;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.kamil.dreamanddoapi.TestUtils.provideDreams;

@Import({DreamMapperImpl.class, Config.class})
@WebMvcTest(DreamController.class)
public class DreamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DreamMapper dm;

    @MockitoBean
    private DreamsFacade gds;

    @Test
    void shouldReturn201_whenCreatedDream() throws Exception {
        // given
        Dream dream = Dream.builder()
                .id(1L)
                .title("Napisać książkę")
                .description("")
                .build();
        when(gds.save(any(Dream.class)))
                .thenReturn(dream);
        // when
        MvcResult result = mockMvc.perform(
                        post("/api/dreams/create")
                                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                                .content(om.writeValueAsString(dm.toDreamDTO(dream)))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        DreamDTO actual = om.readValue(result.getResponse().getContentAsString(),
                DreamDTO.class);
        // then
        DreamDTO expected = dm.toDreamDTO(dream);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturn400_whenDreamIsNull() throws Exception {
        // given
        when(gds.save(any(Dream.class)))
                .thenThrow(MissingDreamException.class);
        // when
        MvcResult result = mockMvc.perform(
                        post("/api/dreams/create")
                                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                                .content("{}")
                )
                .andExpect(status().isBadRequest())
                .andReturn();
        // then
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        Assertions.assertEquals(expected.value(), result.getResponse().getStatus());
    }

    @Test
    void shouldReturn200_whenDreamsExistInDreamsTable() throws Exception {
        // given
        when(gds.findAll())
                .thenReturn(provideDreams());
        // when
        MvcResult result = mockMvc.perform(get("/api/dreams/getAll"))
                .andExpect(status().isOk())
                .andReturn();
        // then
        List<DreamDTO> actual = om.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        List<DreamDTO> expected = provideDreams().stream()
                .map(dm::toDreamDTO)
                .toList();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void whenEmptyDreamsTable_shouldReturnNoContent() throws Exception {
        // given
        when(gds.findAll())
                .thenReturn(List.of());
        // when, then
        mockMvc.perform(get("/api/dreams/getAll"))
                .andExpect(status().isNoContent());
    }
}
