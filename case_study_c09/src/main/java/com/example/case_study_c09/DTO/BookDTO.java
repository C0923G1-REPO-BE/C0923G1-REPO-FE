package com.example.case_study_c09.DTO;

import com.example.case_study_c09.Model.Author;
import com.example.case_study_c09.Model.Category;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO implements Validator {
    private Integer id;
    @Size(min = 2,max = 30,message = "Tên Của Bạn từ 2 - 30 ký tự")
    @Pattern(regexp = "^([\\p{Lu}][\\p{Ll}]{1,8})(\\s([\\p{Lu}]|[\\p{Lu}][\\p{Ll}]{1,10})){0,5}$",message = "Tên Không hợp lệ")
    private String nameBook;
    @Min(value = 0, message = "Giá cần lớn hơn 0")
    @NotNull(message = "Trường bắt buộc phải nhập")
    private Integer price;
    @Min(value = 0, message = "Số lượng cần lớn hơn 0")
    @NotNull(message = "Trường bắt buộc phải nhập")
    private Integer quantity;
    private LocalDate date = LocalDate.now();
    @NotBlank(message = "Trường bắt buộc phải nhập")
    private String image;
    @NotBlank(message = "Trường bắt buộc phải nhập")
    private String describeBook;
    private Author author;
    private Category category;
    private boolean isDelete;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
