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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.OptBoolean;

import lombok.Data;

@Data
@Entity
@Table(name = "client")

//Delete Annotations 
@SQLDelete(sql = "Update client SET active = 1 where client_id=?")
@Where(clause = "active != 1")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private long client_id;

    // Client name
    // size 2-24
    @NotBlank
    @Column(name = "client_name", unique = true)
    private String clientName;

    @NotNull
    @Column(name = "start_date")
    @JsonProperty("startDate")
    @JsonFormat(pattern = "yyyy-MM-dd", lenient = OptBoolean.FALSE)
    private Date startDate;

    @JsonIgnore
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    private User created_by;

    private Timestamp recordTimestamp = new Timestamp(System.currentTimeMillis());

    // Used to mark deleted objects
    private byte active;

    // Constructor for adding a client with no id (it is auto-generated)

    public Client(String clientName, Date start_date) {
        this.clientName = clientName;
        this.startDate = start_date;
    }

    public Client(Client client) {
        this.clientName = client.clientName;
        this.startDate = client.startDate;
    }

    public Client() {

    }

    public String toString() {
        return clientName;
    }

    public Client(long clientId) {
        this.client_id = clientId;
    }
}
