package br.com.jeova.cursoSpring.DTO.security;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class AccountCredetionsDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;

    public AccountCredetionsDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public AccountCredetionsDTO(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountCredetionsDTO that = (AccountCredetionsDTO) o;
        return Objects.equals(userName, that.userName) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }
}
