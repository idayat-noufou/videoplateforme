package com.ynov.webfullstack.videoplateforme;

import com.ynov.webfullstack.videoplateforme.Tag.Tag;
import com.ynov.webfullstack.videoplateforme.Tag.TagRepository;
import com.ynov.webfullstack.videoplateforme.Video.Video;
import com.ynov.webfullstack.videoplateforme.Video.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(TagRepository tagRepo, VideoRepository videoRepo) {
        return args -> {

            Tag tag1 = new Tag("education");
            tagRepo.save(tag1);

            Tag tag2 = new Tag("drôle");
            tagRepo.save(tag2);

            Tag tag3 = new Tag("informatique");
            tagRepo.save(tag3);

            Tag tag4 = new Tag("musique");
            tagRepo.save(tag4);

            Tag tag5 = new Tag("supprimer");
            tagRepo.save(tag5);

            Video video1 = new Video("Bienvenue chez les ch'tis","une courte desc","une très longue desc");
            videoRepo.save(video1);

            Video video2 = new Video("AU NOM DE LA TERRE","une courte desc","une très longue desc");
            List<Tag> tags = new ArrayList<>();
            tags.add(tag2);
            video2.setTags(tags);
            videoRepo.save(video2);

            Video video3 = new Video("Comment je fais mes dessins ?","une courte desc","une très longue desc");
            videoRepo.save(video3);

            Video video4 = new Video("Un jour, une question : Pourquoi les chats ronronnent ?","une courte desc","une très longue desc");
            videoRepo.save(video4);

        };
    }
}
