package uk.co.droog.java8.streams;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	@Test
	public void streamFilterTest() {
		List<String> myList = Arrays.asList("a1","a2","b1","b2","c1","c2");
		
		List<String> result = myList
			.stream()
			.filter(s -> s.startsWith("c"))
			.map(String::toUpperCase)
			.sorted()
			.collect(Collectors.toList());
		
		assertThat(result, contains("C1","C2"));
		assertThat(result, not(contains("c1","c2")));
	}
}
