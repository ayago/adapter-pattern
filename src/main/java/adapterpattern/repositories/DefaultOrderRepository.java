package domainpattern.repositories;

import domainpattern.domain.order.Order;
import domainpattern.domain.order.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultOrderRepository implements OrderRepository {

    @Override
    public void save(Order order) {

    }
}
