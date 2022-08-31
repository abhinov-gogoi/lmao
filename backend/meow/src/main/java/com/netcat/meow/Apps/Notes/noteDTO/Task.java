package com.netcat.meow.Apps.Notes.noteDTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {
    public String id;
    public String content;
    public boolean completed;
}
