package com.netcat.meow.Apps.Notes;

import com.netcat.meow.Apps.Notes.noteDTO.Label;
import com.netcat.meow.Apps.Notes.noteDTO.Note;
import com.netcat.meow.Apps.Notes.noteDTO.Task;
import com.netcat.meow.Service.TokenService;
import com.netcat.meow.Utility.Literal;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Constructor;
import java.util.*;

public class NotesService {
    /**
     * Hold the notes DAO
     */
    private NotesMongoDao dao_mongo;
    /**
     * Hold the user service
     */
    private static NotesService instance;
    /**
     * Hold the token service
     */
    private TokenService tokenservice;


    public static NotesService getInstance() {
        /**
         * Check for the Null
         */
        if (instance == null) {
            instance = new NotesService();
        }
        return instance;
    }

    /**
     * {@link Constructor}
     */
    public NotesService() {
        dao_mongo = NotesMongoDao.getInstance();
        tokenservice = TokenService.getInstance();
    }

    public boolean createNote(Map<String, Object> req_note) {
        Document note  = new Document();

        note.put(Literal._id, req_note.get(Literal.id));
        note.put(Literal.title, req_note.get(Literal.title));
        note.put(Literal.content, req_note.get(Literal.content));
        note.put(Literal.tasks, new ArrayList<>());
        note.put(Literal.image, null);
        note.put(Literal.labels, new ArrayList<>());
        note.put(Literal.archived, false);
        note.put(Literal.createdAt, new Date());
        note.put(Literal.updatedAt, null);
        note.put(Literal.login_id, req_note.get(Literal.login_id));

        return dao_mongo.save(note);
    }

    public List<Document> getNotes(String login_id) {
        return dao_mongo.getNotes(login_id);
    }

    public boolean deleteNote(String id) {
        return dao_mongo.deleteNote(id);
    }

    public boolean updateNote(Map<String, Object> note) {
        try {
            List<Map<String, Object>> tasks = new ArrayList<>();
            if(note.get("tasks") != null) {
                tasks = (List<Map<String, Object>>) note.get("tasks");
                for (Map m : tasks) {
                    if (m.get("id")==null) {
                        return false;
                    }
                    m.put("_id", m.get("id"));
                    m.remove("id");
                }
            }
            List<Map<String, Object>> labels = new ArrayList<>();
            if(note.get("labels") != null) {
                labels = (List<Map<String, Object>>) note.get("labels");
                for (Map m : labels) {
                    if (m.get("id")==null) {
                        return false;
                    }
                    m.put("_id", m.get("id"));
                    m.remove("id");
                    m.remove("title");
                }
            }

            Update update = new Update();
            update.set("title", note.get("title"));
            update.set("content", note.get("content"));
            update.set("tasks", tasks);
            update.set("image", note.get("image"));
            update.set("labels", labels);
            update.set("archived", note.get("archived"));
            update.set("createdAt", note.get("createdAt"));
            update.set("updatedAt", new Date().toString());
            return dao_mongo.upsert(note.get("id").toString(), update);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong while mapping note to update");
            return false;
        }
    }

    public boolean addTask(String _id, String req_task) {
        try {
            Note note = dao_mongo.getNote(_id);
            if(note == null || note.id ==null || _id == null) {
                return false;
            }
            Task task = new Task(UUID.randomUUID().toString(), req_task, false);
            List tasks = note.getTasks();
            if(tasks == null) {
                tasks = new ArrayList<Task>();
            }
            tasks.add(task);

            Update update = new Update();
            update.set("tasks", tasks);

            return dao_mongo.upsert(_id, update);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong while mapping note to update");
            return false;
        }
    }

    public boolean addLabel(Label label, String login_id) {
        try {
            return dao_mongo.save(label, login_id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong while mapping note to update");
            return false;
        }
    }

    public List<Label> getLabels(String login_id) {
        return dao_mongo.getLabels(login_id);
    }

    public boolean updateLabel(Map<String, Object> req_label) {
        try {
            Update update = new Update();
            update.set("title", req_label.get("title"));
            // update in notes_label col
            dao_mongo.upsertLabel(req_label.get("id").toString(), update);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong while mapping label to update");
            return false;
        }
    }

    public boolean deleteLabel(String id) {
        return dao_mongo.deleteLabel(id);
    }

    public boolean labelFoundInNotes(String id) {
        return dao_mongo.labelFoundInNotes(id);
    }
}
