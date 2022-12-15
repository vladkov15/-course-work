package com.tilted.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProducerDTO {
    @JsonProperty("id")
    public int Id;
    @JsonProperty("name")
    public String Name;
    @JsonProperty("place")
    public String Place;
}
