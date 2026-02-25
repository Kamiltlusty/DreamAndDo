package pl.kamil.dreamanddoapi.integration.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.kamil.dreamanddoapi.application.DreamController;
import pl.kamil.dreamanddoapi.domain.DreamsFacade;
import pl.kamil.dreamanddoapi.infrastracture.entities.Dream;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.kamil.dreamanddoapi.TestUtils.provideDreams;

@WebMvcTest(DreamController.class)
public class DreamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @MockitoBean
    private DreamsFacade gds;

    @Test
    void shouldReturnOK() throws Exception {
        // given
        when(gds.findAll())
                .thenReturn(provideDreams());
        // when
        MvcResult result = mockMvc.perform(get("/api/getDreams"))
                .andExpect(status().isOk())
                .andReturn();
        // then
        List<Dream> dreams = om.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<>() {
                });

        List<Dream> expected = provideDreams();
        Assertions.assertEquals(expected, dreams);
    }

    @Test
    void shouldReturnNoContent() throws Exception {
        // given
        when(gds.findAll())
                .thenReturn(List.of());
        // when, then
        mockMvc.perform(get("/api/getDreams"))
                .andExpect(status().isNoContent());
    }
}
