package pl.sda.javagda28.tourmanagingcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import static java.util.Objects.nonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {

  @NotNull
  @Length(min = 2)
  private String username;

  @NotNull
  @Email
  private String email;

  private String password;
  private String confirmPassword;

  @AssertTrue
  public boolean isPasswordPairValid() {
    return nonNull(password) && nonNull(confirmPassword) && password.equals(confirmPassword);
  }
}
