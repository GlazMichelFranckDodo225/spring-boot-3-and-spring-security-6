package com.dgmf.config;

import com.dgmf.entity.Customer;
import com.dgmf.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EasyBankUserDetails implements UserDetailsService {
    private final CustomerRepository customerRepository;

    /**
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user
     * has no GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password;
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Spring Security "username" == Application User Email
        List<Customer> customer = customerRepository.findByEmail(username);

        if (customer.size() == 0) {
            throw new UsernameNotFoundException(
                    "User details not found for the user : " + username
            );

        } else {
            userName = customer.get(0).getEmail();
            password = customer.get(0).getPwd();
            authorities.add(new SimpleGrantedAuthority(customer.get(0).getRole()));
        }

        // Send Back to Spring Security
        return new User(userName, password, authorities);
    }
}
