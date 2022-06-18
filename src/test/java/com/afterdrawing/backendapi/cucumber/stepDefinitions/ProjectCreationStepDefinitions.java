package com.afterdrawing.backendapi.cucumber.stepDefinitions;

import com.afterdrawing.backendapi.cucumber.SpringIntegrationTest;
import com.afterdrawing.backendapi.resource.SaveProjectResource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
public class ProjectCreationStepDefinitions extends SpringIntegrationTest {
    SaveProjectResource saveProjectResource=new SaveProjectResource();

    @Given("i am a user and entered the correct data")
    public void i_am_a_user_and_entered_the_correct_data() {
        saveProjectResource.setTitle("PostCreadonum2");
        saveProjectResource.setDescription("Dimensioon");

    }

    @Given("i am a user and entered the incorrect data")
    public void i_am_a_user_and_entered_the_incorrect_data() {
        saveProjectResource = null;
    }

    @When("i make a project request to {string}")
    public void i_make_a_project_request_to(String path) throws IOException {
        makePost(path, saveProjectResource);
    }
    @Then("the response result received should be {int}")
    public void the_response_result_received_should_be(Integer codeResponse) {
        assertThat(response.value(), is(codeResponse));
    }
    @Then("the response result received should be a bad {int}")
    public void the_response_result_received_should_be_a_bad(Integer codeResponse) {
        assertThat(response.value(), is(HttpStatus.BAD_REQUEST.value()));
    }
}
