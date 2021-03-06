Feature: Scrape lolcounter.com then store in SQL
  This feature will scrape lolcounter.com and store them into an SQL database.
  
  Scenario: Scrape /champions and store
		Given Champions page is scraped
		When Champion service reads champions
    Then champions will contain given names.
  
  Scenario: Scrape /champions/name/relation
  	Given /champions/name/relation is scraped
  	When Page service reads pages
  	Then Page block will contain image, foe, lane, up, and down