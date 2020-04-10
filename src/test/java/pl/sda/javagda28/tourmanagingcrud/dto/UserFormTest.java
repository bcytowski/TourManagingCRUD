package pl.sda.javagda28.tourmanagingcrud.dto;

import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;

import static org.junit.jupiter.api.Assertions.*;

class UserFormTest {

    @Test
    void isPasswordPairValidWithTwoDifferentPasswords() {
        //given
        UserForm userForm = new UserForm("testName", "test@test.com", "lolek123", "lolek1234", null, null);

        //when
        boolean result = userForm.isPasswordPairValid();

        //then
        assertFalse(result);
    }

    @Test
    void isPasswordPairValidWithTwoSamePasswords(){
        UserForm userForm = new UserForm("testName", "test@test.com", "lolek123", "lolek123", null, null);

        boolean result = userForm.isPasswordPairValid();

        assertTrue(result);
    }

    @Test
    void isPasswordPairValidWithOneBeingNull(){
        UserForm userForm = new UserForm("testName", "test@test.com", "lolek123", "null", null, null);

        boolean result = userForm.isPasswordPairValid();

        assertFalse(result);

    }
}