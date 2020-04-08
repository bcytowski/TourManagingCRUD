package pl.sda.javagda28.tourmanagingcrud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import pl.sda.javagda28.tourmanagingcrud.entity.Role;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import java.util.List;

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

  private byte[] userImage;

  @AssertTrue
  public boolean isPasswordPairValid() {
    return nonNull(password) && nonNull(confirmPassword) && password.equals(confirmPassword);
  }

  private List<Role> roles;
}
