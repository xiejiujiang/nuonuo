package com.example.nuonuo.entity.Ekanya.EyuyueByID;

import com.example.nuonuo.entity.Ekanya.Euser.*;

import java.util.List;

public class EyuyueData {

    private Patient patient;

    private String startTime;

    private String endTime;

    private Operatory operatory;

    private String department;

    private Doctor doctor;

    private String nurse;

    private Consultant consultant;

    private String notes;

    private int status;

    private String statusName;

    private List<Procedures> procedures;

    private String checkInTime;

    private CheckInOperator checkInOperator;

    private String checkInType;

    private String officeName;

    private String recordCreatedTime;

    private String recordUpdatedTime;

    private int officeId;

    private int id;

    private String tenantId;

    private boolean isInactive;

    private String timestamp;

    public void setPatient(Patient patient){
        this.patient = patient;
    }
    public Patient getPatient(){
        return this.patient;
    }
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }
    public String getStartTime(){
        return this.startTime;
    }
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }
    public String getEndTime(){
        return this.endTime;
    }
    public void setOperatory(Operatory operatory){
        this.operatory = operatory;
    }
    public Operatory getOperatory(){
        return this.operatory;
    }
    public void setDepartment(String department){
        this.department = department;
    }
    public String getDepartment(){
        return this.department;
    }
    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }
    public Doctor getDoctor(){
        return this.doctor;
    }
    public void setNurse(String nurse){
        this.nurse = nurse;
    }
    public String getNurse(){
        return this.nurse;
    }
    public void setConsultant(Consultant consultant){
        this.consultant = consultant;
    }
    public Consultant getConsultant(){
        return this.consultant;
    }
    public void setNotes(String notes){
        this.notes = notes;
    }
    public String getNotes(){
        return this.notes;
    }
    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setStatusName(String statusName){
        this.statusName = statusName;
    }
    public String getStatusName(){
        return this.statusName;
    }
    public void setProcedures(List<Procedures> procedures){
        this.procedures = procedures;
    }
    public List<Procedures> getProcedures(){
        return this.procedures;
    }
    public void setCheckInTime(String checkInTime){
        this.checkInTime = checkInTime;
    }
    public String getCheckInTime(){
        return this.checkInTime;
    }
    public void setCheckInOperator(CheckInOperator checkInOperator){
        this.checkInOperator = checkInOperator;
    }
    public CheckInOperator getCheckInOperator(){
        return this.checkInOperator;
    }
    public void setCheckInType(String checkInType){
        this.checkInType = checkInType;
    }
    public String getCheckInType(){
        return this.checkInType;
    }
    public void setOfficeName(String officeName){
        this.officeName = officeName;
    }
    public String getOfficeName(){
        return this.officeName;
    }
    public void setRecordCreatedTime(String recordCreatedTime){
        this.recordCreatedTime = recordCreatedTime;
    }
    public String getRecordCreatedTime(){
        return this.recordCreatedTime;
    }
    public void setRecordUpdatedTime(String recordUpdatedTime){
        this.recordUpdatedTime = recordUpdatedTime;
    }
    public String getRecordUpdatedTime(){
        return this.recordUpdatedTime;
    }
    public void setOfficeId(int officeId){
        this.officeId = officeId;
    }
    public int getOfficeId(){
        return this.officeId;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setTenantId(String tenantId){
        this.tenantId = tenantId;
    }
    public String getTenantId(){
        return this.tenantId;
    }
    public void setIsInactive(boolean isInactive){
        this.isInactive = isInactive;
    }
    public boolean getIsInactive(){
        return this.isInactive;
    }
    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }
    public String getTimestamp(){
        return this.timestamp;
    }
}
