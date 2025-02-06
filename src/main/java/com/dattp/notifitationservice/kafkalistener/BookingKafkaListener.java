package com.dattp.notifitationservice.kafkalistener;

import com.dattp.notifitationservice.config.kafka.TopicKafkaConfig;
import com.dattp.notifitationservice.dto.kafka.booking.BookingResponseDTO;
import com.dattp.notifitationservice.service.SendMailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class BookingKafkaListener extends ListenerKafka {
  @Autowired
  private SendMailService sendMailService;

  @Value("${payment.url}")
  private String urlPayment;

  @Value("${mail.username}")
  private String MAIL_USERNAME;

  @Value("${mail.password}")
  private String MAIL_PASSWORD;

  @KafkaListener(topics = TopicKafkaConfig.PROCESS_BOOKING_TOPIC, groupId = "com.dattp.restaurant.notification.process_order", containerFactory = "factoryBooking")
  public void listenerProcessBooking(@Payload BookingResponseDTO dto, Acknowledgment acknowledgment) {
    try {
      telegramService.sendNotificationService(telegramService.genMessageTemplateBooking(dto, "THÔNG TIN ĐẶT BÀN MỚI"));
      acknowledgment.acknowledge();
    } catch (Exception e) {
      log.error("=============> listenerProcessBooking:Exception:{}", e.getMessage());
    }
//        sendMailService.sendOutlook(MAIL_USERNAME, MAIL_PASSWORD, "dattp.b19at040@stu.ptit.edu.vn", "THÔNG BÁO ĐẶT BÀN", createContentMailBooking(dto));
  }

//    private String createContentMailBooking(BookingResponseDTO dto){
//        String style =
//        "<style>"
//        +"table {"
//        +"  font-family: arial, sans-serif;"
//        +"  border-collapse: collapse;"
//        +"  width: 100%;"
//        +"}"
//        +"td, th {"
//        +"  border: 1px solid #dddddd;"
//        +"  text-align: left;"
//        +"  padding: 8px;"
//        +"}"
//        +"tr:nth-child(even) {"
//        +"  background-color: #dddddd;"
//        +"};"
//        +"</style>";
//        String html =
//        "<!DOCTYPE html>\n"
//        + "<html lang=\"en\">\n"
//        + "\n"
//        + "<head>\n"
//        + style
//        + "</head>\n"
//        + "\n"
//        + "<body>\n"
//        + "    <h3 style=\"color: blue;\">ĐẶT HÀNG THÀNH CÔNG</h3>\n"
//        + "    <div>Khách hàng : "+dto.getCustemerFullname().toUpperCase()+"</div>\n"
//        + "    <div>Ngày đặt : "+dto.getCreateAt()+"</div>\n"
//        + "    <div>Ghi chú : "+dto.getDescription()+"</div>\n";
//        // danh sach bàn
//        StringBuilder table =
//            new StringBuilder("<table>"
//                + "<tr>"
//                + "  <th>Tên bàn</th>"
//                + "  <th>Giá</th>"
//                + "  <th>Trạng thái</th>"
//                + "</tr>");
//        for(BookedTableResponseDTO t : dto.getBookedTables()){
//            String tr =
//            "<tr>"
//            +"  <td>"+t.getName()+"</td>"
//            +"  <td>"+formatNumberMonney(t.getPrice())+"</td>"
//            +"  <td>"+t.getState()+"</td>"
//            +"</tr>";
//            table.append(tr);
//        }
//
//        // voucher
//        // String vouchers =
//        // "<tr>"
//        // +"  <td colspan=\"2\">"+"Voucher"+"</td>"
//        // +"  <td>";
//        // for(VoucherBooking v : booking.getVoucherBookings()){
//        //     if(v.getType().equals("n")){
//        //         vouchers += "-"+formatNumberMonney(v.getValue())+"<br/>";
//        //     }else if(v.getType().equals("%")){
//        //         vouchers += "-"+formatNumber(v.getValue())+"%"+"<br/>";
//        //     }
//        // }
//        // vouchers = vouchers.substring(0, vouchers.length()-5);
//        // vouchers+="</td>";
//        // vouchers+="</tr>";
//        // table += vouchers;
//        // thanh tien
//        table.append("  <td colspan=\"2\">" + "Tiền cọc" + "</td>");
//        table.append("  <td>").append(formatNumberMonney(dto.getDeposits())).append("</td>");
//        //
//        table.append("</table>");
//        html += table;
//        String linkPayment = "<a href=\""+urlPayment+"/"+dto.getId()+"\">THANH TOÁN NGAY</a>";
//        html+=linkPayment;
//        //
//        html += "    <h3 style=\"color: blue;\">Cảm ơn bạn đã quan tâm tới chúng tôi</h3>\n"
//        + "\n"
//        + "</body>\n"
//        + "\n"
//        + "</html>";
//        return html;
//    }
//    public static String formatNumberMonney(float number){
//        return NumberFormat.getInstance(new Locale("vi", "VN")).format(number)+ " "
//        + NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).getCurrency().toString();
//    }
//    public static String formatNumber(float number){
//        return NumberFormat.getInstance(new Locale("vi", "VN")).format(number);
//    }
}
