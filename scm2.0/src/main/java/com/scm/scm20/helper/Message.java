package com.scm.scm20.helper;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String content;
    @Builder.Default
    private MessageType type=MessageType.blue;
}
