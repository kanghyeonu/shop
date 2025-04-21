package com.project.shop.service;

import com.project.shop.domain.Sales;
import com.project.shop.repository.MemberRepository;
import com.project.shop.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesService {
    private final SalesRepository salesRepository;

    public void save(Sales sales) {
        validateSales(sales);
        salesRepository.save(sales);
    }

    private void validateSales(Sales sales) {
    }

    public List<Sales> findAll(){
        return salesRepository.customFindAll();
    }
}
