package vn.edu.fpt.mss.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.mss.dto.request.EmployeeRequest;
import vn.edu.fpt.mss.dto.response.EmployeeResponse;
import vn.edu.fpt.mss.entity.Employee;
import vn.edu.fpt.mss.exception.ResourceNotFoundException;
import vn.edu.fpt.mss.repository.EmployeeRepository;
import vn.edu.fpt.mss.service.EmployeeService;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponse> findAll() {
        return employeeRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse findById(Integer id) {
        return toResponse(findEntity(id));
    }

    @Override
    @Transactional
    public EmployeeResponse create(EmployeeRequest request) {
        return toResponse(employeeRepository.save(toEntity(request, new Employee())));
    }

    @Override
    @Transactional
    public EmployeeResponse update(Integer id, EmployeeRequest request) {
        return toResponse(employeeRepository.save(toEntity(request, findEntity(id))));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        employeeRepository.delete(findEntity(id));
    }

    private Employee findEntity(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));
    }

    private Employee toEntity(EmployeeRequest request, Employee employee) {
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setTitle(request.getTitle());
        employee.setReportsTo(request.getReportsToId() == null
                ? null
                : employeeRepository.findById(request.getReportsToId())
                        .orElseThrow(() -> new ResourceNotFoundException("Employee", request.getReportsToId())));
        employee.setBirthDate(request.getBirthDate());
        employee.setHireDate(request.getHireDate());
        employee.setAddress(request.getAddress());
        employee.setCity(request.getCity());
        employee.setState(request.getState());
        employee.setCountry(request.getCountry());
        employee.setPostalCode(request.getPostalCode());
        employee.setPhone(request.getPhone());
        employee.setFax(request.getFax());
        employee.setEmail(request.getEmail());
        return employee;
    }

    private EmployeeResponse toResponse(Employee employee) {
        Employee manager = employee.getReportsTo();
        return EmployeeResponse.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .title(employee.getTitle())
                .reportsToId(manager == null ? null : manager.getEmployeeId())
                .reportsToName(manager == null ? null : manager.getFirstName() + " " + manager.getLastName())
                .birthDate(employee.getBirthDate())
                .hireDate(employee.getHireDate())
                .address(employee.getAddress())
                .city(employee.getCity())
                .state(employee.getState())
                .country(employee.getCountry())
                .postalCode(employee.getPostalCode())
                .phone(employee.getPhone())
                .fax(employee.getFax())
                .email(employee.getEmail())
                .build();
    }
}