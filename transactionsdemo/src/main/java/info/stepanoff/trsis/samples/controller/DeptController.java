package info.stepanoff.trsis.samples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import info.stepanoff.trsis.samples.service.DeptService;

@Controller
public class DeptController {

    @Autowired
    DeptService deptsService;

    @RequestMapping(value = "/depts/{id}", method = RequestMethod.GET)
    public ModelAndView batches(@PathVariable("id") Integer id) {
        ModelAndView mav = new ModelAndView("depts");
        mav.addObject("depts", deptsService.findBySchool(id));
        return mav;
    }
}
