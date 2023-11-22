package com.example.demo.exceptions;

import lombok.*;

/**
 * @author duongnhathuy
 * @version 1.0
 * @since 15/09/2021 Class CrmException kế thừa Exception phục vụ việc xử lý và tổng hợp lỗi trong
 * sourceCode CRM.
 */
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GPSException extends RuntimeException {

  private String messageKey;
  private String message;

  public GPSException(String msgKey) {
    this.messageKey = msgKey;
    this.message = String.format(ExceptionUtils.MESSAGES.get(this.messageKey));
  }

  public GPSException(String msgKey, String msg) {
    this.messageKey = msgKey;
    this.message = msg;
  }
}