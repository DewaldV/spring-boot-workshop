import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@RestController
public class SampleController {

    @RequestMapping("/")
    public String get() {
      return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
    }
}
