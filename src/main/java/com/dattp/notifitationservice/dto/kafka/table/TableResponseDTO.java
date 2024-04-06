package com.dattp.notifitationservice.dto.kafka.table;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TableResponseDTO{
  private TableState state;

  private Long id;

  private String name;

  private String image;

  private Integer amountOfPeople;

  private Float price;

  @JsonFormat(pattern = "HH:mm")
  private LocalDateTime from;

  @JsonFormat(pattern = "HH:mm")
  private LocalDateTime to;

  private String description;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime createAt;

  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime updateAt;

  public TableResponseDTO() {
    super();
  }
}