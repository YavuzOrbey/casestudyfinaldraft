package com.casestudydraft.webcontroller;

import com.casestudydraft.model.User;
import com.casestudydraft.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Controller
public class FileUploadController {
    @Autowired
    private  Environment env;
    //Save the uploaded file to this folder
    @Autowired
    UserService userService;

    @GetMapping("/upload")
    public String index(@RequestParam("recipe") Integer recipe, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            User user = userService.findByUsername(username);
            System.out.println(user);
        } else {
            String username = principal.toString();
            System.out.println(username);
        }
        model.addAttribute("recipe", recipe);
        return "upload/upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("recipe") String recipe,
                                   RedirectAttributes redirectAttributes) {

        System.out.println(recipe);
        String uploadDirectory = env.getProperty("fileUploadPath");;
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadDirectory + recipe + "."+ FilenameUtils.getExtension(file.getOriginalFilename()));
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + path.getFileName() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "upload/uploadStatus";
    }
}
