package com.graduate.mobilekiosk.web.controller;

import com.graduate.mobilekiosk.domain.OrderStatus;
import com.graduate.mobilekiosk.domain.TestOrder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/order-management")
public class OrderController {

    @GetMapping
    public String moveOrder(Model model, Principal principal) {

        model.addAttribute("username", principal.getName());


        //////////////////////////////////////// 테스트데이터///////////////////////////////////////////////////
        TestOrder order1 = new TestOrder(1, "메뉴1", "요청사항1", OrderStatus.ORDER);
        TestOrder order2 = new TestOrder(2, "메뉴2", "요청사항2", OrderStatus.ACCEPT);
        TestOrder order3 = new TestOrder(3, "메뉴3", "요청사항3", OrderStatus.ORDER);

        ArrayList<TestOrder> orders = new ArrayList();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        log.info("오더2 스테이터스: " + order2.status);
        model.addAttribute("orders", orders);


        return "seller/order-management.html";
    }
    /****자세히 버튼 누를 시 세부 내용 출력****/


    /*********** 오더 데이터를 가져와서 모델에 추가***********/



    /****수락 누를 시 ******/
    /***************서버로 OrderStatus 상태 변경 후 DB 전송 , 판매하기 버튼 활성화 ,리다이렉트 *******************/




    /*****거절일 경우 OrderStatus(거절 사유) 상태 변경 후 DB 전송, 리다이렉트 (사라짐) ******/




}