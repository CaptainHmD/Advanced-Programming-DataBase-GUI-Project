package ap.project2;

public class Student {

    private int id; // create private variable
    private String fullName; // create private variable
    private String dateOfBirth; // create private variable
    private float gpa; // create private variable

    public Student() { // create constructor for class
    }

    public Student(int id, String fullName, String dateOfBirth, float gpa) { // create constructor for class with parameters
        this.id = id; // set value of id
        this.fullName = fullName; // set value of fullname
        this.dateOfBirth = dateOfBirth; // set value of dateofbirth
        this.gpa = gpa; // set value of gpa
    }

    public int getId() { // create get method
        return id; // get value of id
    }

    public void setId(int id) { // create set method
        this.id = id; // set value of id
    }

    public String getFullName() { // create get method
        return fullName; // get value of fullname
    }

    public void setFullName(String fullName) { // create set method
        this.fullName = fullName; // set value of fullname
    }

    public String getDateOfBirth() { // create get method
        return dateOfBirth; // get value of dateofbirth
    }

    public void setDateOfBirth(String dateOfBirth) { // create set method
        this.dateOfBirth = dateOfBirth; // set value of dateofbirth
    }

    public float getGpa() { // create get method
        return gpa; // get value of gpa
    }

    public void setGpa(float gpa) { // create set method
        this.gpa = gpa;  // set value of gpa
    }
}