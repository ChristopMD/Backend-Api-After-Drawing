Feature: Create a new Post
  As a user
  I want to create a new project
  So I start to make wireframes

  Scenario: The project was created successfully
    Given i am a user and entered the correct data
    When i make a project request to "/api/users/1/projects"
    Then the response result received should be 200

  Scenario: The post could not be created
    Given i am a user and entered the incorrect data
    When i make a project request to "/api/users/2/projects"
    Then the response result received should be a bad 400