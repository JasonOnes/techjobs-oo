package org.launchcode.controllers;

import org.launchcode.models.*;
import org.launchcode.models.data.JobFieldData;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;

import static org.launchcode.models.Job.*;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {
        // TODO #1 - get the Job with the given ID and pass it into the view DONE
        Job someJob = jobData.findById(id);
        model.addAttribute("job", someJob);
        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model,  @ModelAttribute @Valid JobForm jobForm, Errors errors, @RequestParam Location locations,
                      @RequestParam CoreCompetency coreCompetencies,
                      @RequestParam PositionType positionTypes,
                      @RequestParam String name,
                      @RequestParam int employerId) {

        // TODO #6 - Validate the JobForm model, and if valid, create a DONE
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.
        //note only error possible is with name
        if (errors.hasErrors()) {
            return "new-job";
        }

        //note instantiates newJob with Employer name retrieved by id
        Employer someEmployer = jobData.getEmployers().findById(employerId);

        Job newJob = new Job(name, someEmployer, locations, positionTypes, coreCompetencies);

        jobData.add(newJob);

        //TODO return to job/id?= why?
        int id = newJob.getId();
        model.addAttribute("id", id);
        return "redirect:/job/?id=" + id;

        //model.addAttribute("job", newJob);
        //return "/job-detail"; note this makes much more sense to me!!


    }
}
