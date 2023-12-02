import common.FileReaderUtil;
import dayone.DayOne;
import daytwo.DayTwo;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        FileReaderUtil fileReaderUtil = new FileReaderUtil();
        System.out.println("--- Day one ---");
        var input1 = fileReaderUtil.readFileToString("input1.txt");
        var result = DayOne.partOne(input1);
        System.out.println(result);
        var result2 = DayOne.partTwo(input1);
        System.out.println(result2);
        System.out.println("--- Day two ---");
        var input2 = fileReaderUtil.readFileToString("input2.txt");
        var dayTwoResult1 = DayTwo.partOne(input2);
        System.out.println(dayTwoResult1);
        var dayTwoResult2 = DayTwo.partTwo(input2);
        System.out.println(dayTwoResult2);
    }
}
