package com.example.demo.service.impl;

import com.example.demo.dto.order.OrderCreateBodyDTO;
import com.example.demo.dto.order.OrderPageResponseDTO;
import com.example.demo.dto.order.OrderSearchParamDTO;
import com.example.demo.dto.sim.SimCreateBodyDTO;
import com.example.demo.enums.CodeTypeEnum;
import com.example.demo.exceptions.ExceptionUtils;
import com.example.demo.exceptions.GPSException;
import com.example.demo.model.Adminitrator;
import com.example.demo.model.Order;
import com.example.demo.model.Provider;
import com.example.demo.model.Sim;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.AdminService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProviderService;
import com.example.demo.service.SimService;
import com.example.demo.utils.GPSUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final AdminService adminService;
  private final ProviderService providerService;
  private final SimService simService;

  @PersistenceContext private final EntityManager entityManager;

  @Override
  public void create(OrderCreateBodyDTO request) {
    Integer maxId = orderRepository.findMaxId();
    if (maxId == null) {
      maxId = 0;
    }
    String code = GPSUtils.generateCode(CodeTypeEnum.ORDER.getCode(), maxId + 1);
    Integer quantity = request.getSims().size();
    Double totalPrice = request.getSims().stream().mapToDouble(SimCreateBodyDTO::getPrice).sum();
    Adminitrator adminitrator = adminService.getAdmin(request.getAdminCode());
    Order newOrder =
        Order.builder()
            .code(code)
            .createdDate(LocalDate.now())
            .quantity(quantity)
            .totalPrice(totalPrice)
            .adminitrator(adminitrator)
            .build();
    orderRepository.saveAndFlush(newOrder);

    List<Sim> newSims = new ArrayList<>();
    for (SimCreateBodyDTO dto : request.getSims()) {
      Provider provider = providerService.getProviderByCode(dto.getProviderCode());
      Sim sim =
          Sim.builder()
              .provider(provider)
              .order(newOrder)
              .price(dto.getPrice())
              .phone(dto.getPhone())
              .build();
      newSims.add(sim);
    }
    simService.create(newSims);
  }

  @Override
  public Order getOrderByOrderCode(String orderCode) {
    return orderRepository
        .findByCode(orderCode)
        .orElseThrow(() -> new GPSException(ExceptionUtils.E_RECORD_NOT_EXIST));
  }

  @Override
  public Page<OrderPageResponseDTO> find(OrderSearchParamDTO request, Pageable pageable) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Order> cq = cb.createQuery(Order.class);
    Root<Order> root = cq.from(Order.class);
    List<Predicate> predicates = new ArrayList<>();
    if (request.getEndDate() != null && request.getStartDate() != null) {
      predicates.add(
          cb.between(root.get("createdDate"), request.getStartDate(), request.getEndDate()));
    }
    cq.select(root).where(cb.and(predicates.toArray(new Predicate[0])));
    TypedQuery<Order> orderTypedQuery = entityManager.createQuery(cq);
    orderTypedQuery.setFirstResult((int) pageable.getOffset());
    orderTypedQuery.setMaxResults(pageable.getPageSize());
    List<Order> orders = orderTypedQuery.getResultList();
    List<OrderPageResponseDTO> orderPageResponseDTOS =
        orders.stream().map(OrderPageResponseDTO::new).toList();
    CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
    countCq.select(cb.count(countCq.from(Order.class)));
    Long count = entityManager.createQuery(countCq).getSingleResult();
    return new PageImpl<>(orderPageResponseDTOS, pageable, count);
  }
}
