package com.example.shopping.repository;

import com.example.shopping.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.file.OpenOption;
import java.util.List;
import java.util.Optional;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

    List<OrderHistory> findAllByUserId(Long id);
}
