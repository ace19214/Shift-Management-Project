package shift.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shift.management.entity.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {
    Salary findById(int id);

    Salary findByRole(String role);

}
