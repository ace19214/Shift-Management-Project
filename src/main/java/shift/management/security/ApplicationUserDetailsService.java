package shift.management.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shift.management.entity.User;
import shift.management.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository accountRepository;
    //private final AuthorityRepository authorityRepository;


    public ApplicationUserDetailsService(UserRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User account = accountRepository.findByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority(account.getRole()));

        return new org.springframework.security.core.userdetails.User(account.getUsername(),
                account.getPassword(),
                authorities);
    }

}
