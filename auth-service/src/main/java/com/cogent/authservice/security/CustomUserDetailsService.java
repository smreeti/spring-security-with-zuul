package com.cogent.authservice.security;

import com.cogent.authservice.responseDTO.AdminResponseDTO;
import com.cogent.authservice.feignInterface.AdminInterface;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private AdminInterface adminInterface;

    public CustomUserDetailsService(AdminInterface adminInterface) {
        this.adminInterface = adminInterface;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminResponseDTO adminResponseDTO = adminInterface.fetchAdminByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username:" + username + " not found"));

        List<GrantedAuthority> grantedAuthorities = adminResponseDTO.getRoles()
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(String.join("-", username, adminResponseDTO.getEmailAddress()),
                adminResponseDTO.getPassword(), true, true, true,
                true, grantedAuthorities);
    }
}
