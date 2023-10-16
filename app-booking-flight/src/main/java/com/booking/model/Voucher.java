package com.booking.model;

public class Voucher {
    private String voucherCode;
    private int valueVoucher;   //tính băng % trên giá vé

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public int getValueVoucher() {
        return valueVoucher;
    }

    public void setValueVoucher(int valueVoucher) {
        this.valueVoucher = valueVoucher;
    }
}
