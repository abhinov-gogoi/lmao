package com.netcat.meow.Apps.Notes;

import com.mongodb.client.result.UpdateResult;
import com.netcat.meow.Apps.Notes.noteDTO.Label;
import com.netcat.meow.Apps.Notes.noteDTO.Note;
import com.netcat.meow.Utility.Literal;
import com.netcat.meow.Utility.MongoDbConnection;
import com.netcat.meow.Utility.Utility;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotesMongoDao {

    private static NotesMongoDao instance = new NotesMongoDao();
    private MongoTemplate mongoTemplate;

    public NotesMongoDao() {
        mongoTemplate = MongoDbConnection.getInstance().mongoTemplate();
    }

    public static NotesMongoDao getInstance() {
        if (instance == null) {
            instance = new NotesMongoDao();
        }
        return instance;
    }

    public boolean save(Map<String, Object> note) {
        try {
            mongoTemplate.save(note, Utility.col_notes);
            return Literal.TRUE;
        } catch (Exception e) {
            Utility.printStackTrace(e, this.getClass().getName());
            return Literal.FALSE;
        }
    }

    public boolean save(Label label, String login_id) {
        try {
            Document filter_doc = new Document().append(Literal._id, label.id);
            Query qry = new BasicQuery(filter_doc);

            Update update = new Update();
            update.set(Literal.title, label.title);
            update.set(Literal.login_id, login_id);
            UpdateResult upd = mongoTemplate.upsert(qry, update, Utility.col_notes_label);
            return upd.wasAcknowledged();
        } catch (Exception e) {
            Utility.printStackTrace(e, this.getClass().getName());
            return Literal.FALSE;
        }
    }

    public List<Document> getNotes(String login_id) {
        try {
            Document filter_doc = new Document().append(Literal.login_id, login_id);
            return mongoTemplate.find(new BasicQuery(filter_doc), Document.class, Utility.col_notes);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Document>();
        }
    }

    public boolean deleteNote(String id) {
        try {
            Document filter_doc = new Document().append("_id", id);
            BasicQuery query = new BasicQuery(filter_doc);
            return mongoTemplate.remove(query, Utility.col_notes).wasAcknowledged();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean upsert(String _id, Update update) {
        try {
            Document filter_doc = new Document().append(Literal._id, _id);
            Query qry = new BasicQuery(filter_doc);
            /**
             * Update the login data
             */
            UpdateResult upd = mongoTemplate.upsert(qry, update, Utility.col_notes);
            return upd.wasAcknowledged();
        } catch (Exception e) {
            Utility.printStackTrace(e, this.getClass().getName());
            return Literal.FALSE;
        }
    }

    public Note getNote(String id) {
        try {
            Document filter_doc = new Document().append(Literal._id, id);
            Query query = new BasicQuery(filter_doc);
            return mongoTemplate.findOne(query, Note.class, Utility.col_notes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Label> getLabels(String login_id) {
        try {
            Document filter_doc = new Document().append(Literal.login_id, login_id);
            return mongoTemplate.find(new BasicQuery(filter_doc), Label.class, Utility.col_notes_label);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean upsertLabel(String _id, Update update) {
        try {
            Document filter_doc = new Document().append(Literal._id, _id);
            Query qry = new BasicQuery(filter_doc);
            /**
             * Update the login data
             */
            UpdateResult upd = mongoTemplate.upsert(qry, update, Utility.col_notes_label);
            return upd.wasAcknowledged();
        } catch (Exception e) {
            Utility.printStackTrace(e, this.getClass().getName());
            return Literal.FALSE;
        }
    }

    public boolean deleteLabel(String id) {
        try {
            Document filter_doc = new Document().append("_id", id);
            BasicQuery query = new BasicQuery(filter_doc);

            // todo :: after deleting the label, also delete label from all the notes that implement this label
            // { $pull: { 'labels': { _id: 'd387fcb0-e77e-4463-b406-2a3032738cb4' } } }

            return mongoTemplate.remove(query, Utility.col_notes_label).wasAcknowledged();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getLabelTitle(String id) {
        try {
            Document filter_doc = new Document().append(Literal._id, id);
            String title = mongoTemplate.findOne(new BasicQuery(filter_doc), Label.class, Utility.col_notes_label).title;
            return title;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean labelFoundInNotes(String id) {
        // {labels: {_id : 'd387fcb0-e77e-4463-b406-2a3032738cb4'}}
        Document filter_doc = new Document().append(Literal.labels, new Document().append(Literal._id, id));
        long count = mongoTemplate.count(new BasicQuery(filter_doc), Utility.col_notes);
        if (count > 0) {
            return true;
        } else {
            return false;
        }


    }
}
