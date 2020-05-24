package shift.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shift.management.entity.Schedule;

import java.sql.Date;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Schedule findByDate(Date date);

    Schedule findById(int id);
}
