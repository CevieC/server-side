package sg.edu.nus.iss.loginpractice.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PROTECTED)
    @NotEmpty(message = "username cannot be empty")
    private String username;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PROTECTED)
    @NotEmpty(message = "password cannot be empty")
    private String password;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PROTECTED)
    @Email
    @NotEmpty(message = "email cannot be empty")
    private String email;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PROTECTED)
    @NotNull(message = "age cannot be null")
    private Integer age;

    
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PROTECTED)
    @NotNull(message= "please pick a gender")
    private String gender;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PROTECTED)
    @NotNull(message = "marital status is mandatory")
    private Boolean isMarried;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PROTECTED)
    @NotNull(message = "please select an occupation")
    private String occupation;

    
}
