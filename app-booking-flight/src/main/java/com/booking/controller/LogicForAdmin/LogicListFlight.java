package com.booking.controller.LogicForAdmin;

import com.booking.View.ViewForAdmin.AddDeleteFlight;
import com.booking.View.ViewForAdmin.MenuOptionAdmin;
import com.booking.controller.LogicData.LogicFile;
import com.booking.controller.LogicData.LogicJson;
import com.booking.controller.LogicForUser.DateAnalysis;
import com.booking.controller.Regex.DateRegex;
import com.booking.model.Booking;
import com.booking.model.Flight;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class LogicListFlight {

    LogicFile logicFile = new LogicFile();
    LogicJson logicJson= new LogicJson();

    Scanner sc = new Scanner(System.in);

    public boolean CheckListFlightNull() throws FileNotFoundException {
        List<Flight> flights= logicFile.ConvertFileToFlight();
        if(flights.size()==0){
            return true;
        }
        return false;
    }

    public boolean CheckListFightCodeIsExist(String FlightCode) throws FileNotFoundException {
        List<Flight> flights= logicFile.ConvertFileToFlight();
        if(flights!=null){
            for (Flight flight : flights) {
                if(flight.getFlightCode().equalsIgnoreCase(FlightCode)){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    // tẠO CODE Random add cho flightcode
    public String RandomFlightCode(){
        String str ="ABCDEFGHYJKQMNL123456789";
        String ramdomFlightCode = "";
        Random random = new Random();
        for (int i =0;i<5;i++){
            int n =random.nextInt(str.length());
            ramdomFlightCode+=str.charAt(n);
        }
        return ramdomFlightCode;
    }

    public void AddFlight() throws FileNotFoundException {
        try {
            Flight flight = new Flight();
            System.out.println("*****THÊM CHUYẾN BAY*****");
            // Random mã flight code
            String codeFlight = RandomFlightCode();
            while (CheckListFightCodeIsExist(codeFlight)){
                codeFlight = RandomFlightCode();
            }
            flight.setFlightCode(codeFlight);
            System.out.println("Nhập tên chuyến bay : ");
            String nameFlight = sc.nextLine();
            flight.setFlightName(nameFlight);
            System.out.println("Nhập điểm xuất phát : ");
            String fromPlace = sc.nextLine();
            flight.setFromPlace(fromPlace);
            System.out.println("Nhập điểm hạ cánh : ");
            String endPlace = sc.nextLine();
            flight.setToPlace(endPlace);
            System.out.println("Ngày bay (dd/mm/yyyy): ");
            String tempDateQuery = sc.nextLine();
            while (DateRegex.dateRegex(tempDateQuery) || DateRegex.realDate(tempDateQuery)) {
                System.out.println("Vui lòng nhập ngày bay hợp lệ theo định dạng dd/mm/yyyy");
                System.out.println("Ngày bay (dd/mm/yyyy): ");
                tempDateQuery = sc.nextLine();
            }
            System.out.println("Giờ bay (hh:mm): ");
            String tempTimeQuery = sc.nextLine();
            while (DateRegex.timeRegex(tempTimeQuery) || DateRegex.realTime(tempTimeQuery)) {
                System.out.println("Vui lòng nhập giờ bay hợp lệ theo định dạng hh:mm");
                System.out.println("Giờ bay (hh:mm): ");
                tempTimeQuery = sc.nextLine();
            }
            Calendar dateCal = DateAnalysis.datetimeToCal(tempDateQuery,tempTimeQuery);
            flight.setTime(dateCal);
            System.out.println("Nhập giá tiền : ");
            long price = sc.nextLong();
            flight.setPrice(price);
            System.out.println("Nhập số ghế ngồi tối đa : ");
            int seats = sc.nextInt();
            flight.setNumberOfSeats(seats);
            System.out.println("*************************");
            //flights.add(flight);
            logicFile.WriteStringJsonToFile(logicJson.ConvertObjectToStringJson(flight),"list_flight.txt");
            System.out.println("Chuyến bay được thêm thành công");
            // sau đó trở về màn hình
            System.out.println("Đang quay trở về màn hình tuỳ chọn...");
            AddDeleteFlight addDeleteFlight = new AddDeleteFlight();
            addDeleteFlight.AddDeleteFlight();
        }
        catch (InputMismatchException e){
            System.out.println("Nhập sai cú pháp vui lòng nhập lại");
            AddFlight();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void CancelFlight() throws IOException {
        List<Flight> flights= logicFile.ConvertFileToFlight();
        List<Booking> bookings= logicFile.ConvertFileToBooking();
        if(CheckListFlightNull()){
            System.out.println("Danh sách đang rỗng . ");
            //trở về màn hình ...
            System.out.println("Đang quay trở về màn hình tuỳ chọn...");
            AddDeleteFlight addDeleteFlight = new AddDeleteFlight();
            addDeleteFlight.AddDeleteFlight();
        }
        else {
            System.out.println("Nhập mã chuyến bay để huỷ : ");
            String code = sc.nextLine();
            int count =0;
            for (Flight flight : flights) {
                if(code.contains(flight.getFlightCode())){
                    if(FindUserByFlight(flight.getFlightCode())!=null){
                        // tìm và gửi thông báo đến user
                        LogicNotificationAndVoucher logicNotificationAndVoucher = new LogicNotificationAndVoucher();
                        List<String> listUserEmail = FindUserByFlight(code);
                        for (String email : listUserEmail) {
                            logicNotificationAndVoucher.SendNotificationCancelFlight(email,code);
                        }
                        // hủy hoặc sưả trong list booking
                        for (Booking booking : bookings) {
                            if(booking.getUserEmail().equals(FindUserByFlight(flight.getFlightCode()))){
                                bookings.remove(booking);
                                logicFile.DeleteBookingInFile(bookings);
                                // nếu xoá hết trong list -> file null -> tạo file trước khi out chương trình
                                File file = new File("list_booking.txt");
                                // if file  exists, then create it
                                if (!file.exists()) {
                                    file.createNewFile();
                                }
                            }
                        }
                    }
                    flights.remove(flight);
                    logicFile.DeleteFlightInFile(flights);
                    // nếu xoá hết trong list -> file null -> tạo file trước khi out chương trình
                    File file = new File("list_flight.txt");
                    // if file  exists, then create it
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    System.out.println("Huỷ chuyến bay thành công !");
                    count++;
                    break;
                }
            }
            if(count==0){
                System.out.println("Không tìm thấy chuyến bay phù hợp . ");
            }
            ///trở về màn hình ...
            System.out.println("Đang quay trở về màn hình tuỳ chọn...");
            AddDeleteFlight addDeleteFlight = new AddDeleteFlight();
            addDeleteFlight.AddDeleteFlight();
        }
    }

    public void ShowListFlight() throws IOException {
        List<Flight> flights= logicFile.ConvertFileToFlight();
        DateRegex dateRegex = new DateRegex();
        if(CheckListFlightNull()){
            System.out.println("Danh sách đang rỗng . ");
            //trở về màn hình ...
            MenuOptionAdmin menuOptionAdmin = new MenuOptionAdmin();
            menuOptionAdmin.MenuOptionAdmin();
        }
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
            System.out.println("     *******LIST CÁC CHUYẾN BAY*******");
            for (Flight flight : flights) {
                System.out.println("*****************************************");
                System.out.println("Mã chuyến bay : "+ flight.getFlightCode());
                System.out.println("Tên chuyến bay : "+flight.getFlightName());
                System.out.println("Điểm xuất phát : "+flight.getFromPlace()+"--------  Điểm hạ cánh : "+flight.getToPlace());
                System.out.println("Ngày bay : "+formatter.format(flight.getTime().getTime()));
                System.out.println("Giá vé : "+flight.getPrice());
                System.out.println("Số chỗ ngồi tối đa : "+flight.getNumberOfSeats());
                System.out.println("*****************************************");
            }
            System.out.println("Đang quay trở về màn hình chính...");
            MenuOptionAdmin menuOptionAdmin = new MenuOptionAdmin();
            menuOptionAdmin.MenuOptionAdmin();
        }
    }

    public void ChangeFlight() throws FileNotFoundException {
        try {
            List<Flight> flights= logicFile.ConvertFileToFlight();
            List<Booking> bookings= logicFile.ConvertFileToBooking();
            if(CheckListFlightNull()){
                System.out.println("Danh sách đang rỗng . ");
                //trở về màn hình ...
                System.out.println("Đang quay trở về màn hình chính...");
                MenuOptionAdmin menuOptionAdmin = new MenuOptionAdmin();
                menuOptionAdmin.MenuOptionAdmin();
            }
            else {
                System.out.print("Nhập mã chuyến bay muốn sửa : ");
                String flightCode = sc.nextLine();
                if(!CheckListFightCodeIsExist(flightCode)){
                    System.out.println("Mã chuyến bay không tồn tại .");
                    System.out.println("Đang quay trở về màn hình chính...");
                    MenuOptionAdmin menuOptionAdmin = new MenuOptionAdmin();
                    menuOptionAdmin.MenuOptionAdmin();
                }
                for (Flight flight : flights) {
                    // duyệt đến chuyến bay có mã chuyến bay nhập bên trên
                    if(flight.getFlightCode().equalsIgnoreCase(flightCode)){
                        System.out.println("Nhập tên chuyến bay : ");
                        String flightName = sc.nextLine();
                        flight.setFlightName(flightName);
                        System.out.println("Nhập điểm xuất phát :");
                        String fromPlace = sc.nextLine();
                        flight.setFromPlace(fromPlace);
                        System.out.println("Nhập điểm hạ cánh : ");
                        String endPlace = sc.nextLine();
                        flight.setToPlace(endPlace);
                        System.out.println("Ngày bay (dd/mm/yyyy): ");
                        String tempDateQuery = sc.nextLine();
                        while (DateRegex.dateRegex(tempDateQuery) || DateRegex.realDate(tempDateQuery)) {
                            System.out.println("Vui lòng nhập ngày bay hợp lệ theo định dạng dd/mm/yyyy");
                            System.out.println("Ngày bay (dd/mm/yyyy): ");
                            tempDateQuery = sc.nextLine();
                        }
                        System.out.println("Giờ bay (hh:mm): ");
                        String tempTimeQuery = sc.nextLine();
                        while (DateRegex.timeRegex(tempTimeQuery) || DateRegex.realTime(tempTimeQuery)) {
                            System.out.println("Vui lòng nhập giờ bay hợp lệ theo định dạng hh:mm");
                            System.out.println("Giờ bay (hh:mm): ");
                            tempTimeQuery = sc.nextLine();
                        }
                        Calendar dateCal = DateAnalysis.datetimeToCal(tempDateQuery,tempTimeQuery);
                        flight.setTime(dateCal);
                        System.out.println("Nhập giá tiền : ");
                        long price = sc.nextLong();
                        flight.setPrice(price);
                        System.out.println("Nhập số ghế ngồi tối đa : ");
                        int seats = sc.nextInt();
                        //nếu chuyến bay đã đưc đặt thì số ghế tối đa phải trừ đi số ghế của những khách đã đặt
                        //gửi thoong báo nếu có user đăng ký chuyến bay
                        if(FindUserByFlight(flight.getFlightCode())!=null){
                            LogicNotificationAndVoucher logicNotificationAndVoucher = new LogicNotificationAndVoucher();
                            List<String> listUserEmail = FindUserByFlight(flightCode);
                            for (String email : listUserEmail) {
                                logicNotificationAndVoucher.SendNotificationChangeFlight(email,flightCode);
                            }
                            // sửa vào file list booking
                            for (Booking booking : bookings) {
                                if(booking.getFlightCode().equals(flightCode)){
                                    booking.setFlightName(flightName);
                                    booking.setFromPlace(fromPlace);
                                    booking.setToPlace(endPlace);
                                    booking.setTime(dateCal);
                                    seats-=booking.getNumberOfSeats();
                                    logicFile.DeleteBookingInFile(bookings);
                                }
                            }
                        }
                        flight.setNumberOfSeats(seats);
                        //AddFile mới
                        logicFile.DeleteFlightInFile(flights);
                        System.out.println("Sửa thông tin thành công . ");
                    }
                }
                System.out.println("Đang quay trở về màn hình chính...");
                MenuOptionAdmin menuOptionAdmin = new MenuOptionAdmin();
                menuOptionAdmin.MenuOptionAdmin();
            }
        }
        catch (InputMismatchException e){
            System.out.println("Nhập sai cú pháp vui lòng nhập lại ");
            ChangeFlight();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> FindUserByFlight(String flightCode) throws IOException {
        // List<Flight> flights= logicFile.ConvertFileToFlight();
        List<String> listUserEmail = new ArrayList<>();
        //clone list booking
        List<Booking> bookings= logicFile.ConvertFileToBooking();
        // khởi tạo để gửi thông báo
        LogicNotificationAndVoucher logicNotificationAndVoucher = new LogicNotificationAndVoucher();
        for (Booking booking : bookings) {
            if (booking.getFlightCode().equals(flightCode)){
                //logicNotificationAndVoucher.SendNotification(booking.getUserEmail());
                listUserEmail.add(booking.getUserEmail());
            }
        }
        return listUserEmail;
    }

}
