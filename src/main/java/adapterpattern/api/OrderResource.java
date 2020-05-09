package domainpattern.api;

import domainpattern.domain.order.NewOrderCommand;
import domainpattern.domain.order.NewOrderCommand.OrderedItem;
import domainpattern.domain.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderResource {

    @Autowired
    OrderService orderService;

    @PostMapping("/api/orders")
    public String newOrderId(@RequestBody OrderParam orderParam){
        List<OrderedItem> items = orderParam.getOrderedItems().stream()
                .map(o -> NewOrderCommand.itemBuilder()
                        .setItemCode(o.getItemCode())
                        .setQuantity(o.getQuantity())
                        .setNotes(o.getNotes())
                        .build()
                )
                .collect(Collectors.toList());

        NewOrderCommand command = NewOrderCommand.builder()
                .setUserId("12345")
                .setOrderedItems(items)
                .build();

        return orderService.newOrder(command);
    }
}
