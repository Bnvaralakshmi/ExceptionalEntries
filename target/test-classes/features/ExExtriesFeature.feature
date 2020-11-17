#Author: Naga Bhamidipati and Karthik Kalidoss
#Exceptional Entries Feature File

Feature: CAF Admin Feature File
  
  @tag
  Scenario: CAF ADMIN Login using exsiting user id 
    Given CAF Admin login page is up and running
    And user id and password entered
    When user  tried to login with details
    Then user is logged in successfully 
    
    @CLC
    Scenario: CLC Exemptions 
    Given CLC page is visible
    When The user chooses the CSV file to load
    Then File upload is successful 