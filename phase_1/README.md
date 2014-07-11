#Phase 1: Bringing the hammer down
  1. **WorkshopController** contains only the Controller annotations and logic.
    1. **@RestController** designates this as a spring component with Spring MVC Controller mappings for which the return values of the methods should be treated as the ResponseBody
    2. **@RequestMapping** defines the URL segment to which this controller method is mapped relative to "/".

  2. **WorkshopConfiguration** is a spring Annotated and code config file describing the application.   There can be more than one configuration class in a Spring Context but in general there should never be more than one @EnableAutoConfiguration annotation.
    1. **@Configuration** designates this as a spring context configuration class. Configuration classes are also Spring components and will be ComponentScan'd if they are not directly imported.
    2. **@EnableAutoConfiguration** is a new spring boot addition which instructs spring to attempt to automatically configure any components which are on the class path.
    3. **@ComponentScan** will allow Spring to scan the application's packages for marked components (@Component, @Service, @Configuration @Repository, @Controller and @RestController are some common ones).

  3. **WorkshopApplication** contains the main method used to bootstrap the spring context.