package com.schwa.schwacoffe.TimeCode;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class TimeCode {



    public static void main (String[] args) {


        Date date = new Date();

        Calendar calen = new GregorianCalendar();
        calen.setTime(date);


        int minval = SetMinuteVal(15); //15 is a placeholder value. For the real thing Use the slider value and the method, SetMinuteVal
        calen.add(Calendar.MINUTE, minval);

        //Code below was for testing purposes, I commented it out for now and to not disrupt the project.

		/*System.out.println("Current time: " + date);
		System.out.print("Pick-Up Time: ");
		System.out.print(calen.get(Calendar.HOUR) + ":");
		System.out.printf("%02d",calen.get(Calendar.MINUTE));  // the "%02d" was in my old textbook, this displays 2 integer points no matter what. EX: 3 comes out as 03*/
    }


        public static int SetMinuteVal(int a)
        {
            //We put the value from the slider into here, this will let us easily adapt the number into minute/hours
            if (a < 15)
            {
                return 15;
            }
            else if(a > 120)
            {
                return 120; //Fun fact: This seems to Automatically take 60 into an hour, pretty useful.
            }
            else {
                return a;
            }



        }


}
