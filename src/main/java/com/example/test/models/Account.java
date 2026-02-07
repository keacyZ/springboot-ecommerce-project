package com.example.test.models;
import com.example.test.util.constants.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotEmpty
    @Column(unique = true, nullable = false)
    private String email;



    @Setter
    @Getter
    private String gender;


    @NotEmpty
    private String password;

    private String firstName;
    private String lastName;

    private Integer age;


    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    @Column(unique = true)
    private String passwordResetToken;

    private LocalDateTime password_reset_token_expiry;

    @ManyToMany
    private Set<Authority> authorities = new HashSet<>();

    @Column(nullable = false)

    private boolean active = true;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender = gender;}



}
