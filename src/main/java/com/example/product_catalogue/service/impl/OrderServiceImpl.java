package com.example.product_catalogue.service.impl;

import com.example.product_catalogue.dto.OrderItemRequest;
import com.example.product_catalogue.dto.OrderItemResponse;
import com.example.product_catalogue.dto.OrderRequest;
import com.example.product_catalogue.dto.OrderResponse;
import com.example.product_catalogue.entity.Order;
import com.example.product_catalogue.entity.OrderItem;
import com.example.product_catalogue.entity.Product;
import com.example.product_catalogue.entity.User;
import com.example.product_catalogue.exception.InsufficientStockException;
import com.example.product_catalogue.exception.OrderNotFoundException;
import com.example.product_catalogue.exception.ProductNotFoundException;
import com.example.product_catalogue.exception.UserNotFoundException;
import com.example.product_catalogue.repository.OrderItemRepository;
import com.example.product_catalogue.repository.OrderRepository;
import com.example.product_catalogue.repository.ProductRepository;
import com.example.product_catalogue.repository.UserRepository;
import com.example.product_catalogue.service.OrderService;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    @Override
    public OrderResponse placeOrder(OrderRequest request) {
        User user=userRepository.findById(request.getUserId())
                .orElseThrow(()->new UserNotFoundException(request.getUserId()));

        Order order= Order.builder()
                .user(user)
                .orderDate(LocalDate.now())
                .totalAmount(0.0)
                .build();

        double totalAmount=0.0;
        List<OrderItem> orderItems=new ArrayList<>();

        for(OrderItemRequest itemRequest : request.getItems()){
            Product product=productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(()->new ProductNotFoundException(itemRequest.getProductId()));

            if(product.getStock()<itemRequest.getQuantity()){
                throw new InsufficientStockException(product.getName());
            }

            double itemTotal=product.getPrice()*itemRequest.getQuantity();
            totalAmount+=itemTotal;

            OrderItem orderItem=OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.getQuantity())
                    .price(product.getPrice())
                    .build();

            orderItems.add(orderItem);

            product.setStock(product.getStock()-itemRequest.getQuantity());
            productRepository.save(product);
        }

        order.setTotalAmount(totalAmount);
        order.setOrderItems(orderItems);

        Order savedOrder=orderRepository.save(order);

        orderItemRepository.saveAll(orderItems);

        return mapToOrderResponse(savedOrder);
    }

    private OrderResponse mapToOrderResponse(Order order) {

        List<OrderItemResponse> itemResponses =
                order.getOrderItems()
                        .stream()
                        .map(this::mapToOrderItemResponse)
                        .toList();

        return OrderResponse.builder()
                .orderId(order.getId())
                .customerName(order.getUser().getName())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .items(itemResponses)
                .build();
    }

    private OrderItemResponse mapToOrderItemResponse(OrderItem item){

        return OrderItemResponse.builder()
                .productId(item.getProduct().getId())
                .productName(item.getProduct().getName())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .totalPrice(item.getPrice() * item.getQuantity())
                .build();
    }


    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToOrderResponse)
                .toList();
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException(id));

        return mapToOrderResponse(order);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new OrderNotFoundException(id));

        orderRepository.delete(order);
    }
}
