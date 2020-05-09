package domainpattern.api;

import java.util.List;

public class OrderParam {

    private List<Item> orderedItems = List.of();

    public List<Item> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<Item> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public static class Item {

        private String notes;

        private String itemCode;

        private Integer quantity;

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }
}
