package com.example.case_study_c09.Repository;

import com.example.case_study_c09.Model.Order;
import com.example.case_study_c09.Model.OrderDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IThamOrdersRepository extends JpaRepository<Order,Integer> {
    @Query(nativeQuery = true, value = "select * from orders where account_id = :param and is_delete = 0")
    Set<Order> findByAccountId(@Param("param") int id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET is_delete = 1 WHERE (id = :param)", nativeQuery = true)
    void cancelOrder( @Param("param") int idOrder);

    @Query(nativeQuery = true, value = "select * from orders where is_delete = 0")
    Set<Order> findByIdCheckDel(@Param("param") int idOrder);

    List<Order> findByCode(String code);


}
