package com.winandronux.forohub.service.impl;

import com.winandronux.forohub.dto.*;
import com.winandronux.forohub.entity.Course;
import com.winandronux.forohub.entity.Reply;
import com.winandronux.forohub.entity.Topic;
import com.winandronux.forohub.repository.CourseRepository;
import com.winandronux.forohub.repository.ReplyRepository;
import com.winandronux.forohub.repository.TopicRepository;
import com.winandronux.forohub.repository.UserRepository;
import com.winandronux.forohub.service.TopicService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final ReplyRepository replyRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, UserRepository userRepository, CourseRepository courseRepository, ReplyRepository replyRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.replyRepository = replyRepository;
    }

    @Override
    public Page<TopicDTO> getAllTopics(Pageable pageable) {
        return topicRepository.findAll(pageable).map(TopicDTO::new);
    }

    @Override
    public TopicDTO getTopic(Long id) {
        return topicRepository.findById(id).map(TopicDTO::new).orElse(null);
    }

    @Override
    @Transactional
    public TopicDTO createTopic(TopicAddDTO topicAddDTO) {
        if (topicRepository.existsByTitle(topicAddDTO.title()))
            throw new EntityExistsException("A topic with the title \"" + topicAddDTO.title() + "\"  already exists");

        var user = userRepository.findById(topicAddDTO.userId());
        var course = courseRepository.findById(topicAddDTO.courseId());

        if (user.isEmpty())
            throw new  ValidationException("The user with id " + topicAddDTO.userId() + " does not exist");

        if (course.isEmpty())
            throw new  ValidationException("The courseId with id " + topicAddDTO.courseId() + " does not exist");

        var topic = new Topic(topicAddDTO, user.get(), course.get());
        topicRepository.save(topic);

        return new TopicDTO(topic);
    }

    @Override
    @Transactional
    public TopicDTO updateTopic(Long id, TopicAddDTO topicAddDTO) {
        var optionalTopic = topicRepository.findById(id);

        if (optionalTopic.isEmpty())
            throw new EntityNotFoundException("Topic with id " + id + " not found");

        Course course = null;

        if (topicAddDTO.courseId() != null) {
            var optionalCourse = courseRepository.findById(topicAddDTO.courseId());
            if (optionalCourse.isEmpty())
                throw new  ValidationException("The courseId with id " + topicAddDTO.courseId() + " does not exist");

            course = optionalCourse.get();
        }

        var topic = optionalTopic.get();
        topic.updateInfo(topicAddDTO, course);

        return new TopicDTO(topic);
    }

    @Override
    @Transactional
    public void deleteTopic(Long id) {
        var optionalTopic = topicRepository.findById(id);

        if (optionalTopic.isPresent())
            topicRepository.delete(optionalTopic.get());
        else
            throw new EntityNotFoundException("The topic with id " + id + " does not exist");
    }

    @Override
    public Page<ReplyDTO> getReplies(Long topicId, Pageable pageable) {
        if (!topicRepository.existsById(topicId))
            throw new EntityNotFoundException("The topic with id " + topicId + " does not exist");

        return replyRepository.findAllByTopicId(topicId, pageable).map(ReplyDTO::new);
    }

    @Override
    @Transactional
    public ReplyDTO addReply(Long topicId, ReplyAddDTO replyAddDTO) {

        var optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isEmpty())
            throw new EntityNotFoundException("The topic with id " + topicId + " does not exist");

        var optionalUser = userRepository.findById(replyAddDTO.userId());
        if (optionalUser.isEmpty())
            throw new  ValidationException("The user with id " + replyAddDTO.userId() + " does not exist");

        var reply = new Reply(replyAddDTO, optionalTopic.get(), optionalUser.get());
        replyRepository.save(reply);

        return new ReplyDTO(reply);
    }

    @Override
    @Transactional
    public ReplyDTO editReply(Long topicId, Long replyId, ReplyEditDTO replyEditDTO) {
        if (!topicRepository.existsById(topicId))
            throw new EntityNotFoundException("The topic with id " + topicId + " does not exist");

        var optionalReply = replyRepository.findById(replyId);
        if (optionalReply.isEmpty()) return null;

        var reply = optionalReply.get();
        reply.UpdateInfo(replyEditDTO);

        return new ReplyDTO(reply);
    }

    @Override
    @Transactional
    public void removeReply(Long topicId, Long replyId) {
        if (!topicRepository.existsById(topicId))
            throw new EntityNotFoundException("The topic with id " + topicId + " does not exist");

        var reply = replyRepository.findById(replyId);
        if (reply.isPresent())
            replyRepository.delete(reply.get());
        else
            throw new EntityNotFoundException("the reply with id " + replyId + " does not exist");
    }
}
