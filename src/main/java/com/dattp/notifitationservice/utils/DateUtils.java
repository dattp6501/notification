package com.dattp.notifitationservice.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class DateUtils {
  public static final ZoneOffset zoneOffset = ZoneOffset.UTC;
  public static final ZoneId zoneId = ZoneId.of("UTC");
  public static final ZoneId zoneIdHCM = ZoneId.of("Asia/Ho_Chi_Minh");

  public static long getCurrentMils() {
    return LocalDateTime.now(zoneId).toInstant(zoneOffset).toEpochMilli();
  }

  public static LocalDateTime convertToLocalDateTime(long timeMils) {
    return Instant.ofEpochMilli(timeMils).atZone(zoneId).toLocalDateTime();
  }

  public static LocalDateTime getcurrentLocalDateTime() {
    return LocalDateTime.now(zoneId);
  }
}