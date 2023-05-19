//package com.example.projectHub.security;
//
//import com.example.projectHub.data.entity.User;
//import com.example.projectHub.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//@Component
//@RequiredArgsConstructor
//public class SimpleUserDetailsService implements UserDetailsService {
//
//    private final UserService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String[] usernameAndDomain = StringUtils.split(
//                username, String.valueOf(Character.LINE_SEPARATOR));
//        if (usernameAndDomain == null || usernameAndDomain.length != 2) {
//            throw new UsernameNotFoundException("Username and domain must be provided");
//        }
//        User user = userService.findUser(usernameAndDomain[0], usernameAndDomain[1]);
//        if (user == null) {
//            throw new UsernameNotFoundException(
//                    String.format("Username not found for domain, username=%s, domain=%s",
//                            usernameAndDomain[0], usernameAndDomain[1]));
//        }
//        return user;
//    }
//}