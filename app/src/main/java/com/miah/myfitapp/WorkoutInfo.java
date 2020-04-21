package com.miah.myfitapp;

public class WorkoutInfo {
    private int id;
    private int squat,deadlift,benchpress,ohp;
    private int squatR,deadliftR,benchR,ohpR;
    private int date;

    public WorkoutInfo(){

    }

    public WorkoutInfo(int id, int squat,int squatR, int deadlift, int benchpress, int ohp,  int deadliftR, int benchR, int ohpR,int date) {
        this.id = id;
        this.squat = squat;
        this.squatR = squatR;
        this.deadlift = deadlift;
        this.benchpress = benchpress;
        this.ohp = ohp;
        this.deadliftR = deadliftR;
        this.benchR = benchR;
        this.ohpR = ohpR;
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getSquatR() {
        return squatR;
    }

    public void setSquatR(int squatR) {
        this.squatR = squatR;
    }

    public int getDeadliftR() {
        return deadliftR;
    }

    public void setDeadliftR(int deadliftR) {
        this.deadliftR = deadliftR;
    }

    public int getBenchR() {
        return benchR;
    }

    public void setBenchR(int benchR) {
        this.benchR = benchR;
    }

    public int getOhpR() {
        return ohpR;
    }

    public void setOhpR(int ohpR) {
        this.ohpR = ohpR;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSquat() {
        return squat;
    }

    public void setSquat(int squat) {
        this.squat = squat;
    }

    public int getDeadlift() {
        return deadlift;
    }

    public void setDeadlift(int deadlift) {
        this.deadlift = deadlift;
    }

    public int getBenchpress() {
        return benchpress;
    }

    public void setBenchpress(int benchpress) {
        this.benchpress = benchpress;
    }

    public int getOhp() {
        return ohp;
    }

    public void setOhp(int ohp) {
        this.ohp = ohp;
    }
}
