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
    public String index(Model model,int id) { //} @PathVariable int id) {

        // TODO #1 - get the Job with the given ID and pass it into the view
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
    public String add(Model model, @Valid JobForm jobForm, Errors errors, @RequestParam Location locations,
                      @RequestParam CoreCompetency coreCompetencies,
                      @RequestParam PositionType positionTypes,
                      @RequestParam String name,
                      @RequestParam int employerId){
                     // @PathVariable int id) {
    //public String add(Model model, Job newJob, @Valid JobForm jobForm, Error errors, @RequestParam CoreCompetency coreCompetency,
     //                 Location location, PositionType positionType) {
        //jobData = JobData.getInstance();

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.
        //note only error possible is with name
        if (errors.hasErrors()) {
            return "add";
        }

//        JobFieldData jobFieldData = new JobFieldData<Employer>;//findAll();
//        ArrayList<Employer> employers = jobFieldData.getEmployers().findAll();
//        Employer employer = employers.findById(employerId);
        //TODO get employer value from employer id and add that to istantiation

        //int someId = (Integer).valueOf((String) toString(employerId));
        Employer someEmployer = jobData.getEmployers().findById(employerId);

        Job newJob = new Job(name, someEmployer, locations, positionTypes, coreCompetencies);
//        newJob.setName(name);
//        newJob.setEmployer(employerId);
//        newJob.setLocation(locations);
//        newJob.setPositionType(positionTypes);
//        newJob.setCoreCompetency(coreCompetencies);
        System.out.println("((((((((((((((" + newJob.getLocation());
        System.out.println("%%%%%%%%%%%%%%%" + newJob.getName());

        System.out.println("**********" + newJob.getId());
        System.out.println("((((((((((((((" + newJob.getLocation());
        System.out.println(")))))))))))))" + newJob.getPositionType());
        System.out.println("+==============" + newJob.getCoreCompetency());
        System.out.println(":::::::::::::;" + newJob.getEmployer());
        System.out.println("ID # for it is " + newJob.getId());

        System.out.println("&&&&&&" + jobData.findById(newJob.getId()));


        jobData.add(newJob);

        System.out.println("&&&&&&" + jobData.findById(newJob.getId()));
        model.addAttribute("job", newJob);
        //TODO return to job/id?=
        //return "/job/?id={job.id}";
        //int id = newJob.getId();
        //return "/job?id={id}";
        return "/job-detail";

    }
}
