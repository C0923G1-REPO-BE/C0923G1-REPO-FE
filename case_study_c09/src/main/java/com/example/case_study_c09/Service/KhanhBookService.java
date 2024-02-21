package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.*;
import com.example.case_study_c09.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class KhanhBookService implements IKhanhBookService {
    @Autowired
    private IKhanhBookRepository iKhanhBookRepository;

    @Autowired
    private IKhanhOrderRepository iKhanhOrderRepository;

    @Autowired
    private IKhanhPaymentRepository iKhanhPaymentRepository;

    @Autowired
    private IKhanhCustomerRepository iKhanhCustomerRepository;

    @Autowired
    private IKhanhAccountRepository iKhanhAccountRepository;

    @Override
    public Page<Book> findAllBook(Pageable pageable) {
        return iKhanhBookRepository.findAllBook(pageable);
    }

    @Override
    public Book findBookById(Integer id) {
        return iKhanhBookRepository.findById(id).orElse(null);
    }

    @Override
    public void addOrder(List<CartBook> cartBookList, String address) {
        Integer codeOrder = null;
        int count = 0;
        List<Order> orderList = null;
        do {
            count = 0;
            codeOrder = generateRandomCode();
            List<Order> orderList1 = iKhanhOrderRepository.findAll();
            for (Order order: orderList1){
                if(order.getCode().equals(codeOrder)){
                    count++;
                    break;
                }
            }
        }while (count != 0);

        System.out.println(codeOrder);
        LocalDate bookingDate = LocalDate.now();
        //id account lay ve để tìm ra tên khách hàng (thông qua đăng nhập)
        Integer idAccount = 1;

        String nameConsignee = iKhanhAccountRepository.findById(idAccount).orElse(null).getCustomer().getName();

        LocalDate deliveryDate = bookingDate.plusDays(5);
        String email = iKhanhAccountRepository.findById(idAccount).orElse(null).getCustomer().getEmail();
        Boolean isDelete = false;
        Integer paymentCode = generateRandomCode();
        String phoneNumber = iKhanhAccountRepository.findById(idAccount).orElse(null).getCustomer().getPhone();
        String status = "Đang xử lý";
        Account account = iKhanhAccountRepository.findById(idAccount).orElse(null);
        Payment payment = iKhanhPaymentRepository.findById(1).orElse(null);
        Order newOrder = new Order(null, String.valueOf(codeOrder), nameConsignee, address, phoneNumber, email,bookingDate, deliveryDate, status, paymentCode, isDelete,null, account, payment);

        iKhanhOrderRepository.save(newOrder);
    }

    public Integer generateRandomCode(){
        Random random = new Random();
        int firstDigit = random.nextInt(9) + 1;
        StringBuilder concatenatedNumber = new StringBuilder();
        concatenatedNumber.append(firstDigit);
        for (int i = 0; i < 4; i++) {
            // tao so random 0 -> 9
            int randomNumber = random.nextInt(10);
            concatenatedNumber.append(randomNumber);
        }
        int finalNumber = Integer.parseInt(concatenatedNumber.toString());
        //tao ra so XXXXX
        return finalNumber;
    }


}
