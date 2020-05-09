package domainpattern.domain.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public String newOrder(@Valid NewOrderCommand command){
        Order newOrder = Order.newOrder(command);
        orderRepository.save(newOrder);
        return newOrder.id.toString();
    }
}
