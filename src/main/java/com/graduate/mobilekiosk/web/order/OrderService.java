package com.graduate.mobilekiosk.web.order;

import com.graduate.mobilekiosk.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public void save(Order order1) {
        long count = orderRepository.count();
        log.warn("count: =========== {}",count);
        order1.setOrderNum(count+1);
        orderRepository.save(order1);
    }
}
