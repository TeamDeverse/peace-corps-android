package com.richardwonseokshin.peacecorpsv2;

public class OpeningInformation {

	String title = "";
	String req_id = "";
	String country = "";
	String region = "";
	String sector = "";
	String apply_date = "";
	String know_date = "";
	String staging_start_date = "";
	boolean featured = false;
	String project_description = "";
	String required_skills = "";
	String desired_skills = "";
	String language_skills = "";
	String language_skills_comments = "";
	int volunteers_requested = 0;
	boolean accepts_couples = false;
	String living_conditions_comments = "";
	String country_medical_considerations = "";
	String country_site_url = "";
	String country_flag_image = "";
	String opening_url = "";
	
	public String toString(){
		String openingInfo = "";
		openingInfo = openingInfo + "title: " + title + "\n";
		openingInfo = openingInfo + "req_id: " + req_id + "\n";
		openingInfo = openingInfo + "country: " + country + "\n";
		openingInfo = openingInfo + "region: " + region + "\n";
		openingInfo = openingInfo + "sector: " + sector + "\n";
		openingInfo = openingInfo + "apply_date: " + apply_date + "\n";
		openingInfo = openingInfo + "know_date: " + know_date + "\n";
		openingInfo = openingInfo + "staging_start_date: " + staging_start_date + "\n";
		openingInfo = openingInfo + "featured: " + featured + "\n";
		openingInfo = openingInfo + "project_description: " + project_description + "\n";
		openingInfo = openingInfo + "required_skills: " + required_skills + "\n";
		openingInfo = openingInfo + "desired_skills: " + desired_skills + "\n";
		openingInfo = openingInfo + "language_skills: " + language_skills + "\n";
		openingInfo = openingInfo + "language_skills_comments: " + language_skills_comments + "\n";
		openingInfo = openingInfo + "volunteers_requested: " + volunteers_requested + "\n";
		openingInfo = openingInfo + "accepts_couples: " + accepts_couples + "\n";
		openingInfo = openingInfo + "living_conditions_comments: " + living_conditions_comments + "\n";
		openingInfo = openingInfo + "country_medical_considerations: " + country_medical_considerations + "\n";
		openingInfo = openingInfo + "country_site_url: " + country_site_url + "\n";
		openingInfo = openingInfo + "country_flag_image: " + country_flag_image + "\n";
		openingInfo = openingInfo + "opening_url: " + opening_url;
		
		return openingInfo;
	}
}
