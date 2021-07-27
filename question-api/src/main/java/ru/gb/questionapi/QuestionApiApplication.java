package ru.gb.questionapi;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import ru.gb.questionapi.parsing.Response;
import java.io.IOException;
import static ru.gb.questionapi.SendRequest.sendGet;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class QuestionApiApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(QuestionApiApplication.class, args);

        Gson gson = new Gson();
        Response response = gson.fromJson(sendGet(3,1), Response.class);
        response.getData();
    }
}
