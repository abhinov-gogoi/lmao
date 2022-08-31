package com.netcat.meow.Apps.Notes.noteDTO;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Note {

    public String id;
    public String title;
    public String content;
    public List<Map<String, Object>> tasks;
    public String image;
    public List labels;
    public Boolean archived;
    public String createdAt;
    public String updatedAt;

    public static Note getARandomNote(String title) {
        Task task1 = new Task(UUID.randomUUID().toString(), "Buy a Scarf", false);
        Task task2 = new Task(UUID.randomUUID().toString(), "Feed the Bone", true);
        List taskArrayList = new ArrayList<>();
        taskArrayList.add(task1);
        taskArrayList.add(task2);

        Label label1 = new Label(UUID.randomUUID().toString(), "School");
        Label label2 = new Label(UUID.randomUUID().toString(), "Work");
        ArrayList<Label> labelArrayList = new ArrayList<>();
        labelArrayList.add(label1);
        labelArrayList.add(label2);

        return new Note(
                UUID.randomUUID().toString(),
                title,
                "Demo content",
                taskArrayList,
                null,
                labelArrayList,
                false,
                new Date().toString(),
                new Date().toString());
    }
}
