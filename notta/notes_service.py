import requests
from datetime import datetime
from notes import Notes
class NotesService: # classe para a comunicação com a api.
    def __init__(self, base_url):
        self.base_url = base_url

    def add_note(self, note, token):
        url = f"{self.base_url}/notes/create"
        headers = {
            "Authorization": f"Bearer {token}"
        }
        data = {
            "title": note.title,
            "content": note.content,
            "creation": note.creation.strftime("%Y-%m-%d ") if isinstance(note.creation, datetime) else note.creation,
            "lastUpdate": note.lastUpdate.strftime("%Y-%m-%d ") if isinstance(note.lastUpdate, datetime) else note.lastUpdate,
            "favorite": note.favorite
        }

        response = requests.post(url, json=data, headers=headers)
        if response.status_code == 200:
            print("Nota criada com sucesso.")
        else:
            print(f"Erro ao criar a Nota: {response.status_code}")
            print(response.text)

    def get_notes(self, token):
        url = f"{self.base_url}/notes/list"
        notes = []
        headers = {
            "Authorization": f"Bearer {token}"
        }
        response = requests.get(url, headers=headers)
        if response.status_code == 200:
            for listed_note in response.json():
                note = Notes(listed_note.get("id"), listed_note.get("title"), listed_note.get("content"),
                             listed_note.get("creation"), listed_note.get("lastUpdate"), listed_note.get("favorite"))
                notes.append(note)
            return notes
        else:
            print(f"Erro ao listar as Notas: {response.status_code}")
            print(response.text)
        return []

    #def get_note_by_favorite(self, token): não implementada.


    def update_note(self, note, token):
        url = f"{self.base_url}/notes/update"
        body = {
            "id": note.id,
            "title": note.title,
            "content": note.content,
            "creation": note.creation.strftime("%Y-%m-%d ") if isinstance(note.creation, datetime) else note.creation,
            "lastUpdate": note.lastUpdate.strftime("%Y-%m-%d ") if isinstance(note.lastUpdate,datetime) else note.lastUpdate,
            "favorite": note.favorite
        }
        headers = {
            "Authorization": f"Bearer {token}"
        }
        response = requests.put(url, json=body, headers=headers)
        if response.status_code == 200:
            print("Nota atualizada com sucesso.")
        else:
            print(f"Erro ao atualizar a Nota: {response.status_code}")
            print(response.text)
    def delete_note_by_id(self, note, token):
        header = {
            "Authorization": f"Bearer {token}"
        }
        url = f"{self.base_url}/notes/delete/{note.id}"
        response = requests.delete(url, headers=header)
        if response == 204:
            print("Nota deletada com sucesso.")
        else:
            print(f"Erro ao deletar a Nota: {response.status_code}")
            print(response.text)