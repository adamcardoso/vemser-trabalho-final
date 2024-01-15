<h1 align="center" style="color: 
#2ea06a;"> NOTIFICA </h1>
<img src="https://notifica-nine.vercel.app/assets/head-img-peoples.png" />
<p align="center">
  O NOTIFICA é um aplicativo que permite aos usuários relatar problemas no abastecimento de água e saneamento básico. Com recursos como localização geográfica, anexação de imagens e acompanhamento em tempo real, o app capacita os cidadãos a contribuírem para a resolução rápida.<br/>
</p>

# TIME NOTIFICA

## Application:

- Main

## Config:

- ConexaoBancoDeDados

## Exceptions:

- DataBaseException
- MaxAttemptsExceedeException

## Helpers:

- CadstroUsuarioHelper
- CadastroDenunciaHelper
- ConversosDateHelper

## Models:

- Usuário
- Denúncia
- Localização

## Enums:

- Categoria
- Situacao
- Etnia
- Genero
- Classe Social
- TipoUsuario
- TipoDenuncia

## Repositories.impl:

- AdminRepositoryImpl
- DenunciaRepositoryImpl
- EstatisticaRepositoryImpl
- UsuarioRepositoryImpl

## Repository.interfaces:

- AdminRepository
- DenunciaRepository
- EstatisticaRepository
- UsuarioRepository

## Services.impl:

- AdminServiceImpl
- DenunciaServiceImpl
- EstatisticaServiceImpl
- UsuarioServiceImpl
- HomeServiceImpl

## Services.interfaces:

- AdminService
- DenunciaService
- EstatisticaService
- UsuarioService
- HomeService

## Views:

- Estatistica
- Home

## Diagrama Link

Explore nosso diagrama clicando [aqui](https://lucid.app/lucidchart/5adf4b52-d0bf-42fa-be20-9cf1f0ab1121/edit?view_items=URV0b4~lnq6Z&invitationId=inv_7c44ca34-3e04-49e1-b59e-bd1d6714659d)!

## Diagrama ER Link

Explore nosso diagrama ER clicando [aqui](https://miro.com/app/board/uXjVN7EHcdY=/?share_link_id=613380518636)!

## Branches

- main
- default
- fix/CorrigindoClasses
- fix/CorrigindoBugExcluirDenuncia
- fix/CorrigindoBugDeListarDenuncias
- fix/CadastrarDenuncia
- fix/BancoDeDados
- feat/readme
- feat/enumEtnia
- feat/diagrama
- feat/atualiza-CRUD-Denuncia
- feat/VizualizarDenunciaUsuario
- feat/Usuario
- feat/ExcluirVisualizarDados
- feat/Excluir-ExcluirDenuncia
- feat/Exceptions
- feat/EstatisticaUsuario
- feat/EnumsCategoriaSituacao
- feat/EnumGeneros
- feat/EnumClasseSocial
- feat/EditarUsuarioRepositoryService
- feat/EditarDenunciaServiceRepository
- feat/Editar-Usuario
- feat/EditandoPerfilAdmin
- feat/DiagramaER

## Nossa Equipe:

Esse projeto foi desenvolvido pelos seguintes integrantes:

<div style="display:flex; justify-content:center; gap:4px;">
  <div>
  <img width="150px" style="border-radius: 3px;" src="https://avatars.githubusercontent.com/u/45438661?v=4">
  <p>Adam Cardoso</p>
  </div>
  <div>
   <img width="150px" style="border-radius: 3px;" src="https://avatars.githubusercontent.com/u/83096934?v=4">
    <p>Renata Schäfer</p>
  </div>
  <div>
   <img width="150px" style="border-radius: 3px;" src="https://avatars.githubusercontent.com/u/78749443?v=4">
    <p>Anderson Luiz</p>
  </div>
  <div>
   <img width="150px" style="border-radius: 3px;" src="https://avatars.githubusercontent.com/u/66953194?v=4">
    <p>André Miranda</p>
  </div>
  <div>
   <img width="150px" style="border-radius: 3px;" src="https://avatars.githubusercontent.com/u/118921604?v=4">
    <p>Assucena Araújo</p>
  </div>
  <div>
   <img width="150px" style="border-radius: 3px;" src="https://avatars.githubusercontent.com/u/47366440?v=4">
    <p>Wesley Alencar</p>
  </div>
  <div>
   <img width="150px" style="border-radius: 3px;" src="https://avatars.githubusercontent.com/u/132415449?v=4">
    <p>Lorenzo Panato</p>
  </div>
  <div>
   <img width="150px" style="border-radius: 3px;" src="https://avatars.githubusercontent.com/u/91156133?v=4">
    <p>Kevin Matos</p>
  </div>
</div>
