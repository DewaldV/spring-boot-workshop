#Phase 2: Persistence is Magic
  1. **Pony** is a JPA Entity class used to map a database entry to an object.
    1. **@Entity** describes this class as a persisted entity which is mapped to table in a relational database.
    2. **@Id** informs the JPA implementation which field is used as the primary key for the entity.
    3. **@GeneratedValue** marks the field for value generation according to the selected strategy (default AUTO which is based on the underlying relational database).
    4. **@Column** maps a field to a regular column
    5. **@Enumerated** indicates that a column value should be mapped from a java enum. The default is to use **oridinal** values for the enums.

    In addition the entity provides overrides for the default equals and hash code methods to allow for equality to be established by the JPA framework

  2. **PonyRepository** is a spring data JPA repository interface extending from the base CrudInterface<T, I>. This interface is implemented at runtime by the spring data JPA framework with basic CRUD methods.
    1. **@Repository** marks this class as a spring data JPA repository and spring framework component.
    2. **findByName(String name)** method is a custom query. Spring data JPA will interpret this method definition and generate the required JPA query.
  Note that at no time do we provide an implementation for this interface, spring will ensure that the basic CRUD queries are populated.

  3. **PonyType** is a simple enum

  4. **PonyController** is a new controller for our Pony resources.
    1. **@RequestMapping** on the class level applies the path as a base for all subsequent methods.
    2. **@AutoWired** is a spring annotation indicating that the spring framework should inject a matching instance into this class's constructor.
    3. **@ResponseStatus** tells the framework which HTTP response code to use for a successful response.
    4. **@RequestBody** maps the HTTP request body into this parameter.
    5. **@PathVariable** maps the correspondingly named variable from the URI to the parameter.

    This controller provides us with a POST method for creating entities which are persisted into the database, a GET method for fetching a list of all entities and a GET method to get a specific entity by name.