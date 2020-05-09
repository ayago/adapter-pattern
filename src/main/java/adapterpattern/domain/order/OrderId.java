package domainpattern.domain.order;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class OrderId {
    public final String userId;
    public final LocalDateTime creationDate;

    OrderId(String userId, LocalDateTime creationDate) {
        this.userId = userId;
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("userId", userId)
                .append("creationDate", creationDate)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderId orderId = (OrderId) o;
        return Objects.equals(userId, orderId.userId) &&
                Objects.equals(creationDate, orderId.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, creationDate);
    }
}
