package com.example.demo.dto.error;

import com.example.demo.exceptions.ExceptionUtils;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorDTO {

  private String messageKey;

  private String message;

  public ErrorDTO(String messageKey) {
    this.messageKey = messageKey;
    this.message = ExceptionUtils.MESSAGES.get(messageKey);
  }
}
