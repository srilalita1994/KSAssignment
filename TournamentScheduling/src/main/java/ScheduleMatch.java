import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class ScheduleMatch {
    List<JSONObject> jsonList = new ArrayList<>();
    int teamMatches = 0;
    int totalMatch = 0;

    /**
     *totalMatchConduct method: calculates total number of matches to be conducted
     * @param  teamNum: integer >= 3 as input
     * @return totalMatch
     */
    public int totalMatchConduct(int teamNum) {
        if(teamNum>=3){
            teamMatches = (teamNum * (teamNum-1)) / 2;
            totalMatch = teamMatches * 2;
            System.out.println("Number of Matches Scheduled: " + totalMatch);
        } else {
            System.out.println("Match will be scheduled only if No of Teams >= 3");
        }
        return totalMatch;
    }

    /**
     * matchSchedule method: creates list of matches
     * @param teamNum : integer >= 3 as input
     * @return jsonList
     */

    public List<JSONObject> matchSchedule(int teamNum) {
        int matchNum = 1; //numbering of matches(as Match_1)
        for (int playTwice = 0; playTwice < 2; playTwice++) { // Since each team plays twice
            for (int valA = 1; valA <= teamNum; valA++) { // team A values
                for (int valB = 1; valB <= teamNum; valB++) { // team B values
                    if ((valA < valB)) {
                        JSONObject mainMatch = new JSONObject();
                        JSONObject team = new JSONObject();
                        team.put("A", "Team_" + valA);
                        team.put("B", "Team_" + valB);
                        mainMatch.put("Match_" + matchNum, team);
                        jsonList.add(mainMatch);
                        //System.out.println(mainMatch);
                        matchNum++;
                    }
                }
            }
        }
        System.out.println(jsonList);
        return jsonList;
    }

    /**
     * matchScheduleJsonFileCreator method: creates JSON file from JSON list
     * @param jsonList : list of matches scheduled
     * @param matchScheduleJsonFile : generated JSON file name = "jsonOwnMatches.json"
     */
    public void matchScheduleJsonFileCreator(List<JSONObject> jsonList, String matchScheduleJsonFile) {
        // Write the content in file
        try(FileWriter fileWriter = new FileWriter(matchScheduleJsonFile)) {
            String fileContent = jsonList.toString();
            fileWriter.write(fileContent);
        } catch (IOException e) {
            System.out.println("Exception handled");
        }
    }
}



