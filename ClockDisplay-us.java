
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private boolean afternoon = false;
    
    private int alarmHrs, alarmMins;
    boolean alarmState, alarmAfternoon;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(13);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute, boolean afternoon)
    {
        this();
        setTime(hour, minute, afternoon);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
            
            if(hours.getValue() == 0) //we don't have 0 o'clock on the 12 hour clock...
                hours.setValue(1); //....so skip it.
                
            if(hours.getValue() == 12) //daytime always gets toggled on 12
                afternoon = !afternoon;
        }
        
        if(alarmState && 
            hours.getValue() == alarmHrs && 
            minutes.getValue() == alarmMins &&
            afternoon == alarmAfternoon)
                 System.out.println("Riiiiiiiing!");
                 
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute, boolean afternoon)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        this.afternoon = afternoon;
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue() + (afternoon ? " pm" : " am");
    }
    
    /**
     * sets the alarm in 12hr format and activates it
     */
    public void setAlarmTime(int hour, int minute, boolean afternoon)
    {
        alarmHrs = hour;
        alarmMins = minute;
        alarmAfternoon = afternoon;
        alarmState = true;
    }
    
    /**
     * toggles the alarm and returns the updated state
     */
    public boolean toggleAlarm()
    {
        return alarmState = !alarmState;
    }

}
