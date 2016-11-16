using System;
using System.Collections.Generic;
using System.Text;
using System.Threading;

namespace info1
{
    class Program
    {
        static void Main(string[] args)
        {
            for (int i = 0; i < 24; i++)
            {
                bool afternoon = false;
                int hr12 = Convert(i, ref afternoon);
                string formatted = hr12 + (afternoon ? " pm" : " am");

                Console.WriteLine($"Input: {i};  12hr: {formatted};    backTo24: {ConvertBack(hr12, afternoon)}");
            }

            for (int i = 0; i < 25; i++)
            {
                timeKeep();
            }

            Console.Read();
        }

        static bool cntTo13 = true;
        static bool afternoon = false;
        static int value = 12;

        static void timeKeep()
        {
            value++;

            if (cntTo13)
            {
                if (value == 13)
                {
                    value = 1;
                    cntTo13 = false;
                }
            }
            else
            {
                if (value == 12)
                {
                    cntTo13 = true;
                    afternoon = !afternoon;
                }
            }

            Console.WriteLine(value + (afternoon ? " pm" : " am"));
        }

        static int Convert(int input, ref bool afternoon)
        {
            if (input >= 0 && input < 1)
                input += 12;
            else if (input >= 12 && input < 13)
                afternoon = true;
            else if (input >= 13 && input < 24)
            {
                afternoon = true;
                input -= 12;
            }

            return input;// + (afternoon ? " pm" : " am");
        }


        static int ConvertBack(int hour, bool afternoon)
        {
            if (hour >= 1 && hour < 12 && afternoon)
                hour += 12;
            else if (hour == 12 && !afternoon)
                hour -= 12;

            return hour;
        }

        public static String formatValue(int input)
        {
            return (input < 10 ? "0" + input : "" + input);
        }
    }
}
