package shift.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shift.management.entity.RegisterShift;

public interface RegisterShiftRepository extends JpaRepository<RegisterShift, Integer> {
    RegisterShift findById(int id);

    RegisterShift findTopByOrderByIdDesc();
}
