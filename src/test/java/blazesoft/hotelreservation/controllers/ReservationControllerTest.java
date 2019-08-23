package blazesoft.hotelreservation.controllers;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import blazesoft.hotelreservation.repo.ReservationRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    public void shouldGetEmptyListOfReservations() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/reservations")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void shouldGetReturnEmptyStatusCode() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/reservations/123")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnBadRequestOnEmptyRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/reservations")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCorrectlyAddReservation() throws Exception {
        String startDate = LocalDate.now().plusDays(3).toString();
        String endDate = LocalDate.now().plusDays(4).toString();
        String content = String.format("{ \"userName\" : \"unittest\", \"numberOfPeople\": 3, \"startDate\": \"%s\", \"endDate\": \"%s\" }"
                , startDate, endDate);

        mvc.perform(MockMvcRequestBuilders
                .post("/reservations")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void shouldNotAllowStartDateAfterEndDate() throws Exception {
        String startDate = LocalDate.now().plusDays(5).toString();
        String endDate = LocalDate.now().plusDays(4).toString();
        String content = String.format("{ \"userName\" : \"unittest\", \"numberOfPeople\": 3, \"startDate\": \"%s\", \"endDate\": \"%s\" }"
                , startDate, endDate);

        mvc.perform(MockMvcRequestBuilders
                .post("/reservations")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldNotAllowTooManyPeople() throws Exception {
        String startDate = LocalDate.now().plusDays(3).toString();
        String endDate = LocalDate.now().plusDays(4).toString();
        String content = String.format("{ \"userName\" : \"unittest\", \"numberOfPeople\": 10, \"startDate\": \"%s\", \"endDate\": \"%s\" }"
                , startDate, endDate);

        mvc.perform(MockMvcRequestBuilders
                .post("/reservations")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isBadRequest());
    }

    @After
    public void tearDown() {
        reservationRepository.deleteAll();
    }


}