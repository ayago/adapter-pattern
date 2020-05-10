package hexagonal.domain.order;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class NewOrderCommand {

    public final LocalDateTime creationDate;

    @NotBlank
    public final String userId;

    @NotEmpty
    @Valid
    public final List<OrderedItem> orderedItems;

    private NewOrderCommand(String userId, List<OrderedItem> orderedItems) {
        this.creationDate = LocalDateTime.now();
        this.userId = userId;
        this.orderedItems = orderedItems;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static OrderedItemBuilder itemBuilder(){
        return new OrderedItemBuilder();
    }

    public static class Builder {
        private String userId;
        private List<OrderedItem> orderedItems = new ArrayList<>();

        private Builder(){}

        public Builder setOrderedItems(List<OrderedItem> orderedItems) {
            this.orderedItems = orderedItems;
            return this;
        }

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public NewOrderCommand build(){
            return new NewOrderCommand(userId, unmodifiableList(orderedItems));
        }
    }

    public static class OrderedItemBuilder {
        private String notes;

        private String itemCode;

        private Integer quantity;

        public OrderedItemBuilder setNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public OrderedItemBuilder setItemCode(String itemCode) {
            this.itemCode = itemCode;
            return this;
        }

        public OrderedItemBuilder setQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderedItem build(){
            return new OrderedItem(notes, itemCode, quantity);
        }
    }

    public static class OrderedItem {

        @Length(max=100)
        final String notes;

        @NotBlank
        final String itemCode;

        @Min(1)
        @NotNull
        final Integer quantity;

        private OrderedItem(String notes, String itemCode, Integer quantity) {
            this.notes = notes;
            this.itemCode = itemCode;
            this.quantity = quantity;
        }
    }
}
