package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;


@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
//        Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @MessageMapping("/loadEmployee")
    @SendTo("/topic/employees")
    public Employee loadEmployee(Request request) throws Exception {
        Long id = Long.parseLong((String) request.getRequestObject());
        System.out.println("received id:" + id);
        Employee e = new Employee();
        e.setId(id);
        e.setCreatedDate(new Date().toString());
        e.setFirstName("Jeff");
        e.setLastName("Willis");
        e.setCity("Bellevue");
        e.setState("WA");
        e.setEmail(e.getFirstName() + "." + e.getLastName() + "@ascentis.com");
        e.setPhone("1-800-229-2713");
        return e;
    }

}
