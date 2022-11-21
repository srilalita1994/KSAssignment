import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;
public class TournamentScheduling {
    public static int teamNum;
    static List<JSONObject> jsonList = new ArrayList<>();
    static String matchScheduleJsonFile = "jsonOwnMatches.json";
    static Map<String,Integer> teamMap = new HashMap<>();
    static LinkedHashMap<String, Integer> descendingSortedMap = new LinkedHashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
            try {
                System.out.println("Number of Teams:");
                teamNum = input.nextInt();
                input.close();
                if(teamNum>= 3){
                    ScheduleMatch scheduleMatch = new ScheduleMatch();
                    scheduleMatch.totalMatchConduct(teamNum);
                    jsonList = scheduleMatch.matchSchedule(teamNum);
                    scheduleMatch.matchScheduleJsonFileCreator(jsonList,matchScheduleJsonFile );
                    // add method of scheduler
                    TournamentConduct tournamentConduct = new TournamentConduct();
                    JSONArray jsonArray = tournamentConduct.readFileCreateJsonArray(matchScheduleJsonFile);
                    teamMap = tournamentConduct.initializeMapZero(teamMap,teamNum );
                    teamMap = tournamentConduct.allocateWinningPointsMap(teamMap,jsonArray);
                    descendingSortedMap = tournamentConduct.descendingSortedWinnerMap(teamMap);
                    tournamentConduct.displayTeamStanding(descendingSortedMap,teamNum);
                    // add method of tournament
                } else{
                    System.out.println("Enter team number >= 3");
                    System.exit(0);
                }
            } catch (Exception e){
                System.out.println("Caught Exception: " + e);
                System.out.println("Please re-run with correct values");
            }
    }
}
