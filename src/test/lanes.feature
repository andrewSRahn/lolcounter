Feature: Lanes feature
 	I want to check if champions and lanes are persisted to the sql database
 	
 	Scenario: Blitzcrank lanes
 		Given Champion and Lane tables are initialized
 		Then Blitzcrank will be a support and jungler
 		
 	Scenario: Leona lanes
 		Given Champion and Lane tables are initialized
 		Then Leona will be a support
 		