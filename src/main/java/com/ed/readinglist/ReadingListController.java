package com.ed.readinglist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {

    private ReadingListRepository readingListRepository; 

    public ReadingListController (ReadingListRepository readinglistRepository) {
            this.readingListRepository = readinglistRepository;
        }

     @GetMapping("/{reader}")
     public String readersBooks(
        @PathVariable String reader, Model model){
        List<Book> readingList=readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
     }

     @PostMapping("/{reader}")
     public String addToReadinglist(
        @PathVariable String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
     }
}
