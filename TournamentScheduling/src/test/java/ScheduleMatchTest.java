import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ScheduleMatchTest {
    private ScheduleMatch scheduleMatch;
    @BeforeEach
    public void setup(){
        scheduleMatch = new ScheduleMatch();
    }
    @Test
    public void testTotalMatchConduct_WithTeamNum3() {
        int actual = scheduleMatch.totalMatchConduct(3);
        int expected = 6;
        assertEquals(expected,actual);
    }
    @Test
    public void testTotalMatchConduct_WhenTeamNumLessThan3() {
        int actual = scheduleMatch.totalMatchConduct(1);
        int expected = 0;
        assertEquals(expected,actual);
    }
    @Test
    public void testTotalMatchConduct_WhenTeamNumLessThan3_AssertFalse() {
        int actual = scheduleMatch.totalMatchConduct(2);
        assertFalse(actual>0);
    }
    @Test
    public void testTotalMatchConduct_WhenTeamNumLessThan3_AssertTrue() {
        int actual = scheduleMatch.totalMatchConduct(6);
        assertTrue(actual>0);
    }
    @Test
    public void testTotalMatchConduct_WithTeamNumGreaterThan3() {
        int actual = scheduleMatch.totalMatchConduct(12);
        int expected = 132;
        assertEquals(expected,actual);
    }
    @Test
    public void testTotalMatchConduct_WithTeamNumNegative() {
        int actual = scheduleMatch.totalMatchConduct(-4);
        int expected = 0;
        assertEquals(expected,actual);
    }
    @Test
    public void testMatchFixing_WithTeamNum3() {
        List<JSONObject> actual = scheduleMatch.matchSchedule(3);
        assertNotNull(actual);
    }

    @Test
    public void testMatchFixing_WithJsonAssertEqualsHardCoded() throws JSONException {
        List<JSONObject> actual = scheduleMatch.matchSchedule(3);
        String expected = "[{\"Match_1\":{\"A\":\"Team_1\",\"B\":\"Team_2\"}}," +
                " {\"Match_2\":{\"A\":\"Team_1\",\"B\":\"Team_3\"}}, " +
                "{\"Match_3\":{\"A\":\"Team_2\",\"B\":\"Team_3\"}}," +
                " {\"Match_4\":{\"A\":\"Team_1\",\"B\":\"Team_2\"}}, " +
                "{\"Match_5\":{\"A\":\"Team_1\",\"B\":\"Team_3\"}}," +
                " {\"Match_6\":{\"A\":\"Team_2\",\"B\":\"Team_3\"}}] " ;
        JSONAssert.assertEquals(expected, actual.toString(), JSONCompareMode.LENIENT);
    }
    @Test
    public void testMatchFixing_WithAssertEquals_SizeCheck() {
        List<JSONObject> actual = scheduleMatch.matchSchedule(5);
        assertEquals(20,actual.size());
    }
    @AfterEach
    public void clean(){
        scheduleMatch = null;
    }

}