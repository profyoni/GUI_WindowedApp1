import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PersonGson {
    private String name;
    private int age;

    public PersonGson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args) {
        // create a new person object
        PersonGson person = new PersonGson("John Doe", 30);

        // serialize the person object to JSON
        Gson gson = new Gson();
        String json = gson.toJson(person);

        System.out.println(json);

        // deserialize the JSON string back into a person object
        PersonGson deserializedPerson = gson.fromJson(json, PersonGson.class);

        // print the original and deserialized person objects
        System.out.println("Original person: " + person.getName() + ", " + person.getAge());
        System.out.println("Deserialized person: " + deserializedPerson.getName() + ", " + deserializedPerson.getAge());
    }
}