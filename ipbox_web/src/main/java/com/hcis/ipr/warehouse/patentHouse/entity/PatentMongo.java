package com.hcis.ipr.warehouse.patentHouse.entity;

import com.hcis.ipanther.core.entity.BaseEntity;


public class PatentMongo extends BaseEntity {
	
	private static final long serialVersionUID = -6062576613614616470L;

	private String priorities;
	private String cited_patents;
	private String citing_patents;
	private String cited_literatures;
	private String ipcs;
	private String Locarno_classification;
	private String main_ipc;
	private String cpcs;
	private String main_cpc;
	private String examiner;
	private String kind;
	private String application_number;
	private String nonstandard_application_numbers;
	private String publication_number;
	private String nonstandard_publication_numbers;
	private String application_date;
	private String earliest_publication_date;
	private String earliest_priority_date;
	private String national_entry_date;
	private String type;
	private String world_application_number;
	private String world_application_date;
	private String world_publication_number;
	private String world_publication_date;
	private String world_application;
	private String world_publication;
	private String agencies;
	private String countries;
	private String eprc;
	private String nonstandard_priority_numbers;
	private String neics;
	private String ecs;
	private String ncls;
	private String main_patent;
	private String pdan;
	private String dan;
	private String main_claim;
	
	public String getPriorities(){
		return this.priorities;
	}
	public void setPriorities(String priorities){
		this.priorities = priorities;
	}
	
	public String getCited_patents(){
		return this.cited_patents;
	}
	public void setCited_patents(String cited_patents){
		this.cited_patents = cited_patents;
	}
	public String getCiting_patents(){
		return this.citing_patents;
	}
	public void setCiting_patents(String citing_patents){
		this.citing_patents = citing_patents;
	}
	public String getCited_literatures(){
		return this.cited_literatures;
	}
	public void setCited_literatures(String cited_literatures){
		this.cited_literatures = cited_literatures;
	}
	
	public String getIpcs(){
		return this.ipcs;
	}
	public void setIpcs(String ipcs){
		this.ipcs = ipcs;
	}
	
	public String getLocarno_classification(){
		return this.Locarno_classification;
	}
	public void setLocarno_classification(String Locarno_classification){
		this.Locarno_classification = Locarno_classification;
	}
	
	public String getMain_ipc(){
		return this.main_ipc;
	}
	public void setMain_ipc(String main_ipc){
		this.main_ipc = main_ipc;
	}
	
	public String getCpcs(){
		return this.cpcs;
	}
	public void setCpcs(String cpcs){
		this.cpcs = cpcs;
	}
	
	public String getMain_cpc(){
		return this.main_cpc;
	}
	public void setMain_cpc(String main_cpc){
		this.main_cpc = main_cpc;
	}
	
	public String getApplication_number(){
		return this.application_number;
	}
	public void setApplication_number(String application_number){
		this.application_number = application_number;
	}
	
	public String getNonstandard_application_numbers(){
		return this.nonstandard_application_numbers;
	}
	public void setNonstandard_application_numbers(String nonstandard_application_numbers){
		this.nonstandard_application_numbers = nonstandard_application_numbers;
	}
	
	public String getPublication_number(){
		return this.publication_number;
	}
	public void setPublication_number(String publication_number){
		this.publication_number = publication_number;
	}
	
	public String getNonstandard_publication_numbers(){
		return this.nonstandard_publication_numbers;
	}
	public void setNonstandard_publication_numbers(String nonstandard_publication_numbers){
		this.nonstandard_publication_numbers = nonstandard_publication_numbers;
	}
	
	public String getApplication_date(){
		return this.application_date;
	}
	public void setApplication_date(String application_date){
		this.application_date = application_date;
	}
	
	public String getKind(){
		return this.kind;
	}
	public void setKind(String kind){
		this.kind = kind;
	}
	
	public String getExaminer(){
		return this.examiner;
	}
	public void setExaminer(String examiner){
		this.examiner = examiner;
	}
	
	public String getEarliest_publication_date(){
		return this.earliest_publication_date;
	}
	public void setEarliest_publication_date(String earliest_publication_date){
		this.earliest_publication_date= earliest_publication_date;
	}
	
	public String getEarliest_priority_date(){
		return this.earliest_priority_date;
	}
	public void setEarliest_priority_date(String earliest_priority_date){
		this.earliest_priority_date= earliest_priority_date;
	}
	
	public String getNational_entry_date(){
		return this.national_entry_date;
	}
	public void setNational_entry_date(String national_entry_date){
		this.national_entry_date= national_entry_date;
	}
	
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type= type;
	}
	
	public String getWorld_application_number(){
		return this.world_application_number;
	}
	public void setWorld_application_number(String world_application_number){
		this.world_application_number= world_application_number;
	}
	
	public String getWorld_application_date(){
		return this.world_application_date;
	}
	public void setWorld_application_date(String world_application_date){
		this.world_application_date= world_application_date;
	}
	
	public String getWorld_publication_number(){
		return this.world_publication_number;
	}
	public void setWorld_publication_number(String world_publication_number){
		this.world_publication_number= world_publication_number;
	}
	
	public String getWorld_publication_date(){
		return this.world_publication_date;
	}
	public void setWorld_publication_date(String world_publication_date){
		this.world_publication_date= world_publication_date;
	}
	
	public String getWorld_application(){
		return this.world_application;
	}
	public void setWorld_application(String world_application){
		this.world_application= world_application;
	}
	
	public String getWorld_publication(){
		return this.world_publication;
	}
	public void setWorld_publication(String world_publication){
		this.world_publication= world_publication;
	}
	
	public String getAgencies(){
		return this.agencies;
	}
	public void setAgencies(String agencies){
		this.agencies= agencies;
	}
	
	public String getCountries(){
		return this.countries;
	}
	public void setCountries(String countries){
		this.countries= countries;
	}
	
	public String getEprc(){
		return this.eprc;
	}
	public void setEprc(String eprc){
		this.eprc= eprc;
	}
	
	public String getNonstandard_priority_numbers(){
		return this.nonstandard_priority_numbers;
	}
	public void setNonstandard_priority_numbers(String nonstandard_priority_numbers){
		this.nonstandard_priority_numbers= nonstandard_priority_numbers;
	}
	
	public String getNeics(){
		return this.neics;
	}
	public void setNeics(String neics){
		this.neics= neics;
	}
	
	public String getEcs(){
		return this.ecs;
	}
	public void setEcs(String ecs){
		this.ecs= ecs;
	}
	
	public String getNcls(){
		return this.ncls;
	}
	public void setNcls(String ncls){
		this.ncls = ncls ;
	}
	
	public String getMain_patent(){
		return this.main_patent;
	}
	public void setMain_patent(String main_patent){
		this.main_patent = main_patent ;
	}
	
	public String getPdan(){
		return this.pdan;
	}
	public void setPdan(String pdan){
		this.pdan = pdan ;
	}
	
	public String getDan(){
		return this.dan;
	}
	public void setDan(String dan){
		this.dan = dan ;
	}
	
	public String getMain_claim(){
		return this.main_claim;
	}
	public void setMain_claim(String main_claim){
		this.main_claim = main_claim;
	}
}