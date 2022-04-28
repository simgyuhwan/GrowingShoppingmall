package com.growing.sgh.domain.order.service;

import com.growing.sgh.domain.item.entity.Item;
import com.growing.sgh.domain.item.repository.ItemRepository;
import com.growing.sgh.domain.member.entity.Member;
import com.growing.sgh.domain.member.repository.MemberRepository;
import com.growing.sgh.domain.order.dto.OrderDto;
import com.growing.sgh.domain.order.entity.Order;
import com.growing.sgh.domain.order.entity.OrderItem;
import com.growing.sgh.domain.order.repository.OrderRepository;
import com.growing.sgh.exception.ItemNotFoundException;
import com.growing.sgh.exception.MemberDoesNotMatchException;
import com.growing.sgh.exception.MemberNotFoundException;
import com.growing.sgh.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public void order(OrderDto orderDto, Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        Item item = itemRepository.findById(orderDto.getItemId()).orElseThrow(ItemNotFoundException::new);

        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());

        Order order = Order.createOrder(member);
        order.addOrderItem(orderItem);

        orderRepository.save(order);
    }

    public void cancelOrder(Long orderId, long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);

        validateOrder(member, order);

        order.cancelOrder();
    }

    private void validateOrder(Member member, Order order){
        if(!StringUtils.equals(member.getUsername(), order.getMember().getUsername()))
            throw new MemberDoesNotMatchException();
    }


}
