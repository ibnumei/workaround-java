package com.playground.workaround.controller;

import com.playground.workaround.entity.Department;
import com.playground.workaround.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.validation.Valid;

import java.util.List;

// Annotation
@RestController

// Class
public class DepartmentController {
    private static final Logger logger = LogManager.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    // Save operation
    @PostMapping("/departments")
    public Department saveDepartment(
            @Valid @RequestBody Department department)
    {
        try {
            logger.info("save department with: {}", department);
            return departmentService.saveDepartment(department);
        } catch (Exception e) {
            logger.error("error save department with message:, casuse: ", e.getMessage(), e.getStackTrace());
            throw e;
        }
    }

    // Read operation
    @GetMapping("/departments")
    public List<Department> fetchDepartmentList()
    {
        return departmentService.fetchDepartmentList();
    }

    // Update operation
    @PutMapping("/departments/{id}")
    public Department
    updateDepartment(@RequestBody Department department,
                     @PathVariable("id") Long departmentId)
    {
        return departmentService.updateDepartment(
                department, departmentId);
    }

    // Delete operation
    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id")
                                       Long departmentId)
    {
        departmentService.deleteDepartmentById(
                departmentId);
        return "Deleted Successfully";
    }
}