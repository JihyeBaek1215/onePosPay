package onepos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import onepos.domain.Paid;
import onepos.domain.Pay;
import onepos.service.*;


  // 클라이언트와 상호 작용

 @RestController
 @RequestMapping("/")
 public class PayController {

  PayService payService;


  @GetMapping("/pays/heath_check")
  public String status() {
    return "It's Working in Pay Service"
;  }


  @Autowired
  public PayController(PayService payService){
    this.payService = payService;
  }

  //계산 내역 전체 조회
  @CrossOrigin(origins = "*")
  @GetMapping("/pays")
  public List<Pay> getPayList() {
    return payService.getPayList();
  }

  //계산 1건 조회
  @GetMapping("/pays/{orderId}")
  public  java.util.Optional<Pay> getPayDetail(@PathVariable("orderId") int orderId) {
    return payService.getPayDetail(orderId);
  }

  //계산 진행
  @PatchMapping("/pays/{orderId}")
  public void requestPayStart(@PathVariable("orderId") int orderId){
    payService.requestPayStart(orderId);
  }

  //환불 진행
  @PatchMapping("/pays/refund/{orderId}")
  public void requestRefundStart(@PathVariable("orderId") int orderId){
    payService.requestRefundStart(orderId);
  }


 }
