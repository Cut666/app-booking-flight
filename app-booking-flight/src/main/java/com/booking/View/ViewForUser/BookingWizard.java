package com.booking.View.ViewForUser;

import com.booking.controller.LogicForUser.*;
import com.booking.controller.Regex.DateRegex;
import com.booking.model.Flight;
import com.booking.model.Voucher;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookingWizard {
    public void bookingWizard() throws FileNotFoundException {
        Scanner scan = new Scanner (System.in);
        System.out.println("-----------------------------------------");
        System.out.println("|---------------- ĐẶT VÉ ---------------|");
        System.out.println("-----------------------------------------");
        System.out.println("*********** Tra cứu chuyến bay **********");
        System.out.print("Điểm đi: ");
        String tempFromPlace = scan.nextLine();

        System.out.print("Điểm đến: ");
        String tempToPlace = scan.nextLine();

        System.out.print("Ngày bay (dd/mm/yyyy): ");
        String tempDateQuery = scan.nextLine();
        while (DateRegex.dateRegex(tempDateQuery) || DateRegex.realDate(tempDateQuery)) {
            System.out.println("Vui lòng nhập ngày bay hợp lệ theo định dạng dd/mm/yyyy");
            System.out.print("Ngày bay (dd/mm/yyyy): ");
            tempDateQuery = scan.nextLine();
        }

        LogicListFlight tempLLF = new LogicListFlight();
        ArrayList<Flight> filteredFlights = tempLLF.SearchFlight(tempFromPlace,tempToPlace,tempDateQuery);
		// Scan list chuyến bay
        if (filteredFlights.isEmpty()) {
            System.out.println("Không tìm thấy chuyến bay theo bộ lọc. Đang quay trở về màn hình chính...");
            return;
        }

        for (Flight temp: filteredFlights) {
            System.out.println("*****************************************");
            System.out.println(temp);
        }
        Flight selected;
        do {
            System.out.println("*****************************************");
            System.out.println("Tra cứu thành công.");
            System.out.println("Vui lòng nhập mã chuyến bay mong muốn. " +
                    "Nhập 0 để quay trở về màn hình chính.");
            String tempFlightCode = scan.nextLine();
            if (tempFlightCode.equals("0")) return;
            else {
                selected = tempLLF.verifyFlight(filteredFlights, tempFlightCode);
                if (selected != null) {
                    System.out.println("Đang thực hiện đặt vé máy bay cho chuyến bay "
                            + selected.getFlightCode() + "...");
                    break;
                } else System.out.println("Không tìm thấy chuyến bay. Vui lòng thử lại.");
            }
        } while (true);

        int amount;
        do {
            System.out.printf("Số ghế muốn đặt (Tối thiểu là 1, tối đa là %d): ",selected.getNumberOfSeats());
            amount = scan.nextInt();
            scan.nextLine();
            if (amount < 1 || amount > selected.getNumberOfSeats()) {
                System.out.printf("Vui lòng chỉ lựa chọn số lượng giữa 1 và %d.\n",selected.getNumberOfSeats());
            }
            else break;
        } while (true);

        Voucher selectedVoucher = new Voucher();
        do {
            System.out.print("Mã khuyến mãi (Nhập 0 nếu không sử dụng mã khuyến mãi): ");
            String tempVoucher = scan.nextLine();
            if (tempVoucher.equals("0")) {
                selectedVoucher.setVoucherCode("0");
                selectedVoucher.setValueVoucher(0);
                break;
            }
            else {
                selectedVoucher = tempLLF.verifyVoucher(tempVoucher);
                if (selectedVoucher != null) {
                    float discount = selected.getPrice()*amount*((float) selectedVoucher.getValueVoucher()/100);
                    System.out.printf("Mã khuyến mãi áp dụng thành công. " +
                                    "Bạn được giảm %d%% (%f VND) cho chuyến bay này.\n",
                                    selectedVoucher.getValueVoucher(),discount);
                    break;
                }
                else System.out.println("Mã khuyến mãi không hợp lệ. Vui lòng thử lại.");
            }
        } while (true);

        System.out.println("*****************************************");
        System.out.println("Đang đặt vé. 0% hoàn thành...");
        tempLLF.selectFlight(selected,amount,selectedVoucher);

        System.out.println("Đặt chuyến bay thành công!");
        System.out.println("*****************************************");
        System.out.println("Nhập 1 nếu bạn muốn xem các chuyến bay đã đặt.");
        System.out.println("Nhập 0 nếu bạn muốn quay trở về màn hình chính.");
        int ans;
        do {
            try {
                ans = scan.nextInt();
                if (ans != 1 && ans != 0)
                    System.out.println("Vui lòng chỉ nhập giá trị 1 hoặc 0.");
                else break;
            } catch (InputMismatchException e) {
                System.out.println("Vui lòng chỉ nhập giá trị 1 hoặc 0.");
            }
        } while (true);
        switch (ans) {
            case 1 -> {
                DisplayFlights display = new DisplayFlights();
                display.displayFlights();
            }
            case 0 -> {
                MenuOptionUser menu = new MenuOptionUser();
                menu.menuOptionUser();
            }
        }
    }
}
