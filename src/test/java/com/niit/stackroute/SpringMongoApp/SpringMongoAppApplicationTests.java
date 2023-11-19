package com.niit.stackroute.SpringMongoApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
class SpringMongoAppApplicationTests {

	@Test
	void contextLoads() {

		List mockedList = mock(List.class);

		//using mock object
		mockedList.add("one");
		mockedList.clear();

		//verification
		verify(mockedList).add("one");
		verify(mockedList).clear();
	}

	@Test
	void stubMethods(){
		//You can mock concrete classes, not just interfaces
		LinkedList mockedList = mock(LinkedList.class);

		//stubbing
		when(mockedList.get(0)).thenReturn("first");
		when(mockedList.get(1)).thenThrow(new RuntimeException());

		//following prints "first"
		System.out.println(mockedList.get(0));

		//following throws runtime exception
	//	System.out.println(mockedList.get(1));

		//following prints "null" because get(999) was not stubbed
		System.out.println(mockedList.get(999));

		//Although it is possible to verify a stubbed invocation, usually it's just redundant
		//If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
		//If your code doesn't care what get(0) returns, then it should not be stubbed.
		verify(mockedList).get(0);
	}

}
