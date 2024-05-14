package com.example.EMTECH_ERP.BACKEND.AuthenticationModule.Users;
import com.example.EMTECH_ERP.BACKEND.AuthenticationModule.Roles.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Table(name = "users")
public class Users {
    @Id
    @SequenceGenerator(name = "user_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "sn", updatable = false)
    private Long sn;
    @Column(name = "username", length = 40, unique = true, nullable = false)
    private String username;
    @Column(name = "firstname",  length = 50)
    private String firstName;
    @Column(name = "lastname", length = 50)
    private String lastName;
    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;
    @Column(name = "phone", length = 15)
    private String phoneNo;
    @Column(name = "password", length = 255, nullable = false)
    private String password;
    @Column(name = "createdOn", length = 50)
    private Date createdOn;
    @Column(name = "modifiedBy", length = 50)
    private String modifiedBy;
    @Column(name = "modifiedOn", length = 50)
    private Date modifiedOn;
    @Column(name = "verifiedBy", length = 50)
    private String verifiedBy;
    @Column(name = "verifiedOn", length = 50)
    private Date verifiedOn;
    @Column(name = "verifiedFlag", length = 5)
    private Character verifiedFlag;
    @Column(name = "deleteFlag", length = 5)
    private Character deleteFlag;
    @Column(name = "deletedOn", length = 50)
    private Date deletedOn;
    @Column(name = "active", length = 50)
    private boolean isAcctActive;
    @Column(name = "first_login", length = 1)
    private Character firstLogin = 'Y';
    @Column(name = "locked", length = 15)
    private boolean isAcctLocked;
    private boolean systemGenPassword = true;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}

