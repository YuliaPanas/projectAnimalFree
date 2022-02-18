package com.example.projectAnimalFree.entity;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "text")
    private String text;

    @Column(name = "local_date")
    private String localDate ;


}
