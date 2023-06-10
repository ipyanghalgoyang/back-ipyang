package com.project.ipyang.domain.chat.dto;

import com.project.ipyang.domain.chat.entity.ChatRoom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {
    private Long id;
    private String message;

    public ChatMessageDto(Long id, String message) {
        this.id = id;
        this.message = message;
    }



}
