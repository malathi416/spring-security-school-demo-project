package com.ramesh.springsecuritydemo.controller;

import com.ramesh.springsecuritydemo.model.Classes;
import com.ramesh.springsecuritydemo.model.Student;
import com.ramesh.springsecuritydemo.model.Teacher;
import com.ramesh.springsecuritydemo.repository.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("classes")
public class ClassController {

    @Autowired
    private ClassesRepository classesRepository;

    @GetMapping
    public String displayAllClasses(Model model) {
        model.addAttribute("title", "Classes List");
        model.addAttribute("classes", classesRepository.findAll());
        return "class/class-page";
    }

    @GetMapping("newClassForm")
    public String displayAddClassForm(Model model) {
        model.addAttribute("title", "Add Class");
        model.addAttribute(new Classes());
        return "class/newClassForm";
    }

    @PostMapping("newClassForm")
    public String processCreateTagForm(@ModelAttribute @Valid Classes classes,
                                       Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Class");
            return "class/newClassForm";
        }
        classesRepository.save(classes);
        return "redirect:";
    }

    @GetMapping("showFormForUpdate/{id}")
    public String displayEditForm(Model model, @PathVariable(value = "id") int id) {
        Optional<Classes> result = classesRepository.findById(id);
        Classes editClass = result.get();
        model.addAttribute("title", "Update Class");
        model.addAttribute("classes", editClass);
        return "class/edit-class-page";
    }


    //    @PostMapping("showFormForUpdate")
//    public String processEditForm(@RequestParam(required = false) Integer id,@ModelAttribute Classes editClass,final RedirectAttributes redirectAttributes) {
////        redirectAttributes.addFlashAttribute("message", "updated the class record Success fully !!");
//        classesRepository.save(editClass);
//        return "redirect:";
//    }
    @GetMapping("/deleteClass/{id}")
    public String deleteClassById(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        classesRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Deleted the class record Success fully!! ");
        return "redirect:/classes";
    }

    //fetching data using one-to-many relation from student and teacher classes to classes page
    @GetMapping("detail")
    public String displayStudentList(@RequestParam Integer classId, Model model) {
        Optional<Classes> result = classesRepository.findById(classId);
        Classes fetchedClass = result.get();
        List<Student> studentList = fetchedClass.getStudents();
        List<Teacher> teacherList = fetchedClass.getTeachers();
        model.addAttribute("title", fetchedClass.getClassName() + " Enrolled List");
        if (studentList.isEmpty()) {
            model.addAttribute("title", "No students Registered");
        } else {
            model.addAttribute("title2", "Students");
            model.addAttribute("students", studentList);
        }
        if (teacherList.isEmpty()) {
            model.addAttribute("title1", "No teachers Registered");
        } else {
            model.addAttribute("title1", "Teachers");
            model.addAttribute("teachers", teacherList);
        }
        return "class/student-list";
    }

}
