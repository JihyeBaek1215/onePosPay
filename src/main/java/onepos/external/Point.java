package onepos.external;

import javax.persistence.*;

import com.esotericsoftware.kryo.util.IntArray;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="Point_table")
public class Point {


    @Id //@GeneratedValue(generator="system-uuid")    //@GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "customer_phone_number")
    String customerPhoneNumber;
    int price;
    double point;
    int storeId;
    LocalDateTime sysDate;
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
    public LocalDateTime getSysDate() {
        return sysDate;
    }
    public void setSysDate(LocalDateTime sysDate) {
        this.sysDate = sysDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @PostPersist // 해당 엔티티를 저장한 이후
    public void onPostPersist(){
        Point point = new Point();
        point.setStatus(point.getStatus());
        System.out.println("##### Status chk : " + point.getStatus());

        if ("PointUseSucess".equals(point.getStatus())
          ||"PointUseFail-Not Enough Point".equals(point.getStatus())
          ||"PointUseFail".equals(point.getStatus())){
            PointUsed pointUsed = new PointUsed();
            BeanUtils.copyProperties(this, point);
            pointUsed.publishAfterCommit(); // 카프카 발행
        }
    }


}
