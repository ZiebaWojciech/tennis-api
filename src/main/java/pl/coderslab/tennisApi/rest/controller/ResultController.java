package pl.coderslab.tennisApi.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.tennisApi.entity.Event;
import pl.coderslab.tennisApi.entity.Result;
import pl.coderslab.tennisApi.service.EventService;
import pl.coderslab.tennisApi.service.ResultService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/results")
public class ResultController {
    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @RequestMapping(value = "/in-progress", method = RequestMethod.GET)
    public List<Result> getAllResults(){
        return resultService.getAll();
    }

}
