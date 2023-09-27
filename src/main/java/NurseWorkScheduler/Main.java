package NurseWorkScheduler;

public class Main {
    public static void main(String[] args) {
        WorkScheduler workScheduler = new WorkScheduler();
        SchedulerPairData schedulerPairData = new SchedulerPairData();

        workScheduler.initializeWorkScheduler();
        schedulerPairData.initializeNursePairData();


        SchedulerEngine schedulerEngine = new SchedulerEngine(
                MonthsData.getNumberOfDays(workScheduler.getYearNumber(), workScheduler.getMonthNumber()),
                schedulerPairData.getPairIdDayShift(),
                schedulerPairData.getPairIdNightShift()
        );
        schedulerEngine.runEngineLoop();

    }
}