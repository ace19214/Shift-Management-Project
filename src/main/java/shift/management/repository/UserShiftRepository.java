package shift.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shift.management.entity.UserShift;

@Repository
public interface UserShiftRepository extends JpaRepository<UserShift, Integer> {
}
