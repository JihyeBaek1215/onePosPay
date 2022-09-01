package onepos.domainkafka;
import java.util.*;
import onepos.AbstractEvent;

public class OrderCancelled extends AbstractEvent {

    private int id;
    private OrderStatus status;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
