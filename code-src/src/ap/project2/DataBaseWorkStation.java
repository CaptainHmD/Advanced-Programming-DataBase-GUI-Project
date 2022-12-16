package ap.project2;

public class DataBaseWorkStation {

    private String fullName; // create private variable
    private String DateOfBirth; // create private variable
    private float GPA; // create private variable

    public String getFullName() { // create get method
        return fullName; // return value of fullname
    }

    public void setFullName(String fullName) { // create set method

        this.fullName = fullName; // set value of fullname
    }

    public String getDateOfBirth() { // create get method

        return DateOfBirth; // get value of dateofbirth
    }

    public void setDateOfBirth(String date) { // create set method


        this.DateOfBirth = date; // set value of dateofbirth
    }

    public float getGPA() { // create get method
        return GPA; // return value of gpa
    }

    public void setGPA(String gpa) { // create set method

        this.GPA = Float.parseFloat(gpa); // set value of gpa

    }
}