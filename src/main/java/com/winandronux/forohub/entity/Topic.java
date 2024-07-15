package com.winandronux.forohub.entity;

import com.winandronux.forohub.dto.TopicAddDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "topics")
@Entity(name = "Topic")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    private Integer numResponses;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reply> replies;

    public Topic(TopicAddDTO topicAddDTO, User author, Course course) {
        this.title = topicAddDTO.title();
        this.content = topicAddDTO.content();
        this.author = author;
        this.course = course;
        this.numResponses = 0;
        this.createdAt = LocalDateTime.now();
        this.replies = new HashSet<>();
    }

    public void updateInfo(TopicAddDTO topicAddDTO, Course course) {
        if (topicAddDTO.title() != null) this.title = topicAddDTO.title();
        if (topicAddDTO.content() != null) this.content = topicAddDTO.content();
        if (topicAddDTO.courseId() != null) this.course = course;
    }
}
