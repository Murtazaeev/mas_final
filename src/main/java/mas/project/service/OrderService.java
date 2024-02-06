package mas.project.service;

import lombok.RequiredArgsConstructor;
import mas.project.model.Order;
import mas.project.model.enumeration.OrderState;
import mas.project.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order>  getCustomerOrdersByCustomerId(UUID id){
        return orderRepository.findCustomerOrdersByCustomerId(id);
    }

    public Order getOrderByOrderId(UUID id){
        return orderRepository.findOrderById(id);
    }

    public OrderState getOrderStatus(UUID id){
        var order = orderRepository.findOrderById(id);
        return order.getOrderState();
    }

    public boolean canGetInvoice(UUID id){
        OrderState currentOrderState = getOrderStatus(id);
        if(currentOrderState == OrderState.WAITING_FOR_SUPPLY || currentOrderState == OrderState.CANCELED ){
            return false;
        }
        return true;
    }
 //   public List<Order>  getCustomerOrdersByCustomerIdTrue(String customerStringUUID){
 //       return orderRepository.findCustomerOrdersByCustomerId(customerStringUUID);
 //   }

}
