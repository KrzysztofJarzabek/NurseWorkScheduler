package NurseWorkScheduler;

import lombok.Getter;
import java.util.Scanner;
//ToDo nazewnictwo

@Getter
public class WorkScheduler {
    //Prepares time data for scheduler engine.
    Scanner scanner = new Scanner(System.in);
    private int monthNumber = 0;
    private int yearNumber = 0;


    private void setMonthNumber(int monthNumber) {
        if (monthNumber > 0 & monthNumber < 12) this.monthNumber = monthNumber;
        else System.out.println("Month number out of scope.");
        //ToDo throw exception
    }

    private void setYearNumber(int yearNumber) {
        if (yearNumber > 2000 & yearNumber < 3000) this.yearNumber = yearNumber;
        else System.out.println("Year number out of scope.");
    }

    public void initializeWorkScheduler() {
        System.out.println("Proszę wprowadzić numer miesiąca.");
        //setMonthNumber(insertNumber());
        setMonthNumber(2);

        System.out.println("Proszę wprowadzić numer roku.");
        //setYearNumber(insertNumber());
        setYearNumber(2023);
    }

    private int insertNumber() {
        try {
            return Integer.parseInt(scanner.next());
        } catch (
                NumberFormatException e) {
            System.out.println("Incorect number!");
            throw e;
        }
    }
}
