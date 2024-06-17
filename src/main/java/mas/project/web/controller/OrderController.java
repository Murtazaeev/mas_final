package mas.project.web.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import mas.project.model.Order;
import mas.project.model.User;
import mas.project.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
@Data
@Slf4j
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customerOrders/{customerId}")
    public ResponseEntity<List<Order>> getCustomerUserDetails(@PathVariable UUID customerId) {
        var customerOrders = orderService.getCustomerOrdersByCustomerId(customerId);
        if (customerOrders.size() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerOrders, HttpStatus.OK);
    }

    @GetMapping("/orderDetails/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable UUID orderId) {
        var order = orderService.getOrderByOrderId(orderId);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}