package vn.edu.fpt.mss.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.fpt.mss.dto.request.CustomerRequest;
import vn.edu.fpt.mss.dto.response.CustomerResponse;
import vn.edu.fpt.mss.entity.Customer;
import vn.edu.fpt.mss.entity.Employee;
import vn.edu.fpt.mss.exception.ResourceNotFoundException;
import vn.edu.fpt.mss.repository.CustomerRepository;
import vn.edu.fpt.mss.repository.EmployeeRepository;
import vn.edu.fpt.mss.service.CustomerService;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> findAll() {
        return customerRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> findByLastName(String lastName) {
        return customerRepository.findByLastNameContainingIgnoreCase(lastName)
                .stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponse findById(Integer id) {
        return toResponse(findEntity(id));
    }

    @Override
    @Transactional
    public CustomerResponse create(CustomerRequest request) {
        return toResponse(customerRepository.save(toEntity(request, new Customer())));
    }

    @Override
    @Transactional
    public CustomerResponse update(Integer id, CustomerRequest request) {
        return toResponse(customerRepository.save(toEntity(request, findEntity(id))));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        customerRepository.delete(findEntity(id));
    }

    private Customer findEntity(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", id));
    }

    private Customer toEntity(CustomerRequest request, Customer customer) {
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setCompany(request.getCompany());
        customer.setAddress(request.getAddress());
        customer.setCity(request.getCity());
        customer.setState(request.getState());
        customer.setCountry(request.getCountry());
        customer.setPostalCode(request.getPostalCode());
        customer.setPhone(request.getPhone());
        customer.setFax(request.getFax());
        customer.setEmail(request.getEmail());
        customer.setSupportRep(request.getSupportRepId() == null
                ? null
                : employeeRepository.findById(request.getSupportRepId())
                        .orElseThrow(() -> new ResourceNotFoundException("Employee", request.getSupportRepId())));
        customer.setDeleted(false);
        return customer;
    }

    private CustomerResponse toResponse(Customer customer) {
        Employee rep = customer.getSupportRep();
        return CustomerResponse.builder()
                .customerId(customer.getCustomerId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .company(customer.getCompany())
                .address(customer.getAddress())
                .city(customer.getCity())
                .state(customer.getState())
                .country(customer.getCountry())
                .postalCode(customer.getPostalCode())
                .phone(customer.getPhone())
                .fax(customer.getFax())
                .email(customer.getEmail())
                .supportRepId(rep == null ? null : rep.getEmployeeId())
                .supportRepName(rep == null ? null : rep.getFirstName() + " " + rep.getLastName())
                .build();
    }
}