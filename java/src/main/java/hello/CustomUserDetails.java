package hello;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: cmathias
 * Date: 1/8/14
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomUserDetails implements UserDetails, Serializable {

    private static final long serialVersionUID = 9075208587287088223L;

    private User user;
    private boolean accountNonExpired  = true;
    private boolean accountNonLocked  = true;
    private boolean credentialsNonExpired  = true;
    private boolean enabled = true;

    public CustomUserDetails(final User anUser,
                             boolean isAccountNonExpired,
                             boolean isAccountNonLocked,
                             boolean isCredentialsNonExpired,
                             boolean isEnabled) {
        this.user = anUser;
        this.accountNonExpired = isAccountNonExpired;
        this.accountNonLocked = isAccountNonLocked;
        this.credentialsNonExpired = isCredentialsNonExpired;
        this.enabled = isEnabled;
    }

    public CustomUserDetails() {
    }

    public CustomUserDetails(final User anUser) {
        this.user = anUser;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Spring Security is a bit schizophrenic when it comes to roles vs. permissions. Most of the examples, and to a
     * large part, the entire framework is very role-centric. In the real-world we require finer grained control to
     * the applications we build. It usually is not enough simply to say this user is an Admin or an User. Usually the
     * business requirements dictate that we have the ability to turn on/off specific features even within these
     * high-level roles (or modes of our application). Thus the introduction of the concept of Feature. Features are
     * those finer grained permissions within the application that allow us to turn on/off specific features and
     * functionality within an application or API.
     * @return
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//        // first do the individual features...
//        for (Feature feature : user.getFeatures()) {
//            authorities.add(new SimpleGrantedAuthority(feature.toString()));
//        }
//        // now do the roles
//        for (UserRole role : user.getRoles() ) {
//            authorities.add(new SimpleGrantedAuthority(role.toString()));
//        }
//
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "CustomUserDetails [user=" + user + "]";
    }
}