package com.ramesh.springsecuritydemo.controller;

import com.ramesh.springsecuritydemo.model.Gender;
import com.ramesh.springsecuritydemo.model.Teacher;
import com.ramesh.springsecuritydemo.repository.ClassesRepository;
import com.ramesh.springsecuritydemo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("teachers")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @GetMapping
    public String displayAllTeachers(Model model) {
        model.addAttribute("title", "All Teachers List");
        model.addAttribute("teachers", teacherRepository.findAll());
        return "teacher/teacher-page";
    }

    @GetMapping("newTeacherForm")
    public String displayAddTeacherForm(Model model) {
        model.addAttribute("title", "Add Teacher");
        model.addAttribute(new Teacher());
        model.addAttribute("genders", Gender.values());
        model.addAttribute("classesToTeach", classesRepository.findAll());
        return "teacher/newTeacherForm";
    }

    @PostMapping("newTeacherForm")
    public String processTeacherForm(@ModelAttribute @Valid Teacher teacher,
                                     Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Teacher");
            model.addAttribute("genders", Gender.values());
            model.addAttribute("classesToTeach", classesRepository.findAll());
            return "teacher/newTeacherForm";
        }
        teacherRepository.save(teacher);
        return "redirect:";
    }

    @GetMapping("showFormForUpdate/{id}")
    public String displayTeacherEditForm(Model model, @PathVariable(value = "id") Integer id) {
        Optional<Teacher> result = teacherRepository.findById(id);
        Teacher editTeacher = result.get();
//        model.addAttribute("title","Update  Teacher  where  ID = "+id +" name is "+ editTeacher.getPersonalDetails().getFirstName()) ;
        model.addAttribute("title", "Update Teacher Details ");
        model.addAttribute("teacher", editTeacher);
        model.addAttribute("genders", Gender.values());
        model.addAttribute("classesToTeach", classesRepository.findAll());
        return "teacher/edit-teacher-page";
    }

    //    @PostMapping("showFormForUpdate")
//    public String processTeacherEditedForm(@RequestParam(required = false) Integer id,@ModelAttribute Teacher editTeacher,RedirectAttributes redirectAttributes) {
//
//        teacherRepository.save(editTeacher);
////        redirectAttributes.addFlashAttribute("message", "updated the Teacher record Success fully !!");
////        model.addAttribute("message","updated the Teacher record Success fully !!");
//
//        return "redirect:";
//    }
    @GetMapping("/deleteTeacher/{id}")
    public String deleteTeacherById(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        teacherRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Deleted the Teacher record Success fully !!");
        return "redirect:/teachers";
    }

    @GetMapping("detail")
    public String displayTeacherDetails(@RequestParam Integer teacherId, Model model) {

        Optional<Teacher> result = teacherRepository.findById(teacherId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: " + teacherId);
        } else {
            Teacher teacherDetail = result.get();
            model.addAttribute("title", teacherDetail.getPersonalDetails().getFirstName() + " Details");
            model.addAttribute("teacher", teacherDetail);
        }
        return "teacher/teacher-details";
    }

}
