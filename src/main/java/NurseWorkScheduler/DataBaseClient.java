package NurseWorkScheduler;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class DataBaseClient {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(DataSourceSQL.getMySqlDataSource());

    public List<NurseData> getAllDataFromDB() {
        try {
            List<NurseData> query = jdbcTemplate.query("SELECT worker_data.id, name," +
                            " last_name, pair_id, worker_salary FROM worker_data " +
                            "JOIN worker_salary ON worker_data.id = worker_salary.worker_id;",
                    BeanPropertyRowMapper.newInstance(NurseData.class));
            return query;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int[][] getIdAndPairID() {
        List<NurseData> nurseDataList = getAllDataFromDB();
        int[][] nurseDataTable = new int[3][nurseDataList.size()];

        for (NurseData object : nurseDataList) {
            nurseDataTable[0][nurseDataList.indexOf(object)] = object.getId();
            nurseDataTable[1][nurseDataList.indexOf(object)] = object.getPair_id();
            nurseDataTable[2][nurseDataList.indexOf(object)] = object.getWorker_salary();
        }
        return nurseDataTable;
    }
}