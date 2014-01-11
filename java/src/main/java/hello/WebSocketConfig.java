
package hello;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@EnableWebSocket
@EnableWebMvc
@ComponentScan
@ImportResource({"classpath:/spring/security.xml"})
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/queue/", "/topic/");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").withSockJS();
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration channelRegistration) {
	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration channelRegistration) {
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //resources.put("/*.html", "/");

//        for (String resourceMatcher : resourcesExcluded.keySet()) {
            registry.addResourceHandler("/*.html").addResourceLocations("/");
            registry.addResourceHandler("/*.js").addResourceLocations("/");
//        }
    }

}