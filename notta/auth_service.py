import requests
class AuthService: # Classe para cuidar da autenticação e envio do token para navegação em endpoints restritos.
    def __init__(self, base_url):
        self.base_url = base_url
        self.token = None

    def register_user(self, username, email, password):
        data = {
            "username": username,
            "email": email,
            "password": password
        }
        url = f"{self.base_url}/auth/register"
        response = requests.post(url, json=data)
        if response.status_code == 200:
            print("Usuário criado com sucesso. Para ativá-lo, acesse seu email.")
        else:
            print(f"Erro ao criar o Usuário: {response.status_code}")
            print(response.text)

    def login_user(self, login, password):
        data = {
            "user": login,
            "password": password
        }
        url = f"{self.base_url}/auth/login"
        response = requests.post(url, json=data)
        if response.status_code == 200:
            print("User logged in successfully.")
            self.token = response.json().get("token")
            return 1
        else:
            print(f"Erro ao logar o Usuário: {response.status_code}")
            print(response.text)
            return 0

    def logout_user(self):
        self.token = None
        print("User logged out successfully.")

    def get_token(self):
        return self.token