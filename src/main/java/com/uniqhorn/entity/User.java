package com.uniqhorn.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.OptBoolean;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@EqualsAndHashCode(of = "id")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@DynamicInsert
@DynamicUpdate

//Delete Annotations 
@SQLDelete(sql = "Update users SET enabled = 1 where user_id=?")
@Where(clause = "enabled != 1")

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @NotBlank
    @Column(unique = true)
    private String username; // Email

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private String password; // Hashed

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    private String position;

    @NotBlank
    @Column(name = "phone_number")
    private String phoneNumber;


    @NotNull
    //@Range(min = 1, max = 300)
    @Column(name = "leave_hours_total")
    private Integer totalLeavesHours;


    @NotNull
    @Column(name = "leave_hours_remain")
    private Integer leftLeavesHours;

    @NotNull
    @Column(name = "leave_hours_unpaid")
    private Integer unpaidLeave = 240;

    @NotBlank
    private String status;


    @JsonIgnore
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User created_by;

    private Timestamp record_timestamp = new Timestamp(System.currentTimeMillis());

    //Used to mark deleted objects
    //@Column (name = "Enabled")
    private byte enabled;

    private byte time_sheet_indicator = 0;

    //Used to mark deleted objects
    //@Column (name = "Enabled")

    @NotNull
    @Column(name = "start_date")
    @JsonProperty("startDate")
    @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
    private Date startDate;

    // ADD ROLES - Join Table

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    // OneToMany relationship with Leaves:

    @OneToMany(targetEntity = Leave.class, mappedBy = "user",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "uid_fk", referencedColumnName = "leave_id")
    //@JsonManagedReference
    @JsonIgnore
    private Set<Leave> leaves;

    //One to many relationship with Work - Timesheet
    @OneToMany(targetEntity = Work.class, mappedBy = "user",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Work> work;


    //One to many relationship with Leave - created By


    @OneToMany(mappedBy = "created_by")
    @JsonIgnore
    private Set<User> users = new HashSet<User>();


    public User(@NotBlank String username, @NotBlank String password, @NotBlank String firstName,
                @NotBlank String lastName, @NotBlank String position, @NotBlank String status,
                @NotBlank String phoneNumber, @NotNull Date start_date,
                @NotNull Integer totalLeavesHours, @NotNull Integer leftLeavesHours) {
        super();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.startDate = start_date;
        this.totalLeavesHours = totalLeavesHours;
        this.leftLeavesHours = leftLeavesHours;
    }

    // This constructor is used for Spring security
    // To create a user from CustomerUserDetails
    public User(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.position = user.getPosition();
        this.status = user.getStatus();
        this.phoneNumber = user.getPhoneNumber();
        this.startDate = user.getStartDate();
        this.roles = user.getRoles();
        this.totalLeavesHours = user.getTotalLeavesHours();
        this.leftLeavesHours = user.getLeftLeavesHours();
    }
}
