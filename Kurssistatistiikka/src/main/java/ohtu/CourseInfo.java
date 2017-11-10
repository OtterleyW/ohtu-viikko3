package ohtu;

import java.lang.reflect.Array;

public class CourseInfo {
    private String name;
    private String term;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setTerm(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }
    
    
    @Override
    public String toString() {
        return "Kurssi: "+name+", "+term;
    }
    
}