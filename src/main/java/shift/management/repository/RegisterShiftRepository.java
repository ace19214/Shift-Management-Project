package shift.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shift.management.entity.RegisterShift;

import java.util.List;

@Repository
public interface RegisterShiftRepository extends JpaRepository<RegisterShift, Integer> {
    RegisterShift findById(int id);

    RegisterShift findTopByOrderByIdDesc();

    List<RegisterShift> findAllByShiftIDAndStatus(int shiftID, String status);

    RegisterShift findByUserIDAndShiftIDAndStatus(String username, int shiftID, String status);
}
