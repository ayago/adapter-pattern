package hexagonal.repositories;

import hexagonal.domain.order.Order;
import hexagonal.domain.order.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultOrderRepository implements OrderRepository {

    Logger logger = LoggerFactory.getLogger(DefaultOrderRepository.class);

    @Override
    public void save(Order order) {
        logger.debug("Successfully saved order: {}", order);
    }
}
