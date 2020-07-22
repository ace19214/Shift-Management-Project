package shift.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shift.management.entity.UserShift;

import java.util.List;

@Repository
public interface UserShiftRepository extends JpaRepository<UserShift, Integer> {

    int countByShiftID(int shiftID);

    UserShift findByUserIDAndShiftID(String username, int shiftID);

    List<UserShift> findAllByShiftID(int shiftId);
}
