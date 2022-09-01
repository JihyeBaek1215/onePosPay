package onepos.domainkafka;

import java.text.SimpleDateFormat;
import java.util.Date;

import onepos.AbstractEvent;

public class Ordered  extends AbstractEvent {

  int id; //주문번호
  int tableNo; //테이블 번호
  int storeId; //매장ID
  String status;
  OrderItem orderItems = new OrderItem();

  public int getOrderId() {
    return id;
  }
  public void setOrderId(int id) {
    this.id = id;
  }
  public int getTableNum() {
    return tableNo;
  }
  public void setTableNum(int tableNo) {
    this.tableNo = tableNo;
  }

  public int getStoreId() {
    return storeId;
  }
  public void setStoreId(int storeId) {
    this.storeId = storeId;
  }

  public String getStatus() {
    return status;
}
public void setStatus(String status) {
    this.status = status;
}
public OrderItem getOrderItems(){
    return orderItems;
}
public void setOrderItems(OrderItem orderItems){
    this.orderItems= orderItems;
}
  public String toJson() {
    return null;
  }

}
