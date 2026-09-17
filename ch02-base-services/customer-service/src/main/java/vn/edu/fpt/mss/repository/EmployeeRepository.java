package vn.edu.fpt.mss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.fpt.mss.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}