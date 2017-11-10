package ohtu;

import java.lang.reflect.Array;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }
    
    public void setHours(int hours) {
        this.hours = hours;
    }
    
    public int getHours(){
        return hours;
    }
    
    public void setExercises(int[] exercises){
        this.exercises = exercises;
    }
    
    public int[] getExercises(){
        return exercises;
    }
    
    public int countExercises(){
        int amount = exercises.length;        
        return amount;
    }
    
    public String printExercises(){
        String listOfExercises = ""; 
        for(int excersice : exercises){
            listOfExercises += (excersice + " ");
            
        }
        
        return listOfExercises;
    }

    @Override
    public String toString() {
        return "viikko "+week+": tehtyjä tehtäviä yhteensä: "+countExercises()+", aikaa kului "+hours+" tuntia, tehdyt tehtävät: "+printExercises();
    }
    
}