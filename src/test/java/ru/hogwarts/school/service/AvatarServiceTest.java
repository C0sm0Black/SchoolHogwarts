package ru.hogwarts.school.service;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AvatarService.class})
@ExtendWith(SpringExtension.class)
class AvatarServiceTest {

    @Autowired
    private AvatarService avatarService;

    @MockBean
    private AvatarRepository avatarRepository;

    @MockBean
    private StudentService studentService;

    @Test
    void getAvatarsByPage() {

        //Подготовка входных данных

        int pageNumber = 1;
        int pageSize = 2;

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);

        Avatar avatarOne = new Avatar();
        avatarOne.setId(1l);

        Avatar avatarTwo = new Avatar();
        avatarTwo.setId(2l);

        List<Avatar> avatars = List.of(avatarOne, avatarTwo);

        PageImpl pageable = new PageImpl<>(avatars);

        //Подготовка ожидаемого результата

        when(avatarRepository.findAll(pageRequest)).thenReturn(pageable);

        //Начало теста

        List<Avatar> actualAvatars = avatarService.getAvatarsByPage(pageNumber, pageSize);
        assertEquals(avatars, actualAvatars);
        verify(avatarRepository).findAll(pageRequest);
        verifyNoMoreInteractions(avatarRepository);

    }

}