package HEI;

import java.time.LocalDateTime;

public class Table {
    private int id;
    private int number;
    private LocalDateTime occupiedFrom;
    private LocalDateTime occupiedUntil;

    public Table(int id, int number) {
        this.id = id;
        this.number = number;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDateTime getOccupiedFrom() {
        return occupiedFrom;
    }

    public void setOccupiedFrom(LocalDateTime occupiedFrom) {
        this.occupiedFrom = occupiedFrom;
    }

    public LocalDateTime getOccupiedUntil() {
        return occupiedUntil;
    }

    public void setOccupiedUntil(LocalDateTime occupiedUntil) {
        this.occupiedUntil = occupiedUntil;
    }

    public boolean isAvailable(LocalDateTime from, LocalDateTime until) {
        if (occupiedFrom == null || occupiedUntil == null) {
            return true;
        }
        return until.isBefore(occupiedFrom) || from.isAfter(occupiedUntil);
    }   
}