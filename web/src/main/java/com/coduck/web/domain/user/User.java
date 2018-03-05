package com.coduck.web.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by YG-MAC on 2018. 1. 21..
 */

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String githubId;

    @Column(updatable = false)
    private Date registerDate;

    @Column(insertable = false)
    private Date updateDate;

    public User(String githubId) {
        this.githubId = githubId;
    }
}
