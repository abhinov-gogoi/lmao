package com.netcat.meow.Apps.Notes;

import com.netcat.meow.Apps.Notes.noteDTO.Label;
import com.netcat.meow.Apps.Notes.noteDTO.Note;
import com.netcat.meow.Service.TokenService;
import com.netcat.meow.Utility.Literal;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * This module is used for notes app.
 */

@RestController
@CrossOrigin("*")
public class NotesController {

    Logger logger = LoggerFactory.getLogger(NotesController.class);

    private NotesService notesService;
    @Autowired
    private HttpServletRequest request;

    public NotesController() {
        notesService = NotesService.getInstance();
    }

    @RequestMapping(value = "api/v1/apps/notes/test", method = RequestMethod.GET, headers = Literal.APPLICATION_JSON)
    public boolean notesTest() {
        logger.info("notes test api called");
        return true;
    }

    @RequestMapping(value = "api/v1/apps/notes", method = RequestMethod.POST, headers = Literal.APPLICATION_JSON)
    public Map<String, Object> createNote(@RequestBody Map<String, Object> req_map) {
        String accessToken = new String(Base64.getDecoder().decode(request.getHeader(Literal.accessToken)));
        String login_id = new String(Base64.getDecoder().decode(request.getHeader(Literal.username)));
        if(!TokenService.getInstance().validateToken(accessToken, login_id)) {
            logger.info(Literal.INVALID_TOKEN + request.getRequestURI());
            return null;
        }
        Map<String, Object> ret_map = new HashMap<>(Literal.SIX);
        Map<String, Object> req_note = (Map<String, Object>) req_map.get("note");
        req_note.put(Literal.login_id, login_id);

        if (notesService.createNote(req_note)) {
            ret_map.put(Literal.STATUS, Literal.SUCCESS);
            ret_map.put(Literal.MESSAGE, Literal.DATA_UPDATED);
            ret_map.put(Literal.id, req_note.get(Literal.id));
            return ret_map;
        }
        ret_map.put(Literal.STATUS, Literal.ERROR);
        ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
        ret_map.put(Literal.REQUEST_DATA, req_map);
        return ret_map;
    }

    @RequestMapping(value = "api/v1/apps/notes", method = RequestMethod.DELETE, headers = Literal.APPLICATION_JSON)
    public Map<String, Object> deleteNote(@RequestParam String id, String accessToken, String username) {
        logger.info("deleteNote id :: " + id);

        String accessToken_decoded = new String(Base64.getDecoder().decode(accessToken));
        String login_id_decoded = new String(Base64.getDecoder().decode(username));
        if(!TokenService.getInstance().validateToken(accessToken_decoded, login_id_decoded)) {
            logger.info("token validation failed" + request.getRequestURI());
            return null;
        }

        Map<String, Object> ret_map = new HashMap<>(Literal.SIX);
        ret_map.put("isDeleted", notesService.deleteNote(id));
        return ret_map;
    }

    @RequestMapping(value = "api/v1/apps/notes", method = RequestMethod.PATCH, headers = Literal.APPLICATION_JSON)
    public Map<String, Object> updateNote(@RequestBody Map<String, Object> req_map) {
        logger.info("update note api hit");
        String accessToken = new String(Base64.getDecoder().decode(request.getHeader(Literal.accessToken)));
        String login_id = new String(Base64.getDecoder().decode(request.getHeader(Literal.username)));
        if(!TokenService.getInstance().validateToken(accessToken, login_id)) {
            logger.info(Literal.INVALID_TOKEN + request.getRequestURI());
            return null;
        }

        Map<String, Object> ret_map = new HashMap<>(Literal.SIX);

        Map<String, Object> req_note = (Map<String, Object>) req_map.get("updatedNote");
        System.out.println(req_note);

        if (req_note.get("id") == null) {
            return ret_map;
        }

        if (req_note.get("tasks") != null) {
            List<Map<String, Object>> tasks = (List<Map<String, Object>>) req_note.get("tasks");
            for (Map<String, Object> t : tasks) {
                if (t.get("id") == null) {
                    System.out.println("null task id. return");
                    return ret_map;
                }
            }
        }

        if (notesService.updateNote(req_note)) {
            ret_map.put(Literal.STATUS, Literal.SUCCESS);
            ret_map.put(Literal.MESSAGE, Literal.DATA_UPDATED);
            ret_map.put(Literal.id, req_note.get(Literal.id));
            return ret_map;
        }
        ret_map.put(Literal.STATUS, Literal.ERROR);
        ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
        ret_map.put(Literal.REQUEST_DATA, req_map);
        return ret_map;
    }

