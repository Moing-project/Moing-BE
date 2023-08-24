package com.moing.moingbe.domain.workspace.entity;

import com.moing.moingbe.domain.workspace.dto.WorkCreateRequestDto;
import com.moing.moingbe.domain.workspace.enums.WorkAllowEnum;
import com.moing.moingbe.domain.workspace.enums.WorkSubjectEnum;
import com.moing.moingbe.global.maps.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Workspace extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(value = EnumType.STRING)
    private WorkSubjectEnum subject; // 분야

    private Integer totalMember;

    private String introduce;

    private String imageSrc;

    // Team Service

    @Enumerated(value = EnumType.STRING)
    private WorkAllowEnum allowType; // 멤버 참가 방식

    private LocalDateTime lastTime; // 모집 마감 시간;

    private Integer needMember;

    public Workspace() {

    }

    public Workspace(String title, WorkSubjectEnum subject, Integer totalMember, String introduce, String imageSrc, WorkAllowEnum allowType, LocalDateTime lastTime, Integer needMember) {
        this.title = title;
        this.subject = subject;
        this.totalMember = totalMember;
        this.introduce = introduce;
        this.imageSrc = imageSrc;
        this.allowType = allowType;
        this.lastTime = lastTime;
        this.needMember = needMember;
    }

    public Workspace(WorkCreateRequestDto createDto, Integer totalMember) {
        this.title = createDto.title();
        this.subject = WorkSubjectEnum.get(createDto.subject());
        this.totalMember = totalMember;
        this.introduce = createDto.introduce();
        this.imageSrc = createDto.imageSrc();
        String[] date = createDto.date().split("\\.");
        this.lastTime = LocalDateTime.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2])-1, 23,59);
        this.allowType = WorkAllowEnum.get(createDto.allowType());
        this.needMember = createDto.needMember();
    }
}
