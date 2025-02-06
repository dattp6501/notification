package com.dattp.notifitationservice.dto.kafka.bookedtable;

public enum BookedTableState {
  PROCESSING,
  NOT_FOUND,
  SUCCESS,
  CANCEL,
  DELETE
}