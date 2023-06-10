package com.project.ipyang.domain.chat.dto;

import com.project.ipyang.domain.chat.entity.ChatMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class ChatRoomDto {
    private Long id;
    private Long member1;
    private Long member2;
    private List<ChatMessage> messages = new ArrayList<>();

    public ChatRoomDto(Long id,
                       Long member1,
                       Long member2,
                       List<ChatMessage> messages) {
        this.id = id;
        this.member1 = member1;
        this.member2 = member2;
        this.messages = messages;
    }

}
