package hexagonal.domain.order;

public interface OrderRepository {
    void save(Order order);
}
