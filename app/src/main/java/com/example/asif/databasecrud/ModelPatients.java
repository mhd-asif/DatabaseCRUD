package com.example.asif.databasecrud;

/**
 * Created by nasir on 1/9/2017.
 */

public class ModelPatients {
    String patient_id;
    String name;
    String age;
    String disease;

    public String getName() {
        return name;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
}
