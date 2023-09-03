package io.vamani.application.model;

import io.vamani.application.domain.*;
import io.vamani.application.mssql.domain.*;

import java.util.List;

public class WorkerMasterBean {
    private List<BankMaster> bankMasters;
    private List<Catgory> catgories;
    private List<Cost> costs;
    private List<RecruitmentCountryMaster> countries;
    private List<DepartmentMaster> departmentMasters;
    private List<DesignationMaster> designationMasters;
    private List<EducationMaster> educationMasters;
    private List<EducationTypeMaster> educationTypeMasters;
    private List<Floor> floors;
    private List<Foodcat> foodcats;
    private List<Grade> grades;
    private List<InstituteMaster> instituteMasters;
    private List<LanguageMaster> languageMasters;
    private List<NominationTypeMaster> nominationTypeMasters;
    private List<OccupationMaster> occupationMasters;
    private List<RelationMaster> relationMasters;
    private List<Section> sections;
    private List<Shift> shifts;
    private List<StaffWork> staffWorks;
    private List<Subdept> subdepts;
    private List<Woff> woffs;

    public List<Cost> getCosts() {
        return costs;
    }

    public void setCosts(List<Cost> costs) {
        this.costs = costs;
    }

    public List<BankMaster> getBankMasters() {
        return bankMasters;
    }

    public void setBankMasters(List<BankMaster> bankMasters) {
        this.bankMasters = bankMasters;
    }

    public List<DesignationMaster> getDesignationMasters() {
        return designationMasters;
    }

    public void setDesignationMasters(List<DesignationMaster> designationMasters) {
        this.designationMasters = designationMasters;
    }

    public List<DepartmentMaster> getDepartmentMasters() {
        return departmentMasters;
    }

    public void setDepartmentMasters(List<DepartmentMaster> departmentMasters) {
        this.departmentMasters = departmentMasters;
    }

    public List<Subdept> getSubdepts() {
        return subdepts;
    }

    public void setSubdepts(List<Subdept> subdepts) {
        this.subdepts = subdepts;
    }

    public List<RelationMaster> getRelationMasters() {
        return relationMasters;
    }

    public void setRelationMasters(List<RelationMaster> relationMasters) {
        this.relationMasters = relationMasters;
    }

    public List<OccupationMaster> getOccupationMasters() {
        return occupationMasters;
    }

    public void setOccupationMasters(List<OccupationMaster> occupationMasters) {
        this.occupationMasters = occupationMasters;
    }

    public List<LanguageMaster> getLanguageMasters() {
        return languageMasters;
    }

    public void setLanguageMasters(List<LanguageMaster> languageMasters) {
        this.languageMasters = languageMasters;
    }

    public List<NominationTypeMaster> getNominationTypeMasters() {
        return nominationTypeMasters;
    }

    public void setNominationTypeMasters(List<NominationTypeMaster> nominationTypeMasters) {
        this.nominationTypeMasters = nominationTypeMasters;
    }

    public List<RecruitmentCountryMaster> getCountries() {
        return countries;
    }

    public void setCountries(List<RecruitmentCountryMaster> countries) {
        this.countries = countries;
    }

    public List<EducationMaster> getEducationMasters() {
        return educationMasters;
    }

    public void setEducationMasters(List<EducationMaster> educationMasters) {
        this.educationMasters = educationMasters;
    }

    public List<EducationTypeMaster> getEducationTypeMasters() {
        return educationTypeMasters;
    }

    public void setEducationTypeMasters(List<EducationTypeMaster> educationTypeMasters) {
        this.educationTypeMasters = educationTypeMasters;
    }

    public List<InstituteMaster> getInstituteMasters() {
        return instituteMasters;
    }

    public void setInstituteMasters(List<InstituteMaster> instituteMasters) {
        this.instituteMasters = instituteMasters;
    }

    public List<Catgory> getCatgories() {
        return catgories;
    }

    public void setCatgories(List<Catgory> catgories) {
        this.catgories = catgories;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public List<Foodcat> getFoodcats() {
        return foodcats;
    }

    public void setFoodcats(List<Foodcat> foodcats) {
        this.foodcats = foodcats;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<StaffWork> getStaffWorks() {
        return staffWorks;
    }

    public void setStaffWorks(List<StaffWork> staffWorks) {
        this.staffWorks = staffWorks;
    }

    public List<Woff> getWoffs() {
        return woffs;
    }

    public void setWoffs(List<Woff> woffs) {
        this.woffs = woffs;
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(List<Shift> shifts) {
        this.shifts = shifts;
    }
}
