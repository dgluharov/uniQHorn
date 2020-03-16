package com.uniqhorn.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.json.JSONObject;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.OptBoolean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor

@Setter
@Getter
public class UserUpdate extends JSONObject {


    @Column(unique = true)
    private String username; // Email

    //@NotBlank
    //private String password; // Hashed

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String position;

    @NotBlank
    private String status;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    @JsonProperty("startDate")
    @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
    private Date startDate;

    @NotNull
    private Integer totalLeavesHours;

    @NotNull
    private Integer leftLeavesHours;

//	// Add Role 
//	@NotBlank
//	@Enumerated(EnumType.STRING)
//	private UserRole role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;



    public UserUpdate(String username, @NotBlank String firstName, @NotBlank String lastName, @NotBlank String position,
                      @NotBlank String status, @NotBlank String phoneNumber, @NotBlank Date startDate, @NotBlank Set<Role> roles, @NotNull Integer totalLeavesHours, @NotNull Integer leftLeavesHours) {

        this.username = username;
       // this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.startDate = startDate;
        this.roles = roles;
        this.totalLeavesHours = totalLeavesHours;
        this.leftLeavesHours = leftLeavesHours;
    }
}
