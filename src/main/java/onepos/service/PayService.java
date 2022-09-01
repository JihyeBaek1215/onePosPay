package onepos.service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;


import org.bouncycastle.crypto.ec.ECElGamalDecryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import onepos.domain.Pay;
import onepos.domain.PayRepository;
import onepos.external.Point;

@RequiredArgsConstructor
@Service
public class PayService {

  PayRepository payRepository;

  //계산 내역 전체 조회
  public List<Pay> getPayList() {
    return (List<Pay>) payRepository.findAll();
  }



  @Transactional(readOnly = true) // 변경감지 자체를 수행안한다. select하는곳에는 다 붙혀줘야함
	public Optional<Pay> getPayDetail(int orderId) {

    return payRepository.findById(orderId);
	}


// 포인트 차감
public void usePoint(String phoneNumber, int usingPoint) {

}

// 포인트 적립
public void savePoint(String phoneNumber, int savingPoint) {

}



  //계산 진행
 public void requestPayStart(int orderId) {
   Optional<Pay> optional = payRepository.findById(orderId);
   Pay pay = optional.get();
   pay.setPayStatus("PayRequest"); //ENUM으로 변경
   payRepository.save(pay);

   try {
     Thread.sleep(10000);
   } catch (InterruptedException e) {
     e.printStackTrace();
   }

   pay.setPayStatus("PaySucess");
   payRepository.save(pay);


 }
  //환불 진행
  public void requestRefundStart(int orderId) {
    Optional<Pay> optional = payRepository.findById(orderId);
    Pay pay = optional.get();
    pay.setPayStatus("RefundRequest"); //ENUM으로 변경
    payRepository.save(pay);

    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    pay.setPayStatus("RefundSucess");
    payRepository.save(pay);


  }


 public Optional<Point> searchPoint(String phoneNumber) {

   return Optional.empty();
 }



}
