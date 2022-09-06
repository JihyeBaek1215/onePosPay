package onepos.domain;

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

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    int id;
    int orderId; //주문번호
    int tableNum; //테이블 번호
    int amt; //가격
    int qty; //수량
    int storeId; //매장ID
    int price;
    String menuId;
    String menuNm;
    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuNm() {
        return menuNm;
    }

    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }


    String payTool; //계산수단
    String payStatus; // 계산결과
    LocalDateTime payDate; //계산 시간

    String cardNum; // 카드번호
    String validNum; // 유효기간
    String password; // 비밀번호 앞 2자리

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getValidNum() {
        return validNum;
    }

    public void setValidNum(String validNum) {
        this.validNum = validNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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


  //  @PrePersist // 해당 엔티티를 저장하기 이전
 //   public void onPrePersist(){
  //      try {
  //          System.out.println("##### Status chk : " + payStatus);
  //          if ("PayRequest".equals(payStatus)){
   //             price = qty * amt;
   //             payStatus = "PaySucess";
   //         }
   //         else {
   //             payStatus = "PayFail";
   //         }

    //    } catch (Exception e) {
            //TODO: handle exception
          ////  String value = "PayFail";
          //  pay.setPayStatus(value);
    //    }

   // }


   @PostPersist @PostUpdate // 해당 엔티티를 저장/수정한 이후
    public void onPatchPersist(){
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



}
