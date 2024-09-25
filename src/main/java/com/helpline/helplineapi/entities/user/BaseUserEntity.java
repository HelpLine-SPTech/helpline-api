package com.helpline.helplineapi.entities.user;

import com.helpline.helplineapi.entities.BaseEntity;
import com.helpline.helplineapi.entities.address.AddressEntity;
import com.helpline.helplineapi.entities.job.JobEntity;
import com.helpline.helplineapi.enums.UserRole;
import com.helpline.helplineapi.enums.UserTypeEnum;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


/**
 * Entidade que representa um usuário dentro da HelpLine, tanto usuario do tipo ONG e usuários comuns
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity(name = "users")
public abstract class BaseUserEntity extends BaseEntity implements UserDetails {

    private String email;

    private String password;

    private String name;

    private String bio;

    private String document;

    private String profilePicUrl;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne()
    @JoinColumn(name = "fk_address")
    @Nullable
    private AddressEntity address;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Caso o usuário tenha permissões de admin retorna permissões de admin e usuario comum, caso contrário retorna apenas permissões de usuario comum
        if(role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
