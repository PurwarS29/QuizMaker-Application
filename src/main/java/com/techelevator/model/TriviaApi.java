package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "response_code",
        "results"
})
public class TriviaApi {

    @JsonProperty("response_code")
    public Integer responseCode;
    @JsonProperty("results")
    public List<TriviaApiResult> results = null;

    public List<TriviaApiResult> getResults() {
        return results;
    }
}
