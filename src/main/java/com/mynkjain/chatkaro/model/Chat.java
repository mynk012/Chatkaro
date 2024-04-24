package com.mynkjain.chatkaro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String chat_name;
    private String chat_image;

    @ManyToMany
    private List<User> users = new ArrayList<>();

    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "chat")                   // we are mapping it to that it does not create a new table with name message_chat
    private List<Message> messages = new ArrayList<>();

}
