package onepos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import onepos.external.*;


  // 클라이언트와 상호 작용

 @RestController
 @RequestMapping("/")
 public class PointController {

  PointService pointservice;

  @GetMapping("/points/heath_check")
  public String status() {
    return "It's Working in Point Service";
  }
  @Autowired
  public PointController(PointService pointservice){
    this.pointservice = pointservice;
  }
  //포인트 내역 전체 조회
  @CrossOrigin(origins = "*")
  @GetMapping("/points")
  public List<Point> getPointList() {
    return pointservice.getPointList();
  }

  //포인트 1건 조회
  @GetMapping("/points/{customerPhoneNumber}")
  public  java.util.Optional<Point> getPointDetail(@PathVariable("customerPhoneNumber") String customerPhoneNumber) {
    return pointservice.getPointDetail(customerPhoneNumber);
  }


  //기존 포인트 적립 진행
  @PatchMapping("/points/{customerPhoneNumber}")
  public void requestPointSave(@RequestBody Point point) {
    pointservice.requestPointSave(point);
  }

  //포인트 사용
  @PatchMapping("/points/{customerPhoneNumber}/{point}")
  public void requestPointUse(@PathVariable("customerPhoneNumber") String customerPhoneNumber, @PathVariable("point") double point){
    pointservice.requestPointUse(customerPhoneNumber, point);
  }

 }
