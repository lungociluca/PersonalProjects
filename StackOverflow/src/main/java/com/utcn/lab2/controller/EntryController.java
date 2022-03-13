package com.utcn.lab2.controller;


import com.utcn.lab2.model.Entry;
import com.utcn.lab2.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EntryController {

    @Autowired
    private EntryService entryService;

    @RequestMapping(method = RequestMethod.GET, value = "/entry")
    @ResponseBody
    private Entry getEntry(@RequestParam(name = "id") Integer id) {
        return entryService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/update")
    @ResponseBody
    private void update(@RequestParam(name = "id") Integer id, @RequestBody Entry entry) {
        entryService.update(id, entry);
    }
}
