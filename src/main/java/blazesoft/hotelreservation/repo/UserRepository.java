package blazesoft.hotelreservation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import blazesoft.hotelreservation.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
    
}