package hexagonal.domain.order;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class Item {
    public final String notes;
    public final String itemCode;
    public final Integer quantity;

    private Item(String notes, String itemCode, Integer quantity) {
        this.notes = notes;
        this.itemCode = itemCode;
        this.quantity = quantity;
    }

    public static Builder builder(){
        return new Builder();
    }
    
    public static class Builder {
        private String notes;
        private String itemCode;
        private Integer quantity;

        private Builder(){}

        public Builder setNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder setItemCode(String itemCode) {
            this.itemCode = itemCode;
            return this;
        }

        public Builder setQuantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Item build(){
            return new Item(notes, itemCode, quantity);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(notes, item.notes) &&
                Objects.equals(itemCode, item.itemCode) &&
                Objects.equals(quantity, item.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notes, itemCode, quantity);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("notes", notes)
                .append("itemCode", itemCode)
                .append("quantity", quantity)
                .toString();
    }
}
