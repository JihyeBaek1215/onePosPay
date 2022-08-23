package onepos;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import onepos.config.kafka.KafkaProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    PayRepository payRepository;

  //  @StreamListener(KafkaProcessor.INPUT)  //Test . 서빙 완료시 저장되도록 변경
 //   public void whenOrderCreated(@Payload Ordered ordered){


             //   System.out.println("##### listener UpdateStatus: " + ordered.getPayStatus());

              //  Pay pay = new Pay();
              //  pay.setOrderId(ordered.getOrderId()); // MSA 간 전달 파리미터/유형 협의 필요!!!!!!!!!!. Test 를 위해 임의값 대신 저장/
              //  pay.setStoreId(1111);
              //  pay.setStoreName(ordered.getStoreName());

              //  pay.setPayTool("1234");
              //  pay.setSaleQty(ordered.getQty());
              //  System.out.println("##### listener UpdateStatus : " + ordered.toJson());
              //  payRepository.save(pay);


 //   }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_UpdateStatus(@Payload Ordered ordered){

        if(ordered.isMe()){
            Optional<Pay> orderOptional = payRepository.findById(ordered.getOrderId());
            Pay pay = orderOptional.get();
      //      pay.setPayStatus("paid");

            payRepository.save(pay);
            System.out.println("##### listener UpdateStatus : " + ordered.toJson());
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPaid_UpdateStatusTest(@Payload Paid paid){

       // if(paid.isMe()){
      //      System.out.println("##### listener Delivered!!!!!!!!!!### : " + paid.toJson());
      //  }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRefunded_UpdateStatus(@Payload Refunded refunded){

        if(refunded.isMe()){
           // Optional<Pay> orderOptional = payRepository.findById(refunded.getOrderId());
          //  Pay order = orderOptional.get();
          //  order.setPayStatus(refunded.getPayStatus());

            //payRepository.save(order);
           // System.out.println("##### listener UpdateStatus : " + refunded.toJson());
        }
    }

}
