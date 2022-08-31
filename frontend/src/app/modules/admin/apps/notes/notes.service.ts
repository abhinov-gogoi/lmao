import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, of, throwError } from 'rxjs';
import { map, switchMap, take, tap } from 'rxjs/operators';
import { Label, Note } from 'app/modules/admin/apps/notes/notes.types';
import { cloneDeep } from 'lodash-es';
import * as uuid from 'uuid';

@Injectable({
    providedIn: 'root'
})
export class NotesService {
    // Private
    private _labels: BehaviorSubject<Label[] | null> = new BehaviorSubject(null);
    private _note: BehaviorSubject<Note | null> = new BehaviorSubject(null);
    private _notes: BehaviorSubject<Note[] | null> = new BehaviorSubject(null);

    /**
     * Constructor
     */
    constructor(private _httpClient: HttpClient) {
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Accessors
    // -----------------------------------------------------------------------------------------------------

    /**
     * Getter for labels
     */
    get labels$(): Observable<Label[]> {
        return this._labels.asObservable();
    }

    /**
     * Getter for notes
     */
    get notes$(): Observable<Note[]> {
        return this._notes.asObservable();
    }

    /**
     * Getter for note
     */
    get note$(): Observable<Note> {
        return this._note.asObservable();
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Get labels
     */
    getLabels(): Observable<Label[]> {
        let options = {
            headers: this.getHttpHeaders()
        };
        return this._httpClient.get<Label[]>('https://lmao-backend.herokuapp.com/api/v1/apps/notes/labels', options).pipe(
            tap((response: Label[]) => {
                this._labels.next(response);
            })
        );
    }

    /**
     * Add label
     *
     * @param title
     */
    addLabel(title: string): Observable<Label[]> {
        let options = {
            headers: this.getHttpHeaders()
        };
        return this._httpClient.post<Label[]>('https://lmao-backend.herokuapp.com/api/v1/apps/notes/labels', { title }, options).pipe(
            tap((labels) => {
                console.log(labels)
                // Update the labels
                this._labels.next(labels);
                console.log("end")

            })
        );
    }

    /**
     * Update label
     *
     * @param label
     */
    updateLabel(label: Label): Observable<Label[]> {
        let options = {
            headers: this.getHttpHeaders()
        };
        return this._httpClient.patch<Label[]>('https://lmao-backend.herokuapp.com/api/v1/apps/notes/labels', { label }, options).pipe(
            tap((labels) => {

                // Update the notes
                this.getNotes().subscribe();

                // Update the labels
                this._labels.next(labels);
            })
        );
    }

    /**
     * Delete a label
     *
     * @param id
     */
    deleteLabel(id: string): Observable<Label[]> {
        return this._httpClient.delete<Label[]>('https://lmao-backend.herokuapp.com/api/v1/apps/notes/labels', {
            params: {
                id,
                username: btoa(localStorage.getItem('username')),
                accessToken: btoa(localStorage.getItem('accessToken'))
            }
        }).pipe(
            tap((labels) => {
                if (labels === null) {
                    alert("Label is used. Can't delete an active label")
                    return new Observable();
                }

                // Update the notes
                this.getNotes().subscribe();

                // Update the labels
                this._labels.next(labels);
            })
        );
    }

    /**
     * Get notes
     */
    getNotes(): Observable<Note[]> {
        let options = {
            headers: this.getHttpHeaders()
        };
        return this._httpClient.get<Note[]>('https://lmao-backend.herokuapp.com/api/v1/apps/notes/all', options).pipe(
            tap((response: Note[]) => {
                console.log(response)
                this._notes.next(response);
            })
        );
    }

    /**
     * Get note by id
     */
    getNoteById(id: string): Observable<Note> {
        return this._notes.pipe(
            take(1),
            map((notes) => {

                // Find within the folders and files
                const note = notes.find(value => value.id === id) || null;

                // Update the note
                this._note.next(note);

                // Return the note
                return note;
            }),
            switchMap((note) => {

                if (!note) {
                    return throwError('Could not find the note with id of ' + id + '!');
                }

                return of(note);
            })
        );
    }

    /**
     * Add task to the given note
     *
     * @param note
     * @param task
     */
    addTask(note: Note, task: string): Observable<Note> {
        let options = {
            headers: this.getHttpHeaders()
        };
        return this._httpClient.post<Note>('https://lmao-backend.herokuapp.com/api/v1/apps/notes/tasks', {
            note,
            task
        }, options).pipe(switchMap(() => this.getNotes().pipe(
            switchMap(() => this.getNoteById(note.id))
        )));
    }

    /**
     * Create note
     *
     * @param note
     */
    createNote(note: Note): Observable<Note> {

        let options = {
            headers: this.getHttpHeaders()
        };

        // Add an id
        note.id = uuid.v4();
        console.log(JSON.stringify(note));

        return this._httpClient.post<any>('https://lmao-backend.herokuapp.com/api/v1/apps/notes', { note }, options).pipe(
            tap(response => {
                console.log('Tap :: ' + JSON.stringify(response))
                if (response.STATUS === 'Error') {
                    alert(response.MESSAGE);
                } else if (response.STATUS === 'Success') {
                    console.log(response.MESSAGE)
                } else {
                    alert("Something went wrong")
                }
            }),
            switchMap(response => this.getNotes().pipe(
                switchMap(() => this.getNoteById(response.id).pipe(
                    map(() => response)
                ))
            )));
    }

    /**
     * Update the note
     *
     * @param note
     */
    updateNote(note: Note): Observable<Note> {

        let options = {
            headers: this.getHttpHeaders()
        };

        if (!note.tasks === null || !note.tasks === undefined) {
            note.tasks.forEach(task => {
                if (task.id === null || task.id === undefined || task.id === '') {
                    console.log("taskId is null.. return")
                    return null;
                }
            })
        }

        console.log(JSON.stringify(note))
        // Clone the note to prevent accidental reference based updates
        const updatedNote = cloneDeep(note) as any;

        // Before sending the note to the server, handle the labels
        if (updatedNote.labels.length) {
            updatedNote.labels = updatedNote.labels;
        }

        return this._httpClient.patch<Note>('https://lmao-backend.herokuapp.com/api/v1/apps/notes', { updatedNote }, options).pipe(
            tap((response) => {
                console.log(response)
                // Update the notes
                this.getNotes().subscribe();
            })
        );
    }

    /**
     * Delete the note
     *
     * @param note
     */
    deleteNote(note: Note): Observable<boolean> {
        return this._httpClient.delete<boolean>('https://lmao-backend.herokuapp.com/api/v1/apps/notes',
            {
                params:
                {
                    id: note.id,
                    username: btoa(localStorage.getItem('username')),
                    accessToken: btoa(localStorage.getItem('accessToken'))
                }
            }).pipe(
                map((isDeleted: boolean) => {

                    // Update the notes
                    this.getNotes().subscribe();

                    // Return the deleted status
                    return isDeleted;
                })
            );
    }

    getHttpHeaders() {
        // set the headers
        return new HttpHeaders()
            .set('Content-Type', 'application/json')
            .set('Cache-Control', 'no-cache')
            .set('accessToken', btoa(localStorage.getItem('accessToken')))
            .set('username', btoa(localStorage.getItem('username')));
    }
}


