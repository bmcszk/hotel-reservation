package blazesoft.hotelreservation.services;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import blazesoft.hotelreservation.exceptions.NoRoomAvailableException;
import blazesoft.hotelreservation.exceptions.NumberOfPeopleExceeded;
import blazesoft.hotelreservation.model.RoomType;
import blazesoft.hotelreservation.model.dtos.ReservationDto;
import blazesoft.hotelreservation.model.dtos.ReservationRequest;
import blazesoft.hotelreservation.repo.ReservationRepository;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    public void shouldAddReservation() {
        ReservationRequest reservationRequest = ReservationRequest.builder()
                .numberOfPeople(5)
                .startDate(LocalDate.now().plusDays(3))
                .endDate(LocalDate.now().plusDays(4))
                .userName("unitUser")
                .build();
        ReservationDto result = reservationService.addResevation(reservationRequest);
        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull();
        assertThat(result.getStartDate()).isEqualTo(reservationRequest.getStartDate());
        assertThat(result.getEndDate()).isEqualTo(reservationRequest.getEndDate());
        assertThat(result.getUserName()).isEqualTo(reservationRequest.getUserName());
        assertThat(result.getRoomType()).isEqualTo(RoomType.suite);
        assertThat(result.getRoomNumber()).isNotNull();
    }

    @Test(expected = NumberOfPeopleExceeded.class)
    public void shouldExceedNumberOfPeople() {
        ReservationRequest reservationRequest = ReservationRequest.builder()
                .numberOfPeople(9)
                .startDate(LocalDate.now().plusDays(3))
                .endDate(LocalDate.now().plusDays(4))
                .userName("unitUser")
                .build();
        ReservationDto result = reservationService.addResevation(reservationRequest);
        assertThat(result).isNotNull();
    }

    @Test(expected = NoRoomAvailableException.class)
    public void shouldNotAllowReservationIfRoomIsNotAvailable() {
        ReservationRequest reservationRequest1 = ReservationRequest.builder()
                .numberOfPeople(8)
                .startDate(LocalDate.now().plusDays(3))
                .endDate(LocalDate.now().plusDays(5))
                .userName("unitUser")
                .build();
        ReservationDto result = reservationService.addResevation(reservationRequest1);
        assertThat(result).isNotNull();
        ReservationRequest reservationRequest2 = ReservationRequest.builder()
                .numberOfPeople(8)
                .startDate(LocalDate.now().plusDays(4))
                .endDate(LocalDate.now().plusDays(6))
                .userName("unitUser")
                .build();
        result = reservationService.addResevation(reservationRequest2);
    }

    @Test
    public void shouldUpdateReservation() {
        ReservationRequest reservationRequest1 = ReservationRequest.builder()
                .numberOfPeople(8)
                .startDate(LocalDate.now().plusDays(3))
                .endDate(LocalDate.now().plusDays(5))
                .userName("unitUser")
                .build();
        ReservationDto result = reservationService.addResevation(reservationRequest1);
        ReservationRequest reservationRequest2 = ReservationRequest.builder()
                .numberOfPeople(8)
                .startDate(LocalDate.now().plusDays(4))
                .endDate(LocalDate.now().plusDays(6))
                .userName("unitUser")
                .build();
        reservationService.updateReservation(result.getId(), reservationRequest2);
    }

    @Test
    public void shouldFindReservationsByRoomNumber() {
        ReservationRequest reservationRequest1 = ReservationRequest.builder()
                .numberOfPeople(8)
                .startDate(LocalDate.now().plusDays(3))
                .endDate(LocalDate.now().plusDays(4))
                .userName("unitUser")
                .build();
        reservationService.addResevation(reservationRequest1);
        ReservationRequest reservationRequest2 = ReservationRequest.builder()
                .numberOfPeople(8)
                .startDate(LocalDate.now().plusDays(13))
                .endDate(LocalDate.now().plusDays(14))
                .userName("unitUser")
                .build();
        reservationService.addResevation(reservationRequest2);

        List<ReservationDto> result = reservationService.getReservationsByRoom("31");
        assertThat(result).hasSize(2);
    }

    @After
    public void tearDown() {
        reservationRepository.deleteAll();
    }

    
}