    @RequestMapping(value = "api/v1/apps/notes/all", method = RequestMethod.GET, headers = Literal.APPLICATION_JSON)
    public List<Note> getNotes() {
        logger.info("API hit for getNotes " + request.getRemoteUser());
        List<Note> ret_map = new ArrayList<>();

        String accessToken = new String(Base64.getDecoder().decode(request.getHeader(Literal.accessToken)));
        String login_id = new String(Base64.getDecoder().decode(request.getHeader(Literal.username)));
        if(!TokenService.getInstance().validateToken(accessToken, login_id)) {
            logger.info("token validation failed" + request.getRequestURI());
            return ret_map;
        }

        List<Document> notes = notesService.getNotes(login_id);
        List<Label> labels_list = notesService.getLabels(login_id);

        notes.forEach(document -> {
            List<Map<String, Object>> tasks = new ArrayList<>();
            if (document.get("tasks") != null) {
                tasks = (List<Map<String, Object>>) document.get("tasks");
                tasks.forEach(task -> {
                    task.put("id", task.get("_id"));
                    task.remove("_id");
                });
            }

            List<Map<String, Object>> labels = new ArrayList<>();

            if (document.get("labels") != null) {
                labels = (List<Map<String, Object>>) document.get("labels");
                for (Map<String, Object> label_map : labels) {
                    label_map.put("id", label_map.get("_id"));
                    label_map.remove("_id");
                    for (Label l : labels_list) {
                        if (l.id.equalsIgnoreCase(label_map.get("id").toString())) {
                            label_map.put("title", l.getTitle());
                        }
                    }
                }
            }

            Note note = new Note(
                    document.get("_id") == null ? null : document.get("_id").toString(),
                    document.get("title") == null ? null : document.get("title").toString(),
                    document.get("content") == null ? null : document.get("content").toString(),
                    tasks, document.get("image") == null ? null : document.get("image").toString(),
                    labels,
                    document.get("archived") == null ? null : (Boolean) document.get("archived"),
                    document.get("createdAt") == null ? null : document.get("createdAt").toString(),
                    document.get("updatedAt") == null ? null : document.get("updatedAt").toString());
            ret_map.add(note);
        });
        return ret_map;
    }

    @RequestMapping(value = "api/v1/apps/notes/tasks", method = RequestMethod.POST, headers = Literal.APPLICATION_JSON)
    public Map<String, Object> addTask(@RequestBody Map<String, Object> req_map) {
        logger.info("addTask in note api hit");
        Map<String, Object> ret_map = new HashMap<>(Literal.SIX);

        String accessToken = new String(Base64.getDecoder().decode(request.getHeader(Literal.accessToken)));
        String login_id = new String(Base64.getDecoder().decode(request.getHeader(Literal.username)));
        if(!TokenService.getInstance().validateToken(accessToken, login_id)) {
            logger.info("token validation failed" + request.getRequestURI());
            return ret_map;
        }

        Map<String, Object> req_note = (Map<String, Object>) req_map.get("note");
        String task = req_map.get("task").toString();

        if (notesService.addTask(req_note.get(Literal.id).toString(), task)) {
            ret_map.put(Literal.STATUS, Literal.SUCCESS);
            ret_map.put(Literal.MESSAGE, Literal.DATA_UPDATED);
            ret_map.put(Literal.id, req_note.get(Literal.id));
            return ret_map;
        }
        ret_map.put(Literal.STATUS, Literal.ERROR);
        ret_map.put(Literal.MESSAGE, Literal.SOMETHING_WENT_WORNG);
        ret_map.put(Literal.REQUEST_DATA, req_map);
        return ret_map;
    }

