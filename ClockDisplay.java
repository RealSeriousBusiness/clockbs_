
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
    
    private int alarmHrs, alarmMins;
    boolean alarmState;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        this();
        setTime(hour, minute);
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
        }
        if(alarmState && hours.getValue() == alarmHrs && 
            minutes.getValue() == alarmMins)
            {
                System.out.println("Riiiiiiiing!");
            }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
    }
    
    //convert 12hr format to the internal 24hr clock
    public void setTime(int hour, int minute, boolean afternoon)
    {
       setTime(convertTo24hr(hour, afternoon), minute);
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
    private void updateDisplay24hr()
    {
        displayString = NumberDisplay.formatValue(hours.getValue()) + ":" + 
                        NumberDisplay.formatValue(minutes.getValue());
    }
    
    /**
     * show the display in 12 hour format
     */
    private void updateDisplay()
    {
        int input = hours.getValue();
        boolean afternoon = false;

        if (input >= 0 && input < 1)
            input += 12;
        else if (input >= 12 && input < 13)
            afternoon = true;
        else if (input >= 13 && input < 24)
        {
            afternoon = true;
            input -= 12;
        }
        
        displayString = NumberDisplay.formatValue(input) + ":" 
                        + NumberDisplay.formatValue(minutes.getValue()) + (afternoon ? " pm" : " am");
    }
    
    private int convertTo24hr(int hour, boolean afternoon)
    {
       if (hour >= 1 && hour < 12 && afternoon)
            hour += 12;
       else if (hour == 12 && !afternoon)
            hour -= 12;
       return hour;
    }

    /**
     * sets the alarm and activates it
     */
    public void setAlarmTime(int hour, int minute)
    {
        alarmHrs = hour;
        alarmMins = minute;
        alarmState = true;
    }
    
    /**
     * sets the alarm in 12hr format and activates it
     */
    public void setAlarmTime(int hour, int minute, boolean afternoon)
    {
        setAlarmTime(convertTo24hr(hour, afternoon), minute);
    }
    
    
    /**
     * toggles the alarm and returns the updated state
     */
    public boolean toggleAlarm()
    {
        return alarmState = !alarmState;
    }
    
}
