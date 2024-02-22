package com.example.case_study_c09.Service;

import com.example.case_study_c09.Model.*;
import com.example.case_study_c09.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private IKhanhOrderDetailRepository iKhanhOrderDetailRepository;

    @Override
    public Page<Book> findAllBook(Pageable pageable) {
        return iKhanhBookRepository.findAllBook(pageable);
    }

    @Override
    public Book findBookById(Integer id) {
        return iKhanhBookRepository.findById(id).orElse(null);
    }

    @Override
    public void addOrder(List<CartBook> cartBookList, String address, Boolean permitted) {


        if (permitted) {


            Integer codeOrder = null;
            int count = 0;
            List<Order> orderList = null;
            //Lặp qua để đảm bảo mỗi codeOrder là khác nhau
            do {
                count = 0;
                codeOrder = generateRandomCode();
                List<Order> orderList1 = iKhanhOrderRepository.findAll();
                for (Order order : orderList1) {
                    if (order.getCode().equals(codeOrder)) {
                        count++;
                        break;
                    }
                }
            } while (count != 0);

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
            Order newOrder = new Order(null, String.valueOf(codeOrder), nameConsignee, address, phoneNumber, email, bookingDate, deliveryDate, status, paymentCode, isDelete, null, account, payment);
            //Lưu vào order
            iKhanhOrderRepository.save(newOrder);

            //Ghi giảm số sách khi order thành công
            List<Book> allBook = iKhanhBookRepository.findAll();
            Book updateBook = null;

            for (Book book : allBook) {
                for (CartBook cartBook : cartBookList) {
                    if (book.getId().equals(Integer.valueOf(cartBook.getIdBook()))) {
                        updateBook = iKhanhBookRepository.findById(book.getId()).orElse(null);
                        updateBook.setQuantity(book.getQuantity() - Integer.valueOf(cartBook.getValue()));
                        iKhanhBookRepository.save(updateBook);
                    }
                }
            }

            //Lưu vào order-detail

            //Lấy object order ra dựa vào order code(unique)
            Order order = iKhanhOrderRepository.findByCodeOrder(codeOrder);
            OrderDetails orderDetails = null;
            //lặp qua từng idbook vs số lượng trong giỏ hàng để thêm vào order detail
            for (CartBook cartBook : cartBookList) {
                orderDetails = new OrderDetails(null, String.valueOf(generateRandomCode()),
                        iKhanhBookRepository.findById(Integer.valueOf(cartBook.getIdBook())).orElse(null).getPrice(),
                        Integer.valueOf(cartBook.getValue()), false, order,
                        iKhanhBookRepository.findById(Integer.valueOf(cartBook.getIdBook())).orElse(null));

                iKhanhOrderDetailRepository.save(orderDetails);
            }



        }else {
            System.out.println("gio hang khong hop le");
        }

    }

    public void removeCart(@SessionAttribute("cart") Map<Book, Integer> cartMap){
        //Xóa giỏ hàng session
        if(!cartMap.isEmpty()){
            cartMap.clear();
        }
    }

    @Override
    public Page<Book> findAllBookByName(String search, Pageable bookPageable) {
        return iKhanhBookRepository.findByNameBookContaining(search, bookPageable);
    }

    @Override
    public List<Book> findAllList() {
        return iKhanhBookRepository.findAll();
    }

    @Override
    public Page<Book> findAllBookByCategory(Integer sort, Pageable bookPageable) {
//        String category = "";
//        switch (sort){
//            case 1:
//                category = "Trinh thám";
//                break;
//            case 2:
//                category = "Tiểu thuyết";
//                break;
//            case 3:
//                category = "Self Help";
//                break;
//        }
        return iKhanhBookRepository.findAllBookByCategory(sort, bookPageable);
    }

    public Integer generateRandomCode() {
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
