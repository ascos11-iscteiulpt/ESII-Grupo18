# ESII-Grupo18

Projeto da licenciatura de Informática e Gestão de Empresas, para a Unidade Curricular de Engenharia de Software II. Este *software* permite a execução das tarefas de consulta, inserção, atualização e remoção especificadas para os investigadores e administradores de uma base de dados para gestão, manutenção e auditoria de uma estufa.

## Instalação

Não há necessidade de fazer uma instalação, basta ter o Java instalado na máquina, transferir e executar os ficheiros MainInvestigador.jar e MainAdministrador.jar.

## Utilização

Para executar efetivamente este *software* é necessário que estejam presentes todas as componentes que o complementam. Estas são:
- MySQL
- MongoDB
- xampp em execução com o serviço Apache a escutar o porto 8080
- Base de Dados principal MySQL (chamada 'dba') associada ao xampp
- Base de Dados de *logs* MySQL (chamada 'log') associada ao xampp
- Base de Dados de transição MongoDB

Estas componentes estão preparadas na máquina virtual fornecida na entrega final do projeto.
