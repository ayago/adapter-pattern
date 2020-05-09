package hexagonal.repositories;

import hexagonal.domain.order.Order;
import hexagonal.domain.order.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultOrderRepository implements OrderRepository {

    @Override
    public void save(Order order) {

    }
}
