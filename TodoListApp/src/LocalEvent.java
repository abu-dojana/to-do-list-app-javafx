
import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abu Dozana Tahmid
 */
public class LocalEvent {
    private String description;
    private LocalDate date;

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalEvent(LocalDate date, String description) {
        this.setDate(date);
        this.setDescription(description);
    }
    
   
    @Override
    public String toString(){
        return "Date: " + this.getDate() + " | " + "Event: " + this.getDescription(); 
    }
    

    
    
}
