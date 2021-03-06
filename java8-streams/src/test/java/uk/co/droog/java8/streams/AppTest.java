package uk.co.droog.java8.streams;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	/**
	 * 
	 */
	@Test
	public void testStreamFromArrays(){
		System.out.println("AppTest.testStreamFromArrays");
		Arrays.asList("a1","a2","a3")
			.stream()
			.findFirst()
			.ifPresent(System.out::println);
		
	}
	
	@Test
	public void testStreamFromArrays_mapToString(){
		System.out.println("AppTest.testStreamFromArrays_mapToString");
		String result = Arrays.asList("a1","a2","a3")
			.stream()
			.findFirst()
			.get();
		assertThat(result, is("a1"));
	}
	
	/**
	 * 
	 */
	@Test
	public void testStreamOf(){
		System.out.println("AppTest.testStreamOf");
		List<String> resultList = Stream.of("a1","a2","a3")
			.collect(Collectors.toList());
		assertThat(resultList, hasItem("a1"));
		assertThat(resultList, hasItem("a2"));
		assertThat(resultList, hasItem("a3"));
	}
	
	@Test
	public void testIntStream_forEach(){
		System.out.println("AppTest.testIntStream_forEach");
		IntStream.range(1, 4)
			.forEach(System.out::println);
	}
	
	@Test
	public void testIntStream_mapOperation(){
		System.out.println("AppTest.testIntStream_mapOperation");
		IntStream.range(1, 4)
		.map(n -> 2* n + 1)
		.forEach(System.out::println);
	}
	
	@Test
	public void testIntStream_mapOperationAverageToIt(){
		System.out.println("AppTest.testIntStream_mapOperationAverageToIt");
		Double result = IntStream.range(1, 4)
		.map(n -> 2* n + 1)
		.average()
		.getAsDouble();
		 
		assertThat(result, equalTo(5.0D));

	}
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	@Test
	public void testStreamFilter_StartsWith() {
		System.out.println("AppTest.testStreamFilter_StartsWith");
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
	
	@Test
	public void testStreamFilter_ByObject() {
		System.out.println("AppTest.testStreamFilter_FilterObject");
		SimpleDTO obj1 = new SimpleDTO(1, "Um");
		SimpleDTO obj2 = new SimpleDTO(2, "Dois");
		List<SimpleDTO> myList = Arrays.asList(obj1, obj2);
		
		List<SimpleDTO> result = myList
			.stream()
			.filter(s -> s.getId() <= 1)
			.sorted()
			.collect(Collectors.toList());
		
		assertThat(result, contains(obj1));
		assertThat(result, not(contains(obj2)));
		assertThat(result.stream().allMatch(d-> d.equals(obj1)),is(true));
	}
	
	@Test
	public void testStreamFilter_AllMatch() {
		System.out.println("AppTest.testStreamFilter_AllMatch");
		SimpleDTO obj1 = new SimpleDTO(1, "Um");
		SimpleDTO obj2 = new SimpleDTO(2, "Um");
		List<SimpleDTO> myList = Arrays.asList(obj1, obj2);
		
		assertThat(myList.stream().allMatch(d-> d.getId() <= 1),is(false));
		assertThat(myList.stream().allMatch(d-> d.getTitle().equals("Um")),is(true));
	}
	
	@Test
	public void testStreamFilter_AnyMatch() {
		System.out.println("AppTest.testStreamFilter_AnyMatch");
		SimpleDTO obj1 = new SimpleDTO(1, "Um");
		SimpleDTO obj2 = new SimpleDTO(2, "Dois");
		List<SimpleDTO> myList = Arrays.asList(obj1, obj2);
				
		assertThat(myList.stream().anyMatch(d-> d.getId() <= 1),is(true));
		assertThat(myList.stream().anyMatch(d-> d.getTitle().equals("Dois")),is(true));
		assertThat(myList.stream().anyMatch(d-> d.equals(obj1)),is(true));
		assertThat(myList.stream().anyMatch(d-> d.equals(new SimpleDTO(3, "tres"))),is(false));
	}
}
