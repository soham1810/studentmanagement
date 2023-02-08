package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entity.Student;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import play.api.db.Database;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.StudentService;

import javax.inject.Inject;
import java.sql.*;


public class StudentController extends Controller {
    private StudentService studentService;

    private FormFactory formFactory;
//    private RestHighLevelClient client = new RestHighLevelClient(
//            RestClient.builder(
//                    new HttpHost("localhost", 9200, "http")
//            )
//    );
    @Inject
    public StudentController(StudentService studentService, FormFactory formFactory){
        this.studentService = studentService;
        this.formFactory =formFactory;
    }
    @Inject
    Database db;


    public Result fetchDataFromDatabase() {
        try (Connection connection = db.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while(resultSet.next()) {
                String name = resultSet.getString("name");
                System.out.println(name);
                // process the result
            }
        } catch (Exception e) {
            return internalServerError("Failed");
            // handle the exception
        }
        return ok("Successfully connected");
    }

    public Result getStudent() throws SQLException {
        Connection connection = db.getConnection();
        Statement statement = connection.createStatement();
        ArrayNode result = JsonNodeFactory.instance.arrayNode();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");

            result.add(JsonNodeFactory.instance.objectNode().put("id", id).put("name", name).put("email",email));
        }
        return ok(result);
    }

    public Result getStudentById(int id) throws SQLException {
        Connection connection = db.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM student WHERE id=?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        ObjectNode result = JsonNodeFactory.instance.objectNode();
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            result.put("id", id).put("name", name).put("email",email);
        }
        return ok(result);
    }
    public Result addStudent(Http.Request request) throws SQLException {
        Connection connection= db.getConnection();;
        JsonNode json = request.body().asJson();
        int id = Integer.parseInt(json.get("id").asText());
        String name = json.get("name").asText();
        String email =json.get("email").asText();


        PreparedStatement preparedStatement = connection.prepareStatement("insert into student (id,name,email) values(?,?,?)");
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, email);
        preparedStatement.executeUpdate();

        return ok("Data inserted successfully");
    }

}





