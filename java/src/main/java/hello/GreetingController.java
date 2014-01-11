package hello;

import java.security.Principal;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;


@Controller
public class GreetingController {
    private final SimpMessageSendingOperations messagingTemplate;

	@Autowired
	public GreetingController(SimpMessageSendingOperations messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

    @MessageMapping("/hello")
    public void greeting(HelloMessage message, Principal principal) throws Exception {
        if(message.getName().length() <= 0)
            throw new IllegalArgumentException("You didn't enter a name!");
        this.messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/greetings-updates",
                "Hello, " + message.getName() + "(" + principal.getName() + ")!");
    }

    @MessageMapping("/hello-all")
    @SendTo("/topic/greetings")
    public Greeting greetingAll(HelloMessage message, Principal principal) throws Exception {
        if(message.getName().length() <= 0)
            throw new IllegalArgumentException("You didn't enter a name!");
        return new Greeting("Hello everyone from " + message.getName() + "(" + principal.getName() + ")!");
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

    @MessageExceptionHandler
	@SendToUser("/queue/errors")
	public String handleException(Throwable exception) {
        System.err.println("Execption: " + exception);
		return exception.toString();
	}

}
