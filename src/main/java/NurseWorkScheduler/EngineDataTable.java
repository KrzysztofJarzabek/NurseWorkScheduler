package NurseWorkScheduler;

public class EngineDataTable {
    private int[][] engineDataTable; //ID, PairID, lastShift, shifts

    private int monthDaysQuantity;
    private int sizeOfEngineDataTable;

    public int getSizeOfEngineDataTable() {
        return sizeOfEngineDataTable;
    }

    private int engineDataTableLenght;

    public int getEngineDataTableLenght() {
        return engineDataTableLenght;
    }

    NurseDataTable nurseDataTable = new NurseDataTable();
    WorkScheduler workScheduler = new WorkScheduler();


    public EngineDataTable(int monthDaysQuantity, int lastDayShiftPairNumber, int lastNightShiftPairNumber) {
        this.monthDaysQuantity = monthDaysQuantity; //MonthsData.getNumberOfDays(workScheduler.getYearNumber(), workScheduler.getMonthNumber());
        //ToDo ograniczenie wstrzykiwania tylu parametrów do analizy
        this.engineDataTableLenght = monthDaysQuantity + 3;
        this.sizeOfEngineDataTable = nurseDataTable.getNurseDataTableLength();
        setEngineDataTable();
        insertLastShiftsInfoToTable(lastDayShiftPairNumber, lastNightShiftPairNumber);
        displayEngineTable();
        System.out.println("Engine data table constructor - done");
    }

    private void insertLastShiftsInfoToTable(int lastDayShiftPairNumber, int lastNightShiftPairNumber) {
        //Add next column = day/night shift last month (ID, pairID, day/night)
        for (int i = 0; i < sizeOfEngineDataTable; i++) {
            if (engineDataTable[1][i] == lastDayShiftPairNumber) engineDataTable[2][i] = 1;
            if (engineDataTable[1][i] == lastNightShiftPairNumber) engineDataTable[2][i] = 2;
        }
    }

    private void setEngineDataTable() {
        //First column - ID and pair ID
        engineDataTable = new int[3 + monthDaysQuantity][sizeOfEngineDataTable];

        for (int nurseDataRow = 0; nurseDataRow < sizeOfEngineDataTable; nurseDataRow++) {
            engineDataTable[0][nurseDataRow] = nurseDataTable.getNurseData(0, nurseDataRow);
            engineDataTable[1][nurseDataRow] = nurseDataTable.getNurseData(1, nurseDataRow);
        }
    }

    public int getTableComponent(int columnIndex, int rowIndex) {
        return engineDataTable[columnIndex][rowIndex];
    }

    public void insertPairShiftToEngineTable(int idNumber, int dayNumber, int shift) {
        for (int nurseDataRow = 0; nurseDataRow < sizeOfEngineDataTable; nurseDataRow++) {
            if (engineDataTable[1][nurseDataRow] == idNumber) engineDataTable[dayNumber][nurseDataRow] = shift;
        }
    }

    //dodatkowa/pomocnicza metoda
    public void displayEngineTable() {
        for (int nurseDataRow = 0; nurseDataRow < sizeOfEngineDataTable; nurseDataRow++) {
            for (int dayNumber = 0; dayNumber < monthDaysQuantity; dayNumber++) {
                System.out.print(engineDataTable[dayNumber][nurseDataRow] + " ");
            }
            System.out.println();
        }
    }
}
