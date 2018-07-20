Feature: Scrape lolcounter.com then store in SQL
  This feature will scrape lolcounter.com and store them into an SQL database.
  
  Scenario: Scrape /champions and store
		Given Champions page is scraped
		When Champion service reads champions
    Then champions will contain given names.
  
  Scenario Outline: Scrape champions/<name>/<relation>
  	Given /champions/<name>/<relation> is scraped
  	Then Page block will contain <image>, <lane>, <up>, and <down>
  	
  	Examples:
  		|	name			|	relation			| image						|	lane					|	up		|	down	|
  		| 	
			