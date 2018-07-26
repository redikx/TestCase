package datamodel;

import java.util.List;

public interface RunsDAO_interface {

	void save(RunsDAO p);
	public int insertRuns(int users);
	public void updateETime(int run_id);
	public void insertCases(int RunId, String casename);

}
