package com.winandronux.forohub.service;

import com.winandronux.forohub.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TopicService {
    Page<TopicDTO> getAllTopics(Pageable pageable);
    TopicDTO getTopic(Long id);
    TopicDTO createTopic(TopicAddDTO topicAddDTO);
    TopicDTO updateTopic(Long id, TopicAddDTO topicAddDTO);
    void deleteTopic(Long id);
    Page<ReplyDTO> getReplies(Long topicId, Pageable pageable);
    ReplyDTO addReply(Long topicId, ReplyAddDTO replyAddDTO);
    ReplyDTO editReply(Long topicId, Long replyId, ReplyEditDTO replyEditDTO);
    void removeReply(Long topicId, Long replyId);
}
