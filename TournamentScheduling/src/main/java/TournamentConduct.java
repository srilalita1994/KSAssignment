import org.json.JSONArray;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class TournamentConduct {
    LinkedHashMap<String, Integer> descendingSortedMap = new LinkedHashMap<>();

    /**
     *readFileCreateJsonArray method: reads JSON file and creates JSON array for the file
     * @param matchScheduleJsonFile : "jsonOwnMatches.json"
     * @return JSON array as json
     */
    public JSONArray readFileCreateJsonArray(String matchScheduleJsonFile) throws Exception {
        String json = readFileAsString(matchScheduleJsonFile);
        return new JSONArray(json);
    }

    /**
     * readFileAsString method : generates the String of "jsonOwnMatches.json"
     * @return String of the file
     */
    public static String readFileAsString(String file)throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    /**
     * initializeMapZero method : initializes all the points of the teams as 0
     * @param teamMap : all the teams are created based on teamNum
     * @param teamNum : integer >= 3 as input
     * @return teamMap : teams with their initial points as 0
     */
    public Map<String, Integer> initializeMapZero(Map<String, Integer> teamMap, int teamNum){
        int points =0;
        for(int i=0; i< teamNum; i++){
            int tNum = i + 1;
            String tNumStr= Integer.toString(tNum);
            teamMap.put("Team_"+ tNumStr,points);
        }
        return teamMap;
    }

    /**
     * allocateWinningPointsMap method: random allocation of winning points to teams
     * @param teamMap :teams with their initial points as 0 as input
     * @param jsonArray : has the matches scheduled
     * @return teamMap : teams with points allocated randomly
     */
    public Map<String, Integer> allocateWinningPointsMap(Map<String, Integer> teamMap, JSONArray jsonArray) {
        for(int i = 0; i<jsonArray.length(); i++){
            int matchNum = i + 1;
            String matchNumStr= Integer.toString(matchNum);
            String matchStr = "Match_" + matchNumStr;
            Random rand = new Random();
            int matchWinner = rand.ints(0, 2).findAny().getAsInt();

            if(matchWinner==0){
                String matchWin = jsonArray.getJSONObject(i).getJSONObject(matchStr).getString("A");
                int existingPt = teamMap.get(matchWin);
                int increasePt = existingPt + 2;
                teamMap.put(matchWin,increasePt);
            }else {
                String matchWin = jsonArray.getJSONObject(i).getJSONObject(matchStr).getString("B");
                int existingPt = teamMap.get(matchWin);
                int increasePt = existingPt + 2;
                teamMap.put(matchWin,increasePt);
            }
        }
        return teamMap;
    }

    /**
     * descendingSortedWinnerMap method: sorted by team scoring more points
     * @param teamMap: teams with points allocated randomly
     * @return descendingSortedMap
     */
    public LinkedHashMap<String, Integer> descendingSortedWinnerMap(Map<String, Integer> teamMap) {
        teamMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> descendingSortedMap.put(x.getKey(), x.getValue()));
        return descendingSortedMap;
    }

    /**
     * displayTeamStanding method: Print the current standings of the team sorted by team scoring more points
     * @param descendingSortedMap :sorted by team scoring more points
     * @param teamNum : integer >= 3 as input
     */
    public void displayTeamStanding(LinkedHashMap<String, Integer> descendingSortedMap, int teamNum) {
        int positions= 1;
        int matchesPlayed = computeMatches(teamNum);
        Formatter fmt = new Formatter();
        fmt.format("%10s %10s \n", "Team standings :" , teamNum);
        fmt.format("%10s  %10s  %10s %10s\n","Position ", "Team" , "Matches Played ", "Points");
        for (Map.Entry<String, Integer> pair : descendingSortedMap.entrySet()) {
            fmt.format("%10s  %10s  %10s %10s\n",positions, pair.getKey(), matchesPlayed, pair.getValue());
            positions++;
        }
        System.out.println(fmt);
    }

    /**
     * computeMatches method: calculates number of matches each team played
     * @param teamNum :integer >= 3 as input
     * @return as matchesPlayed above
     */
    private int computeMatches(int teamNum) {
        return ( (teamNum -1) * 2);
    }
}
