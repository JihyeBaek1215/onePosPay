package onepos.external;

import java.text.SimpleDateFormat;
import java.util.Date;

import onepos.AbstractEvent;

public class PointUsed  extends AbstractEvent {

  String customerPhoneNumber;
  int price;
  double point;
  int storeId;
  Date sysDate;
  String status;
  public String getCustomerPhoneNumber() {
      return customerPhoneNumber;
  }
  public void setCustomerPhoneNumber(String customerPhoneNumber) {
      this.customerPhoneNumber = customerPhoneNumber;
  }
  public int getPrice() {
      return price;
  }
  public void setPrice(int price) {
      this.price = price;
  }
  public double getPoint() {
      return point;
  }
  public void setPoint(double point) {
      this.point = point;
  }
  public int getStoreId() {
      return storeId;
  }
  public void setStoreId(int storeId) {
      this.storeId = storeId;
  }
  public Date getSysDate() {
      return sysDate;
  }
  public void setSysDate(Date sysDate) {
      this.sysDate = sysDate;
  }
  public String getStatus() {
      return status;
  }
  public void setStatus(String status) {
      this.status = status;
  }

  public String toJson() {
    return null;
  }

}
