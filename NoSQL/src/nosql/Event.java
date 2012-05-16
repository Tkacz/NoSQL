/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nosql;

/**
 *
 * @author rufus
 */
public class Event {
    String id;
    String DateOccurred;
    String DateReported;
    String Location;
    String ShortDescription;
    String Duration;
    String LongDescription;
    String USCity;
    String USState;
    String YearMonth;
    
    public Event() {
        this.id = null;
        this.DateOccurred = null;
        this.DateReported = null;
        this.Location = null;
        this.ShortDescription = null;
        this.Duration = null;
        this.LongDescription = null;
        this.USCity = null;
        this.USState = null;
        this.YearMonth = null;
    }
    
    public Event(String id, String DateOccurred, String DateReported, String Location, String ShortDescription, String Duration, String LongDescription, String USCity, String USState, String YearMonth) {
        this.id = id;
        this.DateOccurred = DateOccurred;
        this.DateReported = DateReported;
        this.Location = Location;
        this.ShortDescription = ShortDescription;
        this.Duration = Duration;
        this.LongDescription = LongDescription;
        this.USCity = USCity;
        this.USState = USState;
        this.YearMonth = YearMonth;
    }

    public String getDateOccurred() {
        return DateOccurred;
    }

    public String getDateReported() {
        return DateReported;
    }

    public String getDuration() {
        return Duration;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return Location;
    }

    public String getLongDescription() {
        return LongDescription;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public String getUSCity() {
        return USCity;
    }

    public String getUSState() {
        return USState;
    }

    public String getYearMonth() {
        return YearMonth;
    }

    public void setDateOccurred(String DateOccurred) {
        this.DateOccurred = DateOccurred;
    }

    public void setDateReported(String DateReported) {
        this.DateReported = DateReported;
    }

    public void setDuration(String Duration) {
        this.Duration = Duration;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public void setLongDescription(String LongDescription) {
        this.LongDescription = LongDescription;
    }

    public void setShortDescription(String ShortDescription) {
        this.ShortDescription = ShortDescription;
    }

    public void setUSCity(String USCity) {
        this.USCity = USCity;
    }

    public void setUSState(String USState) {
        this.USState = USState;
    }

    public void setYearMonth(String YearMonth) {
        this.YearMonth = YearMonth;
    }
}
