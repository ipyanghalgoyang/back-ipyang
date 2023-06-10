package com.project.ipyang.domain.chat.entity;

import com.project.ipyang.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatRoom_id")
    private Long id;

    @Column(name = "member_id1")
    private Long member1;

    @Column(name = "member_id2")
    private Long member2;

    @OneToMany(mappedBy = "chatRoom")
    List<ChatMessage> messages = new ArrayList<>();

}
