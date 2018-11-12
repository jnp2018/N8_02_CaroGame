/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caro.common;

import java.io.Serializable;

/**
 *
 * @author tunado
 */
public class CRoom implements Serializable {
    int so_quan_thang;
    boolean chan_2_dau;
    int time;

    public CRoom(int so_quan_thang, boolean chan_2_dau, int time) {
        this.so_quan_thang = so_quan_thang;
        this.chan_2_dau = chan_2_dau;
        this.time = time;
    }

    public int getSo_quan_thang() {
        return so_quan_thang;
    }

    public void setSo_quan_thang(int so_quan_thang) {
        this.so_quan_thang = so_quan_thang;
    }

    public boolean isChan_2_dau() {
        return chan_2_dau;
    }

    public void setChan_2_dau(boolean chan_2_dau) {
        this.chan_2_dau = chan_2_dau;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
}
