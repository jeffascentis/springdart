package hello;
/*
 * Copyright &copy; 2013 CloudSparkSolutions.com, LLC, All Right Reserved.
 *
 * NOTICE:  All information contained herein is, and remains the property
 * of CloudSparkSolutions.com, LLC. The intellectual and technical concepts
 * contained herein are proprietary to CloudSparkSolutions.com, LLC and may
 * be covered by U.S. and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law. Dissemination of this information
 * or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from CloudSparkSolutions.com, LLC.
 */

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * Class <tt>UserAuthService</tt>
 *
 * UserAuthService just handles authentication for the spring-security framework.  All other user-related
 * service interfaces are in the UserService
 * @see
 */
@Service
public class UserAuthService implements UserDetailsService {

//    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UserAuthService.class);

//    @Autowired
//    UserService userService;

    static Map<String, User> users;
    static {

        users = new HashMap<>();

        User user = new User("jeff", "612404a0c7fef24c9a682126b08fd764fd61eae53cfc0e21154d792dc8c8e9d3", new HashSet<GrantedAuthority>());
        users.put("jeff", user);
        user = new User("chris", "94ffaf1b93c1c58b10e49b33ff21e3912fb090c58dfe25fe21dc8b36234b20f5", new HashSet<GrantedAuthority>());
        users.put("chris", user);
        user = new User("darshan", "90fd4d6d829a1f186ba4028493ea07998e1f8497b7534d119ce2670d74029495", new HashSet<GrantedAuthority>());
        users.put("darshan", user);

    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.findByUsername(username);

        User user = users.get(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found or password invalid.");
        }

//        logger.debug("Attempting to login: " + username);
        UserDetails userDetail = new CustomUserDetails(user);

        return userDetail;
    }
}

