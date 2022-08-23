package onepos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ordered  extends AbstractEvent {

  int orderId; //주문번호
  int tableNum; //테이블 번호
  int price; //가격
  int storeId; //매장ID
  String storeName; //매장명
  String payTool; //계산수단
  String payStatus; // 계산결과
  Date payDate; //계산 시간

  public int getOrderId() {
    return orderId;
  }
  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }
  public int getTableNum() {
    return tableNum;
  }
  public void setTableNum(int tableNum) {
    this.tableNum = tableNum;
  }
  public int getPrice() {
    return price;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public int getStoreId() {
    return storeId;
  }
  public void setStoreId(int storeId) {
    this.storeId = storeId;
  }
  public String getStoreName() {
    return storeName;
  }
  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }
  public String getPayTool() {
    return payTool;
  }
  public void setPayTool(String payTool) {
    this.payTool = payTool;
  }
  public String getPayStatus() {
    return payStatus;
  }
  public void setPayStatus(String payStatus) {
    this.payStatus = payStatus;
  }

  public Date getPayDate() {
    return payDate;
}

public void setPayDate(Date payDate) {
   Date now = new Date();
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 // this.payDate = sdf.format(now.toString());

   this.payDate = now;
}
  public String toJson() {
    return null;
  }

}
