package ge.isbu.demo.repositories;

import ge.isbu.demo.entities.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select * from employees " +
            "where department_id = :departmentId and " +
            "(:searchText is null or concat(first_name, concat(' ' , last_name)) like :searchText)",
            countQuery = "select count(*) from employees " +
                    "where department_id = :departmentId and " +
                    "(:searchText is null or concat(first_name, concat(' ' , last_name)) like :searchText)",
            nativeQuery = true)
    Slice<Employee> search(@Param("departmentId") Long departmentId, @Param("searchText") String searchText, Pageable pageable);


//    @Query("From Employee where department.departmentId = :departmentId and " +
//            "(:searchText is null or concat(firstName, concat(' ' , lastName)) like :searchText)")
//    Slice<Employee> search(@Param("departmentId") Long departmentId, @Param("searchText") String searchText, Pageable pageable);

}
