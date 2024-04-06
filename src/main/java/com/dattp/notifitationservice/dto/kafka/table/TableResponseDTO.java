package com.dattp.notifitationservice.dto.kafka.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class TableResponseDTO implements Serializable {
  private TableState state;

  private Long id;

  private String name;

  private String image;

  private Integer amountOfPeople;

  private Float price;

  @JsonFormat(pattern = "HH:mm")
  private LocalTime from;

  @JsonFormat(pattern = "HH:mm")
  private LocalTime to;

  private String description;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime createAt;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime updateAt;

  public TableResponseDTO() {
    super();
  }
}