    @RequestMapping(value = "api/v1/apps/notes/labels", method = RequestMethod.POST, headers = Literal.APPLICATION_JSON)
    public List<Label> addLabel(@RequestBody Map<String, Object> req_map) {
        logger.info("addLabel note api hit");

        String accessToken = new String(Base64.getDecoder().decode(request.getHeader(Literal.accessToken)));
        String login_id = new String(Base64.getDecoder().decode(request.getHeader(Literal.username)));
        if(!TokenService.getInstance().validateToken(accessToken, login_id)) {
            logger.info("token validation failed" + request.getRequestURI());
            return null;
        }

        String req_label = req_map.get("title").toString();
        Label label = new Label(UUID.randomUUID().toString(), req_label);
        if (notesService.addLabel(label, login_id)) {
            return notesService.getLabels(login_id);
        }
        return null;
    }

    @RequestMapping(value = "api/v1/apps/notes/labels", method = RequestMethod.GET, headers = Literal.APPLICATION_JSON)
    public List<Label> getLabels() {
        String accessToken = new String(Base64.getDecoder().decode(request.getHeader(Literal.accessToken)));
        String login_id = new String(Base64.getDecoder().decode(request.getHeader(Literal.username)));
        if(!TokenService.getInstance().validateToken(accessToken, login_id)) {
            logger.info("token validation failed" + request.getRequestURI());
            return null;
        }

        List<Label> ret_map = new ArrayList<>();
        List<Label> labels = notesService.getLabels(login_id);
        labels.forEach(element -> {
            Label label = new Label(element.getId(), element.getTitle());
            ret_map.add(label);
        });
        return ret_map;
    }

    @RequestMapping(value = "api/v1/apps/notes/labels", method = RequestMethod.PATCH, headers = Literal.APPLICATION_JSON)
    public List<Label> updateLabel(@RequestBody Map<String, Object> req_map) {
        String accessToken = new String(Base64.getDecoder().decode(request.getHeader(Literal.accessToken)));
        String login_id = new String(Base64.getDecoder().decode(request.getHeader(Literal.username)));
        if(!TokenService.getInstance().validateToken(accessToken, login_id)) {
            logger.info("token validation failed" + request.getRequestURI());
            return null;
        }
        Map<String, Object> req_label = (Map<String, Object>) req_map.get("label");
        // only possible if token is valid
        notesService.updateLabel(req_label);
        return notesService.getLabels(login_id);
    }

    @RequestMapping(value = "api/v1/apps/notes/labels", method = RequestMethod.DELETE, headers = Literal.APPLICATION_JSON)
    public List<Label> deleteLabel(@RequestParam String id, String accessToken, String username) {
        String accessToken_decoded = new String(Base64.getDecoder().decode(accessToken));
        String login_id_decoded = new String(Base64.getDecoder().decode(username));
        if(!TokenService.getInstance().validateToken(accessToken_decoded, login_id_decoded)) {
            logger.info("token validation failed" + request.getRequestURI());
            return null;
        }

        // check if this label is used by any note actively, if found, prevent delete
        if(notesService.labelFoundInNotes(id)) {
            // prevent delete
            logger.info("Label is found in notes. Not able to delete active label");
            return null;
        }

        // only possible if token is valid
        if (notesService.deleteLabel(id)) {
            return notesService.getLabels(login_id_decoded);
        }
        return notesService.getLabels(login_id_decoded);
    }

}
