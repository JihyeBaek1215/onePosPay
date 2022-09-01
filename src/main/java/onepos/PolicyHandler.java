package onepos;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import onepos.config.kafka.KafkaProcessor;
import onepos.domain.Paid;
import onepos.domain.Pay;
import onepos.domain.PayRepository;
import onepos.domain.Refunded;
import onepos.domainkafka.OrderCancelled;
import onepos.domainkafka.Ordered;

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

    @StreamListener(KafkaProcessor.INPUT)  //주문 완료시 저장
    public void whenOrderCreated(@Payload Ordered ordered){

            System.out.println("##### listener UpdateStatus: " + ordered.getStatus().toString());

            Pay pay = new Pay();
            pay.setOrderId(ordered.getOrderId());
            pay.setStoreId(ordered.getStoreId());
            pay.setAmt(ordered.getOrderItems().getQuantity()); // ??
            pay.setPrice(ordered.getOrderItems().getQuantity()*ordered.getOrderItems().getPrice());
            pay.setPayStatus("payRequest");
            System.out.println("##### listener UpdateStatus : " + ordered.toJson());
            payRepository.save(pay);
    }

    @StreamListener(KafkaProcessor.INPUT) //손님이 주문취소했을 때
    public void wheneverOrdered_UpdateStatus(@Payload OrderCancelled orderCancelled){

        if(orderCancelled.isMe()){
            Optional<Pay> orderOptional = payRepository.findById(orderCancelled.getId());
            Pay pay = orderOptional.get();
            pay.setPayStatus("Refuned");

            payRepository.save(pay);
            System.out.println("##### listener UpdateStatus : " + orderCancelled.toJson());
        }
    }


}
