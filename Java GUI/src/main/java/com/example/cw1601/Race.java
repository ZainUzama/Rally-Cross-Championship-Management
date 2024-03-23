package com.example.cw1601;
import java.time.LocalDate;
public class Race {
    LocalDate date;
    String Location;
    String Player1;
    String Player2;
    String Player3;

    public Race(LocalDate date, String Location, String Player1, String Player2, String Player3) {
        this.date = date;
        this.Location = Location;
        this.Player1 = Player1;
        this.Player2 = Player2;
        this.Player3 = Player3;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getLocation() {
        return Location;
    }
    public void setLocation(String Location) {
        this.Location = Location;
    }
    public String getPlayer1() {
        return Player1;
    }
    public void setPlayer1(String Player1) {
        this.Player1 = Player1;
    }
    public String getPlayer2() {
        return Player2;
    }
    public void setPlayer2(String Player2) {
        this.Player2 = Player2;
    }
    public String getPlayer3() {
        return Player3;
    }
    public void setPlayer3(String Player3) {
        this.Player3 = Player3;
    }
}