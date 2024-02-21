package com.example.case_study_c09.Controller;

import com.example.case_study_c09.DTO.CustomerDTO;
import com.example.case_study_c09.Model.*;
import com.example.case_study_c09.Service.IThamAccountService;
import com.example.case_study_c09.Service.IThamCustomerService;
import com.example.case_study_c09.Service.IThamOrderDetailsService;
import com.example.case_study_c09.Service.IThamOrdersService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("")
public class ThamController {
    @Autowired
    private IThamCustomerService customerService;
    @Autowired
    private IThamOrdersService ordersService;
    @Autowired
    private IThamAccountService accountService;
    @Autowired
    private IThamOrderDetailsService orderDetailsService;
    private Email emailDefault = new Email("kk");

    @GetMapping("")
    public String show() {
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "sign-in";
    }
    @PostMapping("/user-login")
    public String signIn(@RequestParam ("email") String email, Model model) {
        emailDefault.setEmail(email);
        model.addAttribute("email",email);
        return "user-login";
    }
    @GetMapping("/profile/{email}")
    public String profile(@PathVariable ("email") String email, Model model) {
        Customer customer = customerService.finByEmail(email);
        CustomerDTO customerDTO =new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        model.addAttribute("customerDTO",customerDTO);
        return "profile";
    }
    @PostMapping("/saveProfile")
    public String saveProfile(@Valid CustomerDTO customerDTO,
                              BindingResult bindingResult,
                              @ModelAttribute ("email") String email,
                              RedirectAttributes attributes){

        if(bindingResult.hasErrors()){
            return "profile";
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        customerService.save(customer);
        attributes.addFlashAttribute("email",email);
        return "redirect:/user-login";
    }
    @GetMapping("/user-login")
    public String backLogin(@ModelAttribute ("email") String email, Model model) {
        model.addAttribute("email",email);
        return "user-login";
    }
    @GetMapping("/user-login/{email}")
    public String backHome(@PathVariable ("email") String email, Model model) {
        model.addAttribute("email",email);
        return "user-login";
    }

    //đon hàng
    @GetMapping("/order/{email}")
    public String showOrder(@PathVariable ("email") String email, Model model) {
        Account account = accountService.findByUsername(email);
        Set<Order> orders = ordersService.findByAccountId(account.getId()) ;
        model.addAttribute("orderSet",orders);
        System.out.println(account.getOrders());
        return "customer-order";
    }

    //chi tiet don hang
    @GetMapping("/order-detail/{idOrder}")
    public String orderDetail(@PathVariable ("idOrder") int idOrder, Model model){
        Order order = ordersService.findById(idOrder);
        Set<OrderDetails> orderDetailsSet = orderDetailsService.findByOrders(order);
        model.addAttribute("orderDetailsSet",orderDetailsSet);
        model.addAttribute("order",order);
        return "order-detail";
    }

    @GetMapping("/cancel-order/{idOrder}")
    public String cancelOrder(@PathVariable ("idOrder") int idOrder, Model model){
        Order order = ordersService.findById(idOrder);
        Account account = order.getAccount();
        String email = account.getUsername();

//        nếu đơn hàng đang chờ xác nhận thì được hủy, k thì k hủy đc
        if(order.getStatus().equals("Chờ xác nhận")){
            ordersService.cancelOrder(idOrder);
            Set<Order> orders = ordersService.findByIdCheckDel(idOrder) ;
            model.addAttribute("orderSet",orders);
        }else {
            System.out.println("Không thể hủy đơn");
        }
        return "redirect:/order/"+email;
    }

    //quan ly don hang cua admin
    @GetMapping("/admin-order")
    public String adminOrder(Model model, @RequestParam(value = "page", required = false, defaultValue = "0") Integer page){
        Pageable pageable = PageRequest.of(page, 11);
        Page<Order> orderPage = ordersService.findAll(pageable);
        model.addAttribute("orderPage", orderPage);
        return "admin-order";
    }
    @PostMapping("/customer/search")
    public String searchCustomer(@RequestParam ("code-search") String code, Model model){
        List<Order> orderList = ordersService.findByCode(code);
        model.addAttribute("orderPage",orderList);
        return "admin-order";
    }
    @GetMapping("/order-detail-admin/{idOrder}")
    public String orderDetailAdmin(@PathVariable ("idOrder") int idOrder, Model model){
        Order order = ordersService.findById(idOrder);
        Set<OrderDetails> orderDetailsSet = orderDetailsService.findByOrders(order);
        model.addAttribute("orderDetailsSet",orderDetailsSet);
        model.addAttribute("order",order);
        return "order-detail-admin";
    }

    @GetMapping("/cancel-order-admin/{idOrder}")
    public String cancelOrderAdmin(@PathVariable ("idOrder") int idOrder, Model model){
        Order order = ordersService.findById(idOrder);
//        Admin có quyền hủy hết tất cả đơn
            ordersService.cancelOrder(idOrder);
            Set<Order> orders = ordersService.findByIdCheckDel(idOrder) ;
            model.addAttribute("orderSet",orders);
        return "redirect:/admin-order";
    }
}
