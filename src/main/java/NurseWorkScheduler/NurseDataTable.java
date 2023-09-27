package NurseWorkScheduler;


public class NurseDataTable {
    DataBaseClient dataBaseClient = new DataBaseClient();
    int[][] nurseDataTable;

    public NurseDataTable() {
        setNurseDataTable();
    }


    public int getNurseData(int column, int row){
        return nurseDataTable[column][row];
    }

    private void setNurseDataTable() {
        nurseDataTable = dataBaseClient.getIdAndPairID();
    }

    public int getNurseDataTableLength() {
        return nurseDataTable[0].length;
    }

}

