import org.json.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TournamentConductTest {
    private TournamentConduct tournamentConduct;

    @BeforeEach
    public  void setUp(){
        tournamentConduct = new TournamentConduct();
    }

    @Test
    public void readFileCreateJsonArray_NotNull() throws Exception {
        JSONArray actual = tournamentConduct.readFileCreateJsonArray("jsonOwnMatches.json");
        assertNotNull(actual);
    }
    @Test
    public void readFileCreateJsonArray_length() throws Exception {
        int actual = tournamentConduct.readFileCreateJsonArray("jsonOwnMatches.json").length();
        assertTrue(actual >= 6);
    }
    @Test
    void readFileAsString() throws Exception {
        String actual = TournamentConduct.readFileAsString("jsonOwnMatches.json");
        assertFalse(actual.isEmpty());
    }
    @Test
    void initializeMapZero_CheckSize() {
       Map<String,Integer> actual = tournamentConduct.initializeMapZero(TournamentScheduling.teamMap,4);
       assertEquals(4,actual.size());
    }
    @Test
    void initializeMapZero_CheckPoints() {
        Map<String,Integer> actual = tournamentConduct.initializeMapZero(TournamentScheduling.teamMap,4);
        Map<String,Integer> expected = new HashMap<>();
        expected.put("Team_1",0);
        expected.put("Team_2",0);
        expected.put("Team_3",0);
        expected.put("Team_4",0);
        assertEquals(expected, actual);
    }
    @AfterEach
    void tearDown(){
        tournamentConduct = null;
    }
}