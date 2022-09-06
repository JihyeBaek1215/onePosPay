package onepos.external;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

import org.bouncycastle.crypto.ec.ECElGamalDecryptor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import onepos.external.*;

@RequiredArgsConstructor
@Service
public class PointService {

  @Autowired
  PointRepository pointRepository;

  public List<Point> getPointList(){
    return (List<Point>) pointRepository.findAll();
  }

  @Transactional(readOnly = true) // 변경감지 자체를 수행안한다. select하는곳에는 다 붙혀줘야함
  public Optional<Point> getPointDetail(String customerPhoneNumber){

    return pointRepository.findById(customerPhoneNumber);
	}


  public void requestPointSave(@RequestBody Point point){
    Optional<Point> optional = pointRepository.findById(point.getCustomerPhoneNumber());

    if(optional.isPresent())
    {
      Point points = optional.get();

      BeanUtils.copyProperties(this, points);

      try {
        Thread.sleep(10000);
        points.setPoint(point.getPrice() * 0.1 + points.getPoint()); // 10프로 적립
        points.setStatus("PointSaveSucess");//ENUM으로 변경
      } catch (InterruptedException e) {
        e.printStackTrace();
        points.setStatus("PointSaveFail");//ENUM으로 변경
      }
      pointRepository.save(points);

    }
    else{
      Point points = point;
      points.setPoint(point.getPrice() * 0.1); // 10프로 적립
      points.setPrice(point.getPrice());
      points.setStoreId(point.getStoreId());
      points.setSysDate(LocalDateTime.now());
      points.setStatus("PointSaveSucess");//ENUM으로 변경
      pointRepository.save(points);

    }

  };

  public void requestPointUse(String customerPhoneNumber, double wantToUsePoint){
    Optional<Point> optional = pointRepository.findById(customerPhoneNumber);
    Point point = optional.get();

    try {
      Thread.sleep(10000);

      double calcPoint = point.getPoint()- wantToUsePoint ;
      if(calcPoint > 0){
        point.setPoint(calcPoint); // 포인트 사용 가능
        point.setPrice(point.getPrice()-(int)wantToUsePoint);
        point.setStatus("PointUseSucess");//ENUM으로 변경
      } else{
        point.setStatus("PointUseFail-Not Enough Point");//ENUM으로 변경
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
      point.setStatus("PointUseFail");//ENUM으로 변경
    }
    pointRepository.save(point);
  }

  public void requestNewPointSave(@RequestBody Point point) {

    Point points = new Point();
    points.setPoint(point.getPrice() * 0.1); // 10프로 적립
    points.setPrice(point.getPrice());
    points.setStoreId(point.getStoreId());
    points.setSysDate(LocalDateTime.now());
    points.setStatus("PointSaveSucess");//ENUM으로 변경
    pointRepository.save(points);

  };

}
