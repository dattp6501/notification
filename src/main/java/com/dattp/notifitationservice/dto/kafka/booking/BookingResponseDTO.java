package com.dattp.notifitationservice.dto.kafka.booking;

import com.dattp.notifitationservice.dto.kafka.bookeddish.BookedDishResponseDTO;
import com.dattp.notifitationservice.dto.kafka.bookedtable.BookedTableResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BookingResponseDTO {
  private Long id;

  private Long CustomerId;

  private String custemerFullname;

  private BookingState state;

  private Boolean paid;

  @JsonFormat(pattern = "HH:mm:ss dd/MM/yyyy")
  private LocalDateTime from;

  @JsonFormat(pattern = "HH:mm:ss dd/MM/yyyy")
  private LocalDateTime to;

  private Float deposits;

  private String description;

  @JsonFormat(pattern = "HH:mm:ss dd/MM/yyyy")
  private LocalDateTime createAt;

  @JsonFormat(pattern = "HH:mm:ss dd/MM/yyyy")
  private LocalDateTime updateAt;

  private List<BookedTableResponseDTO> bookedTables;

  private List<BookedDishResponseDTO> dishs;

  public BookingResponseDTO() {
    super();
  }
}