package com.mynkjain.chatkaro.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;
    private String image;

    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToOne
    private Chat chat;

    private LocalDateTime timetamp;


}
