package vn.edu.fpt.mss.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.edu.fpt.mss.dto.client.CustomerInfo;

@FeignClient(name = "customer-service", url = "${app.clients.customer-url}")
public interface CustomerClient {

    @GetMapping("/api/v1/customers/{id}")
    CustomerInfo getCustomer(@PathVariable("id") Integer id);
}