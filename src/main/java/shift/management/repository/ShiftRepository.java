package shift.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shift.management.entity.Shift;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {
}
