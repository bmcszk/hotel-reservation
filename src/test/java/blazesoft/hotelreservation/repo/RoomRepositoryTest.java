package blazesoft.hotelreservation.repo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import blazesoft.hotelreservation.model.Room;
import blazesoft.hotelreservation.model.RoomType;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomRepositoryTest {

    @Autowired
    RoomRepository roomRepository;

    @Test
    public void shouldGetAvailableRoomsOnly() {
        List<Room> result = roomRepository
            .findAvailableRooms(LocalDate.now(), LocalDate.now().plusDays(1), 
                Arrays.asList(RoomType.basic), null);
        assertThat(result).hasSize(3);

        result = roomRepository
            .findAvailableRooms(LocalDate.now(), LocalDate.now().plusDays(1), 
                Arrays.asList(RoomType.suite), null);
        assertThat(result).hasSize(2);

        result = roomRepository
            .findAvailableRooms(LocalDate.now(), LocalDate.now().plusDays(1), 
                Arrays.asList(RoomType.basic, RoomType.suite), null);
        assertThat(result).hasSize(5);
    }
    
    
}