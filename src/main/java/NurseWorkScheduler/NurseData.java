package NurseWorkScheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class NurseData {
//ToDo czy wszystko lombokiem ustawiać?
    private int id;
    private String name;
    private String last_name;
    private int pair_id;
    private int worker_salary;


}
