package com.dattp.notifitationservice.dto.kafka.bookeddish;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class BookedDishResponseDTO {
  private Long id;
  private Long dishId;
  private BookedDishState state;
  private Integer total;
  private Float price;
  private String name;

  public BookedDishResponseDTO() {
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof BookedDishResponseDTO other)) return false;
    return Objects.equals(id, other.id);
  }
}