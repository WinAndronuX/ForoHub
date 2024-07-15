package com.winandronux.forohub.dto;

import com.winandronux.forohub.entity.Topic;

import java.time.LocalDateTime;

public record TopicDTO (
        Long Id,
        String title,
        String content,
        String author,
        String course,
        LocalDateTime createdAt,
        Integer numResponses
) {

    public TopicDTO(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getContent(), topic.getAuthor().getUsername(),
                topic.getCourse().getName(), topic.getCreatedAt(), topic.getNumResponses());
    }

}
