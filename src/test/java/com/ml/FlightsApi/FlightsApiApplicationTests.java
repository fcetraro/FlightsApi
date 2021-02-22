package com.ml.FlightsApi;

import com.ml.FlightsApi.model.request.NewFlightReservationDTO;
import com.ml.FlightsApi.model.request.NewFullReservationDTO;
import com.ml.FlightsApi.stubs.FullReservationStub;
import com.ml.FlightsApi.util.Assembler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.ml.FlightsApi.stubs.FullReservationStub.getFullNewReservationStub;
import static com.ml.FlightsApi.stubs.NewFlightReservationStub.getFlightReservationStub;
import static com.ml.FlightsApi.stubs.PaymentMethodStub.getInvalidCreditPaymentMethod;
import static com.ml.FlightsApi.stubs.PaymentMethodStub.getInvalidDebitPaymentMethod;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FlightsApiApplicationTests {

	String url= "http://localhost:8080/api/v1/";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldGetAllAndBeOk() throws  Exception {
		String request =  url.concat("flights?destination=Bogotá&dateFrom=10/02/2021&dateTo=20/02/2021&origin=" +
				"Puerto Iguazú");
		String expectedResult = "[{\"code\":\"PIBA-1420\",\"origin\":\"Puerto Iguaz\u00fa\",\"destination\":" +
				"\"Bogot\u00e1\",\"seatType\":\"Business\",\"pricePerPerson\":43.2,\"dateFrom\":\"" +
				"2021-02-10T03:00:00.000+00:00\",\"dateTo\":\"2021-02-20T03:00:00.000+00:00\"}]";
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(expectedResult));;
	}

	@Test
	void shouldNotGetDestinationNotFoundFlight() throws  Exception {
		String request =  url.concat("flights?destination=Puerto Iguazúú&dateFrom=05/10/2021");
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	void shouldNotGetOriginNotFoundFlight() throws  Exception {
		String request =  url.concat("flights?origin=Puerto Iguazúú&dateFrom=05/10/2021");
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	void shouldGetBadRequestWrongDatesFormat() throws  Exception {
		String request =  url.concat("flights?dateFrom=01-31-2021");
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldGetFilterNotFound() throws  Exception {
		String request =  url.concat("flights?lateCheckOut=true");
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	void shouldGetBadRequestWrongDates() throws  Exception {

		String request =  url.concat("flights?destination=Puerto Iguazú&dateFrom=05/10/2021&dateTo=03/10/2021");
		this.mockMvc.perform(get(request))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
	@Test
	void shouldGetBadRequestWrongEmail() throws  Exception {
		String request =  url.concat("flight-reservation");
		NewFullReservationDTO stub = getFullNewReservationStub();
		stub.setUserName("wrongemail");
		String requestJson = "{"+ Assembler.transformToStringJson(stub)+"}";
		this.mockMvc.perform(post(request)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldGetBadRequestWrongCreditCard() throws  Exception {
		String request =  url.concat("flight-reservation");
		NewFullReservationDTO stubFull = getFullNewReservationStub();
		NewFlightReservationDTO stubReservation = getFlightReservationStub();
		stubReservation.setPaymentMethod(getInvalidCreditPaymentMethod());
		stubFull.setFlightReservation(stubReservation);
		String requestJson = "{"+ Assembler.transformToStringJson(stubFull)+"}";
		this.mockMvc.perform(post(request)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldGetBadRequestWrongDebitCard() throws  Exception {
		String request =  url.concat("flight-reservation");
		NewFullReservationDTO stubFull = getFullNewReservationStub();
		NewFlightReservationDTO stubReservation = getFlightReservationStub();
		stubReservation.setPaymentMethod(getInvalidDebitPaymentMethod());
		stubFull.setFlightReservation(stubReservation);
		String requestJson = "{"+ Assembler.transformToStringJson(stubFull)+"}";
		this.mockMvc.perform(post(request)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void shouldReservate() throws  Exception {
		String request =  url.concat("flight-reservation");
		NewFullReservationDTO stubFull = getFullNewReservationStub();
		String requestJson = "{"+ Assembler.transformToStringJson(stubFull)+"}";
		this.mockMvc.perform(post(request)
				.contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("86.4")));
	}
}
