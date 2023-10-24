package com.jyong.springboot.controller;

import com.jyong.springboot.designpattern.factory.ExecuteProcess;
import com.jyong.springboot.designpattern.strategy.ProcessMain;
import com.jyong.springboot.entity.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/somnambulist/launcher")
public class LauncherController {


    @Autowired
    private ProcessMain processMain;


    @Autowired
    private ExecuteProcess executeProcess;


    @GetMapping("/app.run")
    public ResponseEntity<String> app(){
        return ResponseEntity.ok("<h1> app is running ...... </h1>");
    }

    @GetMapping("/executeProcess.run")
    public void processMain(@RequestParam String type){
        executeProcess.process(type);
    }

    @GetMapping("/processMain.run")
    public void processFactory(@RequestParam String type){
        processMain.process(type);
    }


    @PostMapping("/test/model")
    public ResponseEntity app(@RequestBody StudentModel studentModel){

        System.out.println("---参数校验----");
        String s = studentModel.toString();
        System.out.println(s);

        return ResponseEntity.ok(s);

    }
    @GetMapping("/app/processMainDo")
    public void processMainDo(String type){
        processMain.process(type);
    }


}
