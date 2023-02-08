package controllers;

import org.elasticsearch.client.RestClient;
import play.mvc.Result;

import javax.inject.Inject;

import static play.mvc.Results.ok;

public class ElasticController {
    @Inject
  private RestClient restClient;
    public Result connection(){
        return ok("success");
    }
}
