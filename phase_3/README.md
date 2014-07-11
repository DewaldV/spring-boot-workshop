#Phase 3: Ascend to RMM Level 3

Not much has changed here. The spring-hateoas dependency has been added to the maven pom and return values from the controller wrapped in the **Resource<T>** pattern to generate links.

  1. **PonyController** now returns **Resource<T>** instances instead of just the entities.
    1. **Resource<T>** is a wrapper class in spring-hateoas to allow for rich hypermedia response generation.
    2. **getPonyResource(Pony pony)** method wraps a **Pony** instance in a **Resource<T>** wrapped with a self link and an "all" link.
    3. **getPonyResources(Iterable<Pony> ponies)** delegates each instance to **getPonyResource** and returns an **Iterable<Resource<T>>**.
    4. **@RequestParam(required = false)** maps an optional query string parameter (?ponyType={}) to a method parameter.

  2. **PonyRepository** has been extended with a new method definition **findByType(PonyType ponyType)** which returns an **Iterable<Pony>** list.