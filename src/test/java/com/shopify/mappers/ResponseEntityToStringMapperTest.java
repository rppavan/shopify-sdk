package com.shopify.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

class ResponseEntityToStringMapperTest {

	@Test
	void givenSomeValuesWhenMappingResponseEntityToAStringThenExpectCorrectValues() throws Exception {
		final Response response = mock(Response.class);

		final String expectedResponseBodyString = "{\"error\": \"something went wrong.\"}";
		final InputStream expectedResponseStream = new ByteArrayInputStream(
				expectedResponseBodyString.getBytes(StandardCharsets.UTF_8));
		when(response.getEntity()).thenReturn(expectedResponseStream);
		final String actualResponseBodyString = ResponseEntityToStringMapper.map(response);
		assertEquals(expectedResponseBodyString, actualResponseBodyString);

	}

	@Test
	void givenSomeExceptionOccursWhenMappingResponseEntityToAStringThenExpectNullValue() throws Exception {
		final Response response = mock(Response.class);

		when(response.getEntity()).thenThrow(new NullPointerException());
		final String actualResponseBodyString = ResponseEntityToStringMapper.map(response);
		assertNull(actualResponseBodyString);

	}

}
