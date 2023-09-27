package NurseWorkScheduler;

import java.util.List;
import java.util.Scanner;

public class SchedulerPairData {
    DataBaseClient dataBaseClient = new DataBaseClient();
    Scanner scanner = new Scanner(System.in);
    private int pairIdNightShift;
    private int pairIdDayShift;

    public void initializeNursePairData() {
        setPairIdDayShift();
        setPairIdNightShift();
    }

    public int getPairIdNightShift() {
        return pairIdNightShift;
    }

    public int getPairIdDayShift() {
        return pairIdDayShift;
    }

    private void setPairIdDayShift() {
        displayNursePairs();
        System.out.println("Wprowadz numer ID pary ktora miała dniowke ostatniego dnia miesiaca");
        this.pairIdDayShift = scanForPairNumber();
    }

    private void setPairIdNightShift() {
        System.out.println("Wprowadz numer ID pary ktora miała nockę ostatniego dnia miesiaca");
        this.pairIdNightShift = scanForPairNumber();
    }

    private void displayNursePairs() {
        List<NurseData> sqlQuery = dataBaseClient.getAllDataFromDB();
        for (NurseData object : sqlQuery) {
            System.out.println("Name: " + object.getName() + "  PairID: " + object.getPair_id());
        }
    }

    private int scanForPairNumber() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}
