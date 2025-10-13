package be.bstorm.tf_java2025_xface_api.dl.entities;

import be.bstorm.tf_java2025_xface_api.dl.entities.msc.BaseEntityNotGenerated;
import be.bstorm.tf_java2025_xface_api.dl.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@EqualsAndHashCode(callSuper=true, of = {"email"})
@ToString(callSuper = true, of = {"email","firstName", "lastName", "roles"})
@Entity @Table(name = "user_")
public class User extends BaseEntityNotGenerated<UUID> implements UserDetails {

    @Getter @Setter
    @Column(unique = true, nullable = false, length = 150)
    private String email;

    @Getter @Setter
    @Column(nullable = false)
    private String password;

    @Getter @Setter
    @Column(nullable = false, length = 123)
    private String firstName;

    @Getter @Setter
    @Column(nullable = false, length = 80)
    private String lastName;

    @Getter @Setter
    @Column(nullable = false)
    private LocalDate birthDate;

    @Getter @Setter
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    @Getter @Setter
    @Column(nullable = true)
    private String image;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Set<User> friends = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Set<Post> rePosts = new HashSet<>();

    public User(String email, String password, String firstName, String lastName, LocalDate birthDate) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Set<User> getFriends() {
        return Set.copyOf(friends);
    }

    public void addFriend(User friend) {
        friends.add(friend);
    }

    public void removeFriend(User friend) {
        friends.remove(friend);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .toList();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }
}
