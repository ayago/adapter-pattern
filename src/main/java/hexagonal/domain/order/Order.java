package hexagonal.domain.order;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toUnmodifiableList;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class Order {

    public final OrderId id;
    public final List<Item> items;

    private Order(OrderId orderId, List<Item> items) {
        this.id = orderId;
        this.items = items;
    }

    public static Order newOrder(NewOrderCommand command){
        List<Item> items = command.orderedItems.stream()
                .map(oI -> Item.builder()
                        .setItemCode(oI.itemCode)
                        .setNotes(oI.notes)
                        .setQuantity(oI.quantity)
                        .build()
                )
                .collect(toUnmodifiableList());

        OrderId id = new OrderId(command.userId, command.creationDate);

        return new Order(id, items);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, items);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("items", items)
                .toString();
    }
}
