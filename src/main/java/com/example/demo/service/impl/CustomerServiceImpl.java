package com.example.demo.service.impl;

import com.example.demo.dto.customer.*;
import com.example.demo.enums.CodeTypeEnum;
import com.example.demo.exceptions.ExceptionUtils;
import com.example.demo.exceptions.GPSException;
import com.example.demo.model.*;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.AddressService;
import com.example.demo.service.CustomerService;
import com.example.demo.utils.GPSUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final AddressService addressService;

  @PersistenceContext private final EntityManager entityManager;

  @Override
  public Customer createCustomer(CustomerCreateDTO request) {
    if (customerRepository.existsByEmail(request.getEmail())) {
      throw new GPSException(ExceptionUtils.E_EMAIL_EXISTED);
    }
    if (customerRepository.existsByPhone(request.getPhone())) {
      throw new GPSException(ExceptionUtils.E_PHONE_EXISTED);
    }

    Integer maxIndex = customerRepository.findMaxIndex();
    if (maxIndex == null) {
      maxIndex = 0;
    }

    Customer customer =
        Customer.builder()
            .customerIndex(maxIndex + 1)
            .code(GPSUtils.generateCode(CodeTypeEnum.CUS.getCode(), maxIndex + 1))
            .name(request.getName())
            .addressDetail(request.getAddressDetail())
            .email(request.getEmail())
            .phone(request.getPhone())
            .ward(addressService.getWard(request.getWardCode()))
            .build();
    return customerRepository.save(customer);
  }

  @Override
  public CustomerDetailResponseDTO getCustomer(String customerCode) {
    Customer customer = customerRepository.getCustomerByCode(customerCode).orElse(null);
    if (customer == null) {
      return new CustomerDetailResponseDTO();
    }
    Ward ward = customer.getWard();
    District district = ward.getDistrict();
    Province province = district.getProvince();

    CustomerDetailResponseDTO customerDetailResponseDTO =
        CustomerDetailResponseDTO.builder()
            .addressDetail(customer.getAddressDetail())
            .customerCode(customer.getCode())
            .email(customer.getEmail())
            .phone(customer.getPhone())
            .name(customer.getName())
            .id(customer.getId())
            .wardCode(ward.getCode())
            .wardName(ward.getName())
            .districtCode(district.getCode())
            .districtName(district.getName())
            .provinceName(province.getName())
            .proviceCode(province.getCode())
            .build();

    return customerDetailResponseDTO;
  }

  @Override
  public Page<CustomerPageResponseDTO> findCustomers(
      CustomerSearchParamDTO request, Pageable pageable) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
    Root<Customer> root = cq.from(Customer.class);
    List<Predicate> predicates = new ArrayList<>();

    if (StringUtils.isNotBlank(request.getCustomerCode())) {
      predicates.add(cb.equal(root.get("code"), request.getCustomerCode().trim().toUpperCase()));
    }
    if (StringUtils.isNotBlank(request.getEmail())) {
      predicates.add(cb.equal(root.get("email"), request.getEmail().trim().toLowerCase()));
    }
    if (StringUtils.isNotBlank(request.getPhone())) {
      predicates.add(cb.equal(root.get("phone"), request.getPhone().trim()));
    }
    if (StringUtils.isNotBlank(request.getPhone())) {
      predicates.add(
          cb.like(cb.lower(root.get("name")), GPSUtils.appendLikeExpression(request.getPhone())));
    }

    cq.select(root).where(cb.and(predicates.toArray(new Predicate[0])));
    TypedQuery<Customer> customerTypedQuery = entityManager.createQuery(cq);
    customerTypedQuery.setFirstResult((int) pageable.getOffset());
    customerTypedQuery.setMaxResults(pageable.getPageSize());
    List<Customer> customers = customerTypedQuery.getResultList();
    List<CustomerPageResponseDTO> customerPageResponseDTOList =
        customers.stream().map(CustomerPageResponseDTO::new).toList();

    CriteriaQuery<Long> countCq = cb.createQuery(Long.class);
    countCq.select(cb.count(countCq.from(Customer.class)));
    Long count = entityManager.createQuery(countCq).getSingleResult();
    return new PageImpl<>(customerPageResponseDTOList, pageable, count);
  }

  @Override
  public void updateCustomer(CustomerUpdateRequestDTO request, String customerCode) {
    Customer customer =
        customerRepository
            .getCustomerByCode(customerCode)
            .orElseThrow(() -> new GPSException(ExceptionUtils.E_RECORD_NOT_EXIST));
    customer.setName(request.getName());
    customer.setEmail(request.getEmail());
    customer.setPhone(request.getPhone());
    customer.setWard(addressService.getWard(request.getWardCode()));
    customer.setAddressDetail(request.getAddressDetail());

    customerRepository.save(customer);
  }

  @Override
  public void deleteCustomer(String customerCode) {
    if (customerRepository.existsByCode(customerCode)) {
      throw new GPSException(ExceptionUtils.E_RECORD_NOT_EXIST);
    }

    customerRepository.deleteByCode(customerCode);
  }
}
