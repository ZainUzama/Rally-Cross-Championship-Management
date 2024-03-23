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



}
