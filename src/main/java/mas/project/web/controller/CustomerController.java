package mas.project.web.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import mas.project.dto.CustomerDetailsDTO;
import mas.project.model.Customer;
import mas.project.model.User;
import mas.project.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")
@Data
@Slf4j
public class CustomerController {

    private CustomerService service;

    public CustomerController(CustomerService customerService) {
        this.service = customerService;
    }

    @GetMapping("/userDetails/{customerId}")
    public ResponseEntity<CustomerDetailsDTO> getCustomerUserDetails(@PathVariable UUID customerId) {
        CustomerDetailsDTO userDetails = service.getCustomerDetails(customerId);
        if (userDetails == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDetails);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerDetailsDTO>> getAllCustomers() {
        List<CustomerDetailsDTO> customers = service.getAllCustomerDetails();
        if (customers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }
}