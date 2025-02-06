package com.dattp.notifitationservice.service;

import com.dattp.notifitationservice.dto.kafka.booking.BookingResponseDTO;
import com.dattp.notifitationservice.dto.kafka.dish.DishResponseDTO;
import com.dattp.notifitationservice.dto.kafka.table.TableResponseDTO;
import com.dattp.notifitationservice.dto.kafka.user.UserResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@Log4j2
public class TelegramService extends com.dattp.notifitationservice.service.Service {
  @Value("${telegram.monitor_bot_token}")
  private String monitorBotToken;

  @Value("${telegram.monitor_bot_chat_id}")
  private String monitorBotChatId;

  @Value("${telegram.notification_bot_token}")
  private String notificationBotToken;

  @Value("${telegram.notification_bot_chat_id}")
  private String notificationBotChatId;

  public void sendNotificationMonitorSystem(String message) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    JSONObject request = new JSONObject();
    request.put("text", "\uD83D\uDD14 " + message);
    request.put("parse_mode", "HTML");
    request.put("disable_web_page_preview", false);
    request.put("chat_id", monitorBotChatId);
    HttpEntity<Object> requestEntity = new HttpEntity<>(request.toString(), headers);

    String url = String.format("https://api.telegram.org/bot%s/sendMessage", monitorBotToken);
    restTemplate.postForObject(url, requestEntity, Object.class);
  }

  public void sendNotificationService(String message) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    JSONObject request = new JSONObject();
    request.put("text", message);
    request.put("parse_mode", "HTML");
    request.put("disable_web_page_preview", false);
    request.put("chat_id", notificationBotChatId);
    HttpEntity<Object> requestEntity = new HttpEntity<>(request.toString(), headers);

    String url = String.format("https://api.telegram.org/bot%s/sendMessage", notificationBotToken);
    restTemplate.postForObject(url, requestEntity, Object.class);
  }

  public String genMessageTemplateDish(DishResponseDTO dto, String title) {
    String message = "";
    message += String.format("<b>\uD83C\uDF7D    %s</b>\n", title);
    message += String.format("<b>ID         : </b>%d.\n", dto.getId());
    message += String.format("<b>Tên        : </b>%s.\n", dto.getName());
    message += String.format("<b>Giá        : </b>%s.\n", dto.getPrice());
    message += String.format("<b>Trạng thái : </b>%s.\n", dto.getState().name());
    message += String.format("<b>Cập nhật   : </b>%s.\n", dto.getUpdateAt().format(DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd")));
    message += String.format("<b>Tạo        : </b>%s.\n", dto.getCreateAt().format(DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd")));
    message += dto.getImage();
    return message;
  }

  public String genMessageTemplateTable(TableResponseDTO dto, String title) {
    String message = "";
    message += String.format("<b>\uD83E\uDE91    %s</b>\n", title);
    message += String.format("<b>ID         : </b>%d.\n", dto.getId());
    message += String.format("<b>Tên        : </b>%s.\n", dto.getName());
    message += String.format("<b>Giá        : </b>%s.\n", dto.getPrice());
    message += String.format("<b>Thời gian  : </b>%s - %s.\n", dto.getFrom().format(DateTimeFormatter.ofPattern("HH:mm")), dto.getTo().format(DateTimeFormatter.ofPattern("HH:mm")));
    message += String.format("<b>Số ghế     : </b>%s.\n", dto.getAmountOfPeople());
    message += String.format("<b>Trạng thái : </b>%s.\n", dto.getState().name());
    message += String.format("<b>Cập nhật   : </b>%s.\n", dto.getUpdateAt().format(DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd")));
    message += String.format("<b>Tạo        : </b>%s.\n", dto.getCreateAt().format(DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd")));
    if (Objects.nonNull(dto.getImage())) message += dto.getImage();
    else message += "(Không có ảnh)";
    return message;
  }

  public String genMessageTemplateUser(UserResponseDTO dto, String title) {
    String message = "";
    message += String.format("<b>\uD83D\uDE4E    %s</b>\n", title);
    message += String.format("<b>ID            : </b>%d.\n", dto.getId());
    message += String.format("<b>Email         : </b>%s.\n", dto.getMail());
    message += String.format("<b>Tên đày đủ    : </b>%s.\n", dto.getFullname());
    message += String.format("<b>Tên đăng nhập : </b>%s.\n", dto.getUsername());
    message += String.format("<b>Ngày tạo      : </b>%s.\n", dto.getCreateAt().format(DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd")));
    return message;
  }

  public String genMessageTemplateBooking(BookingResponseDTO dto, String title) {
    String message = "";
    message += String.format("<b>\uD83E\uDE91    %s</b>\n", title);
    message += String.format("<b>ID            : </b>%d.\n", dto.getId());
    message += String.format("<b>User ID       : </b>%d.\n", dto.getCustomerId());
    message += String.format("<b>Tên đầy đủ    : </b>%s.\n", dto.getCustemerFullname());
    message += String.format("<b>Tiền cọc      : </b>%f.\n", dto.getDeposits());
    message += String.format("<b>Bắt đầu       : </b>%s.\n", dto.getFrom().format(DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd")));
    message += String.format("<b>Kêt thúc      : </b>%s.\n", dto.getTo().format(DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd")));
    message += String.format("<b>Đặt lúc       : </b>%s.\n", dto.getCreateAt().format(DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd")));
    message += String.format("<b>Trạng thái    : </b>%s.\n", dto.getState());
    message += String.format("<b>SL bàn        : </b>%d.\n", dto.getBookedTables().size());
    message += String.format("<b>Mô tả         : </b>%s.\n", dto.getDescription());
    return message;
  }
}