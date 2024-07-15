package com.winandronux.forohub.controller;

import com.winandronux.forohub.dto.*;

import com.winandronux.forohub.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    @Operation(summary = "Obtener el listado de topics")
    public ResponseEntity<Page<TopicDTO>> getTopics(Pageable pageable) {
        return ResponseEntity.ok(topicService.getAllTopics(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener los detalles de un topic a partir del Id")
    public ResponseEntity<TopicDTO> getTopic(@PathVariable Long id) {
        var topic = topicService.getTopic(id);
        if (topic == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(topic);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo topic")
    public ResponseEntity<TopicDTO> createTopic(@RequestBody @Valid TopicAddDTO topicAddDTO) {
        var topic = topicService.createTopic(topicAddDTO);
        var uri = UriComponentsBuilder.fromPath("/topics/{id}").buildAndExpand(topic.Id()).toUri();
        return ResponseEntity.created(uri).body(topic);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar un topic a partir de el Id. Campos editables: title, content, course")
    public ResponseEntity<TopicDTO> updateTopic(@PathVariable Long id, @RequestBody TopicAddDTO topicAddDTO) {
        var topic = topicService.updateTopic(id, topicAddDTO);
        if (topic == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(topic);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un topic a partir del Id")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/replies")
    @Operation(summary = "Obtener el listado de respuestas de un topic. Requiere el id del topic")
    public ResponseEntity<Page<ReplyDTO>> getReplies(@PathVariable Long id, Pageable pageable) {
        return ResponseEntity.ok(topicService.getReplies(id, pageable));
    }

    @PostMapping("/{id}/replies")
    @Operation(summary = "Agregar una respuesta a un topic. Requiere el id del topic")
    public ResponseEntity<ReplyDTO> addReply(@PathVariable Long id, @RequestBody @Valid ReplyAddDTO replyAddDTO) {
        var reply = topicService.addReply(id, replyAddDTO);
        if (reply == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reply);
    }

    @PutMapping("/{id}/replies/{replyId}")
    @Operation(summary = "Editar una respuesta de un topic. Requiere el id del topic y el id del reply. Campos editables: message")
    public ResponseEntity<ReplyDTO> editReply(@PathVariable Long id, @PathVariable Long replyId, @RequestBody @Valid ReplyEditDTO replyEditDTO) {
        var reply = topicService.editReply(id, replyId, replyEditDTO);
        if (reply == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(reply);
    }

    @DeleteMapping("/{id}/replies/{replyId}")
    @Operation(summary = "Eliminar una respuesta de un topic. Requiere el id del topic y el id del reply")
    public ResponseEntity<Void> deleteReply(@PathVariable Long id, @PathVariable Long replyId) {
        topicService.removeReply(id, replyId);
        return ResponseEntity.noContent().build();
    }
}
