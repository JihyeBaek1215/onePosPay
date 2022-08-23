package onepos.external;

import java.util.Date;

public class Point {

    int price;
    int storeId;
    String storeNm;
    String customerPhoneNumber;
    Date sysDate;
    String status;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
    public String getStoreNm() {
        return storeNm;
    }
    public void setStoreNm(String storeNm) {
        this.storeNm = storeNm;
    }
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
    public Date getSysDate() {
        return sysDate;
    }
    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }
    public void setstatus(String string) {
    }



}
