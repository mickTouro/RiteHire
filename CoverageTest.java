
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.junit.Assert;

class CoverageTest {
	
	@Test
	void testGivenTestCase() {

		List<Coverage> coverages = new ArrayList<Coverage>(
			Arrays.asList(
					new Coverage(1, 20),
					new Coverage(21, 30),
					new Coverage(15, 25),
					new Coverage(28, 40),
					new Coverage(50, 60),
					new Coverage(61, 200)));
		
		List<Coverage> results = Coverage.getLongestContinuousCoverage(coverages);
		
		Assert.assertEquals("Number of coverages returned", 1, results.size());
		
		Coverage result = results.get(0);
		
		Assert.assertEquals("Coverage term start", 50, result.start);
		Assert.assertEquals("Coverage term end", 200, result.end);
	}

	@Test
	void testOverlapAndConnected() {

		List<Coverage> coverages = new ArrayList<Coverage>(
			Arrays.asList(
					new Coverage(1, 20),
					new Coverage(21, 30),
					new Coverage(28, 70),
					new Coverage(71, 100),
					new Coverage(200, 260),
					new Coverage(260, 280),
					new Coverage(220, 250),
					new Coverage(220, 292),
					new Coverage(200, 210)));
		
		List<Coverage> results = Coverage.getLongestContinuousCoverage(coverages);
		
		Assert.assertEquals("Number of coverages returned", 1, results.size());
		
		Coverage result = results.get(0);
		
		Assert.assertEquals("Coverage term start", 1, result.start);
		Assert.assertEquals("Coverage term end", 100, result.end);
	}

	@Test
	void testNoOverlap() {

		List<Coverage> coverages = new ArrayList<Coverage>(
			Arrays.asList(
					new Coverage(1, 20),
					new Coverage(30, 32),
					new Coverage(35, 40)));
		
		List<Coverage> results = Coverage.getLongestContinuousCoverage(coverages);
		
		Assert.assertEquals("Number of coverages returned", 1, results.size());
		
		Coverage result = results.get(0);
		
		Assert.assertEquals("Coverage term start", 1, result.start);
		Assert.assertEquals("Coverage term end", 20, result.end);
	}
	


	@Test
	void testMultipleLongest() {

		List<Coverage> coverages = new ArrayList<Coverage>(
			Arrays.asList(
					new Coverage(1, 20),
					new Coverage(41, 60),
					new Coverage(80, 90)));
		
		List<Coverage> results = Coverage.getLongestContinuousCoverage(coverages);
		
		Assert.assertEquals("Number of coverages returned", 2, results.size());
		
		Coverage result1 = results.get(0);
		
		Assert.assertEquals("Coverage term start", 1, result1.start);
		Assert.assertEquals("Coverage term end", 20, result1.end);
		
		Coverage result2 = results.get(1);
		
		Assert.assertEquals("Coverage term start", 41, result2.start);
		Assert.assertEquals("Coverage term end", 60, result2.end);
	}
	
	@Test
	void testMultipleLongestOverlaps() {

		List<Coverage> coverages = new ArrayList<Coverage>(
			Arrays.asList(
					new Coverage(1, 10),
					new Coverage(10, 20),
					new Coverage(80, 90),
					new Coverage(101, 110),
					new Coverage(110, 120)));
		
		List<Coverage> results = Coverage.getLongestContinuousCoverage(coverages);
		
		Assert.assertEquals("Number of coverages returned", 2, results.size());
		
		Coverage result1 = results.get(0);
		
		Assert.assertEquals("Coverage term start", 1, result1.start);
		Assert.assertEquals("Coverage term end", 20, result1.end);
		
		Coverage result2 = results.get(1);
		
		Assert.assertEquals("Coverage term start", 101, result2.start);
		Assert.assertEquals("Coverage term end", 120, result2.end);
	}
}
