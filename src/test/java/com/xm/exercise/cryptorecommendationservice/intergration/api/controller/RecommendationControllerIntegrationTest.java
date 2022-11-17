package com.xm.exercise.cryptorecommendationservice.intergration.api.controller;

import com.xm.exercise.cryptorecommendationservice.api.controller.RecommendationController;
import com.xm.exercise.cryptorecommendationservice.application.service.FileReaderService;
import com.xm.exercise.cryptorecommendationservice.application.service.RecommendationService;
import com.xm.exercise.cryptorecommendationservice.testUtils.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(RecommendationController.class)
public class RecommendationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecommendationService recommendationService;

    @Mock
    private FileReaderService fileReaderService;

    @InjectMocks
    private RecommendationController recommendationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        recommendationController = new RecommendationController(recommendationService);
        mockMvc = MockMvcBuilders.standaloneSetup(recommendationController).build();
    }

    @Test
    public void getAllCryptosNormalizedSuccessfully() throws Exception {
        Mockito.when(fileReaderService.readFiles()).thenReturn(TestData.TEST_CRYPTO_LIST);

        mockMvc.perform(MockMvcRequestBuilders.get("/crypto/normalized")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getCryptoValuesSuccessfully() throws Exception {
        Mockito.when(fileReaderService.readFiles()).thenReturn(TestData.TEST_CRYPTO_LIST);

        mockMvc.perform(MockMvcRequestBuilders.get("/crypto/values/BTC")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getHighestNormalizedRangeSuccessfully() throws Exception {
        Mockito.when(fileReaderService.readFiles()).thenReturn(TestData.TEST_CRYPTO_LIST);

        mockMvc.perform(MockMvcRequestBuilders.get("/crypto/highest-normalized/2022-01-12")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
