package com.example.bdtercerparcial.bdclases;


public class Nota {
    private Integer id;
    private Integer compra_id;
    private String subtotal;
    private String total;
    private String qr;

    public Nota(Integer id, Integer compra_id, String subtotal, String total, String qr) {
        this.id = id;
        this.compra_id = compra_id;
        this.subtotal = subtotal;
        this.total = total;
        this.qr = qr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompra_id() {
        return compra_id;
    }

    public void setCompra_id(Integer compra_id) {
        this.compra_id = compra_id;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
}
