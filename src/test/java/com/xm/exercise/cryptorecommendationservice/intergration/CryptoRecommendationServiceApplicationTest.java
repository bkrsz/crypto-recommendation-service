package com.xm.exercise.cryptorecommendationservice.intergration;

import com.xm.exercise.cryptorecommendationservice.api.controller.RecommendationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CryptoRecommendationServiceApplicationTest {

	@Autowired
	private RecommendationController recommendationController;

	@Test
	void contextLoads() {
		assertNotNull(recommendationController);
	}
}
