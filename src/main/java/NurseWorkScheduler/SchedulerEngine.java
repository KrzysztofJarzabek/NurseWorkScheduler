package NurseWorkScheduler;

import lombok.Getter;
import java.util.LinkedList;
import java.util.List;

@Getter
public class SchedulerEngine {
    DataBaseClient dataBaseClient = new DataBaseClient();
    List<Integer> pairLoopNumberList = new LinkedList<>();
    EngineDataTable engineDataTable1;
    private int numberOfEngineDataTableRows;
    private int numberOfEngineDataTableColumns;

    public SchedulerEngine(int monthDaysQuantity, int lastDayShiftPairNumber, int lastNightShiftPairNumber) {
        engineDataTable1 = new EngineDataTable(monthDaysQuantity, lastDayShiftPairNumber, lastNightShiftPairNumber);
        this.numberOfEngineDataTableRows = engineDataTable1.getSizeOfEngineDataTable();
        this.numberOfEngineDataTableColumns = engineDataTable1.getEngineDataTableLenght();
        System.out.println("Engine initialized.");
//klasa do sprawdzania ilosci loopów
        //czy wywolywac enginedatatable z poziomu main?
    }

    public void runEngineLoop() {
        int maxLoopCounter = checkHowManyLoops();
        for (int loopNumber = 1; loopNumber < maxLoopCounter; loopNumber++) {
            shiftsAssignmentLoop(loopNumber);
        }
    }

    private int checkHowManyLoops() {
        int counter = 0;
        List<Integer> pairLoopNumberList = new LinkedList<>();

        for (int dayNumber = 3; dayNumber < numberOfEngineDataTableColumns; dayNumber++) {
            for (int nurseDataRow = 0; nurseDataRow < numberOfEngineDataTableRows; nurseDataRow++) {
                if (dayNumber == 3 & engineDataTable1.getTableComponent((dayNumber - 1), nurseDataRow) == 0
                        & !pairLoopNumberList.contains(engineDataTable1.getTableComponent(1, nurseDataRow))) {
                    counter++;
                    pairLoopNumberList.add(engineDataTable1.getTableComponent(1, nurseDataRow));
                }
            }
        }
        System.out.println("Check how many loops counter: " + counter);
        return counter;
    }

    private void shiftsAssignmentLoop(int loopNumber) {
        int freeShift = 0;
        int dayShift = 1;
        int nightShift = 2;
        int loopCounter = loopNumber;


        for (int columnIndex = 3; columnIndex < numberOfEngineDataTableColumns; columnIndex++) {
            for (int nurseDataRow = 0; nurseDataRow < numberOfEngineDataTableRows; nurseDataRow++) {
                //               if (checkHoursQuantity(engineDataTable[1][nurseDataRow])) break;
                //for day-shift assignment
                if (columnIndex == 3 & engineDataTable1.getTableComponent(columnIndex - 1, nurseDataRow) == freeShift) {
                    //System.out.println("loopcounter in engine:" + loopCounter);
                    if (loopCounter == 1 & !pairLoopNumberList.contains(engineDataTable1.getTableComponent(1, nurseDataRow))) {
                        engineDataTable1.insertPairShiftToEngineTable(
                                engineDataTable1.getTableComponent(1, nurseDataRow), columnIndex, dayShift);
                        // System.out.println("Ok wpisuje dniowke dla pary" + engineDataTable[1][nurseDataRow]);
                        break;
                    } else if (loopCounter > 1) {
                        pairLoopNumberList.add(engineDataTable1.getTableComponent(1, nurseDataRow));
                        //System.out.println("Para zapisana, ide dalej");
                        loopCounter--;
                        break;
                    }
                }
                if (engineDataTable1.getTableComponent(columnIndex - 2, nurseDataRow) == freeShift & engineDataTable1.getTableComponent(columnIndex - 1, nurseDataRow) == freeShift) {
                    engineDataTable1.insertPairShiftToEngineTable(engineDataTable1.getTableComponent(1, nurseDataRow),
                            columnIndex, dayShift);
                    break;
                }
            }
            for (int nurseDataRow = 0; nurseDataRow < numberOfEngineDataTableRows; nurseDataRow++) {
                //for night-shift assignment
                if (engineDataTable1.getTableComponent(columnIndex - 1, nurseDataRow) == dayShift) {
                    engineDataTable1.insertPairShiftToEngineTable(engineDataTable1.getTableComponent(1, nurseDataRow),
                            columnIndex, nightShift);
                }
            }
        }
        engineDataTable1.displayEngineTable();
    }
}