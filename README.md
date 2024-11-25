# apps4ev-byd-demo

- autor: Alessandre Haua
- contato: https://www.linkedin.com/in/ahaua/

## Introdução
Importante mencionar que o código fonte aqui disponibilizado faz parte de um **estudo inicial** para desenvolvimento de aplicativos específicos para carros eletrificados. Entenda-se como **estudo inicial**, um processo ordenado de aprendizado, baseado em experimentos de desenvolvimento e apoiado unicamente pela documentação do fabricante, disponibilizada atualmente apenas em **chinês simplificado**.

A necessidade desse estudo é relativa a pouca quantidade de material encontrado sobre o assunto na Internet, seja em português ou qualquer outro idioma. Embora a fabricante celebre 30 anos de mercado, a decição de abertura da plataforma e disponibilização do SDK é recente.

Vale lembrar que tais estudos e experimentos devem ser conduzidos com cuidado, por um profissional ou desenvolvedor experiente, seguindo todas as etapas do ciclo de desenvolvimento para este tipo de produto. Tais etapas compreendem minimamente: estudo, planejamento, desenvolvimento, testes unitários, refinamento, implantação e teste final. Recomendo que a etapa de refinamento seja um retorno à todas as etapas anteriores, sendo executada de forma recorrente, multiplas vezes, até que o resultado dos testes unitários sejam satisfatórios. Além disso, recomendamos que os testes unitários sejam conduzimos em ambiente simulado, utilizando o próprio software simulador disponibilizado pela fabricante, antes da implantação do aplicativo compilado no veículo para testes finais.

Devemos seguir rigorosamente todas as instruções e recomendações fornecidas pelo fabricante, além de mantermos todos os cuidados já citados sempre preservados. Lembre-se que temos como plataforma de destino final para o nosso desenvolvimento, um patrimônio de centenas de milhares de reais.

> 
> ### Aviso
> Tão importante quanto, é sabermos que o código aqui disponibilizado, bem como o conjunto de instruções aqui documentadas, só deve ser implementado, compilado e implantado em seu veículo, e por sua única e própria responsabilidade. Ao decidir continuar, você assume todos e quaisquer riscos que invalidem a garantia do veículo ou danifiquem o software do mesmo, comprometendo operações ou, até mesmo, inutilizando parcialmente ou totalmente o equipamento.
> 

## Configuração Inicial do Projeto

### 1. Download do SDK do Fabricante

#### Procedimento:

