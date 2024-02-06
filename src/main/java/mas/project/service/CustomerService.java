package mas.project.service;


import mas.project.dto.CustomerDetailsDTO;
import mas.project.model.Customer;
import mas.project.model.User;
import mas.project.repository.CustomerRepository;
import mas.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;


    public CustomerService(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    public List<CustomerDetailsDTO> getAllCustomerDetails() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> new CustomerDetailsDTO(
                customer.getId(),
                customer.getEmail(),
                customer.getUser().getName(),
                customer.getUser().getSurname()
        )).collect(Collectors.toList());
    }
    @Transactional
    public CustomerDetailsDTO getCustomerDetails(UUID customerId) {
        return customerRepository.findById(customerId)
                .map(customer -> new CustomerDetailsDTO(
                        customer.getId(),
                        customer.getEmail(),
                        customer.getUser().getName(),
                        customer.getUser().getSurname()))
                .orElse(null);
    }

    @Transactional
    public Customer getCustomer(UUID id) {
        return customerRepository.findById(id).get();
    }
}
