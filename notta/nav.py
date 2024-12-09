from  auth_service import AuthService
from datetime import datetime
from notes_service import NotesService
from notes import Notes

base_url = "http://localhost:8080/api/v1"
request = AuthService(base_url)

def navigation_menu(): # menu de navegação
    opcao = -99
    while opcao != 3:
        print("""𝑵𝒐𝒕𝒕𝒂 𝑨𝒑𝒑""")
        print("\nBem-vindo ao Notta App")
        print("1. Registrar")
        print("2. Login")
        print("3. Sair")
        opcao = int(input("Escolha uma opção: "))
        if opcao == 1:
            register_menu()
        elif opcao == 2:
            login_menu()
        elif opcao == 3:
            print("Saindo...")
        else:
            print("Opção inválida. Tente novamente.")

def login_menu():
    print("""====== Entrar no Notta ========""")
    user = input("Email / Username: ")
    password = input("Senha: ")
    logged = request.login_user(user, password)
    if logged==1:
        navigation_menu_logged()
    print("""===========================""")

def register_menu():
    username = input("Enter username: ")
    password = input("Enter password: ")
    email = input("Enter email: ")

    request.register_user(username, email, password)

def navigation_menu_logged():
    print("""𝑵𝒐𝒕𝒕𝒂 𝑨𝒑𝒑""")
    print("1. Criar Nota")
    print("2. Minhas Notas")
    print("3. Logout")
    opcao = int(input("Escolha uma opção: "))

    if opcao == 1:
        navigation_create_note()
    elif opcao == 2:
        navigation_my_notes()
    elif opcao == 3:
        print("Saindo...")
        request.logout_user()
    else:
        print("Opção inválida. Tente novamente.")
        navigation_menu_logged()

def navigation_my_notes():
    print("\n********Minhas Notas********")
    print("1. Listar Notas")
    print("2. Voltar")

    opcao = int(input("Escolha uma opção: "))
    if opcao == 1:
        navigation_list_notes()
    elif opcao == 2:
        navigation_menu_logged()

def navigation_list_notes():
    print("\n********Listar Notas********")
    notes_service = NotesService(base_url)
    notes = notes_service.get_notes(request.get_token())
    notas_index = {index: note for index, note in enumerate(notes, start=1)}
    for index,note in notas_index.items():
        print(f"{index} ID: {note.id} - Título: {note.title} - Data de Criação: {note.creation}")
    print("********************************************************")
    opcao = (input("Escolha uma  nota ou x para voltar."))
    if opcao == 'x':
        navigation_my_notes()
    else:
        note = notas_index.get(int(opcao))
        print(f"ID: {note.id} - Título: {note.title} - Data de Criação: {note.creation} - \nConteúdo: \n{note.content}")
        print("Ações: \n1. Editar Nota\n2. Deletar Nota\n3. Voltar")
        opcao = int(input("Escolha uma opção: "))
        if opcao == 1:
            navigation_edit_notes(note)
        elif opcao == 2:
            navigation_delete_notes(note)
        elif opcao == 3:
            navigation_my_notes()
        else:
            print("Opção inválida.")

def navigation_edit_notes(note):
    print("\n********Editar Nota********")
    print(f"ID: {note.id} - Título: {note.title} - Data de Criação: {note.creation} - Conteúdo: {note.content}")
    titulo = input("Digite o novo título da nota: ")
    content = input("Digite o novo conteúdo da nota: ")
    char = input("Deseja salvar a nota? s/n")
    if char == "s":
        note.title = titulo
        note.content = content
        note.lastUpdate = datetime.now()
        notes_service = NotesService(base_url)
        notes_service.update_note(note, request.get_token())
        navigation_my_notes()
    else:
        print("Nota não salva.")
        navigation_menu_logged()

def navigation_create_note():
    print("\n********Criar Nota********")
    titulo = input("Digite o título da nota: ")
    print(f"Nota: {titulo}")
    conteudo = input("Digite o conteúdo da nota: \n")
    print(f"Conteúdo: {conteudo}")
    char = input("Deseja salvar a nota? s/n")

    if char == "s":
        favorite = input("Deseja favoritar a nota? s/n")
        if favorite == "s":
            favorite = True
        else:
            favorite = False
        nota = Notes(None, titulo, conteudo, datetime.now(), None, favorite)
        notes_service = NotesService(base_url)
        notes_service.add_note(nota, request.get_token())
        print(f"Nota salva {titulo}  salva com sucesso.")
    else:
        print("Nota não salva.")
        navigation_menu_logged()

def navigation_delete_notes(note):
    confirm = input("Deseja deletar a nota? s/n")
    if confirm == "s":
        notes_service = NotesService(base_url)
        notes_service.delete_note_by_id(note, request.get_token())
        print("Nota deletada com sucesso.")
        navigation_my_notes()
    else:
        print("Nota não deletada.")
        navigation_my_notes()
