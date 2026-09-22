package vn.edu.fpt.mss.service;

import java.util.List;
import vn.edu.fpt.mss.dto.request.CustomerRequest;
import vn.edu.fpt.mss.dto.response.CustomerResponse;

public interface CustomerService {

    List<CustomerResponse> findAll();

    List<CustomerResponse> findByLastName(String lastName);

    CustomerResponse findById(Integer id);

    CustomerResponse create(CustomerRequest request);

    CustomerResponse update(Integer id, CustomerRequest request);

    void delete(Integer id);
}