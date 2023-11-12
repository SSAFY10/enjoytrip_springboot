package com.ssafy.enjoytrip.board.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAndAnswerDto {
    QnaBoard question;
    QnaBoard answer;
}
