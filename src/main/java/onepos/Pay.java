package onepos;

import javax.persistence.*;

import com.esotericsoftware.kryo.util.IntArray;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Pay_table")
public class Pay {



    @Id @GeneratedValue
    int id;

    int orderId; //주문번호
    int tableNum; //테이블 번호
    int amt; //가격
    int qty; // 수량
    int storeId; //매장ID
    int price;
    String payTool; //계산수단
    String payStatus; // 계산결과
    LocalDateTime payDate; //계산 시간
    //String customerPhoneNumber;

    @PrePersist // 해당 엔티티를 저장하기 이전
    public void onPrePersist(){
        try {
            System.out.println("##### Status chk : " + payStatus);


            if ("PayRequest".equals(payStatus)){
                price = qty * amt;
                //실제 결제 진행하는 로직 구현 필요 (카드 번호 등 필요함)
                payStatus = "PaySucess";
            }
            else {
                payStatus = "PayFail";

            }

        } catch (Exception e) {
            //TODO: handle exception
          ////  String value = "PayFail";
          //  pay.setPayStatus(value);
        }

    }


    @PostPersist // 해당 엔티티를 저장한 이후
    public void onPostPersist(){
        Pay pay = new Pay();
        pay.setPayStatus(pay.getPayStatus());
        System.out.println("##### Status chk : " + payStatus);

        if ("PaySucess".equals(payStatus)){
            Paid paid = new Paid();
            BeanUtils.copyProperties(this, paid);
            paid.publishAfterCommit(); // 카프카 발행
        }
        if ("PayFail".equals(payStatus)){
            Refunded refunded = new Refunded();
            BeanUtils.copyProperties(this, refunded);
            refunded.publishAfterCommit(); // 카프카 발행
        }
    }

   // public String getCustomerPhoneNumber() {
   //     return customerPhoneNumber;
   // }

    //public void setCustomerPhoneNumber(String customerPhoneNumber) {
    //    this.customerPhoneNumber = customerPhoneNumber;
   // }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
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


    public LocalDateTime getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDateTime payDate) {
       //Date now = new Date();
      // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       this.payDate = LocalDateTime.now();
    }

    public int getAmt() {
        return amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }



}
