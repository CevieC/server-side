package sg.edu.nus.iss.day13work.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// generating hashcodes, setters getters etc.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    
    @NotEmpty(message = "First Name is mandatory")
    @Size(min = 3, max = 20, message = "First Name but be between 3 to 20 characters")
    private String firstName;

    @NotEmpty(message = "Last Name is mandatory")
    @Size(min = 3, max = 20, message = "Last Name but be between 3 to 20 characters")
    private String lastName;

    @Email(message = "Invalid email format")
    @Size(max = 30, message = "Email length longer than expected")
    @NotBlank(message = "Email is mandatory")
    private String email;

    // first digit starts with 8/9, expecting 7 other digits ranging from 0 to 9
    @Pattern(regexp = "(\\8|9)[0-9]{7}", message = "Invalid phone number")
    private String phoneNo;

    @Min(value = 1500, message = "There is just no way you earn lesser than 1500")
    @Max(value = 100000, message = "Damn boy gimme some")
    private Integer salary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Birth Date must be a date that has passed")
    private Date birthDate;

    @Digits(fraction = 0, integer = 6, message = "Postal Code must be 6-digits")
    @Min(value = 111111, message = "Starts from 111111")
    @Max(value = 899999, message = "Ends at 899999")
    private Integer postalCode;
    

}
