package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "011120775";
        if (args.length > 0) {
            studentNr = args[0];
        }
        Gson mapper = new Gson();

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/" + studentNr + "/submissions";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        String courseInfoUrl = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        String courseInfoText = Request.Get(courseInfoUrl).execute().returnContent().asString();
        CourseInfo courseInfo = mapper.fromJson(courseInfoText, CourseInfo.class);

        String courseStatsUrl = "https://studies.cs.helsinki.fi/ohtustats/stats";
        String courseStatsText = Request.Get(courseStatsUrl).execute().returnContent().asString();
        JsonParser parser = new JsonParser();
        JsonObject courseStats = parser.parse(courseStatsText).getAsJsonObject();

        System.out.println(courseInfo.toString());
        System.out.println("");
        System.out.println("opiskelijanumero: " + studentNr);
        int allExercises = 0;
        int allHours = 0;
        for (Submission submission : subs) {
            allExercises += submission.countExercises();
            allHours += submission.getHours();

            System.out.println(submission.toString());
            System.out.println("");
        }
        System.out.println("yhteensä: " + allExercises + " tehtävää, " + allHours + " tuntia");
        System.out.println("");

        int studentsAmount = 0;
        int exercisesAmount = 0;
        for (int i = 1; i <= courseStats.size(); i++) {
            JsonObject stat = courseStats.getAsJsonObject(Integer.toString(i));
            JsonPrimitive students = stat.getAsJsonPrimitive("students");
            JsonPrimitive exercises = stat.getAsJsonPrimitive("exercise_total");
            studentsAmount += Integer.parseInt(students.toString());
            exercisesAmount += Integer.parseInt(exercises.toString());
        }

        System.out.println("Kurssilla yhteensä " + studentsAmount + " palautusta, palautettuja tehtäviä " + exercisesAmount + " kpl");

    }
}
