package com.hcis.ipr.intellectual.agency.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

public class Agency extends BaseEntity {

    private String agencyName;

    private String agencyCode;
    private String agencyEmail;

    private String agencyAddress;

    private String agencyMobile;

    private String agencyer;

    private String agencyerMobile;

    public String getAgencyEmail() {
        return agencyEmail;
    }

    public void setAgencyEmail(String agencyEmail) {
        this.agencyEmail = agencyEmail;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName == null ? null : agencyName.trim();
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode == null ? null : agencyCode.trim();
    }

    public String getAgencyAddress() {
        return agencyAddress;
    }

    public void setAgencyAddress(String agencyAddress) {
        this.agencyAddress = agencyAddress == null ? null : agencyAddress.trim();
    }

    public String getAgencyMobile() {
        return agencyMobile;
    }

    public void setAgencyMobile(String agencyMobile) {
        this.agencyMobile = agencyMobile == null ? null : agencyMobile.trim();
    }

    public String getAgencyer() {
        return agencyer;
    }

    public void setAgencyer(String agencyer) {
        this.agencyer = agencyer == null ? null : agencyer.trim();
    }

    public String getAgencyerMobile() {
        return agencyerMobile;
    }

    public void setAgencyerMobile(String agencyerMobile) {
        this.agencyerMobile = agencyerMobile == null ? null : agencyerMobile.trim();
    }

}