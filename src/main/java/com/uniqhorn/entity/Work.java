package com.uniqhorn.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.OptBoolean;

import lombok.Data;

@Entity
@Data
@Table(name = "work", uniqueConstraints = {@UniqueConstraint(columnNames = {"work_id"})})

//Delete Annotations 
@SQLDelete(sql = "Update work SET enabled = 1 where work_id=?")
@Where(clause = "enabled != 1")

public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_id")
    private long id;

    // Work Date
    @NotNull
    @Column(name = "work_date")
    @JsonProperty("workDate")
    @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
    private Date workDate;

    // Hours of work <=24
    @NotNull
    @Column(name = "work_hours")
    private double workHours;

    @NotNull

    private String workType;

    // Overtime
    @NotNull
    @Column(name = "work_overtime")
    @JsonProperty("isOvertime")
    private boolean isOvertime = false;

    // Used to mark deleted objects
    private byte enabled;

    private Timestamp record_timestamp = new Timestamp(System.currentTimeMillis());


    // cascade = CascadeType.ALL
    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")

    private Client client;

    // Many to one relationship with USER
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    // @JsonBackReference
    @JsonIgnore
    private User user;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    private User created_by;


    public Work() {

    }

    // Constructor No ID - Auto-generated
    // Overtime added
    public Work(@NotNull Date workDate, @NotNull int workHours, String workType, boolean isOvertime, Client client,
                User user) {
        this.workDate = workDate;
        this.workHours = workHours;
        this.workType = workType;
        this.isOvertime = isOvertime;
        this.client = client;
        this.user = user;
    }

    public Work(@NotNull Date workDate, @NotNull int workHours, String workType, Client client) {
        this.workDate = workDate;
        this.workHours = workHours;
        this.workType = workType;
        this.client = client;
    }

    // Constructor No OVERTIME - False by default
    public Work(@NotNull Date workDate, @NotNull int workHours, String workType, Client client, User user) {
        this.workDate = workDate;
        this.workHours = workHours;
        this.workType = workType;
        this.client = client;
        this.user = user;


    }

    // Constructor to create Work for User
    // Check overtime
    public Work(Work work, User user) {
        this.workDate = work.workDate;
        this.workHours = work.workHours;
        this.workType = work.workType;
        // this.client = work.client;
        this.user = user;

        if (work.isOvertime) {
            this.isOvertime = work.isOvertime;
        }
    }

    public void setUserId(long user_id) {

    }
}