1. Acesse a [página principal](https://oip.byd.com/addons/cms/document/index?document_type_id=26) no site global, onde a fabricante disponibiliza os recursos para uso do SDK (recomendo usar uma ferramenta de tradução online);

    ![BYD SDK DOWNLOAD](/DOC/assets/development-setup-01.png)

2. Baixe o SDK, o simulador e a documentação de desenvolvimento disponibilizados pela fabricante. Ao final, você deverá ter os seguintes arquivos em sua pasta de download:

  * SDK: sdk_v1.0.5.tar.gz
  * Simulador: aff821e394287088787bb87f85c72bc1.zip
  * Documentação: a00e8d77e99c59cac9e1d8368c871705.pdf

    > ### Nota
    > Os nomes dos arquivos são referentes às versões 1.0.5 e compatibilidade do sistema 7.1, podendo ter seus outros nomes em versões posteriores. Aqui o mais importante é baixar cada um dos recursos em sua pasta de preferência.


3. Usando um utilitário ZIP, descompacte o arquivo do SDK em uma pasta do seu sistema de arquivos. Como referência, utilizei o caminho: ``C:\Lab\BYD\SDK``

    ![BYD SDK UNZIPED](/DOC/assets/development-setup-02.png)

4. Usando um utilitário ZIP, descompacte o arquivo do Simulador em uma pasta do seu sistema de arquivos. Como referência, utilizei o caminho: ``C:\Lab\BYD\Simulator``

    ![BYD Simulator UNZIPED](/DOC/assets/development-setup-03.png)

### 2. Download e configuração do JAVA

A partir desse ponto, eu utilizei a documentação encontrada em:

  ![PDF SDK](/DOC/assets/development-setup-04.png)

O procedimento abaixo visa facilitar a configuração e funcionou perfeitamente com a versão 1.0.5 do SDK. Caso você encontre uma versão mais atualizada e este procedimento não funcione para ela, siga os passos do PDF encontrado neste mesmo SDK.

#### Procedimento:

1. Acesse o [site de download](https://www.oracle.com/br/java/technologies/downloads/archive/) da Oracle para baixar o JDK do JAVA;

2. Em **Previous JAVA releases**, **JAVA SE**, clique no link **Java SE 8 (8u202 and earlier)**. Na página que abrir, localize a versão **Java SE Development Kit 8u102** e clique na versão de JDK apropriada para o seu sistema operacional:

    ![PDF SDK](/DOC/assets/development-setup-05.png)

  > ### Nota
  > Eu procurei utilizar especificamente essa versão porque é a mesma indicada na documentação do SDK conforme aparece nas telas do PDF. Embora, no mesmo documento, há o [link para a versão 431](https://www.oracle.com/java/technologies/downloads/#java8) que é o último release da versão JAVA 8. Acredito que ambas as versões irão funcionar. Só não recomendo utilizar outra versão diferente do JAVA 8;

3. Instale o JAVA JDK em sua pasta de preferência. Por padrão, a instalação é feita na pasta ``C:\Program Files\Java\jdk1.8.0_102``. Irei utilizar esta pasta como referência para a configuração que faremos a seguir;

4. Abra as informações do sistema para configurar o path do sistema operacional. Clique com o botão direito no botão do Windows e escolha **Sistema**:

    ![PDF SDK](/DOC/assets/development-setup-06.png)

5. Depois localize a opção **Configurações avançadas do sistema** e clique para a próxima etapa:

    ![PDF SDK](/DOC/assets/development-setup-07.png)

6. No diálogo **Propriedades do Sistema**, clique no botão **Variáveis de Ambiente**;

    ![PDF SDK](/DOC/assets/development-setup-08.png)

7. Em **Variáveis do sistema**, localize a variável **Path** e clique no botão **Editar**;

    ![PDF SDK](/DOC/assets/development-setup-09.png)

8. Clique no botão **Novo** duas vezes para incluir duas novas entradas, conforme abaixo:

  * %JAVA_HOME%\bin;
  * %JAVA_HOME%\jre\bin;

    ![PDF SDK](/DOC/assets/development-setup-10.png)

9. Clique no botão **OK** para retornar para o diálogo **Variáveis de Ambiente**;

10. No diálogo **Variáveis de Ambiente**, clique no botão novo para criar a variável de ambiente **JAVA_HOME** apontando para a pasta onde instalou o JDK do JAVA 8;

    ![PDF SDK](/DOC/assets/development-setup-11.png)

11. No diálogo **Variáveis de Ambiente**, clique no botão novo para criar a variável de ambiente **CLASSPATH** apontando para duas bibliotecas importantes a serem utilizadas pelo JDK: ``%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar``

    ![PDF SDK](/DOC/assets/development-setup-12.png)

12. Clique em **OK** no diálogo **Variáveis de Ambiente** e depois em **OK** no diálogo **Propriedades do Sistema**;

13. Abra uma nova tela de **Terminal do Windows** e digite o comando ``java -version``. O comando deverá retornar a versão do JDK instalada e configurada no path do sistema:

    ![PDF SDK](/DOC/assets/development-setup-13.png)

  >
  > ### Atenção
  > Caso a versão apresentada não esteja correta, verifique se todas as variáveis de ambiente foram criadas e apontam para os caminhos corretos


### 3. Download e configuração do Android Studio

#### Procedimento:

1. Acesse o [site de download](https://developer.android.com/studio) do Android Studio e baixe a versão apropriada para o seu sistema operacional;

    ![Android Studio download](/DOC/assets/development-setup-14.png)

2. Instale o Android Studio seguindo as configurações padrões;