package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RequestMapping("/avatars")
@RestController
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }


    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadAvatar(@PathVariable long id, @RequestParam MultipartFile avatar) throws IOException {

        if (avatar.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }

        avatarService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}/avatar/preview")
    public ResponseEntity downloadAvatar(@PathVariable long id) {

        Avatar avatar = avatarService.findAvatar(id);

        if (avatar.getFilePath() == null) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());

    }

    @GetMapping("/{id}/avatar")
    public ResponseEntity downloadAvatar(@PathVariable long id, HttpServletResponse response) throws IOException {

        Avatar avatar = avatarService.findAvatar(id);

        if (avatar.getFilePath() == null) {
            return ResponseEntity.notFound().build();
        }

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();
        ) {

            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());

            is.transferTo(os);

        }

        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity getAvatarsByPage(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {

        List<Avatar> avatars = avatarService.getAvatarsByPage(pageNumber, pageSize);

        if (avatars.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(avatars);

    }

}
