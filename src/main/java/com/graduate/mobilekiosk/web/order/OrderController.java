package com.graduate.mobilekiosk.web.order;

import com.graduate.mobilekiosk.domain.Member;
import com.graduate.mobilekiosk.domain.Order;
import com.graduate.mobilekiosk.domain.OrderStatus;
import com.graduate.mobilekiosk.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order-management")
public class OrderController {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    @GetMapping
    public String moveOrder(Model model, Principal principal) {

        Member member = memberRepository.findByUserId(principal.getName());
        List<Order> orders = orderRepository.findByMemberAndPurchase(member, "purchase");

        model.addAttribute("orders", orders);
        return "seller/order-management";
    }
    /****자세히 버튼 누를 시 세부 내용 출력****/


    /*********** 오더 데이터를 가져와서 모델에 추가***********/



    /****수락 누를 시 ******/
    /***************서버로 OrderStatus 상태 변경 후 DB 전송 , 판매하기 버튼 활성화 ,리다이렉트 *******************/
/*    @PostMapping("/agree")
    public String orderAgree(Model model){

    }
*/
    @RequestMapping("/agree")
    public String orderAgree(Model model, @RequestParam String order) {

        log.warn("리다이렉트 접속");
        log.warn("파라미터 전달 " + order );

        /** 변경 객체를 리포지토리에 저장 **/
        Order changedOrder = orderRepository.getById(Long.parseLong(order));
//        changedOrder.setStatus(OrderStatus.ACCEPT);

        // OrderRepository 업데이트 방법 찾으면 아래 코드 변경
//        orderRepository.save(changedOrder);

//        log.warn("리포지토리 내용 확인: " + TestOrderRepository.findALL());

        return "redirect:";
    }




    /*****거절일 경우 OrderStatus(거절 사유) 상태 변경 후 DB 전송, 리다이렉트 (사라짐) ******/




}
