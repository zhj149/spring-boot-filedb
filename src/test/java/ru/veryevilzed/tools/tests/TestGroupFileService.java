package ru.veryevilzed.tools.tests;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.veryevilzed.tools.dto.IntegerKeyCollection;
import ru.veryevilzed.tools.dto.KeyCollection;
import ru.veryevilzed.tools.dto.LongKeyCollection;
import ru.veryevilzed.tools.repository.FileRepository;

import javax.annotation.PostConstruct;
import java.io.File;

@Service
public class TestGroupFileService extends FileRepository<TextFileEntity> {
    @Override
    protected TextFileEntity createFileEntity(File file) {
        return new TextFileEntity(file);
    }

    @PostConstruct
    @Scheduled(fixedDelay = 60000L)
    public void update() {
        super.update();
    }

    public TestGroupFileService(@Value("${file.path2}") String path) {
        super(path, "(?<group>\\d+)/(?<device>\\d+)?@(?<version>\\d+)?.yml", new KeyCollection[] {
                new LongKeyCollection("group"),
                new LongKeyCollection("device", null),
                new IntegerKeyCollection("version", 0)
        });
    }


}
