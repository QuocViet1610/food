package com.example.osahaneat.security;
import com.example.osahaneat.enity.Users;
import com.example.osahaneat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users =userRepository.findByUserName(username);
        if(users == null){
            throw new UsernameNotFoundException("user name not exist");
        }
        // *********** them mot user xac thuc moi bang chinh user trong sql cua minh
        //return va mot authentication
        return new User(username,users.getPassword(),new ArrayList<>());
    }

    public UserDetails loadUserById(int userId) throws UsernameNotFoundException {
        Optional<Users> users =userRepository.findById(userId);
        Users user=users.get();
        if(users == null){
            throw new UsernameNotFoundException("user name not exist");
        }
        // *********** them mot user xac thuc moi bang chinh user trong sql cua minh
        //return va mot authentication
        return new User(user.getUserName(),user.getPassword(),new ArrayList<>());
    }
}
