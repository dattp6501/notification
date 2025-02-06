package com.dattp.notifitationservice.dto.kafka.dish;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class DishResponseDTO implements Serializable {
  private Long id;

  private DishState state;

  private String name;

  private Float price;

  private String image;

  private String description;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime createAt;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime updateAt;

  public DishResponseDTO() {
    super();
  }
}