package com.toy.foodiminder.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "t_co_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_cd")
    private Integer userCd;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pass", length = 20)
    private String userPass;

    @Column(name = "user_name", length = 20)
    private String userName;

    @Column(name = "user_email", length = 100)
    private String userEmail;

    @Column(name = "user_phone", length = 20)
    private String userPhone;

    @Column(name = "create_dt")
    private LocalDateTime createAt;

    @Column(name = "update_dt")
    private LocalDateTime updateDt;

    @PrePersist
    public void prePersist() {
        this.createAt = LocalDateTime.now();
        this.updateDt = LocalDateTime.now();
    }

}
