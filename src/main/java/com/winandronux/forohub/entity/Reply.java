package com.winandronux.forohub.entity;

import com.winandronux.forohub.dto.ReplyAddDTO;
import com.winandronux.forohub.dto.ReplyEditDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "replies")
@Entity(name = "Reply")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime createdAt;
    private Long repliesTo;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Reply(ReplyAddDTO replyAddDTO, Topic topic, User user) {
        this.message = replyAddDTO.message();
        this.createdAt = LocalDateTime.now();

        if (replyAddDTO.repliesTo() == null) this.repliesTo = 0L; else this.repliesTo = replyAddDTO.repliesTo();

        this.topic = topic;
        this.user = user;
    }

    public void UpdateInfo(ReplyEditDTO replyEditDTO) {
        this.message = replyEditDTO.message();
    }
}
