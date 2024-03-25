package br.com.jeova.cursoSpring.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "permission")
public class Permision implements GrantedAuthority, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String desciption;

    public Permision() {}

    @Override
    public String getAuthority() {
        return this.desciption;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permision permision = (Permision) o;
        return Objects.equals(id, permision.id) && Objects.equals(desciption, permision.desciption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, desciption);
    }
}
