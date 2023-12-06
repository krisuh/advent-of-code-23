import common.FileReaderUtil;
import dayfour.DayFour;
import dayone.DayOne;
import daythree.DayThree;
import daytwo.DayTwo;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        FileReaderUtil fileReaderUtil = new FileReaderUtil();
        System.out.println("--- Day one ---");
        var input1 = fileReaderUtil.readFileToStringList("input1.txt");
        var result = DayOne.partOne(input1);
        System.out.println(result);
        var result2 = DayOne.partTwo(input1);
        System.out.println(result2);
        System.out.println("--- Day two ---");
        var input2 = fileReaderUtil.readFileToStringList("input2.txt");
        var dayTwoResult1 = DayTwo.partOne(input2);
        System.out.println(dayTwoResult1);
        var dayTwoResult2 = DayTwo.partTwo(input2);
        System.out.println(dayTwoResult2);
        System.out.println("--- Day Three ----");
        var input3 = fileReaderUtil.readFileToStringList("input3.txt");
        var dayThreeResult1 = DayThree.partOne(input3);
        System.out.println(dayThreeResult1);
        var dayThreeResult2 = DayThree.partTwo(input3);
        System.out.println(dayThreeResult2);
        System.out.println("---- Day Four ----");
        var input4 = fileReaderUtil.readFileToStringList("input4.txt");
        var dayFourResult1 = DayFour.partOne(input4);
        System.out.println(dayFourResult1);
        var dayFourResult2 = DayFour.partTwo(input4);
        System.out.println(dayFourResult2);

    }
